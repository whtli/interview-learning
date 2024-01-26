package org.example.other;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: whtli
 * @date: 2024/01/26
 * @description: 字符串大数相乘
 */
public class StringBigIntegerMultiply {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String num1 = in.nextLine();
        String num2 = in.nextLine();
        /*
        case1:
        num1: 34421571326548215743784513678546732546713546712354
        num2: 12378654563254167382567412354671235
        正确结果 : 426092740975754874544232861065927907880991891610715464295474187761967011548782937190
        模拟结果1: 426092740975754874544232861065927907880991891610715464295474187761967011548782937190
        模拟结果2: 426092740975754874544232861065927907880991891610715464295474187761967011548782937190

        case2:
        num1: 999999999999999999999999999999999999999999999999999999999999999999
        num2: 999999999999999999999999999999999999999999999921321341999999999999999999999999
        正确结果 : 999999999999999999999999999999999999999999999921321341999999999998999999999999000000000000000000000000000000000078678658000000000000000000000001
        模拟结果1: 999999999999999999999999999999999999999999999921321341999999999998999999999999000000000000000000000000000000000078678658000000000000000000000001
        模拟结果2: 999999999999999999999999999999999999999999999921321341999999999998999999999999000000000000000000000000000000000078678658000000000000000000000001
        */
        Solution solution = new Solution();
        solution.Multiply1(num1, num2);
        solution.Multiply2(num1, num2);
        solution.Multiply3(num1, num2);
    }
}

class Solution {
    /**
     * 竖式乘法
     * num1的单个数字分别和num2相乘，使用字符串乘法并以字符串形式记录所有临时乘积，最后使用字符串加法将所有临时成绩求和
     *
     * @param num1 因数1
     * @param num2 因数2
     */
    public void Multiply1(String num1, String num2) {
        List<StringBuilder> list = new ArrayList<>();
        StringBuilder tmp1 = new StringBuilder(num1);
        StringBuilder tmp2 = new StringBuilder(num2);

        // 字符串乘法，按位相乘，记录各位的乘积
        tmp1.reverse();
        for (int i = 0; i < tmp2.length(); i++) {
            int item2 = tmp2.charAt(i) - '0';
            int flag1 = 0;
            StringBuilder tmpMul = new StringBuilder();
            for (int j = 0; j < tmp1.length(); j++) {
                int item1 = tmp1.charAt(j) - '0';
                int mul = flag1 + item1 * item2;
                tmpMul.append(mul % 10);
                flag1 = mul / 10;
            }
            if (flag1 != 0) {
                tmpMul.append(flag1);
            }
            list.add(tmpMul);
        }

        // 逆转列表中所有临时乘积，得到正确的数值
        for (StringBuilder item : list) {
            item.reverse();
        }

        // 字符串加法
        StringBuilder sumAll = new StringBuilder(list.get(0));
        for (int k = 1; k < list.size(); k++) {
            sumAll.append(0);
            int m = sumAll.length() - 1;
            int n = list.get(k).length() - 1;

            StringBuilder item = list.get(k);
            int flag2 = 0;
            StringBuilder tmpSum = new StringBuilder();
            while (m >= 0 || n >= 0 || flag2 > 0) {
                int x = m >= 0 ? sumAll.charAt(m) - '0' : 0;
                int y = n >= 0 ? item.charAt(n) - '0' : 0;
                int sum = x + y + flag2;
                tmpSum.append(sum % 10);
                flag2 = sum / 10;
                m--;
                n--;
            }
            tmpSum.reverse();
            sumAll = tmpSum;
        }
        System.out.println("模拟结果1: " + sumAll);
    }

    /**
     * 竖式乘法的优化
     *
     * @param num1 因数1
     * @param num2 因数2
     */
    public void Multiply2(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();

        int[] arr = new int[m + n];
        arr[0] = 0;
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                arr[i + j + 1] += x * y;
            }
        }
        for (int k = m + n - 1; k > 0; k--) {
            arr[k - 1] += arr[k] / 10;
            arr[k] %= 10;
        }
        StringBuilder ans = new StringBuilder();
        int idx = arr[0] == 0 ? 1 : 0;
        while (idx < m + n) {
            ans.append(arr[idx]);
            idx++;
        }
        System.out.println("模拟结果2: " + ans);
    }

    /**
     * 借助Java中的BigInteger做正确性检验
     *
     * @param num1 因数1
     * @param num2 因数2
     */
    public void Multiply3(String num1, String num2) {
        BigInteger bi1 = new BigInteger(num1);
        BigInteger bi2 = new BigInteger(num2);
        System.out.println("正确结果 : " + bi1.multiply(bi2));
    }
}
