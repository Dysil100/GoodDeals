package duo.cmr.deuxKolos.boundedContexts.App2Kolos.persitence.Databases.chatmessage;

import duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.models.chatmessage.ChatMessage;
import duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.services.interfaces.repositories.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ChatMessageRepositoryImpl implements ChatMessageRepository {

    DaoChatMessageRepository daoChatMessageRepository;
    @Override
    public void save(ChatMessage msg) {
        daoChatMessageRepository.save(toEntity(msg));
    }

    private ChatMessageEntity toEntity(ChatMessage msg) {
        return new ChatMessageEntity(msg.getSender(), msg.getReceiver(), msg.getMessage(), msg.getCreatedAt());
    }

    @Override
    public List<ChatMessage> findBySenderAndReceiver(String sender, String receiver) {
        List<ChatMessage> chatMessageList = toChatMessageList(daoChatMessageRepository.findAllByReceiverAndSender(sender, receiver));
        chatMessageList.addAll(toChatMessageList(daoChatMessageRepository.findAllBySenderAndReceiver(sender, receiver)));
        return chatMessageList.stream().sorted((Comparator.comparing(ChatMessage::getCreatedAt)).reversed()).collect(Collectors.toList());

    }

    private List<ChatMessage> toChatMessageList(Iterable<ChatMessageEntity> messages) {
        List<ChatMessage> chatMessageList = new ArrayList<>();
        messages.forEach(e -> chatMessageList.add(toChatMessage(e)));
        return  chatMessageList;
    }

    private ChatMessage toChatMessage(ChatMessageEntity e) {
        return new ChatMessage(e.getId(), e.getSender(), e.getReceiver(), e.getMessage(), e.getCreatedAt());
    }
}
