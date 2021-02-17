package com.jaewoo.algorithm.boj.graph.dijkstra;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1238 {

    /**
     * Floyd_warshall or dijkstra로 문제 풀이 가능
     */

    static int N;
    static int M;
    static int X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        int [] dist = new int[N + 1];
        int [] revDist = new int[N + 1];
        List<Edge>[] linkEdges = new ArrayList[N + 1];
        List<Edge>[] revLinkEdges = new ArrayList[N + 1];
        for (int i=1; i<=N; i++) {
            dist[i] = Integer.MAX_VALUE;
            revDist[i] = Integer.MAX_VALUE;
            linkEdges[i] = new ArrayList<>();
            revLinkEdges[i] = new ArrayList<>();
        }

        int s, e, w;
        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            linkEdges[s].add(new Edge(e, w));
            revLinkEdges[e].add(new Edge(s, w));
        }

        dijkstra(linkEdges, dist, X);
        dijkstra(revLinkEdges, revDist, X);

        int maxDist = 0;
        for (int i=1; i<=N; i++) {
            maxDist = Math.max(maxDist, dist[i] + revDist[i]);
        }

        bw.write(maxDist + "\n");

        bw.flush();
        bw.close();
    }

    private static void dijkstra(List<Edge>[] linkEdges, int[] dist, int start) {
        boolean[] visit = new boolean[N + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        int now, next;
        while (!pq.isEmpty()) {
            now = pq.poll().end;

            if (visit[now]) {
                continue;
            }

            visit[now] = true;
            for (Edge edge : linkEdges[now]) {
                next = edge.end;
                if (!visit[next]) {
                    dist[next] = Math.min(dist[next], dist[now] + edge.weight);
                    pq.offer(new Edge(next, dist[next]));
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        public int end;
        public int weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }
}


/* INPUT
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3
*/