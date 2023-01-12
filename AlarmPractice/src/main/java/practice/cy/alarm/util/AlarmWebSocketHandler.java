package practice.cy.alarm.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class AlarmWebSocketHandler extends TextWebSocketHandler {
	
	// 로그인중인 개별 유저
	Map<String, WebSocketSession> users = new HashMap<String, WebSocketSession>();
	
	private Set<WebSocketSession> sessions  = Collections.synchronizedSet(new HashSet<WebSocketSession>());
	
	// 클라이언트, 서버 연결 시
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}
	
	// 클라이언트가 Data를 전송 시
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("============================= handleTextMessage =============================");
		
		// 특정 유저에게 보내기
		String msg = message.getPayload();
		
		TextMessage newTextMsg = new TextMessage(msg);
		
		System.out.println("newTextMsg : " + newTextMsg);
		
		// 이제 Gson으로 보내자!
		for(WebSocketSession s : sessions) {
				s.sendMessage(newTextMsg);
		}
	}
	
	// 연결 해제될 때
	// afterConnectionClosed : 클라이언트와 연결이 끊기면 수행되는 메서드
	// --> 채팅방 화면(SockJS 객체가 있는 화면)을 벗어나면 연결 종료
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
	}
	
	
}
