package com.jaewoo.algorithm.boj.graph.bellman_ford.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A1219 {

    static int N;
    static int M;
    static int S;
    static int E;

    static int[] dist;
    static Edge[] edges;
    static int[] earns;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N];
        earns = new int[N];

        edges = new Edge[M];

        for (int i = 0, u, v, w; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            Edge edge = new Edge(u, v, -w);
            edges[i] = edge;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            earns[i] = Integer.parseInt(st.nextToken());
        }

        boolean isCycle = bellmanFord();

        if (dist[E] == Integer.MIN_VALUE) {
            System.out.println("gg");
        } else if (isCycle) {
            System.out.println("Gee");
        } else {
            System.out.println(dist[E]);
        }
    }

    private static boolean bellmanFord() {
        Arrays.fill(dist, Integer.MIN_VALUE);

        dist[0] = earns[0];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = edges[j];

                int s = edge.s;
                int e = edge.e;

                // Relaxation 불필요
                if (dist[s] == Integer.MIN_VALUE) {
                    continue;
                } else if (dist[s] == Integer.MAX_VALUE) {
                    dist[e] = Integer.MAX_VALUE;
                } else if (dist[e] < dist[s] + edge.c + earns[e]) {
                    dist[e] = dist[s] + edge.c + earns[e];

                    if (i == N) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static class Edge {
        public int s;
        public int e;
        public int c;

        public Edge(int start, int end, int cost) {
            this.s = start;
            this.e = end;
            this.c = cost;
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
