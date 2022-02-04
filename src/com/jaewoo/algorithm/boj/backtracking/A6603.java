package com.jaewoo.algorithm.boj.backtracking;

import java.util.Scanner;

public class A6603 {
    private static int K;
    private static StringBuilder sb;
    private static int[] numbers;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        sb = new StringBuilder();
        while ((K = sc.nextInt()) != 0) {

            // 주어진 숫자 초기화
            numbers = new int[ K + 1 ];
            for (int i = 1; i <= K; i++) {
                numbers[i] = sc.nextInt();
            }

            for (int i = 1; i <= K; i++) {
                dfs(i, 1, numbers[i] + " ");
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int start, int depth, String numberString) {
        if (depth == 6) {
            sb.append(numberString).append("\n");
            return;
        }

        for (int i = start + 1; i <= K; i++) {
            depth++;
            dfs(i, depth, numberString + numbers[i] + " ");
            depth--;
        }
    }
}

/* input
7 1 2 3 4 5 6 7
8 1 2 3 5 8 13 21 34
0
 */
