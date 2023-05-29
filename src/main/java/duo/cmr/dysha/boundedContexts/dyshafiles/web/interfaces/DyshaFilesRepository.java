package duo.cmr.dysha.boundedContexts.dyshafiles.web.interfaces;

import duo.cmr.dysha.boundedContexts.dyshafiles.domain.DyshaFiles;

public interface DyshaFilesRepository {
    void saveFile(String fileName,  byte[] bytesData);

    DyshaFiles findByName(String fileName);
}