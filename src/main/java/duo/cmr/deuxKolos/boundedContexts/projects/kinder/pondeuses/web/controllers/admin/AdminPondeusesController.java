package duo.cmr.deuxKolos.boundedContexts.projects.kinder.pondeuses.web.controllers.admin;

import duo.cmr.deuxKolos.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static duo.cmr.deuxKolos.boundedContexts.routen.Routen.ADMINROUTE;


@Controller
@AllArgsConstructor
@RequestMapping(ADMINROUTE)
@AdminOnly
public class AdminPondeusesController {

}
