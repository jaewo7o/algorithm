package com.jaewoo.algorithm.boj.basic.bfs.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1261 {

    /*
        BFS라기 보다는 다익스트라 알고리즘에 더 맞을 것 같음
     */

    private static int[][] d;
    private static boolean[][] isVisit;
    private static int[][] map;

    private static int N;
    private static int M;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        d = new int[M][N];
        isVisit = new boolean[M][N];
        map = new int[M][N];

        for (int m = 0; m < M; m++) {
            String line = br.readLine();
            for (int n = 0; n < N; n++) {
                d[m][n] = Integer.MAX_VALUE;
                map[m][n] = Integer.parseInt(line.substring(n, n + 1));
            }
        }

        bfs();

        System.out.println(d[M - 1][N - 1]);
    }

    private static void bfs() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (isVisit[current.x][current.y]) {
                continue;
            }
            isVisit[current.x][current.y] = true;

            for (int k = 0, nextX, nextY, nextDist; k < 4; k++) {
                nextX = current.x + dx[k];
                nextY = current.y + dy[k];

                // out of box 인 경우 skip
                if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
                    continue;
                }

                // 미방문 노드인 경우 거리 업데이트
                nextDist = current.d + map[nextX][nextY];
                if (!isVisit[nextX][nextY] && d[nextX][nextY] > nextDist) {
                    d[nextX][nextY] = nextDist;
                    pq.offer(new Edge(nextX, nextY, d[nextX][nextY]));
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        public int x;
        public int y;
        public int d;

        public Edge(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Edge o) {
            return this.d - o.d;
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

/* output
2
 */
