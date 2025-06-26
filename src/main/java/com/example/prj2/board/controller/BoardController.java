package com.example.prj2.board.controller;

import com.example.prj2.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


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

        return "board/list";
    }

    @GetMapping("write")
    public String write(Model model) {
//        model.addAttribute("standardDate", new Date());
//        model.addAttribute("localDateTime", LocalDateTime.now());
//        model.addAttribute("localDate", LocalDate.now());


        return "board/write";
    }

    @GetMapping("calendar")
    public String calendar() {

        return "board/calendar";
    }


}
