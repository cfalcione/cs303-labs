def heap_sort(array, left, right):
    build_max_heap(array, left, right)

    for j in range(right, left - 1, -1):
        swap(array, left, j)
        max_heapify(array, left, j - 1, left)
    
def build_max_heap(array, start, end):
    for i in range((start + end) // 2, start - 1, -1):
        max_heapify(array, start, end, i)

def max_heapify(array, start, end, index):
    if index < start or index > end:
        return
    maximum = max(
        valid_children(start, end, index),
        key=lambda i: array[i]
    )
    if maximum == index:
        return
    swap(array, index, maximum)
    max_heapify(array, start, end, maximum)

def valid_children(start, end, index):
    return filter( lambda i: start <= i <= end, (
            index,
            left_child(start, index),
            right_child(start, index)
        )
    )

def left_child(offset, index):
    return offset + 2 * (index - offset) + 1

def right_child(offset, index):
    return left_child(offset, index) + 1

def swap(array, i, j):
    array[i], array[j] = array[j], array[i]