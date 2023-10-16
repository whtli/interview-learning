package org.example.ch01_java.ch07_feature;

import lombok.Data;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: whtli
 * @date: 2023/10/16
 * @description:
 */
public class Java8 {
    public static void main(String[] args) {
        System.out.println("--------------------Lambda相关--------------------");
        LambdaTest lambdaTest = new LambdaTest();
        lambdaTest.runnable();
        lambdaTest.comparator();
        lambdaTest.collections();
        System.out.println("--------------------------------------------------");

        System.out.println("--------------------Stream相关--------------------");
        StreamTest streamTest = new StreamTest();
        streamTest.basic();
        streamTest.statistic();
        streamTest.other();
        System.out.println("--------------------------------------------------");

        System.out.println("--------------------Optional相关--------------------");
        OptionalTest optionalTest = new OptionalTest();
        optionalTest.basic();
        System.out.println("--------------------------------------------------");

        System.out.println("--------------------Date相关--------------------");
        DateTest dateTest = new DateTest();
        dateTest.format();
        dateTest.transfer();
        dateTest.calculate();
        dateTest.getSpecific();
        dateTest.withZone();
        System.out.println("--------------------------------------------------");
    }
}

/**
 * Lambda相关
 */
class LambdaTest {
    public void runnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("传统的借助Runnable创建线程的方式");
            }
        }).start();

        new Thread(() -> System.out.println("使用lambda借助Runnable创建线程的方式")).start();
    }

    public void comparator() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 4, 1, 3));

        System.out.println("传统的借助Comparator进行列表排序的方式");
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(list);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(list);

        System.out.println("使用lambda借助Comparator进行列表排序的方式");
        list.sort((o1, o2) -> o1 - o2);
        System.out.println(list);

        Collections.sort(list, (o1, o2) -> o2 - o1);
        System.out.println(list);
        System.out.println();
    }

    public void collections() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 4, 1, 3));
        System.out.print("传统的遍历列表方式：");
        for (Integer item : list) {
            System.out.print(item);
        }
        System.out.print("使用lambda遍历列表，方式一：");
        list.forEach(s -> System.out.print(s));
        System.out.println();

        System.out.print("使用lambda遍历列表，方式二：");
        list.forEach(System.out::print);
        System.out.println();

        System.out.println("类似的，使用lambda遍历map：");
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "wht");
        map.put(2, "li");
        map.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println();
    }
}

/**
 * Stream相关
 * 链式编程，函数式接口类型
 * 一个Stream只能操作一次，操作完就关闭了，继续使用这个stream会报错
 * Stream不保存数据，不改变数据源
 */
class StreamTest {
    public void basic() {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        // 过滤计数
        Stream<String> stringStream = strings.stream().filter(s -> s.contains("ab"));
        long count = stringStream.count();
        System.out.println("包含字符串ab的元素个数：" + count);

        System.out.println("遍历列表：");
        strings.stream().forEach(System.out::println);

        System.out.print("取指定数量元素并为转数组：");
        Stream<String> limit = strings.stream().limit(2);
        String[] array = limit.toArray(String[]::new);
        System.out.println(Arrays.toString(array));

        System.out.println("对每个元素进行操作返回新stream：");
        Stream<String> map = strings.stream().map(s -> s + "16");
        map.forEach(System.out::println);

        System.out.println("借助stream排序：");
        strings.stream().sorted().forEach(System.out::println);

        System.out.print("collect把指定字符串（如，abc）放入容器中：");
        List<String> abc = strings.stream().filter(s -> s.equals("abc")).collect(Collectors.toList());
        System.out.println(abc);

        System.out.print("把list转为String，并添加逗号分隔符：");
        String collect = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(", "));
        System.out.println(collect);

        System.out.println("合并流：");
        List<String> strings2 = Arrays.asList("xyz", "jqx");
        long count1 = Stream.concat(strings2.stream(), strings.stream()).count();
        System.out.println("strings1的数量：" + strings.size());
        System.out.println("strings2的数量：" + strings2.size());
        System.out.println("合并流后的数量：" + count1);
        System.out.println();
    }

    public void statistic() {
        System.out.println("Stream用于统计：");
        List<Integer> number = Arrays.asList(1, 2, 5, 4);
        IntSummaryStatistics statistics = number.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("    列表中最大的数 : " + statistics.getMax());
        System.out.println("    列表中最小的数 : " + statistics.getMin());
        System.out.println("    列表中元素之和 : " + statistics.getSum());
        System.out.println("    列表中元素数量 : " + statistics.getCount());
        System.out.println("    列表元素平均值 : " + statistics.getAverage());
    }

    public void other() {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        // 一个Stream只能操作一次，不能断开，否则会报错
        Stream stream = strings.stream();
        // 第一次使用
        // stream.limit(2);
        // 第二次使用
        // stream.forEach(System.out::println);
        // 报错 java.lang.IllegalStateException: stream has already been operated upon or closed

        // 但是可以连续使用
        stream.limit(2).forEach(System.out::println);

        System.out.println("使用flatMap：");
        List<String[]> listOfArrays = Arrays.asList(
                new String[]{"apple", "banana", "cherry"},
                new String[]{"orange", "grape", "pear"},
                new String[]{"kiwi", "melon", "pineapple"}
        );
        List<String> flatMapResult = listOfArrays.stream()
                .flatMap(array -> Arrays.stream(array).map(String::toUpperCase))
                .collect(Collectors.toList());
        System.out.println(flatMapResult);
        System.out.println();
    }
}

/**
 * Optional用于解决NPE问题
 */
class OptionalTest {
    @Data
    class Zoo {
        private Dog dog;
    }

    @Data
    class Dog {
        private int age;
    }

    public void basic() {
        Zoo zoo = new Zoo();
        // 不会报错NPE，无输出
        Optional.ofNullable(zoo).map(o -> o.getDog()).map(d -> d.getAge()).ifPresent(age -> System.out.println(age));
        // 直接调用报错NPE，因为没有初始化：java.lang.NullPointerException
        // System.out.println(zoo.getDog().getAge());
    }
}

class DateTest {
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