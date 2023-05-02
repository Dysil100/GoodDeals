package duo.cmr.deuxKolos.boundedContexts.App2Kolos.persitence.Databases.chatmessage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DaoChatMessageRepository extends CrudRepository<ChatMessageEntity, Long> {

    Iterable<ChatMessageEntity> findAllBySenderAndReceiver(String sender, String receiver);
    Iterable<ChatMessageEntity> findAllByReceiverAndSender(String sender, String receiver);

}
