package com.dojo.worldclock;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorldClockController {
	
	@GetMapping("/world-clock")
	public String index(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("E HH:mm");
		String dateStr = sdf.format(new Date());
		model.addAttribute("jpDate", dateStr);
		return "index";
	}

}
