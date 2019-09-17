/*
  Problem at: https://www.interviewbit.com/problems/repeat-and-missing-number-array/
*/

public class Solution {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public ArrayList<Integer> repeatedNumber(final List<Integer> A) {
        HashSet<Integer> seen = new HashSet<Integer>();
        int first = -1;
        int res = 0;
        for(int i = A.size(); i >= 1; --i){
            int curr = A.get(i-1);
            res ^= i^curr;
            if(seen.contains(curr)){
                first = curr;
            }
            else
                seen.add(curr);
        }
        int second = res^first;
        ArrayList<Integer> rest = new ArrayList<Integer>();
        rest.add(first);
        rest.add(second);
        return rest;
    }
}


