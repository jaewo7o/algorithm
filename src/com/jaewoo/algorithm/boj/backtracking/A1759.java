package com.jaewoo.algorithm.boj.backtracking;

import java.util.Arrays;
import java.util.Scanner;

public class A1759 {

    private static int L;
    private static int C;
    private static int[] chars;
    private static int count;
    private static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        C = sc.nextInt();

        chars = new int[C + 1];
        for (int i = 1; i <= C; i++) {
            chars[i] = sc.next().charAt(0);
        }
        Arrays.sort(chars); // 알파벳이 증가하는 형식이니 배열을 정렬한다.

        sb = new StringBuilder();
        for (int i = 1; i <= C; i++) {
            count = 1;
            dfs(i, (char)chars[i] + "");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int x, String letters) {
        if (count == L) {
            if (isPossible(letters)) {
                sb.append(letters).append("\n");
            }
            return;
        }

        for (int i = x + 1; i <= C; i++) {
            count++;
            dfs(i, letters + (char)chars[i]);
            count--;
        }
    }

    private static boolean isPossible(String letters) {
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};

        int vowelCount = 0;
        for (int i = 0; i < L; i++) {
            char c = letters.charAt(i);
            for (int j = 0; j < vowels.length; j++) {
                if (c == vowels[j]){
                    vowelCount++;
                }
            }
        }

        if (vowelCount >= 1 && L - vowelCount >= 2) {
            return true;
        } else {
            return false;
        }
    }
}
