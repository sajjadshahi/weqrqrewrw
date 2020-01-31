package com.shm.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    private DateUtils() {
    }

    public static Date firstTimeOfToday() {
        return firstTimeOfDay(new Date());
    }

    public static Date firstTimeOfTomorrow() {
        return firstTimeOfDay(tomorrow());
    }

    public static Date firstTimeOfYesterday() {
        return firstTimeOfDay(yesterday());
    }

    public static Date tomorrow() {
        return nextDay(new Date());
    }

    public static Date yesterday() {
        return add(new Date(), Calendar.DAY_OF_MONTH, -1);
    }

    public static Date firstTimeOfDay(Date date) {
        Calendar c = getCalendar(date);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date nextDay(final Date date) {
        return add(date, Calendar.DAY_OF_MONTH, 1);
    }

    public static boolean dateEquals(Date date1, Date date2) {
        return firstTimeOfDay(date1).equals(firstTimeOfDay(date2));
    }

    public static Date add(Date date, int dateField, int value) {
        final Calendar c = getCalendar(date);
        c.add(dateField, value);

        return c.getTime();
    }

    public static Calendar getCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;
    }

    public static int diffInDays(Date date1, Date date2) {
        return (int) Math.round((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
    }

    private static String displayTimeZone(TimeZone tz) {

        long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset()) - TimeUnit.HOURS.toMinutes(hours);
        // avoid -4:-30 issue
        minutes = Math.abs(minutes);

        String result = "";
        if (hours > 0) {
            result = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
        } else {
            result = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
        }

        return result;

    }

    public static Date convertIsoDate(String dateStr, String zone) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Date date;
        if (zone == null || zone.equals(""))
            date = dateFormat.parse(dateStr);
        else {
            dateFormat.setTimeZone(TimeZone.getTimeZone(zone));
            date = dateFormat.parse(dateStr);
        }
        return date;
    }
}
