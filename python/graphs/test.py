from unittest import TestCase, main
from timeit import default_timer

from graph import Graph
from edge_model import AdjacencyList
from priority_queue import PriorityQueue

from traverse import BreadthFirstSearch, DepthFirstSearch, topologicalSort
from mst import prim
from paths import dijkstra


class PriorityQueueTest(TestCase):
    def testPQ(self):
        pq = PriorityQueue()
        self.assertEqual(len(pq), 0)
        pq.add(3, 0)
        self.assertEqual(len(pq), 1)
        pq.update(3, 1)
        self.assertEqual(len(pq), 1)
        item, priority = pq.pop()
        self.assertEqual(item, 3)
        self.assertEqual(priority, 1)
        self.assertEqual(len(pq), 0)

        pq.add(1, 10)
        pq.add(2, 5)
        self.assertEqual(pq.pop(), (2, 5))
        self.assertEqual(pq.pop(), (1, 10))

class BFSTest(TestCase):

    def testMedium(self):
        # before_parse = default_timer()
        graph = parseGraph("mediumG.txt", AdjacencyList(), False)
        # print("mediumG parse time: ", default_timer() - before_parse)

        self.assertListEqual( 
            list(graph.neighbors(0)),
            [15, 24, 44, 49, 58, 59, 68, 80, 97, 114, 149, 160, 163, 176, 191, 202, 204, 209, 211, 222, 225]
        )

        # before_bfs = default_timer()
        bfs = BreadthFirstSearch(graph, 0)
        # print("mediumG bfs time: ", default_timer() - before_bfs)

        self.assertListEqual( bfs.pathTo(15), [0, 15] )
        self.assertListEqual( bfs.pathTo(90), [0, 44, 93, 226, 138, 233, 90] )
    
    def _testLarge(self):
        # before_parse = default_timer()
        graph = parseGraph("largeG.txt", AdjacencyList(), False)
        # print("largeG parse time: ", default_timer() - before_parse)

        self.assertEqual( len(list(graph.neighbors(0))),  11)

        # before_bfs = default_timer()
        bfs = BreadthFirstSearch(graph, 0)
        # print("largeG bfs time: ", default_timer() - before_bfs)


        self.assertEqual(len(bfs.pathTo(102578)), 390)
        self.assertEqual(len(bfs.pathTo(1)), 419)


class DFSTest(TestCase):

    def testMedium(self):
        graph = parseGraph("mediumG.txt", AdjacencyList(), False)

        dfs = DepthFirstSearch(graph, 0)
        self.assertListEqual(dfs.pathTo(15), [0, 15])
        self.assertEqual(len(dfs.pathTo(80)), 33)

    def testTiny(self):
        graph = parseGraph("tinyG.txt", AdjacencyList(), False)

        dfs = DepthFirstSearch(graph, 0)

        self.assertListEqual(dfs.pathTo(4), [0, 5, 4])
        self.assertListEqual(dfs.pathTo(7), [])


class TopologicalSortTest(TestCase):

    def testTinyDG(self):
        graph = parseGraph("tinyG.txt", AdjacencyList(), True)

        topSort = topologicalSort(graph)

        self.assertListEqual(topSort, [9, 11, 10, 12, 7, 8, 0, 6, 2, 1, 5, 4, 3])


class PrimsTest(TestCase):

    def testTinyDG(self):
        graph = parseGraph("tinyDG.txt", AdjacencyList(), True)
        mst = prim(graph, 0)
        
        self.assertEqual(
            mst, 
            (
                [None,   5,   0,   1,   5,   7,   3,   2],
                [   0, .32, .26, .29, .35, .28, .52, .34]
            )
        )
    
    def _testBenchmark(self):
        print("file", "vertices", "edges", "time", sep=', ')
        for filename in ["tinyDG.txt", "mediumDG.txt", "largeDG.txt", "XtraLargeDG.txt"]:
            graph = parseGraph(filename, AdjacencyList(), True)
            before_time = default_timer()
            mst = prim(graph, 0)
            time = default_timer() - before_time
            print(filename, graph.V, graph.E, time, sep=', ')


class DijkstrasTest(TestCase):

    def testTinyDG(self):
        graph = parseGraph("tinyDG.txt", AdjacencyList(), True)
        pathTree = dijkstra(graph, 0)
        parents, weights = dijkstra(graph, 0)

        for i, weight in enumerate(weights):
            weights[i] = round(weight, 5)

        self.assertListEqual(parents, [None, 5   , 0   , 7   , 0   , 4   , 3   , 2  ])
        self.assertListEqual(weights, [   0, 1.05, 0.26, 0.99, 0.38, 0.73, 1.51, 0.6])
    
    def testBenchmark(self):
        print("file", "vertices", "edges", "time", sep=', ')
        for filename in ["tinyDG.txt", "mediumDG.txt", "largeDG.txt", "XtraLargeDG.txt"]:
            graph = parseGraph(filename, AdjacencyList(), True)
            before_time = default_timer()
            mpt = dijkstra(graph, 0)
            time = default_timer() - before_time
            print(filename, graph.V, graph.E, time, sep=', ')


def parseGraph(filename, edgeModel, directed = False):
    with open(filename, 'r') as file:
        vertexCount = int(next(file))
        graph = Graph(edgeModel, vertexCount, directed)
        next(file) # disregard the number of edges
        for line in file:
            lineData = line.split()
            fromV, toV = map(int, lineData[:2])
            weight = 1 if len(lineData) < 3 else float(lineData[2])
            graph.addEdge(fromV, toV, weight)
    return graph


if __name__ == "__main__":
    main()