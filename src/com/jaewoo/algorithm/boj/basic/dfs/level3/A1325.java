package com.jaewoo.algorithm.boj.basic.dfs.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A1325 {
    private static int N;
    private static int M;

    private static List<Integer>[] links;
    private static boolean[] visit;
    private static int[] visitCounts;
    private static int visitCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        StringTokenizer token = new StringTokenizer(line);
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        links = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        for (int i = 1, w, v; i <= M; i++) {
            line = br.readLine();
            token = new StringTokenizer(line);

            w = Integer.parseInt(token.nextToken());
            v = Integer.parseInt(token.nextToken());

            links[v].add(w);
        }

        int maxVisitCount = 0;
        visitCounts = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            visit = new boolean[N + 1];
            visitCount = 0;
            dfs(i);
            visitCounts[i] = visitCount;

            if (maxVisitCount < visitCounts[i]) {
                maxVisitCount = visitCounts[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (visitCounts[i] == maxVisitCount) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);
    }

    private static void dfs(int start) {
        visit[start] = true;
        visitCount++;

        for (int next : links[start]) {
            if (!visit[next]) {
                dfs(next);
            }
        }
    }
}


/* INPUT
5 4
3 1
3 2
4 3
5 3
 */
