package com.devotics.MMORebekaEClarice.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LiveSocketController {

    @MessageMapping("/live")
    @SendTo("/topic/live")
    public String sendLiveMessage(String message) {
        return message;
    }
}