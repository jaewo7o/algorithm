package com.jaewoo.algorithm.boj.graph.bipartite_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A1017 {

    private static int MAX_NUMBER = 50;
    private static int [] numbers = new int[MAX_NUMBER + 1];
    private static int [] numbersA = new int[MAX_NUMBER + 1];
    private static int [] numbersB = new int[MAX_NUMBER + 1];

    private static List<Integer>[] links;
    private static int[] d;

    private static int setACount = 0;
    private static int setBCount = 0;

    private static int[] primeSieve = new int[MAX_NUMBER * 2 + 1];

    public static void main(String[] args) throws IOException {
        initPrimeSieve();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        links = new ArrayList[n + 1];
        String line = br.readLine();
        StringTokenizer token = new StringTokenizer(line);
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(token.nextToken());
            links[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            if (numbers[1] % 2 == numbers[i] % 2) {
                numbersA[++setACount] = numbers[i];
            } else {
                numbersB[++setBCount] = numbers[i];
            }
        }

        for (int i = 1; i <= setACount; i++) {
            for (int j = 1; j <= setBCount; j++) {
                if (primeSieve[numbersA[i] + numbersB[j]] != 0) {
                    links[i].add(j);
                }
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int j = 1; j <= setBCount; j++) {
            if (primeSieve[numbersA[1] + numbersB[j]] != 0) {
                d = new int[setBCount + 1];

                d[j] = 1;
                int matchingCount = 1;

                for (int i = 2; i <= setACount; i++) {
                    if (dfs(i)) {
                        matchingCount++;
                    }
                }

                if (matchingCount == n /2 ) {
                    pq.offer(numbersB[j]);
                }
            }
        }

        if (pq.isEmpty()) {
            System.out.println("-1");
        } else {
            StringBuilder sb = new StringBuilder();
            while (!pq.isEmpty()) {
                sb.append(pq.poll()).append(" ");
            }

            System.out.println(sb);
        }
    }

    private static boolean dfs(int x) {
        for (int y : links[x]) {
            if (d[y] == 0 || dfs(d[y])) {
                d[y] = x;
                return true;
            }
        }
        return false;
    }

    public static void initPrimeSieve() {
        for (int i = 2; i <= MAX_NUMBER * 2; i++) {
            primeSieve[i] = i;
        }

        for (int i = 2; i <= MAX_NUMBER * 2; i++) {
            if (primeSieve[i] == 0) {
                continue;
            }

            for (int j = i + i; j <= MAX_NUMBER * 2; j += i) {
                primeSieve[j] = 0;
            }
        }
    }
}

/* INPUT
6
1 4 7 10 11 12
 */
