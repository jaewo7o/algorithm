package com.jaewoo.algorithm.boj.graph.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1197 {
    static int V;
    static int E;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue();
        int s, e, w;
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(s, e, w));
        }

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        int result = 0;
        Edge edge;
        for (int i = 1; i <= E; i++) {
            edge = pq.poll();

            if (findParent(edge.s) != findParent(edge.e)) {
                result += edge.w;
                union(edge.s, edge.e);
            }
        }

        System.out.println(result);
    }

    private static void union(int s, int e) {
        s = findParent(s);
        e = findParent(e);

        if (s < e) {
            parent[e] = s;
        } else {
            parent[s] = e;
        }
    }

    private static int findParent(int s) {
        if (parent[s] == s) {
            return s;
        }

        parent[s] = findParent(parent[s]);
        return parent[s];
    }

    private static class Edge implements Comparable<Edge> {
        public int s;
        public int e;
        public int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.w - edge.w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "s=" + s +
                    ", e=" + e +
                    ", w=" + w +
                    '}';
        }
    }
}

/* INPUT
3 3
1 2 1
2 3 2
1 3 3
 */
