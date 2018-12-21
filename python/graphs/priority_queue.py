from heapq import heappush, heappop
from itertools import count

REMOVED = '<removed-item>'

class PriorityQueue():
    
    def __init__(self):
        self.pq = []
        self.entry_finder = {}
        self.counter = count()
        self.length = 0

    def add(self, item, priority=0):
        if item in self:
            self.remove(item)
        count = next(self.counter)
        entry = [priority, count, item]
        self.entry_finder[item] = entry
        heappush(self.pq, entry)
        self.length += 1
    
    def update(self, item, priority):
        self.add(item, priority)

    def remove(self, item):
        entry = self.entry_finder.pop(item)
        entry[-1] = REMOVED
        self.length -= 1

    def pop(self):
        pq = self.pq
        while pq:
            priority, _, item = heappop(pq)
            if item is not REMOVED:
                del self.entry_finder[item]
                self.length -= 1
                return item, priority
        raise KeyError('pop from an empty priority queue')
    
    def __contains__(self, item):
        return item in self.entry_finder

    def __len__(self):
        return self.length
