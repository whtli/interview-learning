package org.example.other;

import java.util.Stack;

/**
 * @author: whtli
 * @date: 2023/04/11
 * @description: 火车进出站顺序合法性判断
 */
public class TrainOrder {
    public static void main(String[] args) {
        int[] inOrder = {1, 2, 3, 4};
        int[] outOrder = {3, 4, 2, 1};

        TrainOrderCheckSolution solution = new TrainOrderCheckSolution();
        boolean ans1 = solution.checkTrainSequence1(inOrder, outOrder);
        boolean ans2 = solution.checkTrainSequence2(inOrder, outOrder);

        System.out.println(ans1);
        System.out.println(ans2);
    }
}

class TrainOrderCheckSolution {
    public boolean checkTrainSequence1(int[] inOrder, int[] outOrder) {
        Stack<Integer> stack = new Stack<>();
        int outIndex = 0;
        for (int train : inOrder) {
            stack.push(train);
            while (!stack.isEmpty() && stack.peek() == outOrder[outIndex]) {
                stack.pop();
                outIndex++;
            }
        }
        return stack.isEmpty();
    }

    public boolean checkTrainSequence2(int[] inOrder, int[] outOrder) {
        Stack<Integer> stack = new Stack<>();
        int inIndex = 0;
        int outIndex = 0;
        while (inIndex < inOrder.length) {
            if (!stack.isEmpty() && stack.peek() == outOrder[outIndex]) {
                stack.pop();
                outIndex++;
            } else {
                stack.push(inOrder[inIndex]);
                inIndex++;
            }
        }
        while (!stack.isEmpty() && stack.peek() == outOrder[outIndex]) {
            stack.pop();
            outIndex++;
        }
        return stack.isEmpty();
    }
}
