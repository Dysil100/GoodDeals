package duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.services.subservices;

import duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.Discussion;
import duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.services.interfaces.repositories.DiscussionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DiscussionSetvice {

   DiscussionRepository discussionRepository;

    public List<Discussion> loadDiscussionsFor(String sender) {
        return discussionRepository.findAllFor(sender);
    }
}
