package com.jaewoo.algorithm.boj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A10870 {
    private static int[] f;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        f = new int[n + 1];

        dp(n);

        System.out.println("f[n] = " + f[n]);
    }

    private static int dp(int n) {
        if (n == 0) {
            return f[n] = 0;
        } else if (n == 1) {
            return f[n] = 1;
        } else {
            return f[n] = dp(n - 1) + dp(n - 2);
        }
    }
}
