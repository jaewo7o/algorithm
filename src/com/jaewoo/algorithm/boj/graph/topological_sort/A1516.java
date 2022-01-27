package com.jaewoo.algorithm.boj.graph.topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1516 {

    private static int N;

    private static List<Integer>[] links;
    private static int[] inDegrees;
    private static int[] times;
    private static int[] timeSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // initialize
        times = new int[N + 1];
        inDegrees = new int[N + 1];
        links = new List[N + 1];
        timeSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);

            int token = Integer.parseInt(st.nextToken());
            timeSum[i] = times[i] = token;

            links[i] = new ArrayList<>();
            while (true) {
                int target = Integer.parseInt(st.nextToken());
                if (target == -1) {
                    break;
                }

                links[target].add(i);
                inDegrees[i]++;
            }
        }

        topologySort();

        for (int i = 1; i <= N; i++) {
            System.out.println(timeSum[i]);
        }
    }

    private static void topologySort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegrees[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();
            for (int next : links[current]) {
                timeSum[next] = Math.max(timeSum[next], timeSum[current] + times[next]);
                if (--inDegrees[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}


/* INPUT
5
10 -1
10 1 -1
4 1 -1
4 3 1 -1
3 3 -1
 */
