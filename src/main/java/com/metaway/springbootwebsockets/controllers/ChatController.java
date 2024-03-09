package com.metaway.springbootwebsockets.controllers;

import com.metaway.springbootwebsockets.entities.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage") // Define a URL de mapeamento da mensagem
    @SendTo("/topic/public") // Especifique o destino para onde a mensagem será enviada
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage; // Process the message and send it to the specified destination
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.componentClicked")
    @SendTo("/topic/public")
    public ChatMessage componentClicked(@Payload ChatMessage chatMessage){
        // chamar o evento de registro aqui
        return chatMessage;
    }
}
