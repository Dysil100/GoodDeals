package duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.controllers.User;

import duo.cmr.deuxKolos.boundedContexts.GoodDeals.domain.models.product.Product;
import duo.cmr.deuxKolos.boundedContexts.GoodDeals.domain.oders.InputSearchForm;
import duo.cmr.deuxKolos.boundedContexts.GoodDeals.web.services.subservices.ProductService;
import duo.cmr.deuxKolos.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.deuxKolos.boundedContexts.dasandere.web.services.ServiceSupreme;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

import static duo.cmr.deuxKolos.boundedContexts.routen.Routen.*;

@AllArgsConstructor
@Controller
public class ProductController {

    private ProductService productService;
    private ServiceSupreme serviceSupreme;

    @ModelAttribute("text")
    String handle(Principal user) {
        AppUser userByEmail = serviceSupreme.getUserByEmail(user.getName());
        return "Salut  " +userByEmail.getFirstName() + " trouvons ensemble le Produit dont tu as besoin.";
    }

    @GetMapping(PRODUCTLISTE)
    public String alle(Model model, @ModelAttribute("searchform") InputSearchForm searchForm){
        model.addAttribute("products", productService.findAll());
        model.addAttribute("searchform", searchForm);
        return "productliste";
    }

    // TODO: 29.04.2023 repair this hier and in the frontend
    @GetMapping(PRODUCTSEARCH)
    public String productSearch(Model model, @RequestParam("query") String query, @ModelAttribute("searchform") InputSearchForm searchForm){
        searchForm.setQuery(query);
        model.addAttribute("searchform", searchForm);
        model.addAttribute("restproducts", productService.searchRestByExprr(searchForm.getQuery()));
        model.addAttribute("seachliste", productService.seachByExpr(searchForm.getQuery()));
        return "productsearchliste";
    }

    @GetMapping(NEWPRODUCT)
    public String product(Model model, @ModelAttribute("productForm") Product form) {
        model.addAttribute("productForm", form);
        return "newproduct";
    }

    @PostMapping(NEWPRODUCT)
    public String productPost(Model model, @ModelAttribute("productForm") Product form, @ModelAttribute("creator") String creator) {
        form.setCreatedAt(LocalDateTime.now());
        form.setUserEmail(creator);
        productService.save(form);
        model.addAttribute("products", productService.findAll());
        return "redirect:" + PRODUCTLISTE;
    }

    @GetMapping("/product/details/{id}")
    public String productDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productdetails";
    }

    @ModelAttribute("creator")
    String sender(Principal user) {
        return user.getName();
    }

    @ModelAttribute("productForm")
    Product productForm() {
        return new Product(null, null, null, 0.0, false, false
                , null, null, null, null, null);
    }

    @ModelAttribute("InputSearchForm")
    InputSearchForm inputSearchForm() {
        return new InputSearchForm("");
    }

}
