from collections import deque

class GraphTraversal():

    def __init__(self, graph, vertex):
        self.graph = graph
        self.root = vertex
        self.parents = self.makeParents(graph.V)
        self.traverse(graph, vertex)
    
    def makeParents(self, numEdges):
        return [None] * numEdges
    
    def traverse(self, graph, vertex):
        raise NotImplementedError
    
    def setParent(self, child, parent):
        self.parents[child] = parent

    def pathTo(self, vertex):
        res = self.pathFrom(vertex)
        res.reverse()
        return res

    def pathFrom(self, vertex):
        res = list(self._pathFrom(vertex))
        if res[-1] != self.root:
            return []
        return res

    def _pathFrom(self, vertex):
        parents = self.parents
        while parents[vertex] is not None:
            yield vertex
            vertex = parents[vertex]
        yield vertex


class BreadthFirstSearch(GraphTraversal):

    def traverse(self, graph, startV):
        visited = [False] * graph.V
        visited[startV] = True
        queue = deque()
        queue.appendleft(startV)
        while len(queue) > 0:
            vertex = queue.pop()
            for neighbor in graph.neighbors(vertex):
                if visited[neighbor]:
                    continue
                visited[neighbor] = True
                self.setParent(neighbor, vertex)
                queue.appendleft(neighbor)


class DepthFirstSearch(GraphTraversal):
    
    def traverse(self, graph, startV):
        visited = [False] * graph.V
        visited[startV] = True
        self._visit(graph, startV, visited)
    
    def _visit(self, graph, vertex, visited):
        for neighbor in graph.neighbors(vertex):
            if visited[neighbor]:
                continue
            visited[neighbor] = True
            self.setParent(neighbor, vertex)
            self._visit(graph, neighbor, visited)

def topologicalSort(graph):
    traversal = TopologicalSort(graph, 0)
    return traversal.result

class TopologicalSort(DepthFirstSearch):

    def traverse(self, graph, startV):
        self.result = []
        visited = [False] * graph.V
        visited[startV] = True
        self._visit(graph, startV, visited)
        for vertex in range( graph.V ):
            if visited[vertex]:
                continue
            visited[vertex] = True
            self._visit(graph, vertex, visited)
        self.result.reverse()
    
    def _visit(self, graph, vertex, visited):
        super()._visit(graph, vertex, visited)
        self.result.append(vertex)

        