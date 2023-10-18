package org.example.ch01_java.ch07_feature.java8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @author: whtli
 * @date: 2023/10/16
 * @description: Date相关
 */

public class DateTest {
    public static void main(String[] args) {
        System.out.println("--------------------Date相关--------------------");
        DateTest dateTest = new DateTest();
        dateTest.format();
        dateTest.transfer();
        dateTest.calculate();
        dateTest.getSpecific();
        dateTest.withZone();
        System.out.println("--------------------------------------------------");
    }

    public void format() {
        System.out.println("格式化：");
        LocalDate date = LocalDate.now();
        System.out.println(String.format("      日期格式化: %s", date));

        LocalTime time = LocalTime.now().withNano(0);
        System.out.println(String.format("      时间格式化: %s", time));

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataTimeString = dateTime.format(dateTimeFormatter);
        System.out.println(String.format("      日期时间格式化: %s", dataTimeString));
    }

    public void transfer() {
        LocalDate date1 = LocalDate.of(2023, 10, 16);
        LocalDate date2 = LocalDate.parse("2023-10-16");
        System.out.println("日期，非字符串转化: " + date1);
        System.out.println("日期，字符串转化：  " + date2);

        LocalTime time1 = LocalTime.of(20, 19, 10);
        LocalTime time2 = LocalTime.parse("20:19:10");
        System.out.println("时间，非字符串转化: " + time1);
        System.out.println("时间，字符串转化：  " + time2);

        LocalDateTime dateTime1 = LocalDateTime.of(2023, 10, 16, 20, 19, 10);
        LocalDateTime dateTime2 = LocalDateTime.parse("2023-10-16T20:19:10");
        System.out.println("时间日期，非字符串转化: " + dateTime1);
        System.out.println("时间日期，字符串转化：  " + dateTime2);
    }

    public void calculate() {
        LocalDate localDate = LocalDate.now();
        LocalDate after1 = localDate.plus(1, ChronoUnit.WEEKS);
        LocalDate after2 = localDate.plusWeeks(1);

        LocalDate date1 = LocalDate.parse("2023-10-16");
        LocalDate date2 = LocalDate.parse("2024-12-23");
        Period period = Period.between(date1, date2);
        System.out.println(date1 + "和" + date2 + "相差："
                + period.getYears() + "年"
                + period.getMonths() + "月"
                + period.getDays() + "天 (period.getDays()得到的天是抛去年月以外的天数，并不是总天数)");
        long day = date2.toEpochDay() - date1.toEpochDay();
        System.out.println(date1 + "和" + date2 + "相差：" + day + "天");
    }

    public void getSpecific() {
        LocalDate today = LocalDate.now();
        // 获取当月的第一天
        LocalDate firstDayOfCurrentMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("当月的第一天：   " + firstDayOfCurrentMonth);
        // 获取当月的最后一天
        LocalDate lastDayOfCurrentMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("当月的最后一天：  " + lastDayOfCurrentMonth);
        // 下一天
        LocalDate nextDay = today.plusDays(1);
        System.out.println("下一天：         " + nextDay);
        // 获取当年最后一天
        LocalDate lastDayOfCurrentYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("当年最后一天：    " + lastDayOfCurrentYear);
        // 获取当年第一天
        LocalDate firstDayOfCurrentYear = today.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("当年第一天：      " + firstDayOfCurrentYear);
        // 获取当年最后一个周日
        LocalDate lastSundayOfCurrentYear = today.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println("当年最后一个周日： " + lastSundayOfCurrentYear);
    }

    public void withZone() {
        // 当前时区时间
        ZonedDateTime localZonedDateTime = ZonedDateTime.now();
        System.out.println("当前时区时间: " + localZonedDateTime);

        // 东京时间
        ZoneId zoneId = ZoneId.of(ZoneId.SHORT_IDS.get("JST"));
        ZonedDateTime tokyoZonedDateTime = localZonedDateTime.withZoneSameInstant(zoneId);
        System.out.println("东京时区时间:   " + tokyoZonedDateTime);

        // ZonedDateTime转LocalDateTime
        LocalDateTime localDateTimeTransferFromZonedDateTime = localZonedDateTime.toLocalDateTime();
        System.out.println("时区时间转当地时间: " + localDateTimeTransferFromZonedDateTime);

        // LocalDateTime转ZonedDateTime
        ZonedDateTime localZonedDateTimeTransferFromLocalDateTime = localDateTimeTransferFromZonedDateTime.atZone(ZoneId.systemDefault());
        System.out.println("本地时间转时区时间: " + localZonedDateTimeTransferFromLocalDateTime);
    }
}