package cfalcione.cs303.lab11;

import cfalcione.cs303.lab10.Graph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearch {

    protected Graph graph;
    protected int[] parents;

    public DepthFirstSearch(Graph graph, int startVertex) {
        this.graph = graph;
        this.parents = new int[graph.V];
        search(startVertex);
    }

    public void search(int startVertex) {
        initParents();
        markRoot(startVertex);
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int next = stack.pop();
            processVertex(stack, next);
        }
    }

    public Iterable<Integer> pathFrom(int endVertex) {
        ArrayList<Integer> path = new ArrayList<>();
        while (isVertex(endVertex)) {
            path.add(endVertex);
            endVertex = parents[endVertex];
        }

        if (!isRoot(path.get(path.size() - 1))) {
            return new ArrayList<Integer>();
        }

        return path;
    }

    protected void processVertex(Stack<Integer> stack, int vertex) {
        for (int neighbor : graph.neighbors(vertex)) {
            if (visited(neighbor)) continue;
            parents[neighbor] = vertex;
            stack.push(neighbor);
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

    private boolean isRoot(int vertex) {
        return parents[vertex] < -1;
    }

    private boolean isVertex(int vertex) {
        return vertex >= 0;
    }
}
