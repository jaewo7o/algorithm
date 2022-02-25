package com.jaewoo.algorithm.boj.graph.bipartite_graph.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class A9576 {
    static int T;
    static int N;
    static int M;

    static List<Integer> links[];
    static int[] matchResult;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            links = new ArrayList[M + 1];
            visit = new boolean[M + 1];
            matchResult = new int[M + 1];

            for (int i = 1, min, max; i <= M; i++) {
                links[i] = new ArrayList<>();
                st = new StringTokenizer(br.readLine());

                min = Integer.parseInt(st.nextToken());
                max = Integer.parseInt(st.nextToken());
                for (int j = 1; j <= N; j++) {
                    if (j >= min && j <= max) {
                        links[i].add(j);
                    }
                }
            }

            int count = 0;
            for (int i = 1; i <= N; i++) {
                Arrays.fill(visit, false);
                if (dfs(i)) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }

    private static boolean dfs(int x) {
        for (int y : links[x]) {
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

/* INPUT
1
2 3
1 2
1 2
1 2
 */
