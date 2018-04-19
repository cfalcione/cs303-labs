package cfalcione.cs303.lab10;

import java.util.*;

public class BreadthFirstSearch {

    protected Graph graph;
    protected int[] parents;

    public BreadthFirstSearch(Graph graph, int startVertex) {
        this.graph = graph;
        this.parents = new int[graph.V];
        search(startVertex);
    }

    public void search(int startVertex) {
        initParents();
        markRoot(startVertex);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int next = queue.remove();
            processVertex(queue, next);
        }
    }

    public Iterable<Integer> pathFrom(int endVertex) {
        ArrayList<Integer> path = new ArrayList<>();
        while (isVertex(endVertex)) {
            path.add(endVertex);
            endVertex = parents[endVertex];
        }

        return path;
    }

    protected void processVertex(Queue<Integer> queue, int vertex) {
        for (int neighbor : graph.neighbors(vertex)) {
            if (visited(neighbor)) continue;
            parents[neighbor] = vertex;
            queue.add(neighbor);
        }
    }

    private void initParents() {
        for (int i = 0; i < parents.length; i++) {
            parents[i] = -1;
        }
    }

    private boolean visited(int vertex) {
        return parents[vertex] != -1;
    }

    private void markRoot(int vertex) {
        parents[vertex] = -2;
    }

    private boolean isVertex(int vertex) {
        return vertex >= 0;
    }
}
