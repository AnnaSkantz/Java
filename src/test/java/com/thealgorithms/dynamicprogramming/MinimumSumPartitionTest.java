package com.thealgorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinimumSumPartitionTest {
    /**
     * Tests so that the LPS function outputs the right palindrome when there
     * is a substring palindrome with odd length of 7.
     */
    @Test 
    public void minimumSumPartitionTest() {
        MinimumSumPartition msp = new MinimumSumPartition();
        int expected = 4;
        int acutual = msp.subSet(new int[]{1, 5, 10});
        assertEquals(expected, acutual);
    }
}
