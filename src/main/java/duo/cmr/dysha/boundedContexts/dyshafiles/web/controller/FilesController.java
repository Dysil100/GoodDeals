package duo.cmr.dysha.boundedContexts.dyshafiles.web.controller;

import duo.cmr.dysha.boundedContexts.dyshafiles.domain.DyshaFiles;
import duo.cmr.dysha.boundedContexts.dyshafiles.web.services.DyshaFilesService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@Controller
public class FilesController {
    private DyshaFilesService dyshaFilesService;

    @GetMapping("/files/{fileName}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        DyshaFiles file = dyshaFilesService.findByName(fileName);
        if (file != null) {
            ByteArrayResource resource = new ByteArrayResource(file.getData());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
