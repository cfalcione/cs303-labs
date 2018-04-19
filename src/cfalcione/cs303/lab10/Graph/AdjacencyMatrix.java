package cfalcione.cs303.lab10.Graph;


import java.util.Iterator;

public class AdjacencyMatrix implements EdgeModel {

    public boolean[][] adj;

    @Override
    public void init(int vertexCount) {
        adj = new boolean[vertexCount][vertexCount];
    }

    @Override
    public void addEdge(int vertexA, int vertexB) {
        adj[vertexA][vertexB] = true;
    }

    @Override
    public Iterable<Integer> neighbors(int vertex) {
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new BooleanArrayIterator(adj[vertex]);
            }
        };
    }
}
