package cfalcione.cs303.lab10;

import cfalcione.cs303.shared.Helpers;
import cfalcione.cs303.shared.Lab;

import java.io.BufferedReader;
import java.io.FileReader;

public class Lab10 extends Lab{

    public void main(String[] args) {
        String filename = "src/cfalcione/cs303/lab10/resources/tinyDG.txt";
        FileReader reader;
        Graph graph = getUndirectedGraph(filename);
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 4);
        Helpers.printIterable(bfs.pathFrom(5), 16);
    }

    private DirectedGraph getDirectedGraph(String filename) {
        FileReader reader;
        DirectedGraph graph;
        try {
            reader = new FileReader(filename);
            graph = new DirectedGraph(new BufferedReader(reader));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
        return graph;
    }

    private UndirectedGraph getUndirectedGraph(String filename) {
        FileReader reader;
        UndirectedGraph graph;
        try {
            reader = new FileReader(filename);
            graph = new UndirectedGraph(new BufferedReader(reader));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
        return graph;
    }
}
