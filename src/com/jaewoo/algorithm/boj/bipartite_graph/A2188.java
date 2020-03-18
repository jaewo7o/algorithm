package com.jaewoo.algorithm.boj.bipartite_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A2188 {

    static int N;
    static int M;
    static boolean[][] connect;
    static boolean[] visit;
    static int [] matchA;
    static int [] matchB;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matchA = new int[N + 1];
        matchB = new int[M + 1];
        connect = new boolean[N + 1][M + 1];
        for (int i=1, x, j; i<=M; i++) {
            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            while (x-- > 0){
                j = Integer.parseInt(st.nextToken());
                connect[i][j] = true;
            }
        }

        int count = 0;
        visit = new boolean[N + 1];
        for (int i=1; i<=N; i++) {
            Arrays.fill(visit, false);
            if (dfs(i)) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static boolean dfs(int start) {
        if (visit[start]) {
            return false;
        }

        visit[start] = true;

        for (int m=1; m<=M; m++) {
            if (connect[start][m]) {
                if (matchB[m] == 0 || dfs(matchB[m]) ) {
                    matchA[start] = m;
                    matchB[m] = start;
                    return true;
                }
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
