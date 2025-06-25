package com.example.prj2.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String index(Model model){
        LocalDate now = LocalDate.now();
        model.addAttribute("month", now.getMonthValue());
//        System.out.println("month : "+now.getMonthValue());
        return "board/introduce";
    }

    @GetMapping(value = "calendar",produces = "image/png")
    public String img(){
        return "img/calendar/1.png";
    }

}
