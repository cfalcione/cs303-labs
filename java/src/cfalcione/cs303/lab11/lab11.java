package cfalcione.cs303.lab11;

import cfalcione.cs303.lab10.BreadthFirstSearch;
import cfalcione.cs303.lab10.Graph.*;
import cfalcione.cs303.lab10.Lab10;
import cfalcione.cs303.shared.Helpers;
import cfalcione.cs303.shared.Lab;

import java.io.IOException;

public class lab11 extends Lab{

    public void main(String[] args) {
        String filename = "resources/mediumG.txt";
        Graph graph = Lab10.getGraph(filename, GraphType.UNDIRECTED, new AdjacencyMatrix());
        DepthFirstSearch bfs = new DepthFirstSearch(graph, 2);
        Helpers.printIterable(bfs.pathFrom(213), 16);
    }




}

