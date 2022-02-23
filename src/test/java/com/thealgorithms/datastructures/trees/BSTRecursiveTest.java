package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BSTRecursiveTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Redirects standard out to a printstream called "out".
     */
    @BeforeEach
    public void catchPrints() {
        System.setOut(new PrintStream(out));
    }

    /**
     * Tests if an element can be added to the BSTRecursive Tree 
     * and then removed successfully
     */
    @Test
    public void addAndRemoveOneElementTest() {
        BSTRecursive bst = new BSTRecursive();
        bst.add(1);
        boolean one = bst.find(1);
        assertTrue(one);
        bst.remove(1);
        one = bst.find(1);
        assertFalse(one);
    }

    /**
     * Tests if a node with two children can be removed successfully.
     */
    @Test
    public void removeNodeWithTwoChildrenTest() {
        BSTRecursive bst = new BSTRecursive();
        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.remove(2);
        boolean two = bst.find(2);
        assertFalse(two);
    }

    /**
     * Tests so that the tree stays the same if a node which does not exist is removed.
     */
    @Test
    public void removeFromEmptyTree() {
        BSTRecursive noRemoval = new BSTRecursive();
        noRemoval.add(1);
        noRemoval.add(2);
        noRemoval.add(3);
        BSTRecursive bst = noRemoval;
        assertEquals(noRemoval, bst);
        bst.remove(4);
        assertEquals(noRemoval, bst);
    }

    /**
     * Sets standard out back to normal.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }
}