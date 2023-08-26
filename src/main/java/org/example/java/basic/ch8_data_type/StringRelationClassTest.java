package org.example.java.basic.ch8_data_type;

/**
 * @author: whtli
 * @date: 2023/08/15
 * @description:
 */
public class StringRelationClassTest {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();
        String string = "ababab";
        System.out.println(string.indexOf("ab", string.length()));

    }
}
