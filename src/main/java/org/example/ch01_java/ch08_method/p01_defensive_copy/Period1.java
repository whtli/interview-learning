package org.example.ch01_java.ch08_method.p01_defensive_copy;

import java.util.Date;

/**
 * @author: whtli
 * @date: 2023/12/25
 * @description: Broken "immutable" time period class
 */
public class Period1 {
    private final Date start;
    private final Date end;

    /**
     * constructor
     * @param start the beginning of the period
     * @param end the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException if start or end is null
     */
    public Period1(Date start, Date end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}

class Test1 {
    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period1 period1 = new Period1(start, end);
        System.out.println(period1.getStart() + "\n" + period1.getEnd());
        // 修改类的内部信息 - 成功
        end.setYear(98);
        System.out.println(period1.getStart() + "\n" + period1.getEnd());
    }
}
