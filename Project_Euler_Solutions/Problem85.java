/*
  Problem at : https://projecteuler.net/problem=85
*/

public class Problem85 {
    public static void main(String args[]) {
        int target = 2000000;
        int y = 1, x = 2000;
        int sx = (x*(x + 1)*y*(y+1))>>2;
        int sy = 1;
        int err = (sx * sy) - target;
        int area = x*y;
        while(x >= y)
        {
            int curr = sx*sy;
            int curr_err = Math.abs(target - curr);
            if(err > curr_err){ 
                err = curr_err;
                area = x*y;
            }
            if(curr > target)
                sx -= x--;
            else
                sy += ++y;
        }
        System.out.println(area);
    }
}


