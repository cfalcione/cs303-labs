package cfalcione.cs303.lab10.Graph;


import java.util.LinkedList;

public class AdjacencyList implements EdgeModel {

    public LinkedList<Integer>[] adj;


    @Override
    @SuppressWarnings("unchecked")
    public void init(int vertexCount) {
        adj = new LinkedList[vertexCount];
        for (int v = 0; v < vertexCount; v++) {
            adj[v] = new LinkedList<Integer>();
        }
    }

    @Override
    public void addEdge(int vertexA, int vertexB) {
        adj[vertexA].add(vertexB);
    }

    @Override
    public Iterable<Integer> neighbors(int vertex) {
        return this.adj[vertex];
    }
}
