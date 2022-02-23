package com.thealgorithms.dynamicprogramming;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Algorithm explanation https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {
    private static ArrayList<String> testTrack = new ArrayList<String>();

    public static void main(String[] args) {
        String a = "babad";
        String b = "cbbd";

        String aLPS = LPS(a);
        String bLPS = LPS(b);

        System.out.println(a + " => " + aLPS);
        System.out.println(b + " => " + bLPS);
    }

    public static String LPS(String input) {
        // id 1
        testTrack.add("1");
        if (input == null || input.length() == 0) {
            // id 2
            testTrack.add("2");
            writeToFile(testTrack.toString());
            return input;
        }
        // id 3
        testTrack.add("3");
        boolean arr[][] = new boolean[input.length()][input.length()];
        int start = 0, end = 0;
        for (int g = 0; g < input.length(); g++) {
            // id 4
            testTrack.add("4");
            for (int i = 0, j = g; j < input.length(); i++, j++) {
                // id 5
                testTrack.add("5");
                if (g == 0) {
                    // id 6
                    testTrack.add("6");
                    arr[i][j] = true;
                } else if (g == 1) {
                    // id 7
                    testTrack.add("7");
                    if (input.charAt(i) == input.charAt(j)) {
                        // id 8
                        testTrack.add("8");
                        arr[i][j] = true;
                    } else {
                        // id 9
                        testTrack.add("9");
                        arr[i][j] = false;
                    }
                } else {
                    // id 10
                    testTrack.add("10");
                    if (input.charAt(i) == input.charAt(j) && arr[i + 1][j - 1]) {
                        // id 11
                        testTrack.add("11");
                        arr[i][j] = true;
                    } else {
                        // id 12
                        testTrack.add("12");
                        arr[i][j] = false;
                    }
                }

                if (arr[i][j]) {
                    // id 13
                    testTrack.add("13");
                    start = i;
                    end = j;
                }
            }
        }
        writeToFile(testTrack.toString());
        return input.substring(start, end + 1);
    }

    private static void writeToFile(String s) {
        // write res to file
        File f = new File("/tmp/branchesPall.txt");
        try {
            FileWriter fw = new FileWriter("/tmp/branchesPall.txt");
            fw.write(testTrack.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
