package practice.cy.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
	
	
	// 테스트용 페이지로 이동
	public String goMainPage() {
		return "/";
	}
}
