package org.example.ch01_java.ch08_method.p01_defensive_copy;

import java.util.Date;

/**
 * @author: whtli
 * @date: 2023/12/25
 * @description: Broken "immutable" time period class
 */
public class Period3 {
    private final Date start;
    private final Date end;

    /**
     * repaired constructor - makes defensive copies of parameter
     * @param start the beginning of the period
     * @param end the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException if start or end is null
     */
    public Period3(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
    }

    /**
     * repaired accessors - make defensive copies of internal fields（返回可变内部阈的保护性拷贝）
     * @return 日期
     */
    public Date getStart() {
        // return start;
        return new Date(start.getTime());
    }

    public Date getEnd() {
        // return end;
        return new Date(end.getTime());
    }
}

class Test3 {
    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period3 period3 = new Period3(start, end);
        System.out.println(period3.getStart() + "\n" + period3.getEnd());
        // 修改类的内部信息 - 失败
        period3.getEnd().setTime(1998);
        System.out.println(period3.getStart() + "\n" + period3.getEnd());
    }
}
