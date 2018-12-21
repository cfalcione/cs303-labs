
def ncr(n, k):
    return fac(n) // ( fac(k) * fac(n - k) )

memo = {}
def fac(n):
    if n <= 2:
        return max(1, n)
    if n in memo:
        return memo[n]
    result = fac(n - 1) * n
    memo[n] = result
    return result

if __name__ == "__main__":
    from code import interact
    print("Use ncr(n, k) and fac(n)")
    interact(local=locals(), banner="")