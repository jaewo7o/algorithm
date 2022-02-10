package com.jaewoo.algorithm.boj.graph.mst.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1626 {

    private static int V;
    private static int E;

    private static PriorityQueue<Edge> pq;

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        StringTokenizer token = new StringTokenizer(line);
        V = Integer.parseInt(token.nextToken());
        E = Integer.parseInt(token.nextToken());

        pq = new PriorityQueue<>();
        for (int i = 1, u, v, w; i <= E; i++) {
            token = new StringTokenizer(br.readLine());

            u = Integer.parseInt(token.nextToken());
            v = Integer.parseInt(token.nextToken());
            w = Integer.parseInt(token.nextToken());

            pq.offer(new Edge(u, v, w));
        }

        parents = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }

        int index = 1;
        int sum = 0;
        for (int i = 1; i <= E; i++) {
            Edge e = pq.poll();

            if (findParent(e.u) != findParent(e.v)) {
                index++;

                // 가장 최적의 해는 SKIP하고 두번째 가장 작은 Spanning Tree를 구한다.
                if (index == V - 1) {
                    continue;
                }

                union(e.u, e.v);
                sum += e.w;
            }
        }

        System.out.println(sum);
    }

    private static int findParent(int x) {
        if (parents[x] == x) {
            return x;
        }

        parents[x] = findParent(parents[x]);
        return parents[x];
    }

    private static void union(int u, int v) {
        int pu = findParent(u);
        int pv = findParent(v);

        if (pu < pv) {
            parents[pv] = pu;
        } else {
            parents[pu] = pv;
        }
    }

    public static class Edge implements Comparable<Edge> {
        public int u;
        public int v;
        public int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }


        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}


/* INPUT
7 12
1 2 8
1 3 5
2 3 10
2 4 2
2 5 18
3 4 3
3 6 16
4 5 12
4 6 30
4 7 14
5 7 4
6 7 26
 */
