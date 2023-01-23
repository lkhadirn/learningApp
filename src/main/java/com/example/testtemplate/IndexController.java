package com.example.testtemplate;

//import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class IndexController {

    final EmployeeRepository employeeRepository;

    public IndexController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/employees")
    public String employees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @PostMapping("/update-employees")
    public String updateEmployees(@RequestBody List<Employee> updates) {
        for (Employee update : updates) {
            Employee employee = employeeRepository.findById(update.getId()).get();
            employee.setFirstName(update.getFirstName());
            employee.setLastName(update.getLastName());
            employee.setEmail(update.getEmail());
            employeeRepository.save(employee);
        }
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
