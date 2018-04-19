package cfalcione.cs303.lab10.Graph;

import cfalcione.cs303.lab10.Graph.Graph;

import java.io.BufferedReader;
import java.io.IOException;

public class UndirectedGraph extends Graph {

    public UndirectedGraph(String filename) throws IOException {
        super(filename);
    }

    @Override
    public void addEdge(int v, int w) {
        edges.addEdge(v, w);
        edges.addEdge(w, v);
    }

}
