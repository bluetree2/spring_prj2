package com.example.prj2.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;


@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    @GetMapping("introduce")
    public String introduce(Model model){
        LocalDate now = LocalDate.now();
        model.addAttribute("month", now.getMonthValue());
//        System.out.println("month : "+now.getMonthValue());
        return "board/introduce";
    }
}
