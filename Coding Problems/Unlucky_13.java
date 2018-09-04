/*
* Problem at : https://www.hackerearth.com/challenge/college/institute-coding-test-2-1/algorithm/80c3c4effdf94e5189a0d213c6127d05/
* 
* Basically we have to find the total number of such strings s that are made of exactly N characters, without the number 13
* The strings may contain any integer from 0-9, repeated any number of times.
* 
* For example : if N = 2, i.e number of 2 digit numbers that do not contain 13 = 99 (since we only get one occurence 13)
* if N = 1, we get result as 10, since between 0 and 9 (inclusive), we don't get any occurence of 13
* The output file should contain answer to each query in a new line modulo 1000000009.
*
* If we look at the series carefully, we can well observe that : 
* N = 1 , op = 10
* N = 2 , op = 99
* N = 3 , op = 980
* N = 4 , op = 9701
* 
* Thus, we can observe this recurrence relation : { proof of it can be found by using inclusion-exclusion principle }
*                             
* F(0) = 1, F(1) = 10 ...              F(n) = 10 * F(n-1) - F(n-2)
*
* Once we have this recurrence relation, we can use the matrix exponentiatiion method!
* A beautiful article here : http://zobayer.blogspot.com/2010/11/matrix-exponentiation.html
* 
* Thus, for this recurrence relation, the next state F(n+1) = 10 * F(n) - F(n-1)
* i.e next state depends on previous two states
*  
*             matrix(A)      matrix(A1)
*    [M]   x  |  f(n) |   =  | f(n+1) |
*             | f(n-1 |      |  f(n)  |
*
*   We need to find this 2X2 matrix M, such that the recurrence relation is obeyed!
*   Turns out that if we have M as follows, the relation is obeyed!
*   
*    M = | 10  -1 |    
*        | 1    0 |
*
* Thus, once we find out f(n+1) from matrix(A'), we can do : [M] * matrix(A1) to get matrix(A2) which has f(n+2) and so on...
* so eventually, to get the nth element of the series, given base cases as matrix(A) = [10,1]
*   
* For getting answer for N = 2, we do (M * A)
* For getting ansewr for N = 3, we do  M * (M * A) = (M * M) * A  (associativity for matrix multiplication)
* For getting answer for N = n, we do M * M ... (n - 1) times * A i.e [M^(n-1) * A]
* 
* Now the only thing we need to take care of is the M^(n-1) i.e matrix exponentition
* This isn't too complicated as well and can be done as follows : 
*     
*     Suppose we want to find 9^13, we can write 13 as 2^3 + 2^2 + 2^0 i.e 8 + 4 + 1
*     We can represent 9^13 as : 9^(2^3) * 9^(2^2) * 9^(2^0)
*     2^3 = 2^2 + 2^2 ; 2^2 = 2^1 + 2^1
*     
*     Therefore for n = 13, we compute : {A^2, A^4, A^8}
*     Once we know A,say res = A, we can get A^2 by doing res = res*res;
*     Now, res = A^2, to get A^4, we can do res = res*res again;
*     Now, res = A^4, to get A^8, we can do res = res*res again, finally res = A^8
*     Thus, the number of multiplications required was : log(n) for n = 8, #multiplications needed was 3 i.e log(8)
*     Next since we know 13 can be written as 8 + 4 + 1
*     We can think of 13 in terms of its binary representation as : ..001101
*     i.e whereever we have 1 in the binary representation we multiply those computed values
*     since we need A^13 i.e A^8 * A^4 * A^1, and we already are computing powers for 2,4 and 8
*     hence, wherever we encounter a 1 in the binary representation, we multiply the current computed power to our result.
*     eg: initially result = I (identity matrix or 1) and our computed_power = A , n = 13
*    
*     We start checking bits from lsb to msb... since in 13, lsb = 1 i.e set,
*     we do : result = result * computed_power
*             computed_power = computed_power * computed_power  
*     result now has : I * A = A 
*     We have found out A^2 by doing computed_power = computed_power * computed_power
*     
*     
*     repeat by checking if the next bit from right side (i.e lsb to msb) is set or not, since in 13, it's not set,
*     we only do : computed_power = computed_power * computed_power
*     result now has :  A 
*     We have found out A^4 by doing computed_power = computed_power * computed_power
*     
*     
*     repeat by checking if the next bit from right side (i.e lsb to msb) is set or not, since in 13, it's set,
*     we do : result = result * computed_power
*             computed_power = computed_power * computed_power  
*     result now has :  A * A^4
*     We have found out A^8 by doing computed_power = computed_power * computed_power
*
*     repeat by checking if the next bit from right side (i.e lsb to msb) is set or not, since in 13, it's set,
*     we do : result = result * computed_power
*             computed_power = computed_power * computed_power  
*     result now has :  A * A^4 * A^8
*     We have found out A^16 by doing computed_power = computed_power * computed_power
*
*     Since 16 > 13, we break out of the loop and return result. (i.e we only iterate floor(log(n))+1 times)
*     turns out that floor(log(n)) + 1 is also the minimum number of bits needed to represent the number 'n' in binary!
* 
* This way, once we have computed the (n-1)th power of Matrix M, we can multiply it with matrix(A) and that gives us the matrix
* from which we can easily obtain our result f(n+1) which is stored at matrix(An-1) at index [0,0]
*/

