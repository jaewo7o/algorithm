package com.jaewoo.algorithm.boj.graph.floyd_warshall.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A2458 {

    static int N;
    static int M;
    static int[][] maps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N + 1][N + 1];
        for (int i = 1, s, e; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            maps[s][e] = 1;
        }

        floyd();

        int count, person = 0;
        for (int i = 1; i <= N; i++) {
            count = 0;
            for (int j = 1; j <= N; j++) {
                if (maps[i][j] == 1 || maps[j][i] == 1) {
                    count++;
                }
            }

            if (count == N - 1) {
                person++;
            }
        }

        System.out.println(person);
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (maps[k][j] == 1 && maps[i][k] == 1) {
                        maps[i][j] = 1;
                    }
                }
            }
        }
    }
}

/* input
6 6
1 5
3 4
5 4
4 2
4 6
5 2
 */
