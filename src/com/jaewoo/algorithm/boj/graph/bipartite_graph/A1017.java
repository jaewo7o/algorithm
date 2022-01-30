package com.jaewoo.algorithm.boj.graph.bipartite_graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1017 {

    private static int MAX_NUMBER = 1000;
    private static int MAX_NUMBER_COUNT = 50;
    private static int [] numbers = new int[MAX_NUMBER_COUNT + 1];
    private static int [] numbersA = new int[MAX_NUMBER_COUNT + 1];
    private static int [] numbersB = new int[MAX_NUMBER_COUNT + 1];

    private static List<Integer>[] links;
    private static int[] d;
    private static boolean[] isVisit;

    private static int setACount = 0;
    private static int setBCount = 0;

    private static int[] primeSieve = new int[MAX_NUMBER * 2 + 1];

    public static void main(String[] args) throws IOException {
        // 아리스토테네스의 체 생성(소수 비교 목적)
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

        // 홀수/짝수에 따라 그룹A, 그룹B로 분리
        for (int i = 1; i <= n; i++) {
            if (numbers[1] % 2 == numbers[i] % 2) {
                numbersA[++setACount] = numbers[i];
            } else {
                numbersB[++setBCount] = numbers[i];
            }
        }

        // 그룹간의 합이 소수인 경우 두 그룹 간의 관계 연결
        for (int i = 1; i <= setACount; i++) {
            for (int j = 1; j <= setBCount; j++) {
                if (primeSieve[numbersA[i] + numbersB[j]] != 0) {
                    links[i].add(j);
                }
            }
        }

        d = new int[setBCount + 1];
        isVisit = new boolean[setBCount + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 그룹A의 첫번째 요소를 고정하고 그룹B의 대상을 바꿔가며 전체 매칭되는 케이스 체크
        for (int j = 1; j <= setBCount; j++) {
            if (links[1].contains(j)) {
                Arrays.fill(d, -1);
                d[j] = 1;
                int matchingCount = 1;

                for (int i = 2; i <= setACount; i++) {
                    Arrays.fill(isVisit, false);
                    if (dfs(i, j)) {
                        matchingCount++;
                    }
                }

                // 매칭 건수가 제시한 숫자의 건수의 반이 된다면 전체 매칭 됨
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

    private static boolean dfs(int x, int initY) {
        for (int y : links[x]) {
            // 한번 방문한 그룹B의 요소는 다시 방문할 필요가 없음
            // 초기 그룹B의 요소가 나오는 경우 매칭값을 변경하면 안되기 때문에 skip
            if (isVisit[y] || y == initY) {
                continue;
            }

            isVisit[y] = true;

            if (d[y] == -1 || dfs(d[y], initY)) {
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
