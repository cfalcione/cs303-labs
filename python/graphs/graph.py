
class Graph():

    def __init__(self, edgeModel, numVertices, directed = False):
        self.edgeModel = edgeModel
        self.V = numVertices
        self.E = 0
        self.directed = directed
        edgeModel.initialise(numVertices)
    
    def addEdge(self, fromV, toV, weight=1):
        self.E += 1
        self.edgeModel.addEdge(fromV, toV, weight)
        if self.directed == False:
            self.edgeModel.addEdge(toV, fromV, weight)

    def neighbors(self, vertex):
        return self.edgeModel.neighbors(vertex)
    
    def neighborsWithWeights(self, vertex):
        return self.edgeModel.neighborsWithWeights(vertex)
    
    def __str__(self):
        return str(self.edgeModel)