import java.io.*;
import java.util.*;
 
public class Solution {
 
public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    for(int i = 0; i < n; i++){
        System.out.println(solution(Integer.parseInt(br.readLine())));          
    }    
}
 
    static long solution(int n){
        // trivial cases
        if (n == 0) return 1;
        if (n == 1) return 10;
        
        // initializing our two matrices with values as mentioned in the algo
        long[][] M = {{10,-1},{1,0}};
        long[][] A = {{10},{1}};
        
        // result will be M^(n-1) * A as explained in the algo
        long[][] res = mul(power(M,n-1),A);
      
        // the value required is obtained from the 1st element of the result matrix
        return res[0][0];     
    }
    
    // helper method for calculating the power of a matrix as explained in the algo
    static long[][] power(long[][] M, int n){
        // initialize a result matrix
        long[][] res = new long[M.length][M[0].length];
      
        // the result matrix = I (identity matrix) initially, hence we diagonalize it with 1's
        for(int i = 0; i < res.length; i++){
            for(int j = 0; j < res.length; j++){
                if(i==j) res[i][j] = 1;
            }
        }
      
        // we also maintain the computed_power matrix as explained in the algo, it's initialized as M itself
        long[][] curr = M;
        
        while(n > 0){
            // if lsb is set i.e 1, we update result = result * computed_power
            if((n&1) == 1){
                res = mul(res,curr);
            }
            n >>= 1;
            // keep updating computed_power = computed_power * computed_power
            curr = mul(curr,curr);
        }
        return res;
        // note that the "multiplications" we did are actually matrix multiplications
    }
    
    // helper function for matrix multiplication with modulus checks
    static long[][] mul(long[][] A, long[][] B){
        int m1 = A.length;
        int n1 = A[0].length;
        int m2 = B.length;
        int n2 = B[0].length;
        
        long[][] res = new long[m1][n2];
        for(int i = 0; i < m1; i++){
            for(int k = 0; k < n2 ; k++){
                res[i][k] = 0;
                for(int j = 0; j < n1; j++){
                    res[i][k] += (A[i][j]*B[j][k])%1000000009;
                    if(res[i][k] >= 1000000009) res[i][k] -= 1000000009;
                    if(res[i][k] < 0) res[i][k] += 1000000009;  // we don't want any negative remainders
                    // note that we can also keep the negative remainders and then finally while outputting the result
                    // if result is a negative numbers, we add the mod value to make it positive. Both work.
                }
            }
        }
        // return product matrix
        return res;
    }
}


