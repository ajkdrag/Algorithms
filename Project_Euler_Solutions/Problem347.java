/*
  Problem at : https://projecteuler.net/thread=347
*/

import java.util.HashSet;
public class Problem347 {
    public static void main(String args[]) {
        int N = 10000001;
        int[] arr = new int[N];
        int[] dp = new int[N];
        for(int i = N - 1; i >= 0; --i)
            arr[i] = i;
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> temp = new HashSet<>();
        long res = 0;
        for(int i = 2; i < N; ++i){
            if(arr[i] == i){
                set.add(i);
                temp.clear();
                for(int j = ((N - 1)/i)* i; j >= i; j -= i){
                    int dpVal = dp[j];
                    if(dpVal > 1)
                        continue;
                    ++dp[j];
                    int val = arr[j];
                    while(val%i == 0)
                        val /= i;
                    if(val == 1){
                        arr[j] = 1;
                        continue;
                    }
                    int prod = val * i;
                    if(dpVal == 0){
                        arr[j] = prod;
                        continue;
                    }
                    if(set.contains(val)){
                        if(!temp.contains(prod)){
                            res += j;
                            temp.add(prod);
                        }
                        arr[j] = 1;
                    }
                    else
                        arr[j] = prod;
                }
            }
        }
        System.out.println(res);
    }
}


