package com.jaewoo.algorithm.boj.graph.strong_connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A2150 {

    private static int[] parent;
    private static boolean[] isFinished;
    private static int id = 0;
    private static List<Integer>[] links;
    private static List<List<Integer>> scces;
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        links = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            links[i] = new ArrayList<>();
        }

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            links[s].add(e);
        }

        parent = new int[V + 1];
        isFinished = new boolean[V + 1];
        scces = new ArrayList<>();
        for (int i = 1; i <= V; i++) {
            if (parent[i] == 0) {
                dfs(i);
            }
        }

        System.out.println(scces.size());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scces.size(); i++) {
            List<Integer> scc = scces.get(i);
            for (int j = 0; j < scc.size(); j++) {
                sb.append(scc.get(j)).append(" ");
            }

            sb.append("-1").append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int dfs(int x) {
        parent[x] = ++id;
        stack.add(x);

        int p = parent[x];
        for (int next : links[x]) {
            if (parent[next] == 0) { // 아직 미방문이면 탐색시작
                p = Math.min(p, dfs(next));
            } else if (!isFinished[next]) { // 방문은 했으나 scc가 정해지지 않았다면 --> 자신과, 다음 노드 중 작은 부모값을 가짐
                p = Math.min(p, parent[next]);
            }
        }

        if (p == parent[x]) {
            List<Integer> scc = new ArrayList<>();
            while (true) {
                int node = stack.pop();
                isFinished[node] = true;
                scc.add(node);
                if (node == x) {
                    break;
                }
            }

            scc.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            });

            scces.add(scc);
        }

        return p;
    }
}

/*
7 9
1 4
4 5
5 1
1 6
6 7
2 7
7 3
3 7
7 2
 */