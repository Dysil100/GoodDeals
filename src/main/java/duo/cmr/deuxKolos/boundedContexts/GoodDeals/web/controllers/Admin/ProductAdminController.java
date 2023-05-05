package duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.controllers.Admin;

import duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.services.subservices.ProductService;
import duo.cmr.deuxKolos.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.deuxKolos.boundedContexts.dasandere.persistence.annotations.AdminOnly;
import duo.cmr.deuxKolos.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static duo.cmr.deuxKolos.boundedContexts.routen.Routen.*;

@AllArgsConstructor
@Controller
@RequestMapping(ADMINROUTE)
@AdminOnly
public class ProductAdminController {

    private ProductService productService;
    private ServiceSupreme serviceSupreme;

    @PostMapping(PRODUCTSERVICE)
    public String delete(Model model, @PathVariable("id") Long id) {
        productService.deleteById(id);
        model.addAttribute("alle", productService.findAll());
        return "redirect:" + PRODUCTLISTE;
    }

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return "Salut  " +userByEmail.getFirstName() + " trouvons ensemble le service dont tu as besoin.";
    }
}
