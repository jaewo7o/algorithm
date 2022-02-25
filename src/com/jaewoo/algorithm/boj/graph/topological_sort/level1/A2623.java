package com.jaewoo.algorithm.boj.graph.topological_sort.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A2623 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] links = new ArrayList[N + 1];
        int[] inDegrees = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
            inDegrees[i] = 0;
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int prev = 0, next = 0;
            for (int j = 1; j <= count; j++) {
                if (j == 1) {
                    prev = Integer.parseInt(st.nextToken());
                } else {
                    next = Integer.parseInt(st.nextToken());

                    links[prev].add(next);
                    inDegrees[next]++;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegrees[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll().intValue();

            System.out.println(current);

            for (int next : links[current]) {
                if (--inDegrees[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}

/*
6 3
3 1 4 3
4 6 2 5 4
2 2 3
 */
