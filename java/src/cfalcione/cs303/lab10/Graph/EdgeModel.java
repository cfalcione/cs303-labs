package cfalcione.cs303.lab10.Graph;

public interface EdgeModel {

    public void init(int vertexCount);

    public void addEdge(int vertexA, int vertexB);

    public Iterable<Integer> neighbors(int vertex);
}
