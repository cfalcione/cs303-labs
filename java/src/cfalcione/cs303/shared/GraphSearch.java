package cfalcione.cs303.shared;

import cfalcione.cs303.lab10.Graph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public abstract class GraphSearch {

    protected Graph graph;
    protected int[] parents;
    protected int startVertex;

    public abstract void search(int startVertex);

    public GraphSearch(Graph graph, int startVertex) {
        this.graph = graph;
        this.startVertex = startVertex;
        this.parents = new int[graph.V];
        search(startVertex);
    }

    public Iterable<Integer> pathFrom(int endVertex) {
        ArrayList<Integer> path = new ArrayList<>();
        while (isVertex(endVertex)) {
            path.add(endVertex);
            endVertex = parents[endVertex];
        }

        if (!isRoot(path.get(path.size() - 1))) {
            return new ArrayList<>();
        }

        return path;
    }

    protected void initParents() {
        for (int i = 0; i < parents.length; i++) {
            parents[i] = -1;
        }
    }

    protected void setParent(int vertex, int parent) {
        parents[vertex] = parent;
    }

    protected boolean visited(int vertex) {
        return parents[vertex] != -1;
    }

    protected void markRoot(int vertex) {
        parents[vertex] = -2;
    }

    protected boolean isRoot(int vertex) {
        return vertex == startVertex;
    }

    protected boolean isVertex(int vertex) {
        return vertex >= 0;
    }
}
