package duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.controllers.User;

import duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.models.Product;
import duo.cmr.deuxKolos.boundedContexts.App2Kolos.domaine.oders.InputSearchForm;
import duo.cmr.deuxKolos.boundedContexts.App2Kolos.web.services.subservices.ProductService;
import duo.cmr.deuxKolos.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.deuxKolos.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

import static duo.cmr.deuxKolos.boundedContexts.routen.Routen.*;

@AllArgsConstructor
@Controller
public class ProductController {

    private ProductService productService;
    private ServiceSupreme serviceSupreme;

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        System.out.println(userByEmail.getRole());
        return "Salut  " +userByEmail.getFirstName() + " trouvons ensemble le Produit dont tu as besoin.";
    }

    @GetMapping(PRODUCTLISTE)
    public String alle(Model model){
        model.addAttribute("products", productService.alle());
        return "productliste";
    }

    @PostMapping(PRODUCTSEARCH)
    public String serviceSearch(Model model, @ModelAttribute("search") String search){
        model.addAttribute("products", productService.filterNach(search));
        return "productliste";
    }

    @GetMapping(PRODUCT)
    public String Sservice(Model model, @ModelAttribute("productForm") Product form) {
        model.addAttribute("productForm", form);
        return "product";
    }

    @PostMapping(PRODUCT)
    public String esrvicePost(Model model, @ModelAttribute("productForm") Product form, @ModelAttribute("email") String email) {
        System.out.println(form);
        form.setEmail(email);
        productService.save(form);
        return "redirect:" + PRODUCT;
    }

    @ModelAttribute("productForm")
    Product productForm() {
        return new Product(null, null, null, 0.0, false);
    }

    @ModelAttribute("InputSearchForm")
    InputSearchForm inputSearchForm() {
        return new InputSearchForm(null);
    }

}
