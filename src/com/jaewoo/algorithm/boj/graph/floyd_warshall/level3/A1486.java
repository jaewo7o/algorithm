package com.jaewoo.algorithm.boj.graph.floyd_warshall.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1486 {

    /* 백준 풀이
    https://stonejjun.tistory.com/128
     */

    private static int N;
    private static int M;
    private static int T;
    private static int D;
    private static int MAX;

    private static int[][] map;
    private static int[][] dist;

    private static int[] dn = {0, 1, 0, -1};
    private static int[] dm = {1, 0, -1, 0};

    private static int INF = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        T = Integer.parseInt(token.nextToken());
        D = Integer.parseInt(token.nextToken());

        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            String line = br.readLine();
            for (int m = 0; m < M; m++) {
                char c = line.charAt(m);

                if (c >= 'A' && c <= 'Z') {
                    map[n][m] = (int) c - 65;
                } else if (c >= 'a' && c <= 'z') {
                    map[n][m] = (int) c - 71;
                }
            }
        }

        MAX = N * M;
        dist = new int[MAX][MAX];
        for (int n = 0; n < MAX; n++) {
            for (int m = 0; m < MAX; m++) {
                if (n == m) {
                    dist[n][m] = 0;
                } else {
                    dist[n][m] = INF;
                }
            }
        }

        int nextN, nextM;
        int here, there;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                here = n * M + m;
                for (int i = 0; i < 4; i++) {
                    nextN = n + dn[i];
                    nextM = m + dm[i];

                    there = nextN * M + nextM;

                    // out of box skip
                    if (nextN < 0 || nextM < 0 || nextN >= N || nextM >= M) {
                        continue;
                    }

                    // 높이차가 이동 가능하지 않으면 skip
                    int currentHeight = map[n][m];
                    int nextHeight = map[nextN][nextM];
                    if (Math.abs(currentHeight - nextHeight) > T) {
                        continue;
                    }

                    if (currentHeight >= nextHeight) {
                        dist[here][there] = 1;
                    } else {
                        int diff = nextHeight - currentHeight;
                        dist[here][there] = diff * diff;
                    }
                }
            }
        }

        floydWarshall();


        int maxValue = 0;
        for (int i = 0; i < MAX; i++) {
            if (dist[0][i] + dist[i][0] <= D) {
                maxValue = Math.max(maxValue, map[i / M][ i % M]);
            }
        }

        System.out.println("maxValue = " + maxValue);
    }

    private static void floydWarshall() {

        for (int k = 0; k < MAX; k++) {
            for (int n = 0; n < MAX; n++) {
                for (int m = 0; m < MAX; m++) {
                    if (dist[n][m] > dist[n][k] + dist[k][m]) {
                        dist[n][m] = dist[n][k] + dist[k][m];
                    }
                }
            }
        }
    }
}
