package com.jaewoo.algorithm.boj.graph.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1261 {

    private static int[][] d;
    private static boolean[][] v;
    private static int[][] map;

    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        d = new int[M][N];
        v = new boolean[M][N];
        map = new int[M][N];

        for (int m=0; m<M; m++) {
            String line = br.readLine();
            for (int n=0; n<N; n++) {
                d[m][n] = Integer.MAX_VALUE;
                map[m][n] = Integer.parseInt(line.substring(n, n+1));
            }
        }

        dijkstra(map);

        System.out.println(d[M-1][N-1]);
    }

    private static void dijkstra(int[][] m) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0, 0));

        v[0][0] = true;

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();

            int nextX = currentEdge.getX();
            int nextY = currentEdge.getY();

            if (nextX == N && nextY == M) {
                return;
            }

            for (int k=1; k<=4; k++) {
                if (k == 1) {
                    nextX = currentEdge.getX();
                    nextY = currentEdge.getY() + 1;
                } else if (k == 2) {
                    nextX = currentEdge.getX() + 1;
                    nextY = currentEdge.getY();
                } else if (k == 3) {
                    nextX = currentEdge.getX();
                    nextY = currentEdge.getY() - 1;
                } else if (k == 4) {
                    nextX = currentEdge.getX() - 1;
                    nextY = currentEdge.getY();
                }

                if (nextX >=0 && nextY >=0 && nextX < M && nextY < N) {
                    if (!v[nextX][nextY]) {
                        d[nextX][nextY] = Math.min(d[nextX][nextY], currentEdge.getWeight() + map[nextX][nextY]);
                        v[nextX][nextY] = true;
                        pq.offer(new Edge(nextX, nextY, d[nextX][nextY]));
                    }
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        private int x;
        private int y;
        private int weight;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWeight() {
            return weight;
        }

        public Edge(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}

/*
6 6
001111
010000
001111
110001
011010
100010
 */