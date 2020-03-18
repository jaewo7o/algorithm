package com.jaewoo.algorithm.boj.mcmf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A11408 {

    static int N;
    static int M;

    static List<WorkEdge>[] workEdges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        workEdges = new List[N + 1];

        for (int i=1; i<=N; i++) {
            workEdges[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            while (m-- > 0) {
                int work = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                workEdges[i].add(new WorkEdge(work, cost));
            }
        }

        while (true) {
            Queue<Integer> q = new LinkedList<>();
           // q.
        }
    }

    public static class WorkEdge {
        public int work;
        public int cost;

        public WorkEdge(int work, int cost) {
            this.work = work;
            this.cost = cost;
        }
    }
}

/* input
5 5
2 1 3 2 2
1 1 5
2 2 1 3 7
3 3 9 4 9 5 9
1 1 0
 */