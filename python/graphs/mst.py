def prim(graph, root):
    parents = [None for _ in range(graph.V)]
    costs = [float('inf') for _ in range(graph.V)]
    candidates = {v for v in range(graph.V) }
    costs[root] = 0

    for vertex in minimumVertices(candidates, costs):
        candidates.remove(vertex)
        for neighbor, weight in graph.neighborsWithWeights(vertex):
            if neighbor not in candidates:
                continue
            if weight >= costs[neighbor]:
                continue
            costs[neighbor] = weight
            parents[neighbor] = vertex
    
    return parents, costs
    

def minimumVertices(candidates, costs):
    while len(candidates) > 0:
        yield nextVertex(candidates, costs)

def nextVertex(candidates, costs):
    return min(candidates, key=lambda v: costs[v])
