package com.jaewoo.algorithm.boj.graph.bipartite_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A9576 {
    static int T;
    static int N;
    static int M;

    static boolean[][] connect;
    static int[] matchB;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            connect = new boolean[N + 1][M + 1];
            visit = new boolean[N + 1];
            matchB = new int[M + 1];

            for (int j=1, min, max; j<=M; j++) {
                st = new StringTokenizer(br.readLine());

                min = Integer.parseInt(st.nextToken());
                max = Integer.parseInt(st.nextToken());
                for (int i=1; i<=N; i++) {
                    if (i >= min && i<=max) {
                        connect[i][j] = true;
                    }
                }
            }

            int count = 0;
            for (int i=1; i<=N; i++) {
                Arrays.fill(visit, false);
                if (dfs(i)) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }

    private static boolean dfs(int start) {
        if (visit[start]) {
            return false;
        }

        visit[start] = true;

        for (int j=1; j<=M; j++) {
            if (connect[start][j]) {
                if (matchB[j] == 0 || dfs(matchB[j])) {
                    matchB[j] = start;
                    return true;
                }
            }
        }

        return false;
    }
}

/* INPUT
1
2 3
1 2
1 2
1 2
 */
