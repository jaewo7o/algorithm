package com.jaewoo.algorithm.boj.graph.maximum_flow.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A2316 {
    /*
        백준문제 URL : https://www.acmicpc.net/problem/2316
     */

    private static int N;
    private static int P;
    private static List<Integer>[] links;
    private static int[][] flow;
    private static int[] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        P = Integer.parseInt(token.nextToken());

        links = new List[N + 1];
        flow = new int[N + 1][N + 1];
        prev = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        for (int i = 1, s, e; i <= P; i++) {
            String line = br.readLine();
            token = new StringTokenizer(line);

            s = Integer.parseInt(token.nextToken());
            e = Integer.parseInt(token.nextToken());

            links[s].add(e);
            links[e].add(s);
        }

        int maxFlow = maxFlow(1, 2);

        System.out.println(maxFlow);
    }

    private static int maxFlow(int start, int end) {
        int result = 0;
        while (true) {
            Arrays.fill(prev, -1);

            Queue<Integer> q = new LinkedList();
            q.offer(start);

            while (!q.isEmpty()) {
                int current = q.poll();
                for (int next : links[current]) {
                    if (flow[current][next] < 1 && prev[next] == -1) {
                        q.offer(next);
                        prev[next] = current;

                        if (next == end) {
                            break;
                        }
                    }
                }
            }

            if (prev[end] == -1) {
                break;
            }

            int tempFlow = Integer.MAX_VALUE;
            for (int i = end; i != start; i = prev[i] ) {
                tempFlow = Math.min(tempFlow, 1 - flow[prev[i]][i]);
            }

            for (int i = end; i != start; i = prev[i] ) {
                flow[prev[i]][i] += tempFlow;
                flow[i][prev[i]] -= tempFlow;
            }

            result += tempFlow;
        }

        return result;
    }
}

/* INPUT
5 5
1 3
3 2
1 5
5 4
4 2
 */
