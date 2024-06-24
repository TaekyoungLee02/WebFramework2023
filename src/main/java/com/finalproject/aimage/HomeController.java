package com.finalproject.aimage;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private LocalTime now;
	
	
	public HomeController()
	{
		now = LocalTime.now();
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IOException {

		if(now.isBefore(LocalTime.of(18, 0)) && now.isAfter(LocalTime.of(6, 0)))
			return "main/daytime";
		else
			return "main/nighttime";
	}
}
