package duo.cmr.dysha.boundedContexts.projects.kinder.porcins.web.controllers.leader;

import duo.cmr.dysha.boundedContexts.dasandere.persistence.annotations.Leader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static duo.cmr.dysha.boundedContexts.routen.Routen.LEADERROUTE;

@Controller
@AllArgsConstructor
@RequestMapping(LEADERROUTE)
@Leader
public class LeaderPorcinsController {

}
