package duo.cmr.dysha.boundedContexts.DyshaJobs.web.configuration;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.persistence.dyshajob.DyshaJobRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

@Component
@AllArgsConstructor
public class MyWebSocketHandler extends TextWebSocketHandler {
    private final DyshaJobRepositoryImpl dyshaJobRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        List<DyshaJob> maTableList = dyshaJobRepository.findAll();
        messagingTemplate.convertAndSend("/topic/maTableUpdates", maTableList);
    }
}

