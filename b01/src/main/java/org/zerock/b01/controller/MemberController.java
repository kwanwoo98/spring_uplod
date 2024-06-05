package org.zerock.b01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.MemberJoinDTO;
import org.zerock.b01.service.MemberService;

import java.security.Principal;
import java.util.List;


@Controller
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public void loginGET(String errorCode, String logout) {
        log.info("login get.............");
        log.info("logout: "+logout);

        if(logout != null) {
            log.info("user logout...................");
        }
    }

    @GetMapping("/join")
    public void joinGET() {
        log.info("join get..................");
    }

    @PostMapping("/join")
    public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {
        log.info("join post..................");
        log.info(memberJoinDTO);
        try{
            memberService.join(memberJoinDTO);
        }catch(Exception e) {
            redirectAttributes.addFlashAttribute("error", "mid");
            return "redirect:/member/join";
        }
        redirectAttributes.addFlashAttribute("result", "success");
        return "redirect:/member/login";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/modify")
    public void modifyGET(Principal principal, Model model) {
        log.info("modify get.................."+principal.getName());
        model.addAttribute("memberDTO",memberService.getOne(principal.getName()));
    }

    @PostMapping("/modify")
    public String modifyPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){
        log.info("modify post..................");
        memberService.modify(memberJoinDTO);
        return "redirect:/";
    }

    @PostMapping("/remove")
    public String remove(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {
        String mid = memberJoinDTO.getMid();

        log.info("remove post......"+mid);
        memberService.remove(mid);

        redirectAttributes.addFlashAttribute("result","removed");
        return "redirect:/logout";
    }

    @PostMapping("/checkid")
    public String checkidPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {
        boolean isDuplicate = memberService.isMember(memberJoinDTO);
        log.info("checkid post......"+isDuplicate);
        if(isDuplicate) {
            log.info("checkid duplicate 실패");
            redirectAttributes.addFlashAttribute("error", "mid");
        }else {
            log.info("checkid duplicate 성공");
            redirectAttributes.addFlashAttribute("success", "mid");
        }
        return "redirect:/member/join";
    }
}
