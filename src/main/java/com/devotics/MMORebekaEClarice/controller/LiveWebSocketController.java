package com.devotics.MMORebekaEClarice.controller;

import com.devotics.MMORebekaEClarice.entity.Live;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LiveWebSocketController {

    @MessageMapping("/live")
    @SendTo("/topic/lives")
    public Live broadcastLive(Live live) {
        return live;
    }
}