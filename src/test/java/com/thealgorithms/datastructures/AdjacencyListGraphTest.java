package com.thealgorithms.datastructures;

import com.thealgorithms.datastructures.graphs.AdjacencyListGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdjacencyListGraphTest {

    /**
     * Requirement: Check that addEdge returns false when you try to add an edge
     * that already exists.
     */
    @Test
    void testEdgeExistsInAddEdge()
    {
        // Arrange
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(1, 3);
        // Act
        boolean edgeDoesNotExist = graph.addEdge(1, 2);
        // Assert
        assertFalse(edgeDoesNotExist);
    }

    /**
     * Requirement: Check that addEdge returns true when you add an edge from an
     * existing node to a node that does not exist.
     */
    @Test
    void testEdgeDoesNotExistWhenToIsNull()
    {
        // Arrange
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(1, 3);
        // Act
        boolean edgeDoesNotExist = graph.addEdge(2, 5);
        // Assert
        assertTrue(edgeDoesNotExist);
    }

    /**
     * Requirement: Check that addEdge returns true when you add an edge from a
     * non-existing node to a node that already exists.
     */
    @Test
    void testEdgeDoesNotExistWhenFromIsNull()
    {
        // Arrange
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(1, 3);
        // Act
        boolean edgeDoesNotExist = graph.addEdge(5, 2);
        // Assert
        assertTrue(edgeDoesNotExist);
    }

    /**
     * Requirement: Check that addEdge returns true when you add an edge from a
     * non-existing node to a node that also does not exist.
     */
    @Test
    void testEdgeDoesNotExistWhenFromAndToAreNull()
    {
        // Arrange
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(1, 3);
        // Act
        boolean edgeDoesNotExist = graph.addEdge(5, 6);
        // Assert
        assertTrue(edgeDoesNotExist);
    }
}
