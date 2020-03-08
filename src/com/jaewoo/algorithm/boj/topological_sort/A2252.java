package com.jaewoo.algorithm.boj.topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A2252 {

    static int N;
    static int M;
    static List<Integer>[] links;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N + 1];
        links = new List[N + 1];
        for (int i=1; i<=N; i++) {
            links[i] = new ArrayList();
        }


        for (int i=1, s, e; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            links[s].add(e);

            indegree[e]++;
        }

        Queue<Integer> q = new LinkedList();
        for (int i=1; i<=N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            System.out.println(now);

            for (int next : links[now]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}

/* INPUT
3 2
1 3
2 3
 */