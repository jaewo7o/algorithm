package com.jaewoo.algorithm.boj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A15650 {
    private static int N;
    private static int M;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        StringTokenizer token = new StringTokenizer(line);
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        numbers = new int[N + 1];
        dfs(1, 1, numbers);
    }

    private static void dfs(int start, int depth, int[] numbers) {
        if (depth == M + 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= M; i++) {
                sb.append(numbers[i]).append(" ");
            }

            System.out.println(sb);
        }

        for (int i = start; i <= N; i++) {
            numbers[depth] = i;
            dfs(i + 1, depth + 1, numbers);
        }
    }
}
