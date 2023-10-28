package org.example.ch01_java.ch01_basic.p15_sugar;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: try-with-resource
 */
public class TryWithResource {
    public static void main(String... args) throws IOException {
        String src = "src/main/java/org/example/java/basic/ch6_sugar/try-with-resource-in.txt";
        String dst = "src/main/java/org/example/java/basic/ch6_sugar/try-with-resource-out.txt";

        // 例1
        read(src);
        System.out.println();

        // 例2
        copy(src, dst);

        // 例3
        String path = "";
        String ret = firstLineOfFile(path, "no content");
        System.out.println(ret);
    }

    static void read(String src) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(src))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    static void copy(String src, String dst) throws IOException {
        int bufferSize = 1024;
        try (InputStream in = Files.newInputStream(Paths.get(src)); OutputStream out = Files.newOutputStream(Paths.get(dst))) {
            byte[] buffer = new byte[bufferSize];
            int n;
            while ((n = in.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        }
    }

    static String firstLineOfFile(String path, String defaultValue) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultValue;
        }
    }
}
