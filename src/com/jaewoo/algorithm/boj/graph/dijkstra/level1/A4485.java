package com.jaewoo.algorithm.boj.graph.dijkstra.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A4485 {
    static int N;
    static int[][] maps;
    static int[][] resultMaps;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = 0;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            maps = new int[N + 1][N + 1];
            resultMaps = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1, w; j <= N; j++) {
                    w = Integer.parseInt(st.nextToken());
                    maps[i][j] = w;
                    resultMaps[i][j] = 10000;
                }
            }

            dijkstra();
            System.out.format("Problem %d: %d\n", ++testCase, resultMaps[N][N]);
        }
    }

    private static void dijkstra() {
        PriorityQueue<Point> q = new PriorityQueue<>();
        q.offer(new Point(1, 1, maps[1][1]));

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0, nextX, nextY; i < 4; i++) {
                nextX = p.x + dx[i];
                nextY = p.y + dy[i];

                // check out of box
                if (nextX < 1 || nextX > N || nextY < 1 || nextY > N) {
                    continue;
                }

                int nextWeight = maps[nextY][nextX] + p.w;
                if (resultMaps[nextY][nextX] > nextWeight) {
                    resultMaps[nextY][nextX] = nextWeight;
                    q.offer(new Point(nextX, nextY, resultMaps[nextY][nextX]));
                }
            }
        }
    }

    private static class Point implements Comparable<Point> {
        public int x;
        public int y;
        public int w;

        public Point(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Point point) {
            return this.w - point.w;
        }
    }
}

/* input
3
5 5 4
3 9 1
3 2 7
5
3 7 2 0 1
2 8 0 9 1
1 2 1 8 1
9 8 9 2 0
3 6 5 1 5
7
9 0 5 1 1 5 3
4 1 2 1 6 5 3
0 7 6 1 6 8 5
1 1 7 8 3 2 3
9 4 0 7 6 4 1
5 8 3 2 4 8 3
7 4 8 4 8 3 4
0
 */