/*
 Derivation of f(n+1) = 10*f(n) - f(n-1) , by Inclusion-Exclusion principle :
 
 Let's consider the case for N = 3 i.e 3 digits : __ __ __
 Here total number of possiblities are considered first, hence each digit can be any from [0, 9] i.e 10 choices
 therefore total = 10^3 
 Out of this, we need to deduct the numbers which contain 13 in them. Now, since 13 uses 2 consecutive digits, let's
 consider it as one unit. Thus, initially, we had 3 digits. We considered 13 as one unit, therefore we now only have two slots
 i.e __ __ out of which in one slot, 13 will go and the remaining slot can be filled with any from [0, 9]
 Thus, 2C1 for choosing one of the two slots and 10 choices for filling the remaining slot with any from [0, 9]
 So, we have : 10^3 - 2C1*10
 
 Let's consider the case for N = 4 ie. 4 digits : __ __ __ __
 Total = 10^4 
 Out of this, we deduct those numbers having 13 in them, similar as above, we consider 13 as one unit. Thus, now we have 3 slots
 __ __ __ We can place 13 in any one of these slots, and the other two will be filled with digits from [0, 9]
 Thus, we have : 10^4 - 3C1*(10^2) 
 Now, comes the "inclusion exclusion part", while deducting the numbers having 13 in them , we overcounted.
 For example, suppose 13 went to the 1st slot (we are talking about these slots : __ __ __ ) and we have : 
 1 3 __ __ 
 Suppose the 3rd slot if filled with 1 and 4th slot is filled with 3, so we have : 
 1 3 1 3
 This number is deducted when the 1st slot was filled with 13
 Now supposed 13 went to slot 3, then we have : 
 __ __ 1 3
 Suppose the 1st slot gets filled with 1 and 2nd slot is filled with 3, so we have : 
 1 3 1 3
 This number is again deducted when the 3rd slot was filled with 13
 Thus the same number was deducted more than once, i.e overcouting occured, so we need to add back this number
 Note that 13 going to the 2nd slot didn't create another repeated number.
 
 Thus following the inc-exc princ, we add the numbers that have two 13's in them, i.e
 So now since we have two 13's to fill with, the number of slots now become 2
 __ __ , we have to place two similar objects (13's) in those two slots which can be done in 2C2 ways i.e 1 way (or 2!/2! ways)
 
 Therefore finally, we have : 
 10^4 - 3C1*(10^2) + 2C2
 
 Let's rewrite it as : 
 N = 4 --->             f(4)  =  4C0*(10^4) - 3C1*(10^2) + 2C2(10^0)  
 Similarly;
 
 N = 5 --->             f(5)  =  5C0*(10^5) - 4C1*(10^3) + 3C2*(10^1)
 
 N = 6 --->             f(6)  =  6C0*(10^6) - 5C1*(10^4) + 4C2*(10^2) - 3C3*(10^0)
 
 Generalizing, we have : 
 
 N = k --->             f(k)  =  kC0*(10^k) - (k-1)C1*(10^(k-2)) + (k-2)C2*(10^(k-4)) - (k-3)C3*(10^(k-6)) ...
 
 
 N = k + 1 --->        f(k+1) =  (k+1)C0*(10^(k+1)) - kC1*(10^(k-1)) + (k-1)C2*(10^(k-3)) - (k-2)C3*(10^(k-5)) ...
 
 
 Upon observing we can see 10*f(k) has some similar terms with f(k+1) lets try to do 10*f(k) - f(k+1), we get
 
 (kC0 - (k+1)C0)*(10^(k+1)) + (kC1 - (k-1)C1)*(10^(k-1)) - ((k-1)C2 - (k-2)C2)*(10^(k-3)) + ((k-2)C3 - (k-3)C3)*(10^(k-5) ...
 
 Now, we know that nCr + nCr+1 = n+1Cr+1 , i.e n+1Cr+1 - nCr+1 = nCr, using this reduction, we can simply the above into :
 
 0 + (k-1)C0*(10^(k-1)) - (k-2)C1*(10^(k-3)) + (k-3)C2*(10^(k-5)) ...
 

 which is nothing but f(k-1)
 
 Thus, we obtain the relation : 10*f(k) - f(k+1) = f(k-1) 
                         ==>    f(k+1) = 10*f(k) - f(k-1)
                         
*/
