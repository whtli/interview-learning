package org.example;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {

    public static int dosth(int i) {
        try {
            i = 1;
            System.out.println("try : " + i);
            return i;
        } catch (Exception e) {
            i = 2;
            System.out.println("catch : " + i);
            return i;
        } finally {
            i = 3;
            System.out.println("finally : " + i);
            // return i;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        List<Integer> integerList = new ArrayList<>();
        integerList.clear();

        int i = 0;
        // System.out.println(dosth(i));
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        map1.put('1', 1);
        map1.put('1', 2);
        System.out.println(map1.get('1'));

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
//        boolean flag = true;
//        Set<Character> characters = map1.keySet();
//        Collection<Integer> values = map2.values();
//        Iterator<Map.Entry<Character, Integer>> itr = map1.entrySet().iterator();
//        while (itr.hasNext()) {
//            System.out.println(itr.next());
//        }
//        PriorityQueue<Integer> queue = new PriorityQueue<>();
//        String str = "asdfdga";
//        int t = str.indexOf('a', 2);
//        List<String> strings = Arrays.asList("1", "2", "3");
//        strings.forEach(s -> System.out.println(s));
//        int[] num = {1, 2, 4};
//        Arrays.stream(num).forEach(i -> System.out.println(i));
//        IntSummaryStatistics statistics = Arrays.stream(num).summaryStatistics();
//        System.out.println(statistics.getAverage());
//
//        Map<String, Integer> map = new HashMap<>();
//        map.put("1", 1);
//        map.put("3", 3);
//        map.put("2", 2);
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " , " + entry.getValue());
//        }
//        map.forEach((k, v) -> System.out.println(k + " . " + v));
//        Stream<String> stringStream = strings.stream().filter(s -> s.equals("1"));
//        System.out.println(stringStream.count());
//        String ans = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(","));
//        System.out.println(ans);
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        in.nextLine();
//        String str = in.nextLine();
//
//        char[] array = str.toCharArray();
//        int start = 0, end = n - 1;
//        while (array[start] != 'M'){
//            start ++;
//        }
//        while (array[start] != 'T'){
//            start ++;
//        }
//        while (array[end] != 'T'){
//            end --;
//        }
//        while (array[end] != 'M'){
//            end --;
//        }
//        System.out.println(str.substring(start + 1, end));
//        int firstIndexOfM = str.indexOf('M');
//        int firstIndexOfT = str.indexOf('T', firstIndexOfM + 1);
//        String reverse = new StringBuilder(str.substring(firstIndexOfT + 1)).reverse().toString();
//        int lastIndexOfT = reverse.indexOf('T', 0);
//        int lastIndexOfM = reverse.indexOf('M', lastIndexOfT + 1);
//        System.out.println(str.substring(firstIndexOfT + 1, n - lastIndexOfM - 1));
    }
}

//public class org.example.test {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        final int N = 100010;
//        int[] arrayA = new int[N];
//        int[] arrayB = new int[N];
//        for (int i = 1; i <= n; i ++) {
//            arrayA[i] = in.nextInt();
//            arrayB[i] = -1;
//        }
//        int m = in.nextInt();
//        while (m-- > 0){
//            int op = in.nextInt();
//            if (op == 1) {
//                int k = in.nextInt();
//                int x = in.nextInt();
//                int y = in.nextInt();
//                System.arraycopy(arrayA, x, arrayB, y, k);
//            } else {
//                int x = in.nextInt();
//                System.out.println(arrayB[x]);
//            }
//        }
//    }
//}

//public class org.example.test {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int m = in.nextInt();
//
//        Integer[][] res = new Integer[n][2];
//        for (int i = 0; i < n; i++) {
//            int p = in.nextInt();
//            int w = in.nextInt();
//            res[i][0] = i;
//            res[i][1] = p + w * 2;
//        }
//        Arrays.sort(res, ((o1, o2) -> o2[1] - o1[1]));
//
//        int[] ans = new int[m];
//        for (int i = 0; i < m && i < n; i++) {
//            ans[i] = res[i][0] + 1;
//        }
//        Arrays.sort(ans);
//        for (int i = 0; i < m - 1; i ++) {
//            System.out.print(ans[i] + " ");
//        }
//        System.out.print(ans[m - 1]);
//    }
//}


//public class org.example.test {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        in.nextLine();
//        int[] weight = new int[n];
//        for (int i = 0; i < n; i ++) {
//            weight[i] = in.nextInt();
//        }
//        in.nextLine();
//        Solution solution = new Solution();
//        for (int i = 0; i < n; i ++) {
//            int order = in.nextInt();
//            weight[order - 1] = 0;
//            System.out.println(solution.maxWeight(weight));
//        }
//    }
//
//}
//class Solution {
//    public int maxWeight(int[] weight) {
//        int ans = 0;
//        int sum = 0;
//        for (int i = 0; i < weight.length; i ++){
//            if (weight[i] == 0 || i == weight.length - 1) {
//                ans = Math.max(ans, sum);
//            }
//            sum += weight[i];
//        }
//        return ans;
//    }
//}


//class Soultion{
//    public boolean isIllegal(String name) {
//        // 用户名需要包含至少一个字母和一个数字
//        if (name == null || name.length() == 0) {
//            return false;
//        }
//        // 首字符必须是大写或者小写字母
//        char first = name.charAt(0);
//        if (!isSmallCharacter(first) && !isBigCharacter(first)) {
//            return false;
//        }
//        int len = name.length();
//        int charCount = 0;
//        int numberCount = 0;
//        for (int i = 0; i < len; i ++) {
//            boolean flag1 = isSmallCharacter(name.charAt(i));
//            boolean flag2 = isBigCharacter(name.charAt(i));
//            boolean flag3 = isNumber(name.charAt(i));
//            if (flag1 || flag2) {
//                charCount ++;
//            }
//            if (flag3) {
//                numberCount ++;
//            }
//            if (!flag1 && !flag2 && !flag3) {
//                return false;
//            }
//        }
//        if (charCount == len || numberCount == len) {
//            return false;
//        }
//        return true;
//    }
//
//    public boolean isSmallCharacter(char ch) {
//        return ch >= 'a' && ch <='z';
//    }
//    public boolean isBigCharacter(char ch) {
//        return ch >= 'A' && ch <='Z';
//    }
//    public boolean isNumber(char ch) {
//        return ch >= '0' && ch <='9';
//    }
//}