package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Sergey Hovhannisyan
 * @since : 12/15/2020, Tuesday, 7:31 PM
 **/
@Controller
@RequestMapping("registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addNewUser(@ModelAttribute("new_user") User user) {
        return "registration/new_user";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("new_user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }
}