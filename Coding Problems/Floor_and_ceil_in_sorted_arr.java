/*

  Finding the floor and ceil of a target number in a sorted array with duplicates allowed.
  T.C = O(log n)

*/

public class Solution {
    
    static int find_start(int[] arr, int start, int end, int target){
        int mid;
        while(start < end){
            mid = 1 + (end + start)>>1;
            if(arr[mid] < target) start = mid;
            else  end = mid - 1;
        }
        
        if(arr[start] != target && start + 1 < arr.length && arr[start + 1] == target) return start + 1;
        else return start;
    }
    
     static int find_end(int[] arr, int start, int end, int target){
        int mid;
        while(start < end){
            mid = (end + start)>>1;
            if(arr[mid] <= target) start = mid+1;
            else  end = mid;
        }
        
        else if(arr[end] != target && end - 1 >= 0 && arr[end - 1] == target) return end - 1;
        else return end;
    }
    
}


