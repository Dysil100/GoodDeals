package duo.cmr.deuxKolos.boundedContexts.GoodDeals.persitence.Databases.chatmessage;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table("chatmessage")
public class ChatMessageEntity {

    @Id
    private Long id;
    private String sender;
    private String receiver;
    private String message;
    private String discussionHash;
    private LocalDateTime createdAt;

    public ChatMessageEntity(String sender, String receiver, String message, String discussionHash, LocalDateTime createdAt) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.discussionHash = discussionHash;
        this.createdAt = createdAt;
    }
}
