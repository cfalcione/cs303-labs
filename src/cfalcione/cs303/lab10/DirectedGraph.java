package cfalcione.cs303.lab10;

import java.io.BufferedReader;
import java.io.IOException;

public class DirectedGraph extends Graph {

    public DirectedGraph(BufferedReader bufferedReader) throws IOException {
        super(bufferedReader);
    }

    @Override
    public void addEdge(int v, int w) {
        this.adj[v].add(w);
        this.adj[w].add(v);
    }

    @Override
    protected Iterable<Integer> neighbors(int vertex) {
        return this.adj[vertex];
    }

}
