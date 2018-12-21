from search import linear_search, binary_search
import unittest
from random import Random
from timeit import default_timer

rand = Random()


class SearchTest(unittest.TestCase):

    def test_main(self):
        print("n", "binary_search", "linear_search", sep=',')
        for exponent in range(4, 24):
            with self.subTest(i=exponent):
                size = 1 << exponent
                array = self.make_random_array(size)
                target = self.get_target(array)

                linear_time = self.__test_search_function(linear_search, array, target)
                binary_time = self.__test_search_function(binary_search, array, target)

                print(size, binary_time, linear_time, sep=',')
    
    def test_not_found(self):
        size = 723
        array = self.make_random_array(size)
        self.assertEqual(linear_search(array, size + 100), -1)
        self.assertEqual(binary_search(array, size + 100), -1)

    def make_random_array(self, size):
        array = [rand.randint(0, 1 << 31) for _ in range(size)]
        return array

    def get_target(self, array):
        return rand.choice(array)

    def __test_search_function(self, function, array, target):
        res, time = time_function(function, array, target)
        self.assertEqual(array[res], target)
        return time

def time_function(function, *args):
    before = default_timer()
    res = function(*args)
    after = default_timer()
    return res, after - before

if __name__ == "__main__":
    unittest.main()
