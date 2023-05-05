package duo.cmr.dysha.boundedContexts.GoodDeals.web.controllers.User;

import duo.cmr.dysha.boundedContexts.GoodDeals.domain.models.discussion.Discussion;
import duo.cmr.dysha.boundedContexts.GoodDeals.web.services.subservices.DiscussionService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.ServiceSupreme;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

// TODO: 04.05.2023 remplacer l'utilisation des email par les id en implementation interne

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@Controller
public class DiscussionController {

    DiscussionService discussionService;
    AppUserService appUserService;
    ServiceSupreme serviceSupreme;

    @GetMapping("/discussions")
    public String disscussions(Model model, @ModelAttribute("sender") String sender) {
        model.addAttribute("discussions", discussionService.loadDiscussionsFor(sender));
        return "discussions";
    }

    @GetMapping("/discussions/{id}")
    public String productDetails(@PathVariable("id") String discussionhash, @ModelAttribute("sender") String sender, Model model) {
        Discussion discussion = discussionService.finByDiscussionHash(discussionhash);
        if (discussion.getUsers().isEmpty()){
            List<AppUser> duoForHash = serviceSupreme.findDuoForHash(discussionhash);
            discussion.setUsers(duoForHash);
        }
        model.addAttribute("messages", discussion.getMessages());
        model.addAttribute("sender", sender);
        model.addAttribute("receiverName", receiverName(sender, discussion));
        model.addAttribute("receiver", discussion.getCurrentReceiver(sender));
        return "chatpage";
    }

    @ModelAttribute("sender")
    String sender(Principal user) {
        return user.getName();
    }

    String receiverName(@ModelAttribute("sender") String sender, Discussion discussion) {
        AppUser appUser = appUserService.findByEmail(discussion.getCurrentReceiver(sender));
        return appUser.getFullName();
    }

}
