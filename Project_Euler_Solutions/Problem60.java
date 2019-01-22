/*
  Problem at : http://projecteuler.net/problem=60
*/

import java.util.HashSet;
import java.util.ArrayList;

public class Problem60 {
    public static void main(String args[]) {
        Solution.solve();
    }
}

class Solution {
    static int result = Integer.MAX_VALUE;
    static ArrayList<Integer> primes;
    static HashSet<Integer>[] pairs;
    static int len;
    
    static void solve(){
        fillSieve(30000);
        len = primes.size();
        pairs = new HashSet[len];
        for(int i = 0; i < len; ++i){
            int p1 = primes.get(i);
            if(p1*5 >= result)
                break;
            if(pairs[i] == null)
                findPairs(i);
            
            for(int j = i + 1; j < len; ++j){
                int p2 = primes.get(j);
                if(p1 + p2*4 >= result)
                    break;
                if(!pairs[i].contains(j))
                    continue;
                if(pairs[j] == null)
                    findPairs(j);
                    
                for(int k = j + 1; k < len; ++k){
                    int p3 = primes.get(k);
                    if(p1 + p2 + p3*3 >= result)
                        break;
                    if(!pairs[i].contains(k) || !pairs[j].contains(k))
                        continue;
                    if(pairs[k] == null)
                        findPairs(k);
                    
                    for(int l = k + 1; l < len; ++l){
                        int p4 = primes.get(l);
                        if(p1 + p2 + p3 + p4*2 >= result)
                            break;
                        if(!pairs[i].contains(l) || !pairs[j].contains(l) || !pairs[k].contains(l))
                            continue;
                        if(pairs[l] == null)
                            findPairs(l);
                        
                        for(int m = l + 1; m < len; ++m){
                            int p5 = primes.get(m);
                            if(p1 + p2 + p3 + p4 + p5 >= result)
                                break;
                            if(!pairs[i].contains(m) || !pairs[j].contains(m) || !pairs[k].contains(m) || !pairs[l].contains(m))
                                continue;
                            
                            result = p1 + p2 + p3 + p4 + p5;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }
    
    static void fillSieve(int n){
        int[] cache = new int[n];
        primes = new ArrayList<>();
        primes.add(2);
        for(int i = 3; i < n; i+=2){
            if(cache[i] == 0){
                primes.add(i);
                for(long j = i*i; j < n; j += (i<<1)){
                    cache[(int)j] = 1;
                }
            }
        }
    }
    
    static void findPairs(int x){
        HashSet<Integer> pairSet = new HashSet<>();
        for(int i = x + 1; i < len; ++i){
            if(isPrime(Long.parseLong(""+primes.get(i)+primes.get(x))) && isPrime(Long.parseLong(""+primes.get(x)+primes.get(i))))
                pairSet.add(i);
        }
        pairs[x] = pairSet;
    }
    
    static boolean isPrime(long n) {
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if(n%2 == 0 || n%3 == 0) return false;
        long sqrtN = (long) Math.sqrt(n)+1;
        for(long i = 6L; i <= sqrtN; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }
}


