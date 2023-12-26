package org.example.ch01_java.ch08_method.p02_overload_with_caution;

import java.util.*;

/**
 * @author: whtli
 * @date: 2023/12/26
 * @description: 慎用重载
 * 如果API的普通用户不知道”对于一组给定的参数，其中的哪个重载方法将会被调用“，那么使用这样的API就很可能导致错误
 * 安全而保守的策略是，永远不要导出两个具有相同参数数目的重载方法
 */
public class CollectionClassifier {
    public static String classify(Set<?> set) {
        return "Set";
    }

    public static String classify(List<?> list) {
        return "List";
    }

    public static String classify(Collection<?> collection) {
        return "Unknown Collection";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<>(),
                new ArrayList<>(),
                new HashMap<String, String>(10).values()
        };

        // 预期输出Set、List、Unknown Collection
        // 但是因为classify方法被重载了，而要调用哪个重载方法是在编译时决定的
        // 循环中的三次迭代，参数的编译时类型都是相同的Collection<?>，运行时类型都是不同的，但这并不影响对重载方法的选择
        // 因为重载类型都是Collection<?>，所以唯一合适的重载方法是classify(Collection<?> collection)
        for (Collection<?> c : collections) {
            System.out.println(classify(c));
        }
    }

}
