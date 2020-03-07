package com.jaewoo.algorithm.boj.floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1238 {

    /**
     * Floyd_warshall or dijkstra로 문제 풀이 가능
     */

    static int N;
    static int M;
    static int X;
    static int[][] dist;
    static int INF = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        dist = new int[N + 1][N + 1];
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }

        for (int i=1, s, e, d; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            dist[s][e] = d;
        }

        calculateDistance();

        /*
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                sb.append(dist[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());*/
        int maxDistance = 0;
        for (int i=1; i<=N; i++) {
            maxDistance = Math.max(maxDistance, dist[X][i] + dist[i][X]);
        }

        System.out.println(maxDistance);
    }

    private static void calculateDistance() {
        for (int k=1; k<=N; k++) {
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}

/* input
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3
 */