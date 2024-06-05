package org.zerock.bookmarket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.bookmarket.dto.MemberDTO;
import org.zerock.bookmarket.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/join")
    public void join(MemberDTO memberDTO){
    log.info("get memberDTO: " + memberDTO);
    }

    @PostMapping("/join")
    public String addJoin(MemberDTO memberDTO) {
        log.info("post memberDTO: " + memberDTO);
        memberService.register(memberDTO);

        return "redirect:/";
    }

    @GetMapping("/login")
    public void getlogin(MemberDTO memberDTO) {
        log.info("get login..........");
    }

    @PostMapping("/login")
    public String login(MemberDTO memberDTO, HttpServletRequest request) {
        log.info("post login..........");
        try {
            HttpSession session = request.getSession();
            session.setAttribute("logInfo", memberService.login(memberDTO));
            log.info("session.........."+session);
        }catch (Exception e){
            return "redirect:/member/login?error=fail";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutGet(HttpServletRequest req){
        log.info("logout........");
        HttpSession session = req.getSession();
        session.invalidate();
        return "redirect:/";
    }

}
