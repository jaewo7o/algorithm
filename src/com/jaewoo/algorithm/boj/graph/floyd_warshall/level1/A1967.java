package com.jaewoo.algorithm.boj.graph.floyd_warshall.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1967 {

    /*
        성능문제로 플로이드 와샬은 좋은 솔루션이 아님
        BFS로 거리 계산을 한 후 최대 지름 계산 가능함
     */

    private static int N;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    map[i][j] = Integer.MAX_VALUE;
                }
            }
        }


        for (int n = 2, i, j, d; n <= N; n++) {
            StringTokenizer token = new StringTokenizer(br.readLine());

            i = Integer.parseInt(token.nextToken());
            j = Integer.parseInt(token.nextToken());
            d = Integer.parseInt(token.nextToken());

            map[i][j] = d;
            map[j][i] = d;
        }

        System.out.println(floydWarshall());
    }

    private static int floydWarshall() {
        int maxDistance = 0;
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) {
                        continue;
                    }

                    if (map[k][j] == Integer.MAX_VALUE || map[i][k] == Integer.MAX_VALUE) {
                        continue;
                    }

                    int dist = map[k][j] + map[i][k];
                    if (map[i][j] > dist) {
                        map[i][j] = dist;

                        if (maxDistance < map[i][j]) {
                            maxDistance = map[i][j];
                        }
                    }
                }
            }
        }

        return maxDistance;
    }
}
