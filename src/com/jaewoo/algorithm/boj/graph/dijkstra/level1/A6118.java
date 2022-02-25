package com.jaewoo.algorithm.boj.graph.dijkstra.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A6118 {
    static int N;
    static int M;

    static int[] dist;
    static List<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        edges = new List[N + 1];
        for (int i=1; i<=N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int m=1, u, v; m<=M; m++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            edges[u].add(new Edge(v, 1));
            edges[v].add(new Edge(u, 1));
        }

        Arrays.fill(dist, Integer.MAX_VALUE);

        dijkstra(1);

        int minNode = 0, maxDist = 0, count = 0;
        for (int i=1; i<=N; i++) {
            if (maxDist < dist[i]) {
                maxDist = dist[i];
                minNode = i;
                count = 1;
            } else if (maxDist == dist[i]) {
                count++;
            }
        }

        System.out.format("%d %d %d", minNode, maxDist, count);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue();
        pq.offer(new Edge(start, 0));

        dist[start] = 0;
        int now;
        int next, nextDist;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            now = edge.e;

            for (Edge nextEdge : edges[now]) {
                next = nextEdge.e;
                nextDist = dist[now] + 1;
                if (dist[next] > nextDist) {
                    dist[next] = nextDist;
                    pq.offer(new Edge(next, nextDist));
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        public int e;
        public int d;

        public Edge(int e, int d) {
            this.e = e;
            this.d = d;
        }

        @Override
        public int compareTo(Edge o) {
            return this.d - o.d;
        }
    }
}

/* input
6 7
3 6
4 3
3 2
1 3
1 2
2 4
5 2
 */
