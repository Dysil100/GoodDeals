package duo.cmr.dysha.boundedContexts.DyshaJobs.domain.filesType;


public class FileTypeDetector {
    public FileTypeDetector() {
    }

    public static String determineFileType(byte[] fileBytes) {
        if (fileBytes.length >= 3 && fileBytes[0] == (byte) 0xFF && fileBytes[1] == (byte) 0xD8 && fileBytes[2] == (byte) 0xFF) {
            return "image";
        }
        if (fileBytes.length >= 4 && fileBytes[0] == (byte) 0x25 && fileBytes[1] == (byte) 0x50 && fileBytes[2] == (byte) 0x44 && fileBytes[3] == (byte) 0x46) {
            return "pdf";
        }
        if (fileBytes.length >= 4 && fileBytes[0] == (byte) 0x66 && fileBytes[1] == (byte) 0x74 && fileBytes[2] == (byte) 0x79 && fileBytes[3] == (byte) 0x70) {
            if (fileBytes.length >= 8 && fileBytes[4] == (byte) 0x6D && fileBytes[5] == (byte) 0x70 && fileBytes[6] == (byte) 0x34 && fileBytes[7] == (byte) 0x32) {
                return "video/mp4";
            }
        }
        if (fileBytes.length >= 3 && fileBytes[0] == (byte) 0x49 && fileBytes[1] == (byte) 0x44 && fileBytes[2] == (byte) 0x33) {
            return "audio/mp3";
        }
        return "unknown";
    }
}
