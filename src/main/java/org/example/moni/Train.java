package org.example.moni;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: whtli
 * @date: 2023/04/11
 * @description:
 */
public class Train {
    public static void main(String[] args) {
        int[] inOrder = {1, 2, 3};
        int[] outOrder = {3, 1, 2};
        Solution solution = new Solution();
        boolean ans = solution.checkTrainSequence(inOrder, outOrder);
        System.out.println(ans);
    }
}

class Solution {
    public boolean checkTrainSequence(int[] inOrder, int[] outOrder) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        for (int item : inOrder) {
            stack.push(item);
            while (!stack.isEmpty() && stack.peek() == outOrder[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
