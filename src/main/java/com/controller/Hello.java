package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class Hello {
	@RequestMapping("eco")
	@ResponseBody
    public String hello(){        
        return "hello";
    }
	
    @RequestMapping("hi")
    public ModelAndView show(){
        ModelAndView mv=new ModelAndView("hello");
        mv.addObject("msg", "world");
       // mv.setViewName("hi");
        return mv;
    }
}
