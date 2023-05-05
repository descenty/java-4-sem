package com.mirea.practice16;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Value("${student.name}")
    private String studentName;

    @Value("${student.last_name}")
    private String studentLastName;
    
    @Value("${student.group}")
    private String studentGroup;
    
    @Value("${student.variant_number}")
    private Integer studentVariantNumber;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("studentName", studentName);
        model.addAttribute("studentLastName", studentLastName);
        model.addAttribute("studentGroup", studentGroup);
        model.addAttribute("studentVariantNumber", studentVariantNumber);
        return "home";
    }
}
