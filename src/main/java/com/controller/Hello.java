package com.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.model.po.SimulatorInfo;
import com.service.addition.SimulatorInfoService;
import com.service.addition.TrainingRecordService;
// 将action作为控制类
@Controller
//表示该类中所有的响应请求的方法都是以“/hello”作为父路径
@RequestMapping("/hello")

public class Hello {
	
	@Resource
	private SimulatorInfoService simulatorInfoService ;
	//
	@RequestMapping("eco")
	@ResponseBody
    public String hello(){       
		SimulatorInfo info = simulatorInfoService.queryByKey("27046961A0E908BF967F23C5B9F9EDFBC6FFDCA7EA6CE145");
		if (null != info) {
			return info.getEquipmentCode();
		}
		else {
	        return "hello";
		}
    }
	
    @RequestMapping("hi")
    public ModelAndView show(){
        ModelAndView mv=new ModelAndView("hello");
        mv.addObject("msg", "world");
       // mv.setViewName("hi");
        return mv;
    }
}
