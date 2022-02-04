package com.jaewoo.algorithm.boj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class A1759 {

    private static int L;
    private static int C;
    private static char[] chars;
    private static int count;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer token = new StringTokenizer(line);
        L = Integer.parseInt(token.nextToken());
        C = Integer.parseInt(token.nextToken());

        chars = new char[C + 1];
        token = new StringTokenizer(br.readLine());
        for (int i = 1; i <= C; i++) {
            chars[i] = token.nextToken().charAt(0);
        }
        Arrays.sort(chars); // 알파벳이 증가하는 형식이니 배열을 정렬한다.

        sb = new StringBuilder();
        for (int i = 1; i <= C; i++) {
            count = 1;
            dfs(i, chars[i] + "");
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int x, String letters) {
        if (count == L) {
            if (isPossible(letters)) {
                sb.append(letters).append("\n");
            }
            return;
        }

        for (int i = x + 1; i <= C; i++) {
            count++;
            dfs(i, letters + chars[i]);
            count--;
        }
    }

    private static boolean isPossible(String letters) {
        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};

        int vowelCount = 0;
        for (int i = 0; i < L; i++) {
            char c = letters.charAt(i);
            for (int j = 0; j < vowels.length; j++) {
                if (c == vowels[j]){
                    vowelCount++;
                }
            }
        }

        return vowelCount >= 1 && L - vowelCount >= 2;
    }
}

/* INPUT
4 6
a t c i s w
 */
