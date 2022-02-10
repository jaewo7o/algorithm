package com.jaewoo.algorithm.boj.graph.mst.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1647 {
    static int N;
    static int M;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<Route> pq = new PriorityQueue();
        for (int i=1, s, e, w; i<=M; i++) {
            st = new StringTokenizer(br.readLine());

            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            pq.add(new Route(s, e, w));
        }

        parent = new int[N + 1];
        for (int i=1; i<=N; i++) {
            parent[i] = i;
        }

        int sum = 0;
        int count = 0;
        // NODE 1개 떨어트리기 위해서는 N-2개 ROUTE가 필요
        while ( count < N - 2 ) {
            Route r = pq.poll();
            if (!isSameParent(r.s, r.e)) {
                unionParent(r.s, r.e);
                sum += r.w;
                count++;
            }
        }

        System.out.println(sum);
    }

    private static void unionParent(int s, int e) {
        s = getParent(s);
        e = getParent(e);

        if (s == e) {
            return;
        }

        if (s > e) {
            parent[e] = s;
        } else {
            parent[s] = e;
        }
    }

    private static boolean isSameParent(int s, int e) {
        if (getParent(s) == getParent(e)) {
            return true;
        } else {
            return false;
        }
    }

    private static int getParent(int s) {
        if (parent[s] == s) {
            return s;
        } else {
            return parent[s] = getParent(parent[s]);
        }
    }

    private static class Route implements Comparable<Route> {
        public int s;
        public int e;
        public int w;

        public Route(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Route route) {
            return this.w - route.w;
        }
    }
}

/* INPUT
7 12
1 2 3
1 3 2
3 2 1
2 5 2
3 4 4
7 3 6
5 1 5
1 6 2
6 4 1
6 5 3
4 5 3
6 7 4
 */