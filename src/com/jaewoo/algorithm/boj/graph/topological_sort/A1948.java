package com.jaewoo.algorithm.boj.graph.topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1948 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Edge>[] links = new List[N + 1];
        List<Edge>[] reverseLinks = new List[N + 1];
        int[] inDegrees = new int[N + 1];
        int[] result = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
            reverseLinks[i] = new ArrayList<>();
            inDegrees[i] = 0;
            result[i] = 0;
        }

        StringTokenizer st;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            links[s].add(new Edge(e, t));
            reverseLinks[e].add(new Edge(s, t));
            inDegrees[e]++;
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int now = q.poll().intValue();

            for (Edge e : links[now]) {
                result[e.node] = Math.max(result[e.node], result[now] + e.time);
                if (--inDegrees[e.node] == 0) {
                    q.offer(e.node);
                }
            }
        }

        q = new LinkedList<>();
        q.offer(end);

        boolean[] isVisit = new boolean[N + 1];
        int roadNumber = 0;
        while (!q.isEmpty()) {
            int now = q.poll().intValue();

            for (Edge e : reverseLinks[now]) {
                if (result[now] - e.time == result[e.node]) {
                    roadNumber++;
                    if (!isVisit[e.node]) {
                        q.offer(e.node);
                        isVisit[e.node] = true;
                    }
                }
            }
        }

        System.out.println(result[end]);
        System.out.println(roadNumber);
    }

    public static class Edge {
        int node;
        int time;

        public Edge(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }
}

/*
7
9
1 2 4
1 3 2
1 4 3
2 6 3
2 7 5
3 5 1
4 6 4
5 6 2
6 7 5
1 7
 */