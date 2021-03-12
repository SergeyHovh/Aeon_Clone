package com.example.demo.controller;

import com.example.demo.domain.Session;
import com.example.demo.domain.User;
import com.example.demo.service.SessionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

/**
 * @author : Sergey Hovhannisyan
 * @since : 12/18/2020, Friday, 3:15 AM
 **/
@Controller
@RequestMapping("user_page")
public class UserPageController {

    final SessionService sessionService;

    public UserPageController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping()
    public String personalPage(@AuthenticationPrincipal User user, Model model) {
        String username = user.getUsername();
        model.addAttribute("personalPageUsername", username);
        return "user_page/personal_page";
    }

    @GetMapping("/start")
    public String startSession(@AuthenticationPrincipal User user) {
        sessionService.startSession(user);
        return "redirect:/user_page";
    }

    @GetMapping("/end")
    public String endSession(@AuthenticationPrincipal User user) {
        sessionService.endSession(user);
        return "redirect:/user_page";
    }

    @GetMapping("/show")
    public String showAll(@AuthenticationPrincipal User user, Model model) {
        Set<Session> sessionsByUserId = sessionService.findAllByUserId(user.getId());
        model.addAttribute("showAllSessionsByUserId", sessionsByUserId);
        return "user_page/sessions";
    }
}
