package com.jaewoo.algorithm.boj.graph.bellman_ford.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class A1738 {

    static int N;
    static int M;

    static List<Edge>[] edges;
    static int[] gold;
    static Queue<Integer> path;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new List[N + 1];
        for (int n=1; n<=N; n++) {
            edges[n] = new ArrayList<>();
        }

        gold = new int[N + 1];

        for (int m=1, u, v, w; m<=M; m++) {
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            edges[u].add(new Edge(v, w));
        }

        boolean cycle = bellmanFord(1);

        if (cycle) {
            System.out.println("-1");
        } else {
            String pathString = path.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            System.out.println(pathString);
        }
    }

    private static boolean bellmanFord(int start) {
        Arrays.fill(gold, Integer.MIN_VALUE);
        path = new LinkedList<>();
        gold[start] = 0;
        path.offer(start);

        int next, nextGold;
        for (int i = 1; i <= N; i++) {
            for (Edge edge : edges[i]) {
                nextGold = gold[i] + edge.w;
                next = edge.v;

                if (gold[next] < nextGold) {
                    gold[next] = nextGold;

                    if (!path.contains(next)) {
                        path.offer(next);
                    }

                    if (i == N) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static class Edge {
        public int v;
        public int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}

/* input
5 7
1 2 3
1 3 4
3 1 -7
2 3 2
3 4 1
4 2 -5
4 5 1
 */
