package duo.cmr.deuxKolos.boundedContexts.App2Kolos.persitence.Databases.chatmessage;

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
    private LocalDateTime createdAt;

    public ChatMessageEntity(String sender, String receiver, String message, LocalDateTime createdAt) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.createdAt = createdAt;
    }
}
