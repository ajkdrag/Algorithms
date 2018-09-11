/*
  Given an unsorted array which allows duplicates, we need to find the minimum difference between the indices of two specific elements
  of the array. If one of them or both of them doesn't exist in the array, return -1
  
  eg: arr = {2, 5, 7, 2, 8, 3, 4, 3} and x = 2, y = 3
  Here output should be 2 because closest difference is 2 (5 - 3  = 2)
  
  Algo:
  We use two varibles to store the most recent occurences of the two numbers. 
  If we see 'x', we find the difference between the current index of 'x' and the most recent index of 'y' and update result.
  If we see 'y', we find the difference between the current index of 'y' and the most recent index of 'x' and update result.
  
*/

class SolutionMinDiff {
    
    int solve(int[] arr, int x, int y){
        
        int n = arr.length;
        int index_x = -1;
        int index_y = -1;
        
        int min_d = Integer.MAX_VALUE;
        for(int i = 0; i < n; ++i){
            if(arr[i] == x){
                if(index_y != -1) min_d = Math.min(min_d, (i - index_y));
                index_x = i;
            }
            else if(arr[i] == y){
                if(index_x != -1) min_d = Math.min(min_d, (i - index_x));
                index_y = i;
            }
        }
        return min_d == Integer.MAX_VALUE ? -1 : min_d;
    }
    
}
