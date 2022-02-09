package com.jaewoo.algorithm.boj.basic.dfs.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A11403 {

    /*
        floyd warshall or dfs로 문제 풀이 가능
     */

    private static int N;

    private static List<Integer>[] links;
    private static int visit[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        links = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            links[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1, x; j <= N; j++) {
                x = Integer.parseInt(st.nextToken());

                if (x == 1) {
                    links[i].add(j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            visit = new int[N + 1];
            dfs(i);

            for (int j = 1; j <= N; j++) {
                sb.append(visit[j]).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int start) {
        for (int next : links[start]) {
            if (visit[next] == 0) {
                visit[next] = 1;
                dfs(next);
            }
        }
    }
}

/* input
3
0 1 0
0 0 1
1 0 0
 */
