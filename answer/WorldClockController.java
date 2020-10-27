package com.dojo.worldclock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//このアノテーションをつけることで起動時に読み込まれる
@Controller
public class WorldClockController {

	// このアノテーションをつけることで、このURLが呼ばれた場合にこのメソッドが呼ばれる
	@GetMapping("/world-clock")
	public String index(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("E HH:mm");
		Date date = new Date();
		// 日本
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		model.addAttribute("jpDate", sdf.format(date));
		
		// timezone インド
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		model.addAttribute("istDate", sdf.format(date));

		// timezone　中国
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		model.addAttribute("cstDate", sdf.format(date));
		return "index";
	}
}
