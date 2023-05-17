package duo.cmr.dysha.boundedContexts.DyshaJobs.web.configuration;

import duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshajob.DyshaJobRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@AllArgsConstructor
public class WebSocketConfigurer implements org.springframework.web.socket.config.annotation.WebSocketConfigurer {
    private final DyshaJobRepositoryImpl dyshaJobRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(), "/my-websocket-endpoint").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler myWebSocketHandler() {
        return new MyWebSocketHandler(dyshaJobRepository, messagingTemplate);
    }
}
