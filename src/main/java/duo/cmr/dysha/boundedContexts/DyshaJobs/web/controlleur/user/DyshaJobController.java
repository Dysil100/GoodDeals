package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.workerjobrelation.WorkerJobRelation;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser.GlobalAppUser;
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
import java.time.LocalDateTime;

@AllArgsConstructor
@Controller
public class DyshaJobController {

    private DyshaJobService dyshaJobService;
    private AppUserService appUserService;
    private WorkerJobRelationService workerJobRelationService;
    private DyshaWorkerService dyshaWorkerService;

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
        model.addAttribute("job", dyshaJobService.getJobById(id));
        return "dyshajobdetails";
    }

    @GetMapping("/dyshajobs/postuler/{jobId}")
    public String postuler(@ModelAttribute("jobId") Long jobId, @ModelAttribute("globalUser") GlobalAppUser user) {
        workerJobRelationService.save(new WorkerJobRelation(jobId, user.getUser().getId(), LocalDateTime.now()));
        return "redirect:/dyshajobs";
    }


    @GetMapping("dyshajobs/search")
    public String searchJobs(@RequestParam("keyword") String keyword, Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        //model.addAttribute("searchJobs", dyshaJobService.findAllJobsByExorr(keyword));
        model.addAttribute("globalUser", user);
        model.addAttribute("jobs", dyshaJobService.restLostJobs(keyword));
        return "jobindex";
    }

    @GetMapping("/dyshajobs/createDyshaJob")
    public String showCreateJobForm(Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        model.addAttribute("globalUser", user);
        model.addAttribute("dyshaJob", new DyshaJob(null, null, null, null, null, null));
        return "newdyshajob";
    }

    @PostMapping("/dyshajobs/createDyshaJob")
    public String createJob(Model model, @ModelAttribute("dyshaJob") DyshaJob dyshaJob, BindingResult result , @ModelAttribute("globalUser") GlobalAppUser user) {
        if (result.hasErrors()) {
            model.addAttribute("dyshaJob", dyshaJob);
            return "newdyshajob";
        }
        dyshaJob.setUserId(user.getUser().getId());
        dyshaJobService.save(dyshaJob);
        return "redirect:/dyshajobs";
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
