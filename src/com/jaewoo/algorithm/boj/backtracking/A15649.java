package com.jaewoo.algorithm.boj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A15649 {
    private static int N;
    private static int M;
    private static int[] numbers;
    private static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        StringTokenizer token = new StringTokenizer(line);
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        numbers = new int[N + 1];
        isVisit = new boolean[N + 1];
        dfs(0, numbers);
    }

    private static void dfs(int depth, int [] numbers) {
        if (depth == M) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= M; i++) {
                sb.append(numbers[i]).append(" ");
            }

            System.out.println(sb);
        }

        for (int i = 1; i <= N; i++) {
            if (!isVisit[i]) {
                isVisit[i] = true;
                numbers[++depth] = i;
                dfs(depth, numbers);
                depth--;
                isVisit[i] = false;
            }
        }
    }
}

/* INPUT
3 1
 */
