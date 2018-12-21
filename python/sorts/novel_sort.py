def novel_sort(array, start, end):
    while start < end:
        min_index = min( range(start, end + 1), key=lambda i: array[i] )
        max_index = max( range(start, end + 1), key=lambda i: array[i] )

        swap(array, start, min_index)

        if max_index == start:
            swap(array, min_index, end)
        else:
            swap(array, end, max_index)
        
        
        start += 1
        end -= 1

def swap(array, i, j):
    array[i], array[j] = array[j], array[i]