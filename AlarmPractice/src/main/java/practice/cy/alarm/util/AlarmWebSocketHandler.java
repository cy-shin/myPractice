package practice.cy.alarm.util;

import java.lang.System.Logger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import practice.cy.alarm.model.service.AlarmService;
import practice.cy.alarm.model.vo.Member;
import practice.cy.alarm.model.vo.Message;

public class AlarmWebSocketHandler extends TextWebSocketHandler {
	
	@Autowired
	private AlarmService service;
	
	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
	// 동기화된 Set를 반환(HashSet은 기본적으로 비동기임)
	
	
	// 클라이언트와 서버를 연결이 완려되고 통신할 준비가 되면 수행되는 메서드
	// JS : new SockJs
	// servlet-context.xml에서 연결
	// 핸들러 클래스 매핑
	// 해당 메서드를 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		// WebSocketSession session : 클라이언트와 서버간의 전이 중 통신을 담당하는 객체
		// * 웹소켓에 접속한 회원의 HttpSession을 훔쳐서 가지고있음
		
		sessions.add(session);
		// 현재 접속중인 회원의 세션을 모아둠
		
	}
	
	// 알림을 보낼 때 필요한 정보
	// 회원 번호 (memberNo, targetNo)
	// 회원 이름
	// 알람 내용 (textMessage)
	
	
	// handleTextMessage : 클라이언트로부터 텍스트 메세지를 받으면 수행된는 메서드
	// -> JS : chattingSock.send(JSON 데이터)
	// -> handleTextMessage 수행
	// -> message
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String msg = message.getPayload();
		
		
		
		if(msg != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");
			msg.setSendTime( sdf.format(new Date()) );
			
		
			for(WebSocketSession s : sessions) {
				int loginMemberNo = ((Member)s.getAttributes().get("loginMember")).getMemberNo();
				
				if(loginMemberNo == msg.getTargetNo()) {
					
					s.sendMessage(new TextMessage(new Gson().toJson(msg)));
				}
			}
		}
		
	}
	
	// 클라이언트와 연결이 끊기면 수행되는 메서드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
	}
	
}
