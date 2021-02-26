package com.jaewoo.algorithm.boj.graph.floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A11403 {
    private static int N;
    private static int[][] maps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        maps = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                maps[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        floydWarshall();

        printResult();
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(maps[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (maps[i][k] == 1 && maps[k][j] == 1) {
                        maps[i][j] = 1;
                    }
                }
            }
        }
    }
}

/*
3
0 1 0
0 0 1
1 0 0
 */