package org.example.ch01_java.ch01_basic.p10_generics;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: whtli
 * @date: 2023/08/30
 * @description: 泛型
 */
public class GenericsTest {
    public static void main(String[] args) {

    }
}

/**
 * 泛型类
 * 此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
 * 在实例化泛型类时，必须指定T的具体类型
 *
 * @param <T>
 */
class GenericClass<T> {
    private T key;

    public GenericClass(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    /**
     * 通过属性的类型,可以获取到泛型的类型
     */
    public void getGenericType() {
        System.out.println(key.getClass());
    }

    public static void main(String[] args) {
        // 实例化泛型类：
        GenericClass<Integer> genericInteger = new GenericClass<>(123456);
        System.out.println(genericInteger.getKey());
        genericInteger.getGenericType();
    }
}

/**
 * 子类传入参数后,获取父类的泛型类型
 */
class GenericChildClass extends GenericClass<String> {
    public GenericChildClass(String key) {
        super(key);
    }

    public static void main(String[] args) {
        ParameterizedType parameterizedType = (ParameterizedType) GenericChildClass.class.getGenericSuperclass();

        for (Type actualTypeArgument : parameterizedType.getActualTypeArguments()) {
            System.out.println(actualTypeArgument.getTypeName());
        }
    }
}


/**
 * 泛型接口
 *
 * @param <T>
 */
interface GenericInterface<T> {
    /**
     * 泛型方法测试
     * @return 泛型
     */
    public T method();
}


/**
 * 实现泛型接口，不指定类型
 *
 * @param <T>
 */
class GenericInterfaceImpl1<T> implements GenericInterface<T> {
    @Override
    public T method() {
        return null;
    }
}


/**
 * 实现泛型接口，指定类型
 *
 * @param <T>
 */
class GenericInterfaceImpl2<T> implements GenericInterface<String> {
    @Override
    public String method() {
        return "GenericInterfaceImpl2";
    }
}

class GenericInterfaceTest {
    public static void main(String[] args) {
        GenericInterfaceImpl1 genericInterfaceImpl1 = new GenericInterfaceImpl1();
        System.out.println(genericInterfaceImpl1.method());
        GenericInterfaceImpl2 genericInterfaceImpl2 = new GenericInterfaceImpl2();
        System.out.println(genericInterfaceImpl2.method());
    }
}


/**
 * 泛型方法
 */
class GenericMethod {
    public static void main(String[] args) {
        // 创建不同类型数组：Integer 和 String
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};
        printArray(intArray);
        printArray(stringArray);
    }

    /**
     * public static <E> void printArray(E[] inputArray) {} 一般被称为静态泛型方法
     * 在 java 中泛型只是一个占位符，必须在传递类型后才能使用
     * 静态方法的加载先于类的实例化，而类在实例化时才能真正的传递类型参数
     * 也就是说类中的泛型还没有传递真正的类型参数，静态的方法的加载就已经完成了
     * 所以静态泛型方法是没有办法使用类上声明的泛型的，只能使用自己声明的 <E>
     */
    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }
}


/**
 * 以下两个重载的函数，它们的参数类型不同，分别是List<String>、List<Integer>
 * 但是，这段代码无法通过编译
 * 因为参数List<Integer>和List<String>编译之后都被擦除了
 * 变成了一样的原生类型 List
 * 擦除动作导致这两个方法变得一模一样
 */
//class GenericTypes {
//
//    public static void method(List<Integer> list) {
//        System.out.println("invoke method(List<String> list)");
//    }
//
//    public static void method(List<Integer> list) {
//        System.out.println("invoke method(List<Integer> list)");
//    }
//}


/**
 * 经过泛型擦除,通过反射可以向List等集合类中添加不同类型的元素,因为类型已经被擦除了,变成了Object
 * 泛型虽然被擦除了，但是具体的泛型信息还是被保存在了字节码中
 */
class GenericTypes {
    public static void main(String[] args) throws Exception {
        List<String> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        System.out.println(list1.getClass());
        System.out.println(list2.getClass());
        System.out.println(list1.getClass().equals(list2.getClass()));
        // 以下接口只能拿到泛型个数,不能拿到泛型类型
        System.out.println(Arrays.toString(list1.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(list2.getClass().getTypeParameters()));

        List<String> list3 = new ArrayList<>();
        list3.add("whtli");
        // 编译时报错
        // list3.add(53);

        // 绕过了编译时，在运行时添加 --> 无视泛型参数的安全检查，增加了安全问题
        Class clazz = list3.getClass();
        Method method = clazz.getMethod("add", Object.class);
        method.invoke(list3, 54);
        System.out.println(list3.size());
        System.out.println(list3);
    }
}


/**
 * 经过类型擦除，所有的泛型类实例都关联到同一份字节码上，泛型类的所有静态变量是共享的
 * 所以输出结果为 2
 */
class GenericStaticTest {
    public static void main(String[] args) {
        GenericStatic<Integer> gti = new GenericStatic<Integer>();
        gti.var = 1;
        GenericStatic<String> gts = new GenericStatic<String>();
        gts.var = 2;
        System.out.println(GenericStatic.var);
    }
}


class GenericStatic<T> {
    public static int var = 0;

    public void nothing(T x) {
    }
}