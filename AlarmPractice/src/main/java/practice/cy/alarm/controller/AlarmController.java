package practice.cy.alarm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import practice.cy.alarm.model.service.AlarmService;
import practice.cy.alarm.model.vo.Member;

@Controller
public class AlarmController {
	
	@Autowired
	private AlarmService service;
	
	// 알림을 보내는 메서드
	@GetMapping("/alarm")
	@ResponseBody
	public String sendAlarm(int targetNo, RedirectAttributes ra, @SessionAttribute("loginMember") Member loginMember) {
		
		// 알람 내용을 담을 map
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		// 서비스에서 알림 내역을 받아옴
		
		// 알림 내용을 전달
		return new Gson().toJson(map);
		
	}
	
	
	
}
