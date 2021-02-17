package com.jaewoo.algorithm.boj.basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A11403 {

    private static int N;

    private static int map[][];
    private static int result[][];
    private static boolean visit[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        result = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            visit = new boolean[N + 1];
            dfs(i);

            for (int j = 1; j <= N; j++) {
                if (visit[j]) {
                    result[i][j] = 1;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private static void dfs(int i) {
        for (int j = 1; j <= N; j++) {
            if (map[i][j] == 1 && !visit[j]) {
                visit[j] = true;
                dfs(j);
            }
        }
    }
}
