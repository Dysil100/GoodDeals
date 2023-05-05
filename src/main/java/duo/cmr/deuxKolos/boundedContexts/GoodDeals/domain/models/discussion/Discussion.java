package duo.cmr.deuxKolos.boundedContexts.GoodDeals.domain.models.discussion;

import duo.cmr.deuxKolos.boundedContexts.GoodDeals.domain.models.chatmessage.ChatMessage;
import duo.cmr.deuxKolos.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Discussion {

    private Long id;
    private String discussionHash;
    private List<ChatMessage> messages;
    private LocalDateTime createdAt;
    private List<AppUser> users;
    private Long user1;
    private Long user2;

    public Discussion(String discussionHash, Long user1, Long user2) {
        this.discussionHash = discussionHash;
        this.user1 = user1;
        this.user2 = user2;
        this.createdAt = LocalDateTime.now();
    }

    public Discussion(Long id, String discussionHash, List<ChatMessage> messages, LocalDateTime createdAt, List<AppUser> users) {
        this.id = id;
        this.discussionHash = discussionHash;
        this.messages = messages;
        this.createdAt = createdAt;
        this.users = users;
    }

    public Discussion(String discussionHash) {
        this.discussionHash = discussionHash;
        this.createdAt = LocalDateTime.now();
    }

    public String getCurrentReceiver(String sender){
        return users.isEmpty() ? "" : users.get(0).getEmail().toLowerCase(Locale.ROOT).equals(sender) ? users.get(1).getEmail() : users.get(0).getEmail();
    }

    public String getCurrentReceiverName(String sender){
        return users.get(0).getEmail().toLowerCase(Locale.ROOT).equals(sender.toLowerCase(Locale.ROOT)) ? users.get(1).getFullName() : users.get(0).getFullName();
    }

    public ChatMessage getLastMessage(){
        return messages.get(0);
    }
}
