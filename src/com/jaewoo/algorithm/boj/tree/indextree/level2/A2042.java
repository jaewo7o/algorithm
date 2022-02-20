package com.jaewoo.algorithm.boj.tree.indextree.level2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A2042 {

    /*
        알고리즘 설명 : https://blog.naver.com/PostView.nhn?blogId=sweetgirl0111&logNo=222276677369&parentCategoryNo=&categoryNo=&viewDate=&isShowPopularPosts=false&from=postView
     */

    private static long[] tree;

    private static int startIndex;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] numbers = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        int depth = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeN = (int) Math.pow(2, depth + 1) - 1;
        startIndex = (int) Math.pow(2, depth);
        tree = new long[treeN + 1];

        for (int i = 1; i <= N; i++) {
            updateTree(i, numbers[i]);
        }

        for (int i = 1; i <= M + K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                updateTree(b, c);
            } else
                System.out.println(query(b, c));
        }
    }

    private static long query(int start, int end) {
        start = startIndex + start - 1;
        end = startIndex + end - 1;

        long max = -1;
        while (start <= end) {
            if (start%2 == 1) {
                max = Math.max(tree[start], max);
            } else if (start%2 == 0) {
                max = Math.max(tree[start], max);
            }

            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }

        return max;
    }

    private static void updateTree(int index, long number) {
        index = startIndex + index - 1;
        tree[index] = number;

        index = index / 2;
        while (index > 0) {
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
            index = index / 2;
        }
    }
}

/* INPUT
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 5
 */