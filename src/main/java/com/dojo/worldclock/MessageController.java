package com.dojo.worldclock;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {

	@Autowired
	MessageSource messageSource;

	@GetMapping("/message")
	public String index(Model model,Locale locale) {

		String message = messageSource.getMessage("sample.title", new String[] {"a","b"}, Locale.JAPAN);
		model.addAttribute("title", message);
		return "message";
	}

}
