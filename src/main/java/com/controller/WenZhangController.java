package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jimmy
 * @date 2018/9/19 10:38
 */
@Controller
@RequestMapping("/wenzhang")
public class WenZhangController {

    @RequestMapping("/tool")
    public ModelAndView show() {
        ModelAndView mv = new ModelAndView("wen_zhang_jsp/tool");
        return mv;
    }

    @RequestMapping("/create")
    public ModelAndView Create() {
        ModelAndView mv = new ModelAndView("wen_zhang_jsp/create");
        return mv;
    }

}
