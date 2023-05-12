package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.admin;


import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorker;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaworker.DyshaWorkerForm;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
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

@AllArgsConstructor
@Controller
public class DyshaWorkerController {

    private DyshaWorkerService dyshaWorkerService;
    private AppUserService appUserService;

    @GetMapping("/dyshajobs/workers")
    public String employPage(Model model,@ModelAttribute("user") AppUser user) {
        model.addAttribute("dyshaworkers", dyshaWorkerService.finAll());
        model.addAttribute("role", user.getRole().name());
        return "dyshaworkers";
    }

    @GetMapping("/dyshajobs/newdyshaworker")
    public String showWorkerForm(Model model, @ModelAttribute("user") AppUser user) {
        model.addAttribute("dyshaworker", new DyshaWorker(null,null, null, null, null, null));
        model.addAttribute("role", user.getRole().name());
        return "newdyshajobworker";
    }

    @PostMapping("/dyshajobs/newdyshaworker")
    public String createWorker(Model model, @ModelAttribute("dyshaworker") @Valid DyshaWorker worker, BindingResult result, @ModelAttribute("user") AppUser user) {
        if (result.hasErrors()) {
            model.addAttribute("dyshaworker", worker);
            return "newdyshajobworker";
        }
        worker.setUserId(user.getId());
        dyshaWorkerService.save(worker);
        return "redirect:/dyshajobs/workers";
    }

    @ModelAttribute("role")
    String sender(Principal user) {
        return appUserService.findByEmail(user.getName()).getRole().name();
    }

     @ModelAttribute("user")
    AppUser currentUser(Principal user) {
        return appUserService.findByEmail(user.getName());
    }

}
