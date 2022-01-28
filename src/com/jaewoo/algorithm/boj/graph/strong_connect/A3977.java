package com.jaewoo.algorithm.boj.graph.strong_connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A3977 {
    private static int T;
    private static int N;
    private static int M;
    private static List<Integer>[] links;
    private static int[] parents;
    private static boolean[] isFinished;
    private static int[] group;
    private static int id;
    private static Stack<Integer> stack;
    private static List<List<Integer>> strongConnectedList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            id = 0;

            String line = br.readLine();
            StringTokenizer token = new StringTokenizer(line);
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());

            links = new ArrayList[N + 1];
            for (int i = 0; i < N; i++) {
                links[i] = new ArrayList<>();
            }

            for (int i = 1, s, e; i <= M; i++) {
                line = br.readLine();
                token = new StringTokenizer(line);
                s = Integer.parseInt(token.nextToken());
                e = Integer.parseInt(token.nextToken());

                links[s].add(e);
            }

            parents = new int[N + 1];
            isFinished = new boolean[N + 1];
            group = new int[N + 1];
            stack = new Stack<>();
            strongConnectedList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (!isFinished[i]) {
                    dfs(i);
                }
            }

            int sccSize = strongConnectedList.size();
            int[] inDegree = new int[sccSize + 1];
            for (int i = 0; i < N; i++) {
                for (int j : links[i]) {
                    if (group[i] != group[j]) {
                        inDegree[group[j]]++;
                    }
                }
            }

            int count = 0;
            int groupIndex = 0;
            for (int i = 1; i <= sccSize; i++) {
                if (inDegree[i] == 0) {
                    count++;
                    groupIndex = i;
                }
            }

            if (count == 1) {
                for (int i = 0; i < N; i++) {
                    if (group[i] == groupIndex) {
                        System.out.println(i);
                    }
                }
            } else {
                System.out.println("Confused");
            }

            br.readLine();
        }
    }

    private static int dfs(int current) {
        parents[current] = id++;
        int parent = parents[current];
        stack.add(current);

        for (int next : links[current]) {
            if (parents[next] == 0) {
                parent = Math.min(parent, dfs(next));
            } else if (!isFinished[next]) {
                parent = Math.min(parent, parents[next]);
            }
        }

        if (parents[current] == parent) {
            List<Integer> scc = new ArrayList();
            while (!stack.isEmpty()) {
                int node = stack.pop();
                isFinished[node] = true;
                scc.add(node);
                group[node] = strongConnectedList.size() + 1;
            }

            scc.sort(Comparator.naturalOrder());
            strongConnectedList.add(scc);
        }

        return parent;
    }
}

/* input
2
4 4
0 1
1 2
2 0
2 3

4 4
0 3
1 0
2 0
2 3
 */
