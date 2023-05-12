package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;


import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorker;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser.GlobalAppUser;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Controller
public class DyshaWorkerController {

    private DyshaWorkerService dyshaWorkerService;
    private AppUserService appUserService;

    @AdminOnly
    @GetMapping("/dyshajobs/workers")
    public String employPage(Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        model.addAttribute("globalUser", user);
        model.addAttribute("dyshaworkers", dyshaWorkerService.finAll());
        return "dyshaworkers";
    }

    @GetMapping("/dyshajobs/newdyshaworker")
    public String showWorkerForm(Model model, @ModelAttribute("globalUser") GlobalAppUser globalAppUser, @ModelAttribute("user") AppUser user) {
        model.addAttribute("globalUser", globalAppUser);
        model.addAttribute("dyshaworker", new DyshaWorker(user.getFullName(), null, null, null, null, user.getId()));
        return "newdyshajobworker";
    }

    @PostMapping("/dyshajobs/newdyshaworker")
    public String createWorker(Model model, @ModelAttribute("dyshaworker") @Valid DyshaWorker worker, BindingResult result, @ModelAttribute("user") AppUser user) {
        if (result.hasErrors()) {
            model.addAttribute("dyshaworker", worker);
            return "newdyshajobworker";
        }
        worker.setUserId(user.getId());
        worker.setStartedOn(LocalDateTime.now());
        dyshaWorkerService.save(worker);
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

    @ModelAttribute("globalUser")
    GlobalAppUser currentGlobalUser(Principal user) {
        AppUser appUser = appUserService.findByEmail(user.getName());
        return new GlobalAppUser(appUser, dyshaWorkerService.findByUserId(appUser.getId()));
    }
}
