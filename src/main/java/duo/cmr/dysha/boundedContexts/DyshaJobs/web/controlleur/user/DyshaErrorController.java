package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;

import io.micronaut.http.annotation.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DyshaErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
        // Ajoutez les informations d'erreur nécessaires au modèle, si nécessaire
        model.addAttribute("errorMessage", "Une erreur s'est produite");

        // Renvoyez le nom de la page d'erreur personnalisée
        return "error";
    }

    /*@Override
    public String getErrorPath() {
        return "/error.html";
    }*/
}
