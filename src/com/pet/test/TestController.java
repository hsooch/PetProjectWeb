package com.pet.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private TestService testService;

	@RequestMapping("/test.pet")
	public ModelAndView selectAll(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("test/test");
		List list = testService.selectAll();
		mav.addObject("list", list);
		return mav;
	}

	@ExceptionHandler(Didnot.class)
	public ModelAndView handle(Didnot e) {
		ModelAndView mav = new ModelAndView("didnot");
		mav.addObject("msg", e.getMessage());
		return mav;
	}
}