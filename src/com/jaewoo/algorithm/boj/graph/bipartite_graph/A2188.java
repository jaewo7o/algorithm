package com.jaewoo.algorithm.boj.graph.bipartite_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class A2188 {

    static int N;
    static int M;
    static List<Integer> links[];
    static boolean[] isVisit;
    static int[] matchB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matchB = new int[M + 1];
        links = new ArrayList[N + 1];

        for (int i = 1, x, j; i <= N; i++) {
            links[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            while (x-- > 0) {
                j = Integer.parseInt(st.nextToken());
                links[i].add(j);
            }
        }

        int count = 0;
        isVisit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(isVisit, false);
            if (dfs(i)) {
                count++;
            }
        }

        System.out.println(count);
    }

    // 매칭에 성공한 경우 true 반환
    private static boolean dfs(int x) {
        for (int y : links[x]) {
            // 이미 처리한 축사는 제외
            if (isVisit[y]) {
                continue;
            }

            isVisit[y] = true;
            if (matchB[y] == 0 || dfs(matchB[y])) {
                matchB[y] = x;
                return true;
            }
        }

        return false;
    }
}

/* INPUT
5 5
2 2 5
3 2 3 4
2 1 5
3 1 2 5
1 2
 */
