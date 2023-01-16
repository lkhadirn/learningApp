package com.example.testtemplate;

//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/employees")
    public String employees(Model model) {
        return "employees";
    }
    @GetMapping("/company")
    public String company(Model model) {
        return "company";
    }
   @GetMapping("/logistics")
    public String logistics(Model model) {
        return "logistics";
    }


}
