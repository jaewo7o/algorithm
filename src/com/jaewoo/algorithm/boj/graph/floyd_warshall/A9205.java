package com.jaewoo.algorithm.boj.graph.floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class A9205 {
    private static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        List<Point> points;
        boolean[][] maps;

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            points = new ArrayList<>();
            maps = new boolean[N + 2][N + 2];
            for (int i = 0; i < N + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points.add(new Point(x, y));
            }

            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    if (i != j && getManhattanDistance(points.get(i), points.get(j)) <= 1000) {
                        maps[i][j] = maps[j][i] = true;
                    }
                }
            }

            floyd(maps, N);

            sb.append(maps[0][N + 1]? "happy" : "sad").append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void floyd(boolean[][] maps, int N) {
        for (int k = 0; k < N + 2; k++) {
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    if (maps[i][k] && maps[k][j]) {
                        maps[i][j] = true;
                    }
                }
            }
        }
    }

    private static int getManhattanDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

/*
2
2
0 0
1000 0
1000 1000
2000 1000
2
0 0
1000 0
2000 1000
2000 2000
 */