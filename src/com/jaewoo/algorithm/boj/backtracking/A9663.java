package com.jaewoo.algorithm.boj.backtracking;

import java.util.Scanner;

public class A9663 {
    private static int N;
    private static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int j = 1; j <= N; j++) {
            int[] cols = new int[ N + 1 ];
            cols[1] = j;
            dfs(cols, 1);
        }

        System.out.println(count);
    }

    private static void dfs(int[] cols, int row) {
        if (row == N) {
            count++;
            return;
        }

        int nextRow = row + 1;
        for (int j = 1; j <= N; j++) {
            cols[nextRow] = j;
            if (isPossible(cols, nextRow)) {
                dfs(cols, nextRow);
            }
        }
    }

    private static boolean isPossible(int[] cols, int nextRow) {
        for (int i = 1; i < nextRow; i++) {
            // 이전에 있던 퀸의 열과 같으면 놓을 수 없는 위치로 판단
            if (cols[i] == cols[nextRow]) {
                return false;
            }

            // 대각선 위치에 존재하면 놓을 수 없음
            if (Math.abs(i - nextRow) == Math.abs(cols[i] - cols[nextRow])) {
                return false;
            }
        }

        return true;
    }
}
