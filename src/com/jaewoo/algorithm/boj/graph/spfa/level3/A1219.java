package com.jaewoo.algorithm.boj.graph.spfa.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1219 {

    /*
        백준 문제풀이 URL : https://www.acmicpc.net/problem/1219
     */

    static int N;
    static int M;

    static int[] cost;
    static List<Edge>[] links;
    static int[] earns;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cost = new int[N];
        earns = new int[N];

        links = new List[N];
        for (int i = 0; i < N; i++) {
            links[i] = new ArrayList();
        }

        for (int i = 0, u, v, w; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(u, v, w);
            links[u].add(edge);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            earns[i] = Integer.parseInt(st.nextToken());
        }

        boolean isCycle = spfa(s);

        if (cost[e] == Integer.MIN_VALUE) {
            System.out.println("gg");
        } else if (isCycle) {
            System.out.println("Gee");
        } else {
            System.out.println(cost[e]);
        }
    }

    private static boolean spfa(int start) {
        int[] visit = new int[N];
        Arrays.fill(cost, Integer.MIN_VALUE);

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visit[start]++;
        cost[start] = earns[start];

        int current, next, nextCost;
        while (!q.isEmpty()) {
            current = q.poll();
            for (Edge edge : links[current]) {
                next = edge.end;
                nextCost = cost[current] + (earns[next] - edge.cost);

                if (cost[next] < nextCost) {
                    cost[next] = nextCost;
                    q.offer(next);

                    if (++visit[next] >= N) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static class Edge {
        public int start;
        public int end;
        public int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}

/* input
5 0 4 7
0 1 13
1 2 17
2 4 20
0 3 22
1 3 4747
2 0 10
3 4 10
8 10 20 1 100000
 */
