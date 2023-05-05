package duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.services.interfaces.repositories;

import duo.cmr.deuxKolos.boundedContexts.GoodDeals.domain.models.discussion.Discussion;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DiscussionRepository {
    List<Discussion> findAllFor(String sender);

    boolean existByDiscussionHash(String sb);

    void save(Discussion discussion);

    Discussion findByDiscussionHash(String discussionhash);
}
