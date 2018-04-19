package cfalcione.cs303.lab10;

import cfalcione.cs303.lab10.Graph.DirectedGraph;
import cfalcione.cs303.lab10.Graph.Graph;
import cfalcione.cs303.lab10.Graph.UndirectedGraph;
import cfalcione.cs303.shared.Helpers;
import cfalcione.cs303.shared.Lab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lab10 extends Lab{

    public void main(String[] args) {
        String filename = "resources/mediumG.txt";
        Graph graph = getGraph(filename, GraphType.DIRECTED);
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 2);
        Helpers.printIterable(bfs.pathFrom(213), 16);
    }

    private Graph getGraph(String filename, GraphType type) {
        Graph graph;
        try {
            switch(type) {
                case DIRECTED:
                    graph = new DirectedGraph(filename);
                    break;
                default:
                    graph = new UndirectedGraph(filename);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
        return graph;
    }

}

enum GraphType {
    DIRECTED, UNDIRECTED;
}
