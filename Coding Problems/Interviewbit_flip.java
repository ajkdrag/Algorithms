/*
  Problem at : https://www.interviewbit.com/problems/flip/
*/

public class Solution {
    public ArrayList<Integer> flip(String A) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        char[] carr = A.toCharArray();
        int n = carr.length; 
        int[] arr = new int[n];
        for(int i = 0; i < n; ++i) 
            arr[i] = carr[i]-'0';
        int cm = 0;
        int max = -1;
        int left = -1;
        int right = -1;
        int lVal = 0;
        for(int i = 0; i < n; ++i){
            if(arr[i] == 0)
                cm += 1;
            else 
                cm -= 1;
            if(cm < 0){
                cm = 0;
                lVal = i + 1;
            }
            else {
                if(cm > max){
                    max = cm;
                    left = lVal;
                    right = i;
                }
            }
        }
        if(left == -1)
            return res;
        res.add(left+1);
        res.add(right+1);
        return res;
    }
}

