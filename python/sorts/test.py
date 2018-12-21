import unittest
import sys
from itertools import islice
from random import randint
from timeit import default_timer

from insertion_sort import insertion_sort
from merge_sort import make_merge_sort
from heap_sort import heap_sort
from quicksort import quicksort_basic, quicksort_middle, quicksort_median_three
from novel_sort import novel_sort

algorithms = {
    "reference_sort": lambda a, start, end: a.sort(),
    "insertion_sort": insertion_sort,
    "merge_sort": make_merge_sort(insertion_sort),
    "heap_sort": heap_sort,
    "quicksort_basic": quicksort_basic,
    "quicksort_middle": quicksort_middle,
    "quicksort_median_three": quicksort_median_three,
    "novel_sort": novel_sort
}

filename_prefix = 'input_files/'
lab_files = [
    'input_100.txt',
    'input_1000.txt',
    # 'input_5000.txt',
    # 'input_10000.txt',
    # 'input_100000.txt',
    'Input_Random.txt',
    'Input_ReversedSorted.txt',
    'Input_Sorted.txt'
]
transaction_log_file = "NovelSortInput.txt"

timeout = 1.0
    

class SortTest (unittest.TestCase):

    def test_sorts_random(self):
        # print_headers('n', algorithms)
        valid_set = set(algorithms.keys())
        for exponent in range(2, 28):
            if len(valid_set) == 0:
                break
            size = 1 << exponent + 1
            array = make_random_array(size)
            times = []
            for name, algorithm in algorithms.items():
                if name not in valid_set:
                    times.append('TIMED_OUT')
                    continue
                sorted_copy, time = self.__test_sort(algorithm, array)
                times.append(time)
                if time > timeout:
                    valid_set.remove(name)
                
            # print_times(size, times)
    
    def test_sorts_lab_input(self):
        # print_headers('file', algorithms)
        for filename in lab_files:
            array = get_lab_input(filename_prefix + filename)
            times = []
            for name, algorithm in algorithms.items():
                sorted_copy, time = self.__test_sort(algorithm, array, name)
                times.append(time)
            # print_times(filename, times)
    
    def test_log_sort(self):
        data = get_transaction_log(filename_prefix + transaction_log_file)
        algorithms['merge_sort'](data, 0, len(data) - 1)
        print(*data, sep="\n"),

        with self.subTest(name="data is sorted by city"):
            self.assertSorted(map(lambda row: row[0], data))

        with self.subTest(name="data is still sorted by date"):
            times_by_city = {}
            for city, time in data:
                times_by_city.setdefault(city, [])
                times_by_city[city].append(time)

            for city, times in times_by_city.items():
                self.assertSorted(times)

    
    def __test_sort(self, algorithm, array, name=None):
        with self.subTest(name=name if name is not None else algorithm.__name__, size=len(array)):
            sorted_copy, time = time_sort_algorithm(algorithm, array)
            self.assertSorted(sorted_copy)
            return sorted_copy, time
    
    def assertSorted(self, array):
        self.assertTrue( all( map(lambda pair: pair[1] >= pair[0], zip(array, islice(array, 1, None))) ) )


def print_headers(row_header, algorithms):
    print(row_header, *algorithms.keys() , sep=',')

def print_times(size, times):
    print(size, *times, sep=',')

def make_random_array(size):
    return [randint(-(1 << 30), 1 << 30) for _ in range(size)]


def get_lab_input(filename):
    result = []
    with open(filename, 'r') as file:
        for line in file:
            numbers = map(int, line.strip().split(' '))
            result.extend(numbers)
    return result

def get_transaction_log(filename):
    result = []
    with open(filename, 'r') as file:
        for line in file:
            result.append(tuple(line.split()))
    return result

def time_sort_algorithm(algorithm, array):
    copy = array.copy()
    time_before = default_timer()
    algorithm(copy, 0, len(copy) - 1)
    return copy, default_timer() - time_before



if __name__ == "__main__":
    unittest.main()
