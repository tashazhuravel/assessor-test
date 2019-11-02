package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getCurrentDateAsString() {
        Date date = new Date();
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }
}
