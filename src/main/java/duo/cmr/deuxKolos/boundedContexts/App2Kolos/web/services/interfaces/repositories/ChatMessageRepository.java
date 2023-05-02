package duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.services.interfaces.repositories;

import duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.models.chatmessage.ChatMessage;

import java.util.List;

public interface ChatMessageRepository {
    void save(ChatMessage msg);

    List<ChatMessage> findBySenderAndReceiver(String sender, String receiver);
}
