package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.admin;


import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class DyshaWorkerController {

    DyshaWorkerService dyshaWorkerService;

    @GetMapping("/dyshajobs/workers")
    public String employPage(Model model) {
        model.addAttribute("dyshaworkers", dyshaWorkerService.finAll());
        return "dyshaworker";
    }
}
