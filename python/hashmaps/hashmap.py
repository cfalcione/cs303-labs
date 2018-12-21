DEFAULT_SIZE = 128
identity = lambda x: x
regularProbe = lambda hashed, current, n: 7 * current + 1 if n > 0 else hashed
linearProbe = lambda hashed, current, n: hashed + n
quadraticProbe = lambda hashed, current, n: hashed + n * n
pythonProbe = lambda hashed, current, n: 5 * current + 1 + ((hashed >> n) & 0xF)

def regularMap(hashFunc=identity):
    return HashMap(hashFunc, regularProbe)

def linearMap(hashFunc=identity):
    return HashMap(hashFunc, linearProbe)

def quadraticMap(hashFunc=identity):
    return HashMap(hashFunc, quadraticProbe)

def pythonMap(hashFunc=identity):
    return HashMap(hashFunc, pythonProbe)



class HashMap:
    def __init__(self, hashFunc=identity, probe=pythonProbe, size=DEFAULT_SIZE):
        self.hash = hashFunc
        self.probe = lambda hashed, idx, n: probe(hashed, idx, n) % self.size()
        self.table = [None] * size
        self.length = 0
        self.load = 0
        

    def get(self, key):
        idx = self._get_index(key)
        return self.table[idx].value if idx >= 0 else None


    def put(self, key, value):
        if self.load > (self.size() * 2/3):
            self._resize()
        item = Pair(key, value)
        keyHash = self.hash(item.key)
        self._putItem(item, keyHash)
    

    def delete(self, key):
        idx = self._get_index(key)
        if idx < 0:
            raise KeyError
        self.table[idx].deleted = True
        self.length -= 1
    

    def size(self):
        return len(self.table)


    def items(self):
        for item in self.table:
            if item is None or item.deleted is True:
                continue
            yield item.key, item.value

    def keys(self):
        return map(lambda item: item[0], self.items())
    
    def values(self):
        return map(lambda item: item[1], self.items())
    
    
    def _resize(self, factor=2):
        old = self.table
        self.load = 0
        self.length = 0
        self.table = [None for _ in range(self.size() * factor)]
        for item in filter(lambda x: x is not None and x.deleted is False, old):
            self._putItem(item, self.hash(item.key))


    def _get_index(self, key):
        keyHash = self.hash(key)
        idx = self.probe(keyHash, keyHash, 0)
        retries = self.size()
        for n in range(1, retries + 1):
            item = self.table[idx]
            if item is None:
                break
            if item.key == key:
                if item.deleted is True:
                    break
                return idx
            idx = self.probe(keyHash, idx, n)
        return -1


    def _putItem(self, item, keyHash):
        idx = self.probe(keyHash, keyHash, 0)
        retries = self.size()
        for n in range(1, retries + 1):
            if self.table[idx] is None:
                self.table[idx] = item
                self.length += 1
                self.load += 1
                return
            if self.table[idx].key == item.key:
                self.table[idx] = item
                return
            idx = self.probe(keyHash, idx, n)
        self._resize()
        self._putItem(item, keyHash)    


    def __len__(self):
        return self.length
    
    def __getitem__(self, key):
        res = self.get(key)
        if res is None:
            raise KeyError
        return res
    
    def __setitem__(self, key, value):
        return self.put(key, value)
    
    def __delitem__(self, key):
        return self.delete(key)
    
    def __contains__(self, key):
        return self.get(key) is not None
    
    def __iter__(self):
        return iter(self.keys)



class Pair:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.deleted = False