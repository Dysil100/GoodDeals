package duo.cmr.deuxKolos.boundedContexts.dasandere.web.services;

import duo.cmr.deuxKolos.boundedContexts.GoodDeals.domain.models.discussion.Discussion;
import duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.security.ChatDiscussionHash;
import duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.services.subservices.DiscussionService;
import duo.cmr.deuxKolos.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.deuxKolos.boundedContexts.dasandere.persistence.database.archiv.UserArchivEntity;
import duo.cmr.deuxKolos.boundedContexts.dasandere.web.services.subservices.AppUserService;
import duo.cmr.deuxKolos.boundedContexts.dasandere.web.services.subservices.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceSupreme {
    // TODO: 05.02.22 implement all html using fragments
    private AppUserService appUserService;
    private ConfirmationTokenService confirmationTokenService;
    private DiscussionService discussionService;
    private ChatDiscussionHash ch;

    public AppUser getUserByEmail(String email) {
        return (AppUser) appUserService.loadUserByUsername(email);
    }

    public AppUser getUserByToken(String token) {
       return (AppUser) appUserService.loadUserByUsername(confirmationTokenService.getToken(token).get().getUsername());
    }

    public boolean tokenExist(String token) {
        return confirmationTokenService.getToken(token).isPresent();
    }

    public List<AppUser> alleAppUsers() {
        return appUserService.alleUsers();
    }

    public List<UserArchivEntity> alleUsersArchiv() {
        return appUserService.alleUsersArchiv();
    }

    public List<AppUser> findDuoForHash(String discussionhash) {
        return appUserService.findAllByIds(ch.getUserIdsFromChatDiscussionHash(discussionhash));
    }

    public String getChatDiscussionHashFor(String userName1, String userName2) {
        Long id1 = appUserService.findByEmail(userName1).getId();
        Long id2 = appUserService.findByEmail(userName2).getId();
        String chatDiscussionHash = ch.getChatDiscussionHashFor(id1, id2);

        if (!discussionService.existByDiscussionHash(chatDiscussionHash.toString())){
            discussionService.save(new Discussion(chatDiscussionHash.toString(), id1, id2));
        }
        return chatDiscussionHash;
    }

}
