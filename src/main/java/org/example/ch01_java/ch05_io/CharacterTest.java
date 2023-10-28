package org.example.ch01_java.ch05_io;

import java.io.*;

/**
 * @author: whtli
 * @date: 2023/10/09
 * @description: 字符流
 * 字符流可以避免乱码问题，默认是Unicode编码
 */
public class CharacterTest {
    public static final String FILE_INPUT = "src/main/java/org/example/ch01_java/ch05_io/file-input.txt";
    public static final String FILE_OUTPUT = "src/main/java/org/example/ch01_java/ch05_io/file-output.txt";

    public static void main(String[] args) {
        basicAbility();
    }

    public static void basicAbility() {
        // 输入
        try (FileReader fr = new FileReader(FILE_INPUT)) {
            long skip = fr.skip(3);
            System.out.println("输入流中被忽略的字节数: " + skip);

            System.out.println("文件内容 :\n");
            int content;
            while ((content = fr.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 输出
        try (Writer output = new FileWriter(FILE_OUTPUT)) {
            output.write("你好，我是whtli");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
