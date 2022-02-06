package com.jaewoo.algorithm.boj.basic.dfs.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A11403 {

    private static int N;

    private static int map[][];
    private static int visit[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
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
        for (int j = 1; j <= N; j++) {
            if (map[start][j] == 1 && visit[j] == 0) {
                visit[j] = 1;
                dfs(j);
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
