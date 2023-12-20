package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {
    private DateUtil() {
    }

    public static boolean isValidDate(String dateString) {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);

        try {
            Date date = simpleDateFormat.parse(dateString);
            return dateString.equals(simpleDateFormat.format(date));
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidDate(String dateString, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);

        try {
            Date date = simpleDateFormat.parse(dateString);
            return dateString.equals(simpleDateFormat.format(date));
        } catch (ParseException e) {
            return false;
        }
    }

    public static String today() {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);

        return simpleDateFormat.format(new Date());
    }
}
