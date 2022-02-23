package com.thealgorithms.datastructures.graphs;

import java.util.ArrayList;

public class AdjacencyListGraph<E extends Comparable<E>> {

    ArrayList<Vertex> verticies;
    public int[] branchesReached;

    public AdjacencyListGraph() {
        verticies = new ArrayList<>();
        branchesReached = new int[6];
    }

    private class Vertex {

        E data;
        ArrayList<Vertex> adjacentVerticies;

        public Vertex(E data) {
            adjacentVerticies = new ArrayList<>();
            this.data = data;
        }

        public boolean addAdjacentVertex(Vertex to) {
            for (Vertex v : adjacentVerticies) {
                if (v.data.compareTo(to.data) == 0) {
                    return false; // the edge already exists
                }
            }
            return adjacentVerticies.add(to); // this will return true;
        }

        public boolean removeAdjacentVertex(E to) {
            // use indexes here so it is possible to
            // remove easily without implementing
            // equals method that ArrayList.remove(Object o) uses
            for (int i = 0; i < adjacentVerticies.size(); i++) {
                if (adjacentVerticies.get(i).data.compareTo(to) == 0) {
                    adjacentVerticies.remove(i);
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * this method removes an edge from the graph between two specified
     * verticies
     *
     * @param from the data of the vertex the edge is from
     * @param to the data of the vertex the edge is going to
     * @return returns false if the edge doesn't exist, returns true if the edge
     * exists and is removed
     */
    public boolean removeEdge(E from, E to) {
        Vertex fromV = null;
        for (Vertex v : verticies) {
            if (from.compareTo(v.data) == 0) {
                fromV = v;
                break;
            }
        }
        if (fromV == null) {
            return false;
        }
        return fromV.removeAdjacentVertex(to);
    }

    /**
     * this method adds an edge to the graph between two specified verticies
     *
     * @param from the data of the vertex the edge is from
     * @param to the data of the vertex the edge is going to
     * @return returns true if the edge did not exist, return false if it
     * already did
     */
    public boolean addEdge(E from, E to) {
        Vertex fromV = null, toV = null;
        for (Vertex v : verticies) {
            branchesReached[0] = 1; // ID = 1
            if (from.compareTo(v.data) == 0) { // see if from vertex already exists
                branchesReached[1] = 2;
                fromV = v;
            } else if (to.compareTo(v.data) == 0) { // see if to vertex already exists
                branchesReached[2] = 3;
                toV = v;
            }
            if (fromV != null && toV != null) {
                branchesReached[3] = 4;
                break; // both nodes exist so stop searching
            }
        }
        if (fromV == null) {
            branchesReached[4] = 5;
            fromV = new Vertex(from);
            verticies.add(fromV);
        }
        if (toV == null) {
            branchesReached[5] = 6;
            toV = new Vertex(to);
            verticies.add(toV);
        }
        return fromV.addAdjacentVertex(toV);
    }

    /**
     * this gives a list of verticies in the graph and their adjacencies
     *
     * @return returns a string describing this graph
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex v : verticies) {
            sb.append("Vertex: ");
            sb.append(v.data);
            sb.append("\n");
            sb.append("Adjacent verticies: ");
            for (Vertex v2 : v.adjacentVerticies) {
                sb.append(v2.data);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        assert graph.addEdge(1, 2);
        assert graph.addEdge(1, 5);
        assert graph.addEdge(2, 5);
        assert !graph.addEdge(1, 2);
        assert graph.addEdge(2, 3);
        assert graph.addEdge(3, 4);
        assert graph.addEdge(4, 1);
        assert !graph.addEdge(2, 3);
        // System.out.println(graph);

        task1DIY(graph);
    }

    private static void task1DIY(AdjacencyListGraph<Integer> graph) {
        int numBranchesReached = 0;
        int numOfBranchesInAddEdge = 6;
        for (int i = 0; i < graph.branchesReached.length; i++) {
            if (graph.branchesReached[i] != 0) {
                System.out.println("Branch with the ID " + graph.branchesReached[i] + " was reached.\n");
                numBranchesReached++;
            }
        }
        System.out.println("Branches reached: " + numBranchesReached + " out of " + numOfBranchesInAddEdge + " branches were reached.");
    }
}
