package com.jaewoo.algorithm.boj.graph.strong_connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A4196 {

    private static List<Integer>[] links;
    private static boolean[] isFinished;
    private static int[] parents;
    private static int[] groups;
    private static int[] inDegrees;
    private static int id;
    private static Stack<Integer> stack;
    private static List<List<Integer>> scces;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            links = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                links[i] = new ArrayList<>();
            }

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                links[s].add(e);
            }

            id = 0;
            parents = new int[N + 1];
            groups = new int[N + 1];
            inDegrees = new int[N + 1];
            isFinished = new boolean[N + 1];
            stack = new Stack();
            scces = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (!isFinished[i]) {
                    dfs(i);
                }
            }

            for (int i = 1; i <= N; i++) {
                for (int j : links[i]) {
                    if (groups[i] != groups[j]) {
                        inDegrees[groups[j]]++;
                    }
                }
            }

            int count = 0;
            for (int i = 1; i <= scces.size(); i++) {
                if (inDegrees[i] == 0) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }

    private static int dfs(int current) {
        parents[current] = id++;
        int p = parents[current];

        stack.add(current);

        for (int next : links[current]) {
            if (parents[next] == 0) {
                p = Math.min(p, dfs(next));
            } else if (!isFinished[next]) {
                p = Math.min(p, parents[next]);
            }
        }

        if (p == parents[current]) {
            List<Integer> scc = new ArrayList<>();
            while (true) {
                Integer node = stack.pop();
                isFinished[node] = true;
                scc.add(node);

                groups[node] = scces.size() + 1;

                if (node == current) {
                    break;
                }
            }

            scces.add(scc);
        }

        return p;
    }
}

/*
1
3 2
1 2
2 3
 */