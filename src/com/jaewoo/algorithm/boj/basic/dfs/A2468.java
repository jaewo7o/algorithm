package com.jaewoo.algorithm.boj.basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A2468 {

    private static int N;
    private static int[][] map;
    private static boolean[][] isVisit;
    private static int maxHeight;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        maxHeight = 0;
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            StringTokenizer token = new StringTokenizer(line);

            for (int j = 1; j<=N; j++ ) {
                int height = Integer.parseInt(token.nextToken());

                if (maxHeight < height) {
                    maxHeight = height;
                }

                map[i][j] = height;
            }
        }

        int count, maxCount = 0;
        for (int h = 1; h <= maxHeight; h++) {
            count = 0;
            isVisit = new boolean[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j] > h && !isVisit[i][j]) {
                        dfs(i, j, h);
                        count++;
                    }
                }
            }

            if (maxCount < count) {
                maxCount = count;
            }
        }

        System.out.println("maxCount = " + maxCount);
    }

    private static void dfs(int i, int j, int h) {
        isVisit[i][j] = true;

        for (int n=0, ni, nj; n<4; n++) {
            ni = i + dx[n];
            nj = j + dy[n];

            if (ni < 1 || nj < 1 || ni > N || nj > N) {
                continue;
            }

            if (map[ni][nj] <= h) {
                continue;
            }

            if (!isVisit[ni][nj]) {
                dfs(ni, nj, h);
            }
        }
    }
}

/* INPUT
5
6 8 2 6 2
3 2 3 4 6
6 7 3 3 2
7 2 5 3 6
8 9 5 2 7
 */
