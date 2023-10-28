package org.example.other;

import java.util.*;
/**
 * @author: whtli
 * @date: 2023/10/03
 * @description: 绩点计算器
 */
public class GPA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("********************************");
        System.out.println("           绩点计算器           ");
        System.out.println("********************************");
        int[] scores = {80, 89, 97, 87, 99, 84, 77, 90, 90, 84, 90, 91, 96, 73, 84, 60};
        int[] powers = {4, 2, 3, 1, 2, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1};
        int n = scores.length;
        System.out.println();
        int sumMulti = 0;
        int sumPower = 0;
        int sumScore = 0;
        float[] transfer = new float[n];

        for (int i = 0; i < n; i++) {
            sumMulti += scores[i] * powers[i];
            sumScore += scores[i];
            sumPower += powers[i];
            transfer[i] = (float) (1.0 * (scores[i] - 50) / 10);
        }
        System.out.println("sumMulti: " + sumMulti);
        System.out.println("sumScore: " + sumScore);
        System.out.println("sumPower: " + sumPower);
        System.out.println(Arrays.toString(transfer));

        System.out.println();
        double tmpsum = 0.0;
        for (int i = 0; i < n; i++) {
            tmpsum += transfer[i] * powers[i];
        }
        double gpa = tmpsum / sumPower;
        System.out.println("GPA: " + gpa);
    }
}
