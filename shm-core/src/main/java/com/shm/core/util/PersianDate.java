package com.shm.core.util;

import com.ghasemkiani.util.DateFields;
import com.ghasemkiani.util.SimplePersianCalendar;
import com.ghasemkiani.util.icu.PersianCalendar;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ehsan on 2/19/15.
 */
public class PersianDate {
    private final int year, month, day;
    private final int hour, minute, second, millisecond;

    public static String[] jalaliMonthsName = new String[]{
            "",
            "\u0641\u0631\u0648\u0631\u062F\u06CC\u0646",
            "\u0627\u0631\u062F\u06CC\u0628\u0647\u0634\u062A",
            "\u062E\u0631\u062F\u0627\u062F",
            "\u062A\u06CC\u0631",
            "\u0645\u0631\u062F\u0627\u062F",
            "\u0634\u0647\u0631\u06CC\u0648\u0631",
            "\u0645\u0647\u0631",
            "\u0622\u0628\u0627\u0646",
            "\u0622\u0630\u0631",
            "\u062F\u06CC",
            "\u0628\u0647\u0645\u0646",
            "\u0627\u0633\u0641\u0646\u062F"};

    private static int[] jalaliMonthsLength = new int[]{
            0,
            31, 31, 31, 31, 31, 31,
            30, 30, 30, 30, 30, 29};

    public PersianDate() {
        this(new Date());
    }

    public PersianDate(long milisec) {
        this(new Date(milisec));
    }

    public PersianDate(int year, int month, int day) {
        this(year, month, day, 0, 0, 0, 0);
    }

    public PersianDate(String persianDate) {
        if(persianDate.split("-").length==3){
            this.year = Integer.parseInt(persianDate.split("-")[0]);
            this.month = Integer.parseInt(persianDate.split("-")[1]);
            this.day = Integer.parseInt(persianDate.split("-")[2]);
            this.hour = 0;
            this.minute = 0;
            this.second = 0;
            this.millisecond = 0;
        }else{
            throw new IllegalArgumentException("not a correct persian date:"+persianDate);
        }
    }

    public PersianDate(String persianDate , String persianTime) {
        if(persianDate.split("-").length==3 && persianTime.split(":").length>=2){
            this.year = Integer.parseInt(persianDate.split("-")[0]);
            this.month = Integer.parseInt(persianDate.split("-")[1]);
            this.day = Integer.parseInt(persianDate.split("-")[2]);
            this.hour = Integer.parseInt(persianTime.split(":")[0]);
            this.minute = Integer.parseInt(persianTime.split(":")[1]);
            this.second = persianTime.split(":").length > 2 ? Integer.parseInt(persianTime.split(":")[2]) : 0;
            this.millisecond = 0;
        }else{
            throw new IllegalArgumentException("not a correct persian date:"+persianDate);
        }

    }

    public PersianDate(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        validate(year, month, day, hour, minute, second, millisecond);

        this.month = month;
        this.year = year;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
    }

    private void validate(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        if (year <= 0)
            throw new IllegalArgumentException(String.format("year '%d' must be positive", year));

        if (month < 1 || month > 12)
            throw new IllegalArgumentException(String.format("month '%d' must be between 1 and 12", month));

        if (day < 1 || day > 31)
            throw new IllegalArgumentException(String.format("day '%d' must be between 1 and 31", day));

        if (hour < 0 || hour > 23)
            throw new IllegalArgumentException(String.format("hour '%d' must be between 0 and 23", hour));

        if (minute < 0 || minute > 59)
            throw new IllegalArgumentException(String.format("minute '%d' must be between 0 and 59", minute));

        if (second < 0 || second > 59)
            throw new IllegalArgumentException(String.format("minute '%d' must be between 0 and 59", second));

        if (millisecond < 0 || millisecond > 999)
            throw new IllegalArgumentException(String.format("millisecond '%d' must be between 0 and 999", millisecond));
    }

    public PersianDate(Date date) {
        SimplePersianCalendar c = new SimplePersianCalendar();
        c.setTimeInMillis(date.getTime() + ((int)(4.5 * 60) * 60 * 1000));

        final DateFields dateFields = c.getDateFields();
        this.year = dateFields.getYear();
        this.month = dateFields.getMonth() + 1;
        this.day = dateFields.getDay();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
        this.millisecond = calendar.get(Calendar.MILLISECOND);

    }

    public Date toDate() {
        PersianCalendar c = new PersianCalendar();
        c.set(com.ibm.icu.util.Calendar.YEAR, year);
        c.set(com.ibm.icu.util.Calendar.MONTH, month-1);
        c.set(com.ibm.icu.util.Calendar.DAY_OF_MONTH, day);

        c.set(com.ibm.icu.util.Calendar.HOUR, hour);
        c.set(com.ibm.icu.util.Calendar.MINUTE, minute);
        c.set(com.ibm.icu.util.Calendar.SECOND, second);
        c.set(com.ibm.icu.util.Calendar.MILLISECOND, millisecond);

        return c.getTime();
    }

    public Date getGregorianDate() {
        PersianCalendar c = new PersianCalendar();
        c.set(com.ibm.icu.util.Calendar.YEAR, year);
        c.set(com.ibm.icu.util.Calendar.MONTH, month-1);
        c.set(com.ibm.icu.util.Calendar.DAY_OF_MONTH, day);

        c.set(com.ibm.icu.util.Calendar.HOUR_OF_DAY, hour);
        c.set(com.ibm.icu.util.Calendar.MINUTE, minute);
        c.set(com.ibm.icu.util.Calendar.SECOND, second);
        c.set(com.ibm.icu.util.Calendar.MILLISECOND, millisecond);

        return c.getTime();
    }

    public String gregorianToJalaliFullText() {
        java.text.DecimalFormat nf = new java.text.DecimalFormat("00");
        return
                nf.format(hour) + ":" + nf.format(minute) + ":" +
                        nf.format(second) + " " +
                        " " + year + "-" + month
                        + "-" + day;
    }

    public String gregorianToJalaliFullText2() {
        java.text.DecimalFormat nf = new java.text.DecimalFormat("00");
        return
                nf.format(hour) + ":" + nf.format(minute) + ":" +
                        nf.format(second) + " " +
                        " " + year + "/" + month
                        + "/" + day;
    }

    public String gregorianToJalaliFullText3() {
        java.text.DecimalFormat nf = new java.text.DecimalFormat("00");
        return
                year + "-" + nf.format(month)
                        + "-" + nf.format(day) + " " + nf.format(hour) + ":" + nf.format(minute);
    }

    public String gregorianToJalaliText(){
        java.text.DecimalFormat nf = new java.text.DecimalFormat("00");
        return year + "-" + nf.format(month) + "-" + nf.format(day) +" "+ nf.format(hour) + ":" + nf.format(minute);
    }


    @Override
    public String toString() {
        return String.format("%d/%02d/%02d", year, month, day);
    }

    public String gregorianToJalaliShortDateText() {
        return String.format("%d-%02d-%02d", year, month, day);
    }

    public String gregorianToJalaliShortDateText2() {
        return String.format("%d/%02d/%02d", year, month, day);
    }

    public String gregorianToJalaliShortText() {
        return gregorianToJalaliShortDateText();
    }

    public static int jalaliMonthLength(int Year, int Month) {
        int bKab = (int) ((((((Year - 474 % 2820) + 512) * 682) %
                2816) <
                682) ? 1 : 0);
        return (bKab == 1 && Month == 12) ? 30 : jalaliMonthsLength[Month];
    }

    public String getMonthText() {
        return jalaliMonthsName[month];
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getMillisecond() {
        return millisecond;
    }
}
