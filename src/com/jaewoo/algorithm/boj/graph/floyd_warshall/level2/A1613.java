package com.jaewoo.algorithm.boj.graph.floyd_warshall.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1613 {

    /* 
        문제풀이 URL : 
           1. https://yabmoons.tistory.com/107
           2. https://jaimemin.tistory.com/673
    */
    private static final int INF = 100000;
    static int N;
    static int K;
    static int S;

    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[N + 1][N + 1];

        for (int i = 1, s, e; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            dist[s][e] = -1;
            dist[e][s] = 1;
        }

        floyd();

        S = Integer.parseInt(br.readLine());
        for (int i = 1, s, e; i <= S; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            System.out.println(dist[s][e]);
        }
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] == 1 && dist[k][j] == 1) {
                        dist[i][j] = 1;
                    } else if (dist[i][k] == -1 && dist[k][j] == -1) {
                        dist[i][j] = -1;
                    }
                }
            }
        }
    }
}


/* input
5 5
1 2
1 3
2 3
3 4
2 4
3
1 5
2 4
3 1
 */
