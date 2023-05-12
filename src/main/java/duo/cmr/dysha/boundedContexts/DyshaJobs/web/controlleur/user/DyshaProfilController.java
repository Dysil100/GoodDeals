package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser.GlobalAppUser;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@AllArgsConstructor
@Controller
public class DyshaProfilController {
    private AppUserService appUserService;
    DyshaWorkerService dyshaWorkerService;

    @GetMapping("/dyshajobs/dyshaprofil")
    public String showProfil(Model model, @ModelAttribute("globalUser") GlobalAppUser user) {
        model.addAttribute("globalUser", user);
        return "dyshaprofil";
    }

    @GetMapping("/dyshajobs/dyshaprofil/{id}")
    public String showProfilDetails(@PathVariable("id") Long userId,  Authentication authentication, Model model, @ModelAttribute("user") AppUser user) {
        model.addAttribute("globalUser", new GlobalAppUser(user, dyshaWorkerService.findByUserId(userId)));
        model.addAttribute("role", user.getRole().name());
        model.addAttribute("user", appUserService.findById(userId));
        return "dyshaprofil";
    }

    @PostMapping("/update")
    public String updateProfil(Model model, @ModelAttribute("user") AppUser user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "dyshaprofil";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Profil updated successfully.");
        return "redirect:/dyshajobs/dyshaprofil";
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

