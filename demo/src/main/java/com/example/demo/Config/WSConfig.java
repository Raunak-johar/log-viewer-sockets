package com.example.demo.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WSConfig implements WebSocketMessageBrokerConfigurer {

    //add the endpoint for websocket to connect
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        System.out.println("Registered endpoint");
        registry.addEndpoint("/logs");
    }

    //broker mapping to whatever topic
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/logs");
    }
}
