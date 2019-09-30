package com.udemy.backendninja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/example4")
public class Example4Controller {
	
	@GetMapping("/request1")
	public ModelAndView request1(){
		ModelAndView mav = new ModelAndView("404");
		return mav;
	}

}
