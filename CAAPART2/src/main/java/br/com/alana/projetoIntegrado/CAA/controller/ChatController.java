package br.com.alana.projetoIntegrado.CAA.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import br.com.alana.projetoIntegrado.CAA.model.ChatMessage;

@Controller
public class ChatController {

	// receiving messages from one client and then broadcasting it to others
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

		return chatMessage;

	}

	@MessageMapping("/chat.addCPFUser")
	@SendTo("/topic/public")
	public ChatMessage addCPFUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {

		// Adicione seu CPF na sessao
		headerAccessor.getSessionAttributes().put("CPF", chatMessage.getSender());

		return chatMessage;

	}

}