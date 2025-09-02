package com.example.demo.Controller;

import org.apache.logging.log4j.message.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

@org.springframework.stereotype.Controller
public class Controller
{
    @MessageMapping("/logs")
    @SendTo("topic/logs")
    public Message getMessage(Message message){
        return message;
    }

}
