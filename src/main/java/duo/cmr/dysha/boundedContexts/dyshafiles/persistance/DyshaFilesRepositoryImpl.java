package duo.cmr.dysha.boundedContexts.dyshafiles.persistance;

import duo.cmr.dysha.boundedContexts.dyshafiles.domain.DyshaFiles;
import duo.cmr.dysha.boundedContexts.dyshafiles.web.interfaces.DyshaFilesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class DyshaFilesRepositoryImpl implements DyshaFilesRepository {
   DaoDyshaFilesRepository daoDyshaFilesRepository;
    @Override
    public void saveFile(String fileName, byte[] bytesData) {
        daoDyshaFilesRepository.save(new DyshaFilesEntity(fileName, bytesData));
    }

    @Override
    public DyshaFiles findByName(String fileName) {
        System.out.println(fileName);
        return toDyshaFiles(daoDyshaFilesRepository.findByName(fileName));
    }

    private DyshaFiles toDyshaFiles(DyshaFilesEntity e) {
        return new DyshaFiles(e.getId(), e.getName(), e.getData());
    }
}
