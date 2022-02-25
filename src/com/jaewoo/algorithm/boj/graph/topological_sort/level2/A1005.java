package com.jaewoo.algorithm.boj.graph.topological_sort.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1005 {

    static int T;
    static int N;
    static int K;
    static int D;

    static int[] indegree;
    static int[] delay;

    static List<Integer>[] links;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            indegree = new int[N + 1];
            delay = new int[N + 1];
            links = new List[N + 1];
            int[] sum = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i=1, x; i<=N; i++) {
                x = Integer.parseInt(st.nextToken());
                delay[i] = x;
                sum[i] = x;
                links[i] = new ArrayList();
            }

            for (int k=1, s, e; k<=K; k++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());

                links[s].add(e);
                indegree[e]++;
            }

            D = Integer.parseInt(br.readLine());

            Queue<Integer> q = new LinkedList();
            for (int i=1; i<=N; i++) {
                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }


            while (!q.isEmpty()) {
                int now = q.poll();

                for (int next : links[now]) {
                    sum[next] = Math.max(sum[next], sum[now] + delay[next]);
                    if (--indegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }

            System.out.println(sum[D]);
        }

    }
}

/* INPUT
2
4 4
10 1 100 10
1 2
1 3
2 4
3 4
4
8 8
10 20 1 5 8 7 1 43
1 2
1 3
2 4
2 5
3 6
5 7
6 7
7 8
7
 */
