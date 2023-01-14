package com.example.testtemplate;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("username", authentication.getName());
            return "index";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/employees")
    public String employees(Model model) {
        // Add any necessary data to the model here before returning the view name
        return "employees";
    }

}
