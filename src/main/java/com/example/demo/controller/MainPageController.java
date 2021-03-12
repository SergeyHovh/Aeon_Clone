package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Sergey Hovhannisyan
 * @since : 12/15/2020, Tuesday, 7:14 PM
 **/
@Controller
public class MainPageController {

    @GetMapping()
    public String mainPage(Model model) {
        return "mainPage";
    }
}
