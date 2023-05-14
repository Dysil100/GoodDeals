package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.admin;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshajob.DyshaJob;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser.GlobalAppUser;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaFileService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaJobService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.WorkerJobRelationService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@AllArgsConstructor
@Controller
@AdminOnly
public class DyshaGeneralAdminController {
    DyshaWorkerService dyshaWorkerService;
    AppUserService appUserService;
    WorkerJobRelationService workerJobRelationService;
    DyshaFileService dyshaFileService;
    DyshaJobService dyshaJobService;

    @GetMapping("/dyshajobs/dyshaPostulats")
    public String dyshaPostulats(Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        model.addAttribute("globalUser", user);;
        model.addAttribute("jobRelations", workerJobRelationService.findAll());
        return "dyshapostulats";
    }

    @PostMapping("/dyshajobs/validatejobrelation/")
    public String validateJobRelation(@ModelAttribute("id") Long id, Model model) {
        workerJobRelationService.validateJobRelationById(id);
        return "redirect:/dyshajobs/dyshaPostulats";
    }
    @PostMapping("/dyshajobs/deletejobrelation")
    public String deleteJobRelation(@ModelAttribute("id") Long id, Model model) {
        workerJobRelationService.deleteJobRelationById(id);
        return "redirect:/dyshajobs/dyshaPostulats";
    }

    @GetMapping("/dyshajobs/mesdocuments/{id}")
    public String workerDocuments(@PathVariable("id") Long userId, @ModelAttribute("user") AppUser user, Model model) {
        model.addAttribute("dyshaFiles", dyshaFileService.findAllByUserId(userId));
        model.addAttribute("globalUser", new GlobalAppUser(user, dyshaWorkerService.findByUserId(userId)));
        return "addFiles";
    }

    @PostMapping("/dyshajobs/delete/file")
    public String deleteFile(@ModelAttribute("fileId") Long fileId, @ModelAttribute("globalUser") GlobalAppUser user) {
        // Récupérer le fichier à partir de l'ID
        Long userId = dyshaFileService.findFileById(fileId).getUserId();
        dyshaFileService.deleteById(fileId);
        return "redirect:/dyshajobs/mesdocuments/" + userId;
    }

    @GetMapping("/dyshajobs/dyshaprofil/{userId}")
    public String showProfilDetails(@PathVariable("userId") Long userId, Authentication authentication, Model model, @ModelAttribute("user") AppUser user) {
        model.addAttribute("userHasCuriculumVitae", dyshaFileService.cVExistByUserId(userId));
        model.addAttribute("globalUser", new GlobalAppUser(user, dyshaWorkerService.findByUserId(userId)));
        return "dyshaprofil";
    }

    @GetMapping("/dyshajobs/mesDyshaJobs/{userId}")
    public String dyshaWorkerJobsListe(Model model, @ModelAttribute("user") AppUser user, @PathVariable("userId") Long userId) {
        model.addAttribute("globalUser", new GlobalAppUser(user, dyshaWorkerService.findByUserId(userId)));;
        return "dyshajobliste";
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

    @ModelAttribute("user")
    AppUser currentUser(Principal user) {
        return appUserService.findByEmail(user.getName());
    }

    @ModelAttribute("globalUser")
    GlobalAppUser currentGlobalUser(Principal user) {
        AppUser appUser = appUserService.findByEmail(user.getName());
        return new GlobalAppUser(appUser, dyshaWorkerService.findByUserId(appUser.getId()));
    }
}
