package com.jaewoo.algorithm.boj.graph.maximum_flow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A17412 {

    private static int N;
    private static int P;
    private static List<Integer>[] links;
    private static int[][] flow;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());

        N = Integer.parseInt(token.nextToken());
        P = Integer.parseInt(token.nextToken());

        links = new List[N + 1];
        flow = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        for (int i = 1, s, e; i <= P; i++) {
            String line = br.readLine();
            token = new StringTokenizer(line);

            s = Integer.parseInt(token.nextToken());
            e = Integer.parseInt(token.nextToken());

            links[s].add(e);
        }

        int maxFlow = maxFlow(1, 2);

        System.out.println(maxFlow);
    }

    private static int maxFlow(int start, int end) {
        int result = 0;

        while (true) {
            int[] path = new int[N + 1];
            Queue<Integer> q = new LinkedList<>();
            q.offer(start);

            while (!q.isEmpty()) {
                int current = q.poll();
                for (int next : links[current]) {
                    if (flow[current][next] < 1 && path[next] == 0) {
                        q.offer(next);
                        path[next] = current; // 경로를 기록
                        //flow[current][next] = 1;

                        // 도착점에 도달하면 종료처리
                        if (next == end) {
                            break;
                        }
                    }
                }
            }

            // 더이상 도착점에 도달하는 경우가 없으면 종료처리
            if (path[end] == 0) {
                break;
            }

            // 거꾸로 최소유량을 탐색한다.
            int tempFlow = Integer.MAX_VALUE;
            for (int i = end; i != start; i = path[i]) {
                tempFlow = Math.min(tempFlow, 1 - flow[path[i]][i]);
            }

            // 최소유량 만큼 추가
            for (int i = end; i != start; i = path[i]) {
                flow[path[i]][i] += tempFlow;
                flow[i][path[i]] -= tempFlow;
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
