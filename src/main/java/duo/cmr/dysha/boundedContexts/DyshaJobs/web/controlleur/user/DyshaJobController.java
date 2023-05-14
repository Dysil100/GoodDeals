package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.workerjobrelation.WorkerJobRelation;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser.GlobalAppUser;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaFileService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaJobService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.WorkerJobRelationService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class DyshaJobController {

    private DyshaJobService dyshaJobService;
    private AppUserService appUserService;
    private WorkerJobRelationService workerJobRelationService;
    private DyshaWorkerService dyshaWorkerService;
    private DyshaFileService dyshaFileService;

    @GetMapping("/dyshajobs")
    public String viewDyshaJobs(Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        //model.addAttribute("searchJobs", dyshaJobService.findAllJobsByExorr(keyword));
        model.addAttribute("globalUser", user);;
        model.addAttribute("jobs", dyshaJobService.findAll());
        return "jobindex";
    }

    @GetMapping("/dyshajobs/mesDyshaJobs")
    public String dyshaJobsListe(Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        model.addAttribute("globalUser", user);;
        return "dyshajobliste";
    }

    @GetMapping("/dyshajobs/jobdetails/{id}")
    public String getJobDetails(@PathVariable Long id, Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        model.addAttribute("globalUser", user);
        model.addAttribute("userHasCuriculumVitae", dyshaFileService.cVExistByUserId(user.getUser().getId()));
        model.addAttribute("job", dyshaJobService.getJobById(id));
        return "dyshajobdetails";
    }

    @GetMapping("/dyshajobs/postuler/{jobId}")
    public String postuler(@ModelAttribute("jobId") Long jobId, @ModelAttribute("globalUser") GlobalAppUser user) {
        workerJobRelationService.save(new WorkerJobRelation(jobId, user.getUser().getId(), false, null));
        return "redirect:/dyshajobs";
    }


    @GetMapping("dyshajobs/search")
    public String searchJobs(@RequestParam("keyword") String keyword, Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        //model.addAttribute("searchJobs", dyshaJobService.findAllJobsByExorr(keyword));
        model.addAttribute("globalUser", user);
        model.addAttribute("jobs", dyshaJobService.restLostJobs(keyword));
        return "jobindex";
    }

    @ModelAttribute("role")
    String sender(Principal user) {
        return appUserService.findByEmail(user.getName()).getRole().name();
    }

    @ModelAttribute("user")
    AppUser currentUser(Principal user) {
        return appUserService.findByEmail(user.getName());
    }

    @ModelAttribute("userId")
    Long userId(Principal user) {
        return appUserService.findByEmail(user.getName()).getId();
    }

    @ModelAttribute("globalUser")
    GlobalAppUser currentGlobalUser(Principal user) {
        AppUser appUser = appUserService.findByEmail(user.getName());
        return new GlobalAppUser(appUser, dyshaWorkerService.findByUserId(appUser.getId()));
    }
}
