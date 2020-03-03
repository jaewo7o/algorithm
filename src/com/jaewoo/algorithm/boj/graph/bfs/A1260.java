package com.jaewoo.algorithm.boj.graph.bfs;

import java.io.*;
import java.util.*;

public class A1260 {
    static int N;
    static int M;
    static int V;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        List<Integer>[] links = new ArrayList[N + 1];
        for (int i=1; i<=N; i++) {
            links[i] = new ArrayList();
        }

        int s, e;
        for (int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            links[s].add(Integer.valueOf(e));
            links[e].add(Integer.valueOf(s));
        }

        for (int i=1; i<N; i++) {
            Collections.sort(links[i]);
        }

        visit = new boolean[N + 1];
        dfs(links, V, bw);

        bw.write("\n");

        visit = new boolean[N + 1];
        bfs(links, V, bw);

        bw.flush();
        bw.close();
    }

    private static void bfs(List<Integer>[] links, int start, BufferedWriter bw) throws IOException {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visit[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            bw.write(now + " ");

            for (int next : links[now]) {
                if (!visit[next]) {
                    visit[next] = true;

                    queue.add(next);
                }
            }
        }
    }

    private static void dfs(List<Integer>[] links, int start, BufferedWriter bw) throws IOException {
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.valueOf(start));

        bw.write(start + " ");
        visit[start] = true;

        boolean flag;
        while (!stack.isEmpty()) {
            int now = stack.peek();
            flag = false;

            for (int next : links[now]) {
                if (!visit[next]) {
                    bw.write(next + " ");
                    visit[next] = true;

                    stack.push(next);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                stack.pop();
            }
        }
    }
}


/* INPUT
4 5 1
1 2
1 3
1 4
2 4
3 4
 */