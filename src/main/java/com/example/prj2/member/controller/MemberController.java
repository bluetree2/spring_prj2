package com.example.prj2.member.controller;

import com.example.prj2.member.dto.MemberDto;
import com.example.prj2.member.dto.MemberForm;
import com.example.prj2.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;


    @GetMapping("login")
    public String login(){


        return "member/login";
    }

    @PostMapping("login")
    public String loginProcess(String id, String password, HttpSession session, RedirectAttributes rttr) {

        boolean result = memberService.login(id, password, session);

        if (result) {
            rttr.addFlashAttribute("alert",
                    Map.of("code", "success", "message", "로그인되었습니다"));
            // 로그인 성공
            return "redirect:/board/list";
        } else {
            rttr.addFlashAttribute("alert",
                    Map.of("code", "warning", "message", "아이디/패스워드가 일치하지 않습니다"));
            rttr.addFlashAttribute("id", id);
            // 로그인 실패
            return "redirect:/member/login";
        }

    }

    @GetMapping("signup")
    public String signup(Model model){

        return "member/signup";
    }

    @PostMapping("signup")
    public String signup2(RedirectAttributes rttr,MemberForm data){

        try{
            System.out.println("data = " + data);
            memberService.add(data);

            rttr.addFlashAttribute("alert",
                            Map.of("code", "success","message", "회원 가입 되었습니다"));

            rttr.addFlashAttribute("id", data.getId());
        return "redirect:/member/login";
        }catch (DuplicateKeyException e) {
            rttr.addFlashAttribute("alert",
                    Map.of("code", "warning", "message", e.getMessage()));
            rttr.addFlashAttribute("member", data);
            return "redirect:/member/signup";
        }

    }

}
