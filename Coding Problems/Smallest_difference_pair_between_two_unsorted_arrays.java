/*
  Given two unsorted arrays, find the min absolute difference between two elements such that one is from each array
  eg : A1 = [1,6,3,7] 
       A2 = [10,8,2,9]
       
       min difference = 1  (|3-2| = 1, also |7-8| = 1)
  
  Algorithm :
  1) Sort the two arrays : A1 = [1,3,6,7]; A2 = [2,8,9,10]
  2) Maintain two pointers i and j, i moves along A1 and j along A2 (initially i = 0, j = 0)
  3) Suppose an array is S = [3,6,8,10] and an element is say 4.
     What can be the smallest difference for 4? i.e one element is 4 and the other element from S
     Clearly, it will be = min(4 - (largest_element_less_than_4), (smallest_element_greater_than_4) - 4)
     In this case it's = min((4-3), (6-4)) = 1
     How did we acheive that? We simply loop through S while (S[i+1] < 4)
     This places 'i' such that S[i] < 4 < S[i+1] which is what we want
  4) Thus, we skip through the smaller elements and when we reach that position described as above, we compute difference
     and compare with the current minimum and update the current minimum, if (difference < min)
  5) Now, we walk through the algo :
     
     initially, min = Integer.MAX_VALUE
               
     #1        i = 0,
               j = 0,
               A1[0] < A2[0] 
               ==> We loop through A1 until we reach a position for i such that A1[i] < A2[j] < A1[i+1]
                   i = 0 is the required position
               We compute the difference (A2[j] - A1[i]) = 2 - 1 = 1  { 1 < min, thus, min = 1)
               We increment i,
     
     #2        i = 1,
               j = 0,
               A1[1] > A2[0]
               ==> We loop through A2 until we reach a position for j such that A2[j] < A1[i] < A2[j+1]
                   j = 0 is the required position
               We compute the difference (A1[i] - A2[j]) = 3 - 2 - 1  { 1 >= min, thus do nothing)
               We increment j,
               
     #3        i = 1,
               j = 1,
               A1[1] < A2[1]
               ==> We loop through A1 until we reach a position for i such that A1[i] < A2[j] < A1[i+1]
                   we reach the end of A1 without finding that position
                   i = 3
               We compute the difference (A2[j] - A1[i]) = 8 - 7 = 1  { 1 >= min, thus do nothing)
               We increment i,
               
     #4        i = 4,
               j = 1,
               Since i >= |A1| , we break out of the loop
               
     
     min = 1, and we return 1 in this case.
     To summarize, we are finding [ max_of(A1[i],A2[j]) - it's "floor_value" from the other array ]    
     Note that we can use Binary Search approach to find the floor_value,
     but since the upper bound for this problem is the sorting algorithm, further optimizations aren't too beneficial.
*/  


import java.util.Arrays;

public class Solution {  
     public int getSmallestDifference(int[] array1, int[] array2, int n1, int n2){
        
        // trivial case 
        if(n1==0 && n2==0) return 0;
        
        int min = Integer.MAX_VALUE;
        
        if(n2 == 0){
            // return min from the other array
            for(int i = 0; i < n1; i++){
                if(array1[i] < min) min = array1[i];
            }
            return min;
        }
        
        if(n1 == 0){
            // return min from the other array
            for(int j = 0; j < n2; j++){
                if(array2[j] < min) min = array2[j];
            }
            return min;
        }
        
        // sort both the arrays O(mlogm + nlogn) 
        Arrays.sort(array1);
        Arrays.sort(array2);
        
        int i = 0;
        int j = 0;
        
        // O(m + n) worst case for arrays such as A : [1,3,5,7,9...] ; B = [2,4,6,8,10...] 
        // i.e when A[i] and B[j] strictly alternate eg: A[i] > B[j], A[i+1] < B[j+1], A[i+2] > B[j+2]... 
        // But overall time is still dominated by the sorting algorithm
        
        while(i < n1 && j < n2){
            if(array2[j] > array1[i]){
                // we move 'i' until we fulfil array1[i] < array2[j] < array1[i+1]
                while(i + 1 < n1 && array1[i+1] < array2[j] ){
                    i++;
                }
                // compute the difference between array1[i] and array2[j] and increment 'i'
                if(array2[j]-array1[i] < min) min = array2[j]-array1[i];
                i++;
            }
            else if(array1[i] > array2[j]){
                // we move 'j' until we fulfil array2[j] < array1[i] < array2[j+1]
                while(j + 1 < n2 && array2[j+1] < array1[i] ){
                    j++;
                }
                // compute the difference between array2[j] and array1[i] and increment 'j'                
                if(array1[i]-array2[j] < min) min = array1[i]-array2[j];
                j++;
            }
            
            // if the two values are equal, we simply return 0 since the min. absolute difference can't be less than 0
            else return 0;
        }
        
        return min;
    }
     
}

