from priority_queue import PriorityQueue
from graph import Graph

def dijkstra(graph, source):
    parents = [None] * graph.V
    distances = [float('inf')] * graph.V
    distances[source] = 0
    visited = set()
    queue = PriorityQueue()
    queue.add(source, 0)

    while len(queue) > 0:
        vertex, distance = queue.pop()
        visited.add(vertex)
        for neighbor, weight in graph.neighborsWithWeights(vertex):
            if neighbor in visited:
                continue
            candidateDistance = distance + weight
            if candidateDistance >= distances[neighbor]:
                continue
            distances[neighbor] = candidateDistance
            parents[neighbor] = vertex
            queue.update(neighbor, candidateDistance)
    
    return parents, distances


def makePriorityQueue(distances):
    queue = PriorityQueue()
    for vertex, distance in enumerate(distances):
        queue.add(vertex, distance)
    return queue




