package com.jaewoo.algorithm.boj.backtracking;

import java.util.Scanner;

public class A9663 {
    private static int N;
    private static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int j = 1; j <= N; j++) {
            int[] queenCols = new int[ N + 1 ];
            queenCols[1] = j;
            dfs(queenCols, 1);
        }

        System.out.println(count);
    }

    private static void dfs(int[] queenCols, int row) {
        if (row == N) {
            count++;
            return;
        }

        int nextRow = row + 1;
        for (int j = 1; j <= N; j++) {
            queenCols[nextRow] = j;
            if (isPossible(queenCols, nextRow)) {
                dfs(queenCols, nextRow);
            }
        }
    }

    private static boolean isPossible(int[] queenCols, int nextRow) {
        for (int i = 1; i < nextRow; i++) {
            // 이전에 있던 퀸의 열과 같으면 놓을 수 없는 위치로 판단
            if (queenCols[i] == queenCols[nextRow]) {
                return false;
            }

            // 대각선 위치에 존재하면 놓을 수 없음
            if (Math.abs(i - nextRow) == Math.abs(queenCols[i] - queenCols[nextRow])) {
                return false;
            }
        }

        return true;
    }
}
