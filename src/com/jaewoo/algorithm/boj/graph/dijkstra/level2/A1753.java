package com.jaewoo.algorithm.boj.graph.dijkstra.level2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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

        dist = new int[V + 1];
        List<Edge>[] linkEdges = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            dist[i] = Integer.MAX_VALUE;
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
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        int now, next, nextDistance;
        while (!pq.isEmpty()) {
            now = pq.poll().getEnd();

            for (Edge nextEdge : linkEdges[now]) {
                next = nextEdge.getEnd();
                nextDistance = dist[now] + nextEdge.getWeight();
                if (nextDistance < dist[next]) {
                    dist[next] = nextDistance;
                    pq.offer(nextEdge);
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        int end;
        int weight;

        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        public int getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }

        public int compareTo(Edge o) {
            return this.getWeight() - o.getWeight();
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
