package org.example.ch01_java.ch08_method.p01_defensive_copy;

import java.util.Date;

/**
 * @author: whtli
 * @date: 2023/12/25
 * @description: Broken "immutable" time period class
 */
public class Period2 {
    private final Date start;
    private final Date end;

    /**
     * repaired constructor - makes defensive copies of parameter
     * @param start the beginning of the period
     * @param end the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException if start or end is null
     */
    public Period2(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}

class Test2 {
    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period2 period2 = new Period2(start, end);
        System.out.println(period2.getStart() + "\n" + period2.getEnd());
        // 修改类的内部信息 - 成功
        period2.getEnd().setYear(98);
        System.out.println(period2.getStart() + "\n" + period2.getEnd());
    }
}
