package ru.connor.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people2")
public class Project1Controller {

    @GetMapping()
    public String index(){
        return "project1/person/index";
    }


}
