package com.jaewoo.algorithm.boj.basic.bfs.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1325 {
    private static int N;
    private static int M;

    private static List<Integer>[] links;
    private static boolean[] visit;
    private static int[] visitCounts;

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
            visitCounts[i] = bfs(i);

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

    private static int bfs(int start) {
        int visitCount = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : links[current]) {
                if (!visit[next]) {
                    visit[next] = true;
                    q.offer(next);
                    visitCount++;
                }
            }
        }

        return visitCount;
    }
}


/* INPUT
5 4
3 1
3 2
4 3
5 3
 */
