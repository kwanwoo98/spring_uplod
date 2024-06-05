package org.zerock.b01.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b01.dto.MemberDTO2;
import org.zerock.b01.service.MemberService2;


@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member2")
public class MemberController2 {
    private final MemberService2 memberService2;

    @GetMapping("/join")
    public String join(MemberDTO2 memberDTO2) {

        return "/ex/join";
    }

    @PostMapping("/join")
    public String addMember(MemberDTO2 memberDTO2) {
        memberDTO2.setEmail1(memberDTO2.getMember_id());
        memberService2.register(memberDTO2);

        return "redirect:/ex/index";
    }

    @GetMapping("/login")
    public String login(MemberDTO2 memberDTO2) {
        return "/ex/login";
    }

    @PostMapping("/login")
    public String loginMember(String member_id, String member_pw, HttpServletRequest req, RedirectAttributes redirectAttributes) {

        try{
            MemberDTO2 loginInfo = memberService2. login(member_id, member_pw);
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", loginInfo);

            return "redirect:/ex/index";
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "아이디와 비밀번호를 확인해주세요...");
            return "redirect:/member/login";
        }


    }

    @GetMapping("/logout")
        public String logout(HttpServletRequest req) {
        // session을 변수로 설정하면 중복사용이된다
        // HttpSession session = req.getSession();
        // session.removeAttribute("loginInfo");
        // session.invalidate();
        req.getSession().invalidate();
        return "redirect:/ex/index";
        }

}


