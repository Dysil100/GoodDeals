package duo.cmr.dysha.boundedContexts.avis.web.controller.leader;

import duo.cmr.dysha.boundedContexts.avis.web.service.AvisService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.annotations.Leader;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static duo.cmr.dysha.boundedContexts.routen.Routen.AVISLISTE;
import static duo.cmr.dysha.boundedContexts.routen.Routen.LEADERROUTE;

@Controller
@AllArgsConstructor
@RequestMapping(LEADERROUTE)
@Leader
public class LeaderAvisController {
    private ServiceSupreme serviceSupreme;
    private AvisService avisService;

    @GetMapping(AVISLISTE)
    public String alle(Model model){
        model.addAttribute("alle", avisService.alle());
        return "avisliste";
    }

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return "au Leader " +userByEmail.getFirstName();    }
}
