package com.harman.healthcare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	/**
	 *
	 * @param modal
	 * @return index
	 */
	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title","CRUD Example");
		return "index";
	}

	/**
	 *
	 * @param modal
	 * @return index
	 */
	@RequestMapping("/patients/")
	String homeList(ModelMap modal) {
		modal.addAttribute("title","CRUD Example");
		return "index";
	}

	/**
	 *
	 * @param page
	 * @return page
	 */
	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return page;
	}

	/**
	 *
	 * @param page
	 * @return page
	 */
	@RequestMapping("/partial/{getList}")
	String partialHandler1(@PathVariable("getList") final String page) {
		return page;
	}

}
