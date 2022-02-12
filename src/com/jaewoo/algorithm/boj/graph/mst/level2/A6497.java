package com.jaewoo.algorithm.boj.graph.mst.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A6497 {

    private static int M;
    private static int N;

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();

            StringTokenizer token = new StringTokenizer(line);
            M = Integer.parseInt(token.nextToken());
            N = Integer.parseInt(token.nextToken());

            if (M == 0 && N == 0) {
                break;
            }

            int totalCost = 0;
            PriorityQueue<Edge> pq = new PriorityQueue();
            for (int i = 1, s, e, c; i <= N; i++) {
                line = br.readLine();
                token = new StringTokenizer(line);

                s = Integer.parseInt(token.nextToken());
                e = Integer.parseInt(token.nextToken());
                c = Integer.parseInt(token.nextToken());

                pq.offer(new Edge(s, e, c));
                totalCost += c;
            }

            parent = new int[M];
            for (int i = 0; i < M; i++) {
                parent[i] = i;
            }

            int minimumCost = mst(pq);

            System.out.println(totalCost - minimumCost);
        }
    }

    private static int mst(PriorityQueue<Edge> pq) {
        Edge edge;
        int minimumCost = 0;
        for (int i = 0; i < N; i++) {
            edge = pq.poll();

            if (findParent(edge.s) != findParent(edge.e)) {
                union(edge.s, edge.e);
                minimumCost += edge.c;
            }
        }

        return minimumCost;
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

        return findParent(parent[s]);
    }

    public static class Edge implements Comparable<Edge> {
        public int s;
        public int e;
        public int c;

        public Edge(int s, int e, int c) {
            this.s = s;
            this.e = e;
            this.c = c;
        }


        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "s=" + s +
                    ", e=" + e +
                    ", c=" + c +
                    '}';
        }
    }
}

/* INPUT
7 11
0 1 7
0 3 5
1 2 8
1 3 9
1 4 7
2 4 5
3 4 15
3 5 6
4 5 8
4 6 9
5 6 11
0 0
 */
