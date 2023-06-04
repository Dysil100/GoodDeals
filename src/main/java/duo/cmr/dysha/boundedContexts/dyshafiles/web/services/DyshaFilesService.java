package duo.cmr.dysha.boundedContexts.dyshafiles.web.services;

import duo.cmr.dysha.boundedContexts.dyshafiles.domain.DyshaFiles;
import duo.cmr.dysha.boundedContexts.dyshafiles.web.interfaces.DyshaFilesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DyshaFilesService {
    DyshaFilesRepository dyshaFilesRepository;


    public DyshaFiles findByName(String filename) {
        return dyshaFilesRepository.findByName(filename);
    }

    public List<String> saveAll(List<MultipartFile> images)  {
        return images.stream().map(this::save).toList();
    }

    private String save(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dyshaFilesRepository.saveFile(fileName, bytes);
        return fileName;
    }
}
