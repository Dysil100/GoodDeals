package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;

import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaJobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class DyshaJobController {

    private DyshaJobService dyshaJobService;

    @GetMapping("/dyshajobs")
    public String viewDyshaJobs(Model model) {
        //model.addAttribute("searchJobs", dyshaJobService.findAllJobsByExorr(keyword));
        model.addAttribute("jobs", dyshaJobService.findAll());
        return "jobindex";
    }

    @GetMapping("/dyshajobs/jobs")
    public String dyshaJobs(Model model) {
        model.addAttribute("jobs", dyshaJobService.findAll());
        return "jobliste";
    }

    @GetMapping("dyshajobs/search")
    public String searchJobs(@RequestParam("keyword") String keyword, Model model) {
        //model.addAttribute("searchJobs", dyshaJobService.findAllJobsByExorr(keyword));
        model.addAttribute("jobs", dyshaJobService.restLostJobs(keyword));
        return "jobindex";
    }
}
