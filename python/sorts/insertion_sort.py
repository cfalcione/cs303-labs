def insertion_sort(array, left, right):
    for l in range(left + 1, right + 1):
        i = l
        while i > left and array[i - 1] > array[i]:
            swap(array, i, i - 1)
            i -= 1
    return array

def swap(array, i, j):
    array[i], array[j] = array[j], array[i]