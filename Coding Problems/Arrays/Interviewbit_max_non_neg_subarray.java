/*
  Problem at : https://www.interviewbit.com/problems/max-non-negative-subarray/
*/

public class Solution {
    public ArrayList<Integer> maxset(ArrayList<Integer> arr) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int l = 0;
        int cumS = 0;
        int bestL = -1;
        int r = -1;
        int maxS = -1;
        
        for(int i = 0; i < arr.size(); ++i){
            int curr = arr.get(i);
            if(curr < 0){
                l = i + 1;
                cumS = 0;
            }
            else {
                cumS += curr;
                if(cumS > maxS || (cumS == maxS && (i - l + 1) > (r - bestL + 1))){
                    maxS = cumS;
                    r = i;
                    bestL = l;
                }
            }
        }
        if(bestL == -1)
            return res;
        for(int i = bestL ; i <= r; ++i){
            res.add(arr.get(i));
        }
        return res;
    }
}


