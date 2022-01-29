package com.jaewoo.algorithm.boj.graph.bipartite_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class A11376 {

    static int N;
    static int M;

    static List<Integer>[] jobs;
    static boolean[] visit;

    static int[] matchResult;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matchResult = new int[N + 1];
        jobs = new ArrayList[N + 1];
        visit = new boolean[N + 1];
        for (int i = 1, k; i <= N; i++) {
            jobs[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            while (k-- > 0) {
                int j = Integer.parseInt(st.nextToken());
                jobs[i].add(j);
            }
        }

        int count = 0;
        for (int k = 1; k <= 2; k++) {
            for (int i = 1; i <= N; i++) {
                Arrays.fill(visit, false);
                if (dfs(i)) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static boolean dfs(int x) {
        for (int y : jobs[x]) {
            if (visit[y]) {
                continue;
            }

            visit[y] = true;

            if (matchResult[y] == 0 || dfs(matchResult[y])) {
                matchResult[y] = x;
                return true;
            }
        }

        return false;
    }
}

/* input
5 5
2 1 2
2 1 2
2 1 2
2 4 5
0
 */
