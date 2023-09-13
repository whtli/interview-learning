package org.example.java.basic.ch8_else.string_relation;

/**
 * @author: whtli
 * @date: 2023/09/13
 * @description: StringBuffer相关，为保证线程安全，为方法添加了synchronized关键字修饰
 */
public class StringBufferTest {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(true);
        stringBuffer.append('x');
        stringBuffer.append(1);
        stringBuffer.append(2L);
        stringBuffer.append(2.3d);
        stringBuffer.append(4.5f);
        stringBuffer.append("sss");
        System.out.println(stringBuffer);

        stringBuffer.deleteCharAt(1);
        System.out.println(stringBuffer);
        stringBuffer.delete(2, 3);
        System.out.println(stringBuffer);

        stringBuffer.insert(7, "y");
        System.out.println(stringBuffer);

        stringBuffer.setCharAt(1, 'w');
        System.out.println(stringBuffer);

        System.out.println(stringBuffer.length());
        System.out.println(stringBuffer.capacity());
        stringBuffer.ensureCapacity(30);
        System.out.println(stringBuffer.capacity());
        System.out.println(stringBuffer.length());
        stringBuffer.setLength(9);
        System.out.println(stringBuffer);
        System.out.println(stringBuffer.length());
        System.out.println(stringBuffer.capacity());
        stringBuffer.trimToSize();
        System.out.println(stringBuffer.capacity());

        System.out.println(stringBuffer);
        char[] tmp = new char[stringBuffer.length()];
        stringBuffer.getChars(3, 8, tmp, 3);
        System.out.println(tmp);
    }
}
