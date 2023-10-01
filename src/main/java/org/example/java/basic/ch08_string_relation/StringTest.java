package org.example.java.basic.ch08_string_relation;

/**
 * @author: whtli
 * @date: 2023/08/15
 * @description: String类相关的知识点
 */
public class StringTest {
    public static void main(String[] args) {
        // String 不可变的原因不是单纯因为final关键字修饰其内部的value数组
        // 还要结合private关键字、String类本身被final修饰等因素
        final char[] value = {'a', 'b', 'c'};
        System.out.println(value);
        value[0] = 'd';
        System.out.println(value);

        // 在堆中创建字符串对象”ab“
        // 将字符串对象”ab“的引用保存在字符串常量池中
        String aa = "ab";
        // 直接返回字符串常量池中字符串对象”ab“的引用
        String bb = "ab";
        System.out.println(aa == bb);// true

        // 常量池中的对象 str
        String str1 = "str";
        // 常量池中的对象 ing
        String str2 = "ing";
        // 常量池中的对象 string
        // 编译器在程序编译期就可以确定值的常量可以进行常量折叠
        String str3 = "str" + "ing";
        String str4 = str1 + str2;
        // 常量池中的对象 string
        String str5 = "string";
        // false
        System.out.println(str3 == str4);
        // true
        System.out.println(str3 == str5);
        // false
        System.out.println(str4 == str5);

        // 字符串使用 final 关键字声明之后，可以让编译器当做常量来处理。

        final String str6 = "str";
        final String str7 = "ing";
        // 常量池中的对象，编译器在程序编译期就可以确定值的常量
        String c = "str" + "ing";
        // 编译器在程序编译期就可以确定它的值，其效果就相当于访问常量。
        String d = str6 + str7;
        // true
        System.out.println(c == d);
    }
}

class FinalTest {
    private String pro1;
    public final String pro2 = "final";
    private static String pro3;

    public void setPro1(String pro1) {
        this.pro1 = pro1;
    }

    public static void setPro3(String pro3) {
        FinalTest.pro3 = pro3;
    }

    public static String getPro3() {
        return pro3;
    }

    public String getPro2() {
        return pro2;
    }

    public static void main(String[] args) {
        FinalTest finalTest = new FinalTest();
        finalTest.setPro1("hello");
        System.out.println(finalTest.pro1);
        System.out.println(finalTest.getPro2());
        FinalTest.setPro3("whtli");
        System.out.println(FinalTest.getPro3());
    }
}

/**
 * String支持正则表达式匹配
 */
class RegexTest {
    public static void main(String[] args) {
        String str = "aSF1 34Asssm,h4+0e@";
        System.out.print("str: \t");
        System.out.println(str);
        System.out.println();
        // \s	Any whitespace character
        System.out.println("---------- \\s: Any whitespace character --------------");
        System.out.println(str);
        System.out.println(str.replaceAll("\\s", ""));
        System.out.println();
        // \S	Any non-whitespace character
        System.out.println("---------- \\S: Any non-whitespace character ----------");
        System.out.println(str);
        System.out.println(str.replaceAll("\\S", ""));
        System.out.println();
        // \d	Any digit
        System.out.println("---------- \\d: Any digit -----------------------------");
        System.out.println(str);
        System.out.println(str.replaceAll("\\d", ""));
        System.out.println();
        // \D	Any non-digit
        System.out.println("---------- \\D: Any non-digit -------------------------");
        System.out.println(str);
        System.out.println(str.replaceAll("\\D", ""));
        System.out.println();
        // \w	Any word character (letter, number, underscore)
        System.out.println("---------- \\w: Any word character --------------------");
        System.out.println(str);
        System.out.println(str.replaceAll("\\w", ""));
        System.out.println();
        // \W	Any non-word character
        System.out.println("---------- \\W: Any non-word character ----------------");
        System.out.println(str);
        System.out.println(str.replaceAll("\\W", ""));
        System.out.println();
        // (a|b)	a or b
        System.out.println("---------- (a|b): a or b -------------------------------------------");
        System.out.println(str);
        System.out.println(str.replaceAll("(\\d|\\W)", ""));
        System.out.println();
        System.out.println("---------- (a|b):  -------------------------------------------------");
        System.out.println(str);
        System.out.println(str.replaceAll("(S|h|m)", ""));
        System.out.println();
        // [abc]	A single character of: a, b, or c
        System.out.println("---------- [abc]: A single character of:a, b, or c -----------------");
        System.out.println(str);
        System.out.println(str.replaceAll("[Shm]", ""));
        System.out.println();
        // [^abc]	Any single character except: a, b, or c
        System.out.println("---------- [^abc]: Any single character except:a, b, or c ----------");
        System.out.println(str);
        System.out.println(str.replaceAll("[^Shm]", ""));
        System.out.println();
        // [a-z]	Any single character in the range a-z
        System.out.println("---------- [a-z]: Any single character in the range a-z ------------");
        System.out.println(str);
        System.out.println(str.replaceAll("[a-z]", ""));
        System.out.println();
        // [a-zA-Z]	Any single character in the range a-z or A-Z
        System.out.println("---------- [a-zA-Z]: Any single character in the range a-z or A-Z --");
        System.out.println(str);
        System.out.println(str.replaceAll("[a-zA-Z]", ""));
        System.out.println();
        // x+	One or more of x
        System.out.println("---------- x+: One or more of x --");
        System.out.println(str);
        System.out.println(str.replaceAll("s+", ""));
        System.out.println();
        // a{3}	Exactly 3 of a
        System.out.println("---------- a{3}: Exactly 3 of a --");
        System.out.println(str);
        System.out.println(str.replaceAll("s{2}", ""));
        System.out.println();
        // a{3,}	3 or more of a
        System.out.println("---------- a{3,}: 3 or more of a --");
        System.out.println(str);
        System.out.println(str.replaceAll("s{2,}", ""));
        System.out.println();
        // a{3,6}	Between 3 and 6 of a
        System.out.println("---------- a{3,6}: Between 3 and 6 of a --");
        System.out.println(str);
        System.out.println(str.replaceAll("s{4,7}", ""));
        System.out.println();
    }
}

/**
 * intern()方法
 * + intern()可以将指定的字符串对象的引用保存在字符串常量池中，可以简单分为两种情况：
 * + 如果字符串常量池中保存了对应的字符串对象的引用，就直接返回该引用
 * + 如果字符串常量池中没有保存对应的字符串对象的引用，就在常量池中创建一个指向该字符串对象的引用并返回。
 */
class InternTest {
    public static void main(String[] args) {
        // 在堆中创建字符串对象”Java“
        // 将字符串对象”Java“的引用保存在字符串常量池中
        String s1 = "Java";
        // 直接返回字符串常量池中字符串对象”Java“对应的引用
        String s2 = s1.intern();
        // 会在堆中在单独创建一个字符串对象
        String s3 = new String("Java");
        // 直接返回字符串常量池中字符串对象”Java“对应的引用
        String s4 = s3.intern();
        // s1 和 s2 指向的是堆中的同一个对象
        System.out.println(s1 == s2); // true
        // s3 和 s4 指向的是堆中不同的对象
        System.out.println(s3 == s4); // false
        // s1 和 s4 指向的是堆中的同一个对象
        System.out.println(s1 == s4); //true
    }
}