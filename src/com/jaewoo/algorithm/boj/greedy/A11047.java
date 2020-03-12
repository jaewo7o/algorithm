package com.jaewoo.algorithm.boj.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A11047 {

    static int N;
    static int K;
    static int[] money;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        money = new int[N + 1];
        for (int i=1; i<=N; i++) {
            money[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for (int i=N; i>=1; i--) {
            if (K >= money[i]) {
                count += K / money[i];
                K = K%money[i];
            }
        }

        System.out.println(count);
    }
}

/* input
10 4200
1
5
10
50
100
500
1000
5000
10000
50000
 */
