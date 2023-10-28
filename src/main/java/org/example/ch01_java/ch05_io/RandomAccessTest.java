package org.example.ch01_java.ch05_io;

import java.io.*;

/**
 * @author: whtli
 * @date: 2023/10/10
 * @description: 随机访问流
 */
public class RandomAccessTest {
    public static final String FILE_INPUT = "src/main/java/org/example/ch01_java/ch05_io/file-input-random.txt";

    public static void main(String[] args) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(new File(FILE_INPUT), "rw")) {
            System.out.println("读取之前的偏移量：" + randomAccessFile.getFilePointer()
                    + "，当前读取到的字符" + (char) randomAccessFile.read()
                    + "，读取之后的偏移量：" + randomAccessFile.getFilePointer());

            // 设置指针当前偏移量为 6
            randomAccessFile.seek(6);
            System.out.println("读取之前的偏移量：" + randomAccessFile.getFilePointer()
                    + "，当前读取到的字符" + (char) randomAccessFile.read()
                    + "，读取之后的偏移量：" + randomAccessFile.getFilePointer());

            // 从偏移量7的位置开始往后写入字节数据，如果对应的位置已经有数据会覆盖
            randomAccessFile.write(new byte[]{'H', 'I', 'J', 'K'});

            // 设置指针当前偏移量为 0，回到起始位置
            randomAccessFile.seek(0);
            System.out.println("读取之前的偏移量：" + randomAccessFile.getFilePointer()
                    + "，当前读取到的字符" + (char) randomAccessFile.read()
                    + "，读取之后的偏移量：" + randomAccessFile.getFilePointer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final int DOWNLOAD_THREAD_NUM = 10;

    private static final String FILE_TEMP_SUFFIX = "temp";

    /**
     * RandomAccessFile 比较常见的一个应用就是实现大文件的断点续传
     * 分片（先将文件切分成多个文件分片）上传是断点续传的基础
     * 使用RandomAccessFile可以合并文件分片
     */
    public static boolean merge(String fileName) throws IOException {
        byte[] buffer = new byte[1024 * 10];
        int len = -1;
        try (RandomAccessFile oSavedFile = new RandomAccessFile(fileName, "rw")) {
            for (int i = 0; i < DOWNLOAD_THREAD_NUM; i++) {
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName + FILE_TEMP_SUFFIX + i))) {
                    while ((len = bis.read(buffer)) != -1) {
                        oSavedFile.write(buffer, 0, len);
                    }
                }
            }
            System.out.println("文件合并完毕：" + fileName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
