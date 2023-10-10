package org.example.ch01_java.ch05_io;

import java.io.*;

/**
 * @author: whtli
 * @date: 2023/10/10
 * @description: 字节缓冲流
 */
public class ByteBufferTest {
    private static final String fileInputPath = "src/main/java/org/example/ch01_java/ch05_io/Java开发手册(黄山版).pdf";
    private static final String fileOutputPath1 = "src/main/java/org/example/ch01_java/ch05_io/Java开发手册(黄山版)-副本1.pdf";
    private static final String fileOutputPath2 = "src/main/java/org/example/ch01_java/ch05_io/Java开发手册(黄山版)-副本2.pdf";

    public static void main(String[] args) {
        copyWithoutByteArray();
        copyWithByteArray();
    }

    private static void copyWithoutByteArray() {
        // 缓冲流将数据加载至缓冲区，一次性读取/写入多个字节，从而避免频繁的IO操作，提高流的传输效率
        copyPdfStream();
        copyPdfBufferStream();
    }

    private static void copyWithByteArray() {
        // 如果调用read(byte b[])和write(byte b[], int off, int len)这两个写入字节数组的方法的话，数组的大小合适时，普通流与缓冲流的性能差距并不大
        copyPdfStreamWithByteArrayStream();
        copyPdfStreamWithByteArrayBufferStream();
    }

    private static void copyPdfStream() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream(fileInputPath);
             FileOutputStream fos = new FileOutputStream(fileOutputPath1)) {
            int content;
            while ((content = fis.read()) != -1) {
                fos.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("使用普通流复制PDF文件总耗时:" + (end - start) + " 毫秒");
    }

    private static void copyPdfBufferStream() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileInputPath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileOutputPath1))) {
            int content;
            while ((content = bis.read()) != -1) {
                bos.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("使用缓冲流复制PDF文件总耗时:" + (end - start) + " 毫秒");
    }

    public static void copyPdfStreamWithByteArrayStream() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        try (FileInputStream fis = new FileInputStream(fileInputPath);
             FileOutputStream fos = new FileOutputStream(fileOutputPath2)) {
            int len;
            byte[] bytes = new byte[4 * 1024];
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("使用普通流复制PDF文件总耗时:" + (end - start) + " 毫秒");
    }

    public static void copyPdfStreamWithByteArrayBufferStream() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileInputPath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileOutputPath2))) {
            int len;
            byte[] bytes = new byte[4 * 1024];
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("使用缓冲流复制PDF文件总耗时:" + (end - start) + " 毫秒");
    }
}
