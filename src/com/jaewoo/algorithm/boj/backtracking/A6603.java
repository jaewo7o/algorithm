package com.jaewoo.algorithm.boj.backtracking;

import java.util.Scanner;

public class A6603 {
    private static int K;
    private static StringBuilder sb;
    private static int[] numbers;
    private static int count;

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
                count = 1;
                dfs(i, numbers[i] + " ");
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int x, String numberString) {
        if (count == 6) {
            sb.append(numberString).append("\n");
            return;
        }

        for (int i = x + 1; i <= K; i++) {
            count++;
            dfs(i, numberString + numbers[i] + " ");
            count--;
        }

    }
}
