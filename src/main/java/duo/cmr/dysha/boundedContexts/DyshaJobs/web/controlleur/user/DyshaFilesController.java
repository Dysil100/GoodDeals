package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto.DyshaPhoto;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser.GlobalAppUser;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.PhotoService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@AllArgsConstructor
@Controller
public class PhotoController {

    private AppUserService appUserService;
    private PhotoService photoService;
    private DyshaWorkerService dyshaWorkerService;

    @GetMapping("/dyshajobs/photos/{tablename}/{entityid}")
    public String addPhotos(@PathVariable String tablename, @PathVariable Long entityid, @ModelAttribute("user") AppUser user, Model model) {
        DyshaPhoto attributeValue = new DyshaPhoto(user.getId(), entityid, tablename);
        model.addAttribute("dyshaPhoto", attributeValue);
        model.addAttribute("globalUser", new GlobalAppUser(user, dyshaWorkerService.findByUserId(user.getId())));
        return "addphotos";
    }

    @PostMapping("/dyshajobs/photos")
    public String uploadPhoto(@RequestParam("photoPart") Part photo,
                              @ModelAttribute("photoForm") DyshaPhoto dsphoto, Model model) throws IOException {
        // Lire le contenu de la photo dans un tableau d'octets
        byte[] photoData = new byte[(int) photo.getSize()];
        try (InputStream is = photo.getInputStream()) {
            int read = is.read(photoData);
            while(read != -1){
                read = is.read(photoData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Créer un objet Photo et Enregistrer la photo
        photoService.save(new DyshaPhoto(dsphoto.getUserId(), dsphoto.getEntityId(), dsphoto.getTableName(), photoData));
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
