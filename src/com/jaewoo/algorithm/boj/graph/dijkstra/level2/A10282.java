package com.jaewoo.algorithm.boj.graph.dijkstra.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A10282 {
    private static int T;
    private static int N;
    private static int D;
    private static int C;

    private static List<Edge>[] links;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String line = br.readLine();
            StringTokenizer token = new StringTokenizer(line);
            N = Integer.parseInt(token.nextToken());
            D = Integer.parseInt(token.nextToken());
            C = Integer.parseInt(token.nextToken());

            links = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                links[i] = new ArrayList<>();
            }

            for (int i = 1, s, e, t; i <= D; i++) {
                line = br.readLine();
                token = new StringTokenizer(line);
                e = Integer.parseInt(token.nextToken());
                s = Integer.parseInt(token.nextToken());
                t = Integer.parseInt(token.nextToken());

                links[s].add(new Edge(e, t));
            }

            dijkstra(C);
        }
    }

    private static void dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean[] isVisit = new boolean[N + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        int now, next, nextDist;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            now = edge.end;
            if (isVisit[now]) {
                continue;
            }

            isVisit[now] = true;

            for (Edge nextEdge : links[now]) {
                next = nextEdge.end;
                nextDist = dist[now] + nextEdge.time;
                if (!isVisit[next] && dist[next] > nextDist) {
                    dist[next] = nextDist;
                    pq.offer(new Edge(next, nextDist));
                }
            }
        }

        int maxDist = 0;
        int nodes = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                continue;
            }

            if (dist[i] > maxDist) {
                maxDist = dist[i];
            }

            nodes++;
        }

        System.out.println(nodes + " " + maxDist);
    }

    private static class Edge implements Comparable<Edge> {
        public int end;
        public int time;

        public Edge(int end, int time) {
            this.end = end;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            return this.time - o.time;
        }
    }
}

/* INPUT
2
3 2 2
2 1 5
3 2 5
3 3 1
2 1 2
3 1 8
3 2 4
 */
