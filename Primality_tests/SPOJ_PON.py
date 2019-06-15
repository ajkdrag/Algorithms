/*
  Problem at : https://www.spoj.com/problems/PON/
*/

import random
_mrpt_num_trials = 5  # number of bases to test

def is_probable_prime(n):
    assert n >= 2
    # special case 2
    if n == 2:
        return True
    # ensure n is odd
    if n % 2 == 0:
        return False
    # write n-1 as 2**s * d
    # repeatedly try to divide n-1 by 2
    s = 0
    d = n - 1
    while True:
        quotient, remainder = divmod(d, 2)
        if remainder == 1:
            break
        s += 1
        d = quotient
    assert(2 ** s * d == n - 1)

    def try_composite(a):
        k = pow(a, d, n)
        if k == 1 or k == n - 1:
            return False
        for i in range(s):
            k = (k*k)%n
            if k == n - 1 :
                return False
        return True  

    for _ in range(_mrpt_num_trials):
        a = random.randrange(2, n)
        if try_composite(a):
            return False

    return True  

for i in range(int(input())):
    a = int(input())
    if is_probable_prime(a):
        print("YES")
    else:
        print("NO")


