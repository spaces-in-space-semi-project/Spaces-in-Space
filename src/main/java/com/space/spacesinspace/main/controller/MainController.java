package com.space.spacesinspace.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping({"/", "/main"})
    public ModelAndView main(ModelAndView mv) {
        mv.setViewName("main/main");
        mv.addObject("pageTitle", "Spaces in Space");
//        mv.addObject("mainContent", "layout/main :: mainContent");

        return mv;
    }
}
