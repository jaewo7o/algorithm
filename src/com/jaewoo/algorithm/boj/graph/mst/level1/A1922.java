package com.jaewoo.algorithm.boj.graph.mst.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1922 {
    static int N;
    static int M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        PriorityQueue<Edge> pq = new PriorityQueue();
        int s, e, w;
        for (int i=1; i<=M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(s, e, w));
        }

        parents = new int[N + 1];
        for (int i=1; i<=N; i++) {
            parents[i] = i;
        }

        int sum = 0;
        int count = 0;
        Edge edge;
        while (count < N - 1) {
            edge = pq.poll();

            if (findParent(edge.s) != findParent(edge.e)) {
                sum += edge.w;
                union(edge.s, edge.e);
                count++;
            }
        }

        System.out.println(sum);
    }

    private static void union(int s, int e) {
        s = findParent(s);
        e = findParent(e);

        if (s > e) {
            parents[s] = e;
        } else {
            parents[e] = s;
        }
    }

    private static int findParent(int s) {
        if (s == parents[s]) {
            return s;
        }

        parents[s] = findParent(parents[s]);
        return parents[s];
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
        public String toString() {
            return "Edge{" +
                    "s=" + s +
                    ", e=" + e +
                    ", w=" + w +
                    '}';
        }

        @Override
        public int compareTo(Edge edge) {
            return this.w - edge.w;
        }
    }
}


/* INPUT
6
9
1 2 5
1 3 4
2 3 2
2 4 7
3 4 6
3 5 11
4 5 3
4 6 8
5 6 8
*/