def make_merge_sort(inner_sort, threshold = 8):
    def sort(array, left, right):
        return merge_sort_util(inner_sort, threshold, array, left, right)
    return sort

def merge_sort_util(inner_sort, threshold, array, left, right):
    width = right - left
    if width <= threshold:
        inner_sort(array, left, right)
        return
    mid = (left + right) // 2
    merge_sort_util(inner_sort, threshold, array, left, mid)
    merge_sort_util(inner_sort, threshold, array, mid + 1, right)
    merge(array, left, mid, right)

def merge(array, left, mid, right):
    array[left:right+1] = merge_generator(array, left, mid, right)

def merge_generator(array, left, mid, right):
    left_iter = SubarrayIterator(array, left, mid)
    right_iter = SubarrayIterator(array, mid + 1, right)

    while left_iter and right_iter:
        yield next(min(left_iter, right_iter))
    
    yield from left_iter
    yield from right_iter



class SubarrayIterator():

    def __init__(self, array, start, end):
        self.copy = array[start:end+1]
        self.idx = 0
        self.end = len(self.copy) - 1
    
    def __bool__(self):
        return self.idx <= self.end
    
    def __lt__(self, other):
        return self.peek() < other.peek()
    
    def __iter__(self):
        return self
    
    def __next__(self):
        if not self:
            raise StopIteration
        result = self.peek()
        self.idx += 1
        return result
    
    def peek(self):
        return self.copy[self.idx]
    