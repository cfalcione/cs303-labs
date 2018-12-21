from unittest import TestCase, main

from hashmap import regularMap, linearMap, quadraticMap, pythonMap


maps = {
    'regular': regularMap,
    'linear': linearMap,
    'quadratic': quadraticMap,
    'python': pythonMap,
}

class HashMapTest(TestCase):

    def testHashMap(self):
        case = list(zip( range(3000), range(3000) ))
        for name, factory in maps.items():
            with self.subTest(name=name):
                self._testTable(factory(), case)
    
    def _testTable(self, hashmap, case):
        for key, val in case:
            hashmap.put(key, val)
            self.assertEqual(hashmap[key], val)
            hashmap[key] = 2 * val
            self.assertEqual(hashmap[key], val * 2)
        for key, val in case:
            self.assertTrue(key in hashmap)
        for i in range( 4 ):
            key, val = case[i]
            del hashmap[key]
            self.assertFalse(key in hashmap)
        self.assertEqual( len(hashmap), len(case) - 4 )


if __name__ == "__main__":
    main()
