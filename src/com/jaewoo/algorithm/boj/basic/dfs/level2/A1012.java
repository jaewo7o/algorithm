package com.jaewoo.algorithm.boj.basic.dfs.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1012 {
    static int T;
    static int M;
    static int N;
    static int K;
    static int[][] maps;
    static int[] dm = {-1, 0, 1, 0};
    static int[] dn = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // initialize
            maps = new int[M + 1][N + 1];
            for (int i=1, m, n; i<=K; i++) {
                st = new StringTokenizer(br.readLine());
                m = Integer.parseInt(st.nextToken());
                n = Integer.parseInt(st.nextToken());

                maps[m + 1][n + 1] = 1;
            }

            // solve
            int count = 0;
            for (int m=1; m<=M; m++) {
                for (int n=1; n<=N; n++) {
                    if (maps[m][n] == 1) {
                        count++;
                        dfs(m, n);
                    }
                }
            }

            System.out.println(count);
        }
    }

    private static void dfs(int m, int n) {
        // 방문했던 cell은 초기화 처리
        maps[m][n] = 0;

        int nm, nn;
        for (int x=0; x<4; x++) {
            nm = m + dm[x];
            nn = n + dn[x];

            if (nm >= 1 && nn >= 1 && nm <= M && nn <= N) {
                if (maps[nm][nn] == 1) {
                    dfs(nm, nn);
                }
            }
        }
    }
}

/* input
2
10 8 17
0 0
1 0
1 1
4 2
4 3
4 5
2 4
3 4
7 4
8 4
9 4
7 5
8 5
9 5
7 6
8 6
9 6
10 10 1
5 5
 */
