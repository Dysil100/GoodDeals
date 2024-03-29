package duo.cmr.dysha.boundedContexts.avis.web.controller;

import duo.cmr.dysha.boundedContexts.avis.forms.FormAvis;
import duo.cmr.dysha.boundedContexts.avis.web.service.AvisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static duo.cmr.dysha.boundedContexts.routen.Routen.AVIS;

@AllArgsConstructor
@Controller
public class AvisController {

    private AvisService avisService;

    @GetMapping(AVIS)
    public String avis(Model model, @ModelAttribute("formAvis") FormAvis form) {
        model.addAttribute("formavis", form);
        return "avis";
    }

    @PostMapping(AVIS)
    public String avisPost(Model model, @ModelAttribute("formavis") FormAvis form, @ModelAttribute("email") String email) {
        form.setEmail(email);
        avisService.save(form.toAvis());
        return "redirect:" + AVIS;
    }

    @ModelAttribute("formAvis")
    FormAvis formavis() {
        return new FormAvis(null, null, null);
    }

}
