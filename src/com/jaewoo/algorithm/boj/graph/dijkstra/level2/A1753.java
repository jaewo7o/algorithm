package com.jaewoo.algorithm.boj.graph.dijkstra.level2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class A1753 {
    static int V;
    static int E;
    static int S;

    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        S = Integer.parseInt(br.readLine());

        List<Edge>[] linkEdges = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            linkEdges[i] = new ArrayList<>();
        }

        int s, e, w;
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());

            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            linkEdges[s].add(new Edge(e, w));
        }

        dijkstra(linkEdges, S);

        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                bw.write("INF\n");
            } else {
                bw.write(dist[i] + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static void dijkstra(List<Edge>[] linkEdges, int start) {
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean[] isVisit = new boolean[V + 1];

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
            for (Edge nextEdge : linkEdges[now]) {
                next = nextEdge.end;
                nextDist = dist[now] + nextEdge.dist;
                if (!isVisit[next] && nextDist < dist[next]) {
                    dist[next] = nextDist;
                    pq.offer(new Edge(next, nextDist));
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        int end;
        int dist;

        public Edge(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}

/* INPUT VALUE
5 6
1
5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6
 */
