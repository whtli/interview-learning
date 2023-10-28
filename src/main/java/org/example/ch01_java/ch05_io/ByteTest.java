package org.example.ch01_java.ch05_io;

import lombok.AllArgsConstructor;

import java.io.*;
import java.util.Arrays;

/**
 * @author: whtli
 * @date: 2023/10/09
 * @description: 字节流
 * 如果不知道编码类型容易乱码
 */
public class ByteTest {
    public static final String FILE_INPUT = "src/main/java/org/example/ch01_java/ch05_io/file-input.txt";
    public static final String FILE_OUTPUT = "src/main/java/org/example/ch01_java/ch05_io/file-output.txt";
    public static final String FILE_INPUT_OBJECT = "src/main/java/org/example/ch01_java/ch05_io/file-input-object.data";

    public static void main(String[] args) throws IOException {
        basicAbility();
        newAbility();
        combineAbility();
    }

    private static void basicAbility() {
        // 输入
        try (FileInputStream fis = new FileInputStream(FILE_INPUT)) {
            System.out.println("输入流中可以读取的字节数: " + fis.available());

            long skip = fis.skip(2);
            System.out.println("输入流中被忽略的字节数: " + skip);

            System.out.println("文件内容 :\n");
            int content;
            while ((content = fis.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        // 输出
        try (FileOutputStream output = new FileOutputStream(FILE_OUTPUT)) {
            byte[] array = FILE_INPUT.getBytes();
            output.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void newAbility() {
        try (FileInputStream fis = new FileInputStream(FILE_INPUT)) {
            // byte[] bytes = fis.readAllBytes();
            // System.out.println(Arrays.toString(bytes));
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void combineAbility() {
        // 通常会配合BufferedInputStream（字节缓冲输入流）使用FileInputStream
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(FILE_INPUT))) {
            // 读取文件的内容并复制到String对象中（readAllBytes方法需要调整为jdk9或以上）
            // String result = new String(bufferedInputStream.readAllBytes());
            // System.out.println(result);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // FileOutputStream通常也会配合BufferedOutputStream（字节缓冲输出流）来使用
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_OUTPUT)) {
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
            bos.write(FILE_INPUT.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DataInputStream不能单独用于读取指定类型数据，必须结合其它流，如FileInputStream
        try (FileInputStream fileInputStream = new FileInputStream(FILE_INPUT)) {
            //必须将fileInputStream作为构造参数才能使用
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            //可以读取任意具体的类型数据
            //可以读取任意具体的类型数据
            boolean b = dataInputStream.readBoolean();
            int i = dataInputStream.readInt();
            System.out.println(b);
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DataOutputStream不能单独用于写入指定类型数据，必须结合其它流，如 FileOutputStream
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_OUTPUT);) {
            // 输出流
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            // 输出任意数据类型
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeByte(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ObjectOutputStream将对象写入到输出流(序列化)
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_INPUT_OBJECT))) {
            Person person = new Person("whtli", 123);
            output.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ObjectInputStream用于从输入流中读取Java对象（反序列化）
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_INPUT_OBJECT))) {
            Person person = (Person) input.readObject();
            System.out.println(person.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

@AllArgsConstructor
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{" + "name='" + name + ", age=" + age + '}';
    }
}