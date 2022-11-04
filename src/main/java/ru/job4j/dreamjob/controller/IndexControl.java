package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.util.UserSession;

import javax.servlet.http.HttpSession;

@Controller
public class IndexControl {

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        User user = UserSession.getUser(session);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginPage";
    }
}
