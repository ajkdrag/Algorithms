/*
 
 Problem at : https://projecteuler.net/problem=74

*/

public class Problem74 {
    static int[] chain_len;
    static int[] factorial = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
    static int[] temp_chain;
    
    public static void main(String args[]) {
        temp_chain = new int[60];
        chain_len = new int[2177286];
        chain_len[1] = 1;
        chain_len[2] = 1;
        chain_len[145] = 1;
        chain_len[169] = 3;
        chain_len[363601] = 3;
        chain_len[1454] = 3;
        chain_len[871] = 2;
        chain_len[45361] = 2;
        chain_len[872] = 2;
        chain_len[45362] = 2;
        
        int count_res = 0;
        for(int i = 1; i < 1000000; ++i){
            if(chain_len[i] == 0){
                get_chain_len(i);
            }
            if(chain_len[i] == 60) ++count_res;
            
        }
        System.out.println(count_res);
    }
    
    static void get_chain_len(int num){
        int curr = num;
        int counter = 0;
        while(chain_len[curr] == 0){
            temp_chain[counter++] = curr;
            int sum = get_digit_fact_sum(curr);
            if(sum == curr){
                chain_len[sum] = 1;
                break;
            }
            curr = sum;
        }
        
        int val = counter + chain_len[curr];
        for(int i = 0; i < counter; ++i){
            chain_len[temp_chain[i]] = val--;
        }
    }
    
    static int get_digit_fact_sum(int num){
        int res = 0;
        while(num > 0){
            int digit = num%10;
            res += factorial[digit];
            num /= 10;
        }
        return res;
    }
}


