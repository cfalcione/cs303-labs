def make_quick_sort(pivot_func):
    return lambda array, left, right: quicksort(pivot_func, array, left, right)

def quicksort(pivot_func, array, left, right):
    if left >= right:
        return
    pivot_idx = pivot_func(array, left, right)
    mid = partition(array, left, right, pivot_idx)

    quicksort(pivot_func, array, left, mid - 1)
    quicksort(pivot_func, array, mid + 1, right)

def partition(array, left, right, pivot_idx):
    pivot = array[pivot_idx]
    swap(array, pivot_idx, right)
    i = left
    for j in range(left, right):
        if array[j] <= pivot:
            swap(array, i, j)
            i += 1
    swap(array, i, right)
    return i

def swap(array, i, j):
    array[i], array[j] = array[j], array[i]

def pivot_basic(array, left, right):
    return right

def pivot_middle(array, left, right):
    return (left + right) // 2

def pivot_median_three(array, left, right):
    mid = (left + right) // 2
    indices = [left, mid, right]
    indices.sort(key=lambda i: array[i])
    return indices[1]

quicksort_basic = make_quick_sort(pivot_basic)
quicksort_middle = make_quick_sort(pivot_middle)
quicksort_median_three = make_quick_sort(pivot_median_three)

