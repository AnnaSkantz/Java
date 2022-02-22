package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BSTIterativeTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(out));
    }

    @Test
    public void testPostorderPrintMessageEmptyTree() {
        BSTIterative tree = new BSTIterative();
        tree.postorder();
        String expected = "This BST is empty.";
        String actual = out.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void testPostorderPrintMessageTree() {
        BSTIterative tree = new BSTIterative();
        tree.add(3);
        tree.add(9);
        tree.add(30);
        tree.add(40);
        String expected = "40 30 9 3";
        tree.postorder();
        assertEquals("Postorder traversal of this tree is:\n" + expected, out.toString().trim());
    }

    @Test
    public void testPreorderPrintMessageEmptyTree() {
        BSTIterative tree = new BSTIterative();
        tree.preorder();
        String expected = "This BST is empty.";
        String actual = out.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void testPreorderPrintMessageTree(){
        BSTIterative tree = new BSTIterative();
        tree.add(3);
        tree.add(9);
        tree.add(30);
        tree.add(40);
        String expected = "3 9 30 40";
        tree.preorder();
        assertEquals("Preorder traversal of this tree is:\n" + expected, out.toString().trim());
    }

    @Test
    public void testAddAndRemoveNode() {
        BSTIterative tree = new BSTIterative();
        tree.add(3);
        assertTrue(tree.find(3));
        tree.remove(3);
        assertFalse(tree.find(3));
    }

    @AfterEach
    public void breakDown() {
        System.setOut(System.out);
    }
}
