package cfalcione.cs303.lab11;

import cfalcione.cs303.lab10.Graph.Graph;
import cfalcione.cs303.shared.GraphSearch;

public class DepthFirstSearch extends GraphSearch {

    public DepthFirstSearch(Graph graph, int startVertex) {
        super(graph, startVertex);
    }

    public void search(int startVertex) {
        initParents();
        markRoot(startVertex);
        processVertex(startVertex);
    }

    protected void processVertex(int vertex) {
        for (int neighbor : graph.neighbors(vertex)) {
            if (visited(neighbor)) continue;
            setParent(neighbor, vertex);
            processVertex(neighbor);
        }
    }
}
