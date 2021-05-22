package com.s1cket.labs.node.controller;

import com.s1cket.labs.node.model.EnvelopeDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/chat/{addressFrom}/{addressTo}/")
    @SendTo("/topic/chat/{addressTo}/private/")
    public EnvelopeDto chat(@Payload EnvelopeDto envelopeDto,
                            @DestinationVariable("addressFrom") String addressFrom,
                            @DestinationVariable("addressTo") String addressTo) {
        System.out.println("Received: " + envelopeDto);
        return envelopeDto;
    }
}
