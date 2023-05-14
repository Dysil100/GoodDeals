package duo.cmr.dysha.boundedContexts.DyshaJobs.web.controlleur.user;

import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.dyshaphoto.DyshaFile;
import duo.cmr.dysha.boundedContexts.DyshaJobs.domain.globaluser.GlobalAppUser;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaWorkerService;
import duo.cmr.dysha.boundedContexts.DyshaJobs.web.services.subservices.DyshaFileService;
import duo.cmr.dysha.boundedContexts.dasandere.domain.model.appsuer.AppUser;
import duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.Valid;
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
    public String mesDocuments(@ModelAttribute("globalUser") GlobalAppUser user, Model model) {
        model.addAttribute("dyshaFiles", dyshaFileService.findAllByUserId(user.getUser().getId()));
        model.addAttribute("globalUser", user);
        return "addFiles";
    }

    @GetMapping("/dyshajobs/files/{tablename}/{entityid}/{filesType}")
    public String addFiles(@PathVariable String tablename, @PathVariable Long entityid, @PathVariable String filesType, @ModelAttribute("user") AppUser user, Model model) {
        model.addAttribute("dyshaFile", new DyshaFile(user.getId(), entityid, tablename, filesType, null));
        model.addAttribute("globalUser", new GlobalAppUser(user, dyshaWorkerService.findByUserId(user.getId())));
        return "addFiles";
    }

    @PostMapping("/dyshajobs/files")
    public String addFiles(@RequestParam("file") Part file,
                           @ModelAttribute("dyshaFile") @Valid DyshaFile dyshaFile, BindingResult result, Model model) throws IOException {
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
        // Vérifier si le fichier uploadé est un fichier approprié
        String fileType = determineFileType(filesDataBytes);
        if (!dyshaFile.getFileType().equalsIgnoreCase(fileType)) {
            result.rejectValue("fileType", "file.type.invalid", "Le fichier doit être de type " + dyshaFile.getFileType());
            model.addAttribute("errors", result.getAllErrors());
            return "addFiles";
        }

        if (filesDataBytes.length > 2000000) {
            result.rejectValue("fileType", "file.type.invalid", "Le fichier doit peser environ 1 Megga Octets");
            model.addAttribute("errors", result.getAllErrors());
            return "addFiles";
        }

        // Créer un fichier Photo et Enregistrer la photo
        dyshaFileService.save(new DyshaFile(dyshaFile.getUserId(), dyshaFile.getEntityId(), dyshaFile.getTableName(), fileType, filesDataBytes));
        return "redirect:/dyshajobs";
    }

    @GetMapping("/dyshajobs/download/file/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId, @ModelAttribute("globalUser") GlobalAppUser user) {
        // Récupérer le fichier à partir de l'ID
        DyshaFile dyshaFile = dyshaFileService.findFileById(fileId);
        // Vérifier si le fichier existe
        if (dyshaFile == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(dyshaFile.getFile(), dyshaFile.buildHeadersFor(user.getUser().getFirstName()), HttpStatus.OK);
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
