package duo.cmr.deuxKolos.boundedContexts.projects.kinder.pondeuses.web.controllers.leader;

import duo.cmr.deuxKolos.boundedContexts.dasandere.persistence.annotations.Leader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static duo.cmr.deuxKolos.boundedContexts.routen.Routen.LEADERROUTE;

@AllArgsConstructor
@Controller
@RequestMapping(LEADERROUTE)
@Leader
public class LeaderPondeusesController {

}
