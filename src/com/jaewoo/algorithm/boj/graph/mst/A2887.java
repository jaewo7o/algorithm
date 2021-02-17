package com.jaewoo.algorithm.boj.graph.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A2887 {
    static int N;
    static Pos[] pos;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pos = new Pos[N + 1];
        parents = new int[N + 1];

        for (int i=1, x, y, z; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());

            pos[i] = new Pos(x, y, z);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        for (int i=1; i<=N; i++) {
            Pos p1 = pos[i];

            for (int j=1; j<=N; j++) {
                if (i == j) {
                    continue;
                }

                Pos p2 = pos[j];

                int dist = getMinDist(Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y), Math.abs(p1.z - p2.z));
                pq.offer(new Edge(i, j, dist));
            }
        }

        for (int i=1; i<=N; i++) {
            parents[i] = i;
        }

//        int count = 0;
        int weight = 0;
        while ( !pq.isEmpty() ) {
            Edge edge = pq.poll();
            if (!isSameParent(edge.start, edge.end)) {
                unionParent(edge.start, edge.end);
//                count++;
                weight += edge.weight;
            }
        }

        System.out.println(weight);
    }

    private static void unionParent(int start, int end) {
        int p1 = getParent(start);
        int p2 = getParent(end);

        if (p1 > p2) {
            parents[p1] = p2;
        } else {
            parents[p2] = p1;
        }
    }

    private static boolean isSameParent(int start, int end) {
        int p1 = getParent(start);
        int p2 = getParent(end);

        if (p1 == p2) {
            return true;
        } else {
            return false;
        }
    }

    private static int getParent(int start) {
        if (parents[start] == start) {
            return start;
        } else {
            return parents[start] = getParent(parents[start]);
        }
    }

    private static int getMinDist(int dist1, int dist2, int dist3) {
        int minDist = Math.min(dist1, dist2);
        return Math.min(minDist, dist3);
    }


    private static class Pos {
        public int x;
        public int y;
        public int z;

        public Pos(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static class Edge implements Comparable<Edge>{
        public int start;
        public int end;
        public int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
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
5
11 -15 -15
14 -5 -15
-1 -1 -5
10 -4 -1
19 -4 19
 */
