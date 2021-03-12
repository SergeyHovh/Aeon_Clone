package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.SessionService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author : Sergey Hovhannisyan
 * @since : 12/20/2020, Sunday, 4:04 AM
 **/
@Controller
@RequestMapping("user_management")
public class UserManagementController {
    final UserService userService;
    final SessionService sessionService;

    public UserManagementController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @GetMapping("")
    public String showAllUsers(Model model) {
        List<User> list = userService.findAll();
        model.addAttribute("showAllUsersList", list);
        return "user_management/all_users";
    }

    @GetMapping("start_session/{id}")
    public String startSession(@PathVariable String id) {
        User user = userService.findUserById(id);
        sessionService.startSession(user);
        return "redirect:/user_management";
    }

    @GetMapping("end_session/{id}")
    public String endSession(@PathVariable String id) {
        User user = userService.findUserById(id);
        sessionService.endSession(user);
        return "redirect:/user_management";
    }
}
