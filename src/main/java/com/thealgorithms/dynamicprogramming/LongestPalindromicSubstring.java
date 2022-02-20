package com.thealgorithms.dynamicprogramming;

/*
 * Algorithm explanation https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {
    private static int start = 0;
    private static int end = 0;
    private static boolean[][] arr;

    public static void main(String[] args) {
        String a = "babad";
        String b = "cbbd";

        String aLPS = LPS(a);
        String bLPS = LPS(b);

        System.out.println(a + " => " + aLPS);
        System.out.println(b + " => " + bLPS);
    }

    /**
     * This function uses dynamic programming to find the longest palindromic substring.
     * @param input - The String which is going to be searched for the longest palindrome.
     * @return - The longest palindrome substring found in the input.
     */
    public static String LPS(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        initMatrix(input);
        for (int g = 2; g < input.length(); g++) {
            for (int i = 0, j = g; j < input.length(); i++, j++) {
                if (input.charAt(i) == input.charAt(j) && arr[i+1][j-1]) {
                    arr[i][j] = true;
                    start = i;
                    end = j;
                } else {
                    arr[i][j] = false;
                }
            }
        }
        return input.substring(start, end + 1);
    }

    /**
     * Initiates the matrix for dynamic programming. The diagonal which represents
     * palindromes of length 1 are set to true. The case of palindromes of length
     * 2 (two of the same letter in a row) are also filled in.
     * @param input - The string which is going to be searched for palindromes.
     * @return - The initialized matrix.
     */
    private static void initMatrix(String input) {
        arr = new boolean[input.length()][input.length()];
        for (int i = 0; i < input.length() - 1; i++) {
            arr[i][i] = true;
            arr[i][i+1] = false;
            if (input.charAt(i) == input.charAt(i+1)) {
                arr[i][i+1] = true;
                start = i;
                end = i+1;
            }
        }
        arr[input.length()][input.length()] = true;
    }

}
