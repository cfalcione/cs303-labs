package cfalcione.cs303.lab10.Graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public abstract class Graph {

    public abstract void addEdge(int v, int w);

    public Iterable<Integer> neighbors(int vertex) {
        return edges.neighbors(vertex);
    }

    public int V;

    public int E;

    protected EdgeModel edges;

    public Graph() {
        this(new AdjacencyList());
    }

    public Graph(EdgeModel edgeModel) {
        V = 0;
        E = 0;
        this.edges = edgeModel;
    }

    public Graph(String filename) throws IOException {
        this(filename, new AdjacencyList());
    }

    public Graph(String filename, EdgeModel edgeModel) throws IOException {
        this.edges = edgeModel;
        FileReader reader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(reader);
        initGraph(bufferedReader);
    }

    protected void initGraph(BufferedReader reader) throws IOException {
        String line;
        line = reader.readLine();
        V = Integer.parseInt(line);
        line = reader.readLine();
        E = Integer.parseInt(line);
        edges.init(V);
        while ((line = reader.readLine()) != null) {
            int tempV1, tempV2;
            StringTokenizer st = new StringTokenizer(line, " ");
            tempV1 = Integer.parseInt(st.nextToken());
            tempV2 = Integer.parseInt(st.nextToken());
            addEdge(tempV1, tempV2);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("There are " + V + " vertices and " + E + " edges\n");
        for (int vertex = 0; vertex < V; vertex++) {
            s.append(vertex).append(": ");
            for (Integer neighbor: neighbors(vertex)) {
                s.append(neighbor).append(" ");
            }
            s.append("\n");

        }
        return s.toString();
    }

}
