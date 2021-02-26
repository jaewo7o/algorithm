package com.jaewoo.algorithm.boj.graph.floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A10159 {

    static int N;
    static int M;

    static int[][] maps;

    public static void main(String[] args) throws IOException {
        initialize();

        floydWarshal();

        printResult();
    }

    private static void printResult() {
        int count;
        for (int i=1; i<=N; i++) {
            count = 0;
            for (int j=1; j<=N; j++) {
                if (maps[i][j] == 0 && maps[j][i] == 0) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }

    private static void floydWarshal() {
        for (int k=1; k<=N; k++) {
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    if (maps[i][k] == 1 && maps[k][j] == 1) {
                        maps[i][j] = 1;
                    }
                }
            }
        }
    }

    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        maps = new int[N + 1][N + 1];

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (i == j) {
                    maps[i][j] = 1;
                } else {
                    maps[i][j] = 0;
                }
            }
        }

        StringTokenizer st;
        for (int i=1, s, e; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            maps[s][e] = 1;
        }
    }
}

/* INPUT
6
5
1 2
2 3
3 4
5 4
6 5
 */