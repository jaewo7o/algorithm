package com.jaewoo.algorithm.boj.basic.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A9205 {
    private static int T;
    private static int N;
    private static List<Integer>[] links;
    private static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        List<Point> points;

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            points = new ArrayList<>();
            links = new ArrayList[N + 3];
            isVisit = new boolean[N + 3];
            for (int i = 1; i <= N + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points.add(new Point(x, y));

                links[i] = new ArrayList<>();
            }

            for (int i = 1; i <= N + 2; i++) {
                for (int j = 1; j <= N + 2; j++) {
                    if (i != j && getManhattanDistance(points.get(i-1), points.get(j-1)) <= 1000) {
                        links[i].add(j);
                    }
                }
            }

            bfs(1);

            sb.append(isVisit[N + 2]? "happy" : "sad").append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        isVisit[start] = true;

        while (!q.isEmpty()) {
            int current = q.poll();
            for (Integer next : links[current]) {
                if (!isVisit[next]) {
                    isVisit[next] = true;
                    q.offer(next);
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
