package com.thealgorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LongestPalindromicSubstringTest {

    /**
     * Tests so that if the LPS function gets an empty string as input the
     * it outputs an empty sting.
     */
    @Test
    public void emptyStringTest() {
        String actual = LongestPalindromicSubstring.LPS("");
        String expected = "";
        assertEquals(expected, actual);
    }

    /**
     * Tests so that if the LPS function gets null as input it outputs null.
     */
    @Test
    public void nullStringTest() {
        String actual = LongestPalindromicSubstring.LPS(null);
        String expected = null;
        assertEquals(expected, actual);
    }

    /**
     * Tests so that if the function receives a string with no palindrome loner than
     * one it outputs a palindrome of length 1.
     */
    @Test
    public void noPalindromeTest() {
        String palindrome = LongestPalindromicSubstring.LPS("abcdefghijlkmnopqrstuvxyz");
        assertTrue(palindrome.length() == 1);
    }

    /**
     * Tests so that the LPS function outputs the right palindrome when there
     * is a substring palindrome with the even length of 6.
     */
    @Test
    public void evenLetterPalindromeTest() {
        String actual = LongestPalindromicSubstring.LPS("abcmappamabc");
        String expected = "mappam";
        assertEquals(expected, actual);
    }

    /**
     * Tests so that the LPS function outputs the right palindrome when there
     * is a substring palindrome with odd length of 7.
     */
    @Test 
    public void oddLetter7PalindromeTest() {
        String actual = LongestPalindromicSubstring.LPS("abcjagagajabc");
        String expected = "jagagaj";
        assertEquals(expected, actual);
    }
}