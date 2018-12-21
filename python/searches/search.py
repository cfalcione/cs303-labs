def linear_search(array, target):
    for i, num in enumerate(array):
        if num == target:
            return i
    return -1

def binary_search(array, target):
    array.sort()
    return do_binary_search(array, target, 0, len(array) - 1)

def do_binary_search(array, target, left, right):
    if left > right:
        return -1
    mid = (left + right) // 2
    mid_val = array[mid]
    if target == mid_val:
        return mid
    if target < mid_val:
        return do_binary_search(array, target, left, mid - 1)
    return do_binary_search(array, target, mid + 1, right)