package cfalcione.cs303.lab10;

import java.io.BufferedReader;
import java.io.IOException;

public class UndirectedGraph extends Graph {

    public UndirectedGraph(BufferedReader bufferedReader) throws IOException {
        super(bufferedReader);
    }

    @Override
    public void addEdge(int v, int w) {
        this.adj[v].add(w);
    }

    @Override
    protected Iterable<Integer> neighbors(int vertex) {
        return adj[vertex];
    }

}
