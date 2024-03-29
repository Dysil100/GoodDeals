package duo.cmr.dysha.boundedContexts.dasandere.web.services.subservices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String dateToString(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }

    static public LocalDateTime stringToDate(String stringDatetime){
        return LocalDateTime.parse(stringDatetime, formatter);
    }
}
