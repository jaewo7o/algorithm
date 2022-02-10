package com.jaewoo.algorithm.boj.graph.mst.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class A4386 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Point[] points = new Point[N + 1];
        for (int i=1; i <= N; i++) {
            String[] coordinate = br.readLine().split(" ");
            points[i] = new Point(Double.parseDouble(coordinate[0]), Double.parseDouble(coordinate[1]));
        }

        int[] parents = new int[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }

                pq.offer(new Edge(i, j, getDistance(points[i], points[j])));
            }

            // 부모노드 초기화
            parents[i] = i;
        }

        double sum = 0;
        int count = 0;
        while (count < N - 1) {
            Edge edge = pq.poll();
            if (!isSameParent(parents, edge.i, edge.j)) {
                sum += edge.distance;
                count++;
                union(parents, edge.i, edge.j);
            }
        }

        System.out.println(String.format("%.2f", sum));
    }

    private static void union(int[] parents, int i, int j) {
        int parent1 = findParent(parents, i);
        int parent2 = findParent(parents, j);

        if (parent1 < parent2) {
            parents[j] = parent1;
        } else {
            parents[i] = parent2;
        }
    }

    private static boolean isSameParent(int[] parents, int i, int j) {
        int parent1 = findParent(parents, i);
        int parent2 = findParent(parents, j);

        return parent1 == parent2;
    }

    private static int findParent(int[] parents, int i) {
        if (parents[i] == i) {
            return i;
        }

        parents[i] = findParent(parents, parents[i]);
        return parents[i];
    }

    private static double getDistance(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2));
    }

    public static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Edge implements Comparable<Edge> {
        private int i;
        private int j;
        private double distance;

        public Edge(int i, int j, double distance) {
            this.i = i;
            this.j = j;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "i=" + i +
                    ", j=" + j +
                    ", distance=" + distance +
                    '}';
        }

        @Override
        public int compareTo(Edge e) {
            return (int) (this.distance - e.distance);
        }
    }
}


/*
3
1.0 1.0
2.0 2.0
2.0 4.0
 */