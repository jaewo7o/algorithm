package com.jaewoo.algorithm.boj.graph.bipartite_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A11375 {

    static int N;
    static int M;

    static boolean[][] jobs;
    static boolean[] visit;

    static int[] matchA;
    static int[] matchB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matchA = new int[N + 1];
        matchB = new int[M + 1];
        jobs = new boolean[N + 1][M + 1];
        visit = new boolean[N + 1];
        for (int i=1, k; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            while (k-- > 0) {
                int j = Integer.parseInt(st.nextToken());
                jobs[i][j] = true;
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

    private static boolean dfs(int start) {
        if (visit[start]) {
            return false;
        }

        visit[start] = true;
        for (int j=1; j<=M; j++) {
            if (jobs[start][j]) {
                if (matchB[j] == 0 || dfs(matchB[j])) {
                    matchA[start] = j;
                    matchB[j] = start;
                    return true;
                }
            }
        }

        return false;
    }
}

/* input
5 5
2 1 2
1 1
2 2 3
3 3 4 5
1 1
 */
