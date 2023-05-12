package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto.DyshaFile;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser.GlobalAppUser;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaFileService;
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

import static duo.cmr.dysha.boundedContexts.DyshaJobs.domain.filesType.FileTypeDetector.determineFileType;

@AllArgsConstructor
@Controller
public class DyshaFilesController {

    private AppUserService appUserService;
    private DyshaFileService dyshaFileService;
    private DyshaWorkerService dyshaWorkerService;

    @GetMapping("/dyshajobs/mesdocuments")
    public String mesDocuments(@ModelAttribute("user") AppUser user, Model model) {
        model.addAttribute("dyshaFile", new DyshaFile(user.getId(), user.getId(), null, null, null));
        model.addAttribute("dyshaFiles", dyshaFileService.findAllByEntityId(user.getId()));
        model.addAttribute("globalUser", new GlobalAppUser(user, dyshaWorkerService.findByUserId(user.getId())));
        return "addFiles";
    }

    @GetMapping("/dyshajobs/files/{tablename}/{entityid}")
    public String addFiles(@PathVariable String tablename, @PathVariable Long entityid, @ModelAttribute("user") AppUser user, Model model) {
        model.addAttribute("dyshaFile", new DyshaFile(user.getId(), entityid, tablename, null, null));
        model.addAttribute("globalUser", new GlobalAppUser(user, dyshaWorkerService.findByUserId(user.getId())));
        return "addFiles";
    }

    @PostMapping("/dyshajobs/files")
    public String addFiles(@RequestParam("file") Part file,
                           @ModelAttribute("dyshaFile") DyshaFile dyshaFile, Model model) throws IOException {
        // Lire le contenu de la photo dans un tableau d'octets
        byte[] filesDataBytes = new byte[(int) file.getSize()];
        try (InputStream is = file.getInputStream()) {
            int read = is.read(filesDataBytes);
            while(read != -1){
                read = is.read(filesDataBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Créer un objet Photo et Enregistrer la photo
        dyshaFileService.save(new DyshaFile(dyshaFile.getUserId(), dyshaFile.getEntityId(), dyshaFile.getTableName(), determineFileType(filesDataBytes), filesDataBytes));
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
