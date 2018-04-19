package cfalcione.cs303.lab10;

import cfalcione.cs303.lab10.Graph.*;
import cfalcione.cs303.shared.Helpers;
import cfalcione.cs303.shared.Lab;

import java.io.IOException;

public class Lab10 extends Lab{

    public void main(String[] args) {
        String filename = "resources/mediumG.txt";
        Graph graph = getGraph(filename, GraphType.DIRECTED, new AdjacencyMatrix());
//        System.out.print(graphA);
//        System.out.print(graphB);
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 2);
        Helpers.printIterable(bfs.pathFrom(213), 16);
    }

    public static Graph getGraph(String filename, GraphType type, EdgeModel edgeModel) {
        Graph graph;
        try {
            switch(type) {
                case DIRECTED:
                    graph = new DirectedGraph(filename, edgeModel);
                    break;
                case UNDIRECTED: //undirected is intentionally default
                default:
                    graph = new UndirectedGraph(filename, edgeModel);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
        return graph;
    }

    public static Graph getGraph(String filename, GraphType type) {
        return getGraph(filename, type, new AdjacencyList());
    }

}

