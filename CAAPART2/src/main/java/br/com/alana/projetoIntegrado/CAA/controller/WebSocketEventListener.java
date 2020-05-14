package br.com.alana.projetoIntegrado.CAA.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import br.com.alana.projetoIntegrado.CAA.model.ChatMessage;

public class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent evento) {
		logger.info("Recebendo uma nova conexão web socket");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionConnectedEvent evento) {

		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(evento.getMessage());

		String CPF = (String) headerAccessor.getSessionAttributes().get("CPF");
		if (CPF != null) {

			logger.info("Disconetado: " + CPF);

			ChatMessage chatmessage = new ChatMessage();
			chatmessage.setType(ChatMessage.MessageType.SAIR);
			chatmessage.setSender(CPF);

			messagingTemplate.convertAndSend("/topic/public", chatmessage);
		}
	}

}