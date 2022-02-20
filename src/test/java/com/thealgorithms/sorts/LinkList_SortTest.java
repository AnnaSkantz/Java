package com.thealgorithms.sorts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LinkList_SortTest {

    /**
     * Test that the function isSorted returns false when the merge sort technique has been used
     */
    @Test
    public void testThatisSortedReturnsTrueWhenMergeSort() {
        
        int[] listToSort = new int[]{1,5,3,8,5,9,10};
        assertTrue(LinkList_Sort.isSorted(listToSort, 1));
    }

    /**
     * Test that the function isSorted returns false when the insertion sort technique has been used
     */
    @Test
    public void testThatisSortedReturnsFalseWhenInsertionSort() {
        
        int[] listToSort = new int[]{1,5,3,8,5,9,10};
        assertFalse(LinkList_Sort.isSorted(listToSort, 2));
    }

    /**
     * Tests that the function isSorted returns False when the heap sort technique has been used 
     */
    @Test
    public void testThatisSortedReturnsTrueWhenHeapSort() {
        
        int[] listToSort = new int[]{1,5,3,8,5,9,10};
        assertTrue(LinkList_Sort.isSorted(listToSort, 3));
    }
}