package duo.cmr.dysha.boundedContexts.DyshaJobs.domain.filesType;

import javax.servlet.http.Part;

public class FileTypeDetector {

    public static String determineFileType(Part file) {
        // Obtenir l'extension du fichier
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

        // Définir les types de fichiers acceptés
        String[] imageTypes = {"jpg", "jpeg", "png", "gif"};
        String[] videoTypes = {"mp4", "avi", "mov"};
        String[] pdfTypes = {"pdf"};

        // Vérifier si l'extension correspond à un type de fichier accepté
        if (containsExtsn(extension, imageTypes)) {
            return "image";
        } else if (containsExtsn(extension, videoTypes)) {
            return "video";
        } else if (containsExtsn(extension, pdfTypes)) {
            return "pdf";
        } else {
            return "unknown";
        }
    }

    private static boolean containsExtsn(String value, String[] array) {
        for (String str : array) {
            if (str.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
