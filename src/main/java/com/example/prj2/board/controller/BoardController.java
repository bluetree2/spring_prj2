package com.example.prj2.board.controller;

import com.example.prj2.board.dto.TodoFrom;
import com.example.prj2.board.service.BoardService;
import com.example.prj2.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;



@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("introduce")
    public String introduce(Model model){
        LocalDate now = LocalDate.now();
        model.addAttribute("month", now.getMonthValue());
//        System.out.println("month : "+now.getMonthValue());
        return "board/introduce";
    }
    

    @GetMapping("list")
    public String list(Model model,
                       @RequestParam(defaultValue = "1")
                       Integer page,
                       @RequestParam(defaultValue = "")
                       String keyword
    ) {
        var result = boardService.list(page,keyword);

        model.addAllAttributes(result);
        System.out.println("result = " + result);
        return "board/list";
    }

    @GetMapping("write")
    public String write(Model model) {
        model.addAttribute("standardDate", new Date());
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("localDate", LocalDate.now());

        return "board/write";
    }

    @PostMapping("write")
    public String writePost(TodoFrom data,
                            @SessionAttribute(name = "loggedInUser", required = false)
                            MemberDto user,
                            RedirectAttributes rttr) {

        if (user != null) {
            boardService.add(data,user);
            rttr.addFlashAttribute("alert",
                    Map.of("code","primary","message","새 게시물이 등록되었습니다"));

            return "redirect:/board/list";
        }else{

            return "redirect:/member/login";
        }
    }

    @GetMapping("calendar")
    public String calendar() {

        return "board/calendar";
    }

    @GetMapping("view")
    public String view(Integer id, Model model) {
        // service
        var dto = boardService.get(id);

        // model
        model.addAttribute("board",dto);

        // view로 forward
        return "board/view";
    }



}
