package com.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.service.addition.TrainingRecordService;

@Controller
@RequestMapping("/hello")
public class Hello {
	
	@Resource
	private TrainingRecordService trainingRecordService ;
	
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
