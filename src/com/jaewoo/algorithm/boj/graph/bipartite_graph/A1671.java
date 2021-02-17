package com.jaewoo.algorithm.boj.graph.bipartite_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A1671 {

    static int N;
    static int[] size;
    static int[] velocity;
    static int[] brain;
    static boolean[][] connect;
    private static boolean[] visit;
    private static int[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        size = new int[N + 1];
        velocity = new int[N + 1];
        brain = new int[N + 1];
        connect = new boolean[N + 1][N + 1];
        B = new int[N + 1];

        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            size[i] = Integer.parseInt(st.nextToken());
            velocity[i] = Integer.parseInt(st.nextToken());
            brain[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (i == j) {
                    continue;
                }

                if (size[i] == size[j] && velocity[i] == velocity[j] && brain[i] == brain[j] && i > j) {
                    continue;
                }

                if (size[i] >= size[j] && velocity[i] >= velocity[j] && brain[i] >= brain[j]) {
                    connect[i][j] = true;
                }
            }
        }

        //System.out.println(Arrays.deepToString(connect));
        int count = 0;
        visit = new boolean[N + 1];
        for (int i=1; i<=N; i++) {
            Arrays.fill(visit, false);
            if (dfs(i)) {
                count++;
            }

            Arrays.fill(visit, false);
            if (dfs(i)) {
                count++;
            }
        }

        System.out.println(N - count);
    }

    private static boolean dfs(int start) {
        if (visit[start]) {
            return false;
        }

        visit[start] = true;

        for (int j=1; j<=N; j++) {
            if (connect[start][j]) {
                if (B[j] == 0 || dfs(B[j])) {
                    B[j] = start;
                    return true;
                }
            }
        }

        return false;
    }
}

/* INPUT
5
4 5 5
10 10 8
5 7 10
8 7 7
8 10 3

 */
