package org.example.other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: whtli
 * @date: 2023/09/05
 * @description: 给一个字符串s，一个字符串数组words，问s能否由words中的字符串拼接成
 */
public class WordCheck {
    public static void main(String[] args) {
        String str = "helloxxwhtlirwhtli";
        String[] words = {"whtlir", "hello", "xxwhtli"};
        WordCheckSolution solution = new WordCheckSolution();

        boolean ans1 = solution.check1(str, words);
        boolean ans2 = solution.check2(str, words);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}

class WordCheckSolution {
    /**
     * 直接遍历替换
     * 不合理，不符合单词不可拆分的原则
     * 仅留作错误思路参考
     */
    public boolean check1(String str, String[] words) {
        for (String word : words) {
            if (str.contains(word)) {
                str = str.replaceFirst(word, "");
            }
        }
        return str.isEmpty();
    }

    /**
     * 回溯法
     * 正确解法
     */
    public boolean check2(String str, String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        return backTrace(str, set);
    }

    private boolean backTrace(String str, Set<String> set) {
        if (str.isEmpty()) {
            return true;
        }
        for (String word : set) {
            if (str.startsWith(word)) {
                String remaining = str.substring(word.length());
                if (backTrace(remaining, set)) {
                    return true;
                }
            }
        }
        return false;
    }
}
