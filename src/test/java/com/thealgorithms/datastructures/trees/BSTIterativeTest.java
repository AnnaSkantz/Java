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
        assertEquals("This BST is empty.", out.toString().trim());
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

    @AfterEach
    public void breakDown() {
        System.setOut(System.out);
    }
}
