
class EdgeModel():

    def initialise(self, numVertices):
        raise NotImplementedError
    
    def addEdge(self, fromVertex, toVertex, weight):
        raise NotImplementedError
    
    def getWeight(self, fromVertex, toVertex):
        raise NotImplementedError
    
    def neighbors(self, vertex):
        raise NotImplementedError
    
    def neighborsWithWeights(self, vertex):
        raise NotImplementedError

class AdjacencyList(EdgeModel):

    def initialise(self, numVertices):
        self.list = [[] for _ in range(numVertices)]
        self.count = 0

    def addEdge(self, fromV, toV, weight):
        self.list[fromV].append( (toV, weight) )
        self.count += 1
    
    def getWeight(self, fromV, toV):
        for neighbor, weight in self.neighborsWithWeights(fromV):
            if neighbor == toV:
                return weight
        return None
    
    def neighbors(self, vertex):
        mapped = map(lambda x: x[0], self.list[vertex])
        return list(mapped)
    
    def neighborsWithWeights(self, vertex):
        return self.list[vertex]
    
    def __str__(self):
        res = []
        for vertex, neighbors in enumerate(self.list):
            res.append("{}: {}".format(vertex, neighbors))
        return "\n".join(res)

