package com.udemy.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ejercicio")
public class EjercicioController {

	private static final Log LOGGER = LogFactory.getLog(Example3Controller.class);

	@GetMapping("/ejem1")
	public ModelAndView ejem1(@RequestParam(name="nm", required=false, defaultValue="NULL") String name){
		LOGGER.info("INFO ejem1");
		ModelAndView mav = new ModelAndView("ejercicio1");
		mav.addObject("nm_in_model", name);
		return mav;
	}
	
	@GetMapping("/ejem2")
	public String redirect (){
		LOGGER.info("INFO ejem2 first");
		return "redirect:/ejercicio/ejem1";
	}
}
