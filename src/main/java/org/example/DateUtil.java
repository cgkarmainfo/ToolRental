package org.example;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static boolean isIndependenceDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        if (month == Calendar.JULY) {
            if (day == 4 || (day == 3 && dayOfWeek == Calendar.FRIDAY) || (day == 5 && dayOfWeek == Calendar.MONDAY)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLaborDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int firstMonday = (8 - cal.get(Calendar.DAY_OF_WEEK) + Calendar.MONDAY) % 7 + 1;

        return month == Calendar.SEPTEMBER && day == firstMonday && dayOfWeek == Calendar.MONDAY;
    }

    public static boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }
}
