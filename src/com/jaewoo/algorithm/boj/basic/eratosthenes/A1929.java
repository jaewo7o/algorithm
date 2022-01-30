package com.jaewoo.algorithm.boj.basic.eratosthenes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1929 {
    private static int MAX_NUMBER = 1000000;
    private static int[] sieve = new int[MAX_NUMBER + 1];

    public static void main(String[] args) throws IOException {
        initPrimeSieve();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        StringTokenizer token = new StringTokenizer(line);

        int m = Integer.parseInt(token.nextToken());
        int n = Integer.parseInt(token.nextToken());

        for (int i = m; i <= n; i++) {
            if (sieve[i] != 0) {
                System.out.println(i);
            }
        }
    }

    private static void initPrimeSieve() {
        for (int i = 2; i <= MAX_NUMBER; i++) {
            sieve[i] = i;
        }

        for (int i = 2; i <= MAX_NUMBER; i++) {
            if (sieve[i] == 0) {
                continue;
            }

            for (int j = i + i; j <= MAX_NUMBER; j += i) {
                sieve[j] = 0;
            }
        }
    }
}

/* INPUT
3 16
 */
