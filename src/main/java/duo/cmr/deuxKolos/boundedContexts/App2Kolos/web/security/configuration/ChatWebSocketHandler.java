package duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.security.configuration;

import duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.models.chatmessage.ChatMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String username = extractUsername(session);
        if (username != null) {
            sessions.put(username, session);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String username = extractUsername(session);
        if (username != null) {
            sessions.remove(username);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ChatMessage chatMessage = ChatMessage.fromJson(message.getPayload());
        if (chatMessage != null && chatMessage.getReceiver() != null) {
            WebSocketSession receiverSession = sessions.get(chatMessage.getReceiver());
            if (receiverSession != null && receiverSession.isOpen()) {
                receiverSession.sendMessage(message);
            }
        }
    }

    private String extractUsername(WebSocketSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof User) {
                return ((User) principal).getUsername();
            }
        }
        return null;
    }
}
