/*
    Given a number 'n', we need to find the closest palindrome. If the number is itself a palindrome, return itself.
    
    Algo: Note that this is a trimmed down version of a somewhat better generalized algo which we will added at some point later.
    This algo assumes that the number given will be within the max integer datatype limit. 
    Now, goal is to find the closest palindrome, which can be either greater that the number 'n' itself or less that it.
    We find both : i.e the largest number smaller than 'n' which is a palindrome and the smallest number greater than 'n', which
    is a palindrome. The one which is closest to 'n' will be our answer.
    
    For finding largest number smaller than 'n' which's a palindrome (we will call it : paliFloor) we do as follows : 
        Suppose the given number is of the form : a b c d e f
        We maintain two pointers at two extreme ends , i.e l = 0, r = 5 {here num of digits = 5 and index starts from 0}
        If digit[r] <= digit[l] , we make digit[r] = digit[l] and substract 1 from digit[r-1]. What this achieves is bring us down
        to a number that has digits r to n - 1 , matched with digits 0 to l
        If digit[r] > digit[l] , we simply make digit[r] = digit[l] and do r--,l++
        
        let's take an example for this : say 'n' = 423451 , l = 0, r = 5
        digit[l] < digit[r], so we make them equal i.e : 423454
        and then subtract 1 (with ripple borrows if required) from digit[r-1] i.e digit[4]
        thus, we have : 423444 <-- this is the smaller number closest to 'n' which has digits r --> n matched with 0 --> l
        
        Now, we again repeat this process, 'l' is still 0 and 'r' is still 5, this time digit[l] == digit[r] so we do r--,l++ 
        and move on. Now this may feel a bit redundant because just now in previous iteration we made digit[l] and digit[r] equal,
        but since we didn't change the pointers we had to do an extra loop again. This is to avoid a case as :
        say n = 2103, we show the results at every step :
        2102 <-- Note that at this step since digit[1] < digit[2], thus we make them same and decrease digit[1] by 1, we get :
        2012 <-- Now, if we update the pointers, violating our algo, we would have l = 2, r = 1 and while loop will break
        and this will be our output, which is incorrect, we can overcome this in quite a few ways : 
        we can either go as the algo is described and choose not to update the pointers in the case of digit[l] < digit[r]
        or we can update pointers in any case, but only don't update when l = r - 1, becase that is the case when this problem occurs,
        or we can subtract by 1 first, and then make the digits same.. i.e first 2102 will become 2002 and copy the digits.
        a better example for this would be the case when n = 2422 will become 2322 and then digit[r] = digit[l] will be done,
        to finally result in 2332 after that pointers will be updated, thus any of these modifications can be done.
        
    Similarly, for finding the paliCeil, we do the same operations, only the inequalities will be reversed and instead of subtracting
    1, we will be adding 1.
    
*/

class SolutionClosestPalindrome {
    
    static int getClosestPaliFloor(int n, int len){
        if(n == (int) Math.pow(10,len - 1)) return n - 1;
 
        int l = 0, r = len - 1;
        
        while(l <= r){
            int power = (int)Math.pow(10, l);
            int d1 = (n/ (int) Math.pow(10, r)) % 10;
            int d2 = (n/ power) % 10;
            
            if(d1 <= d2) {
                n -= (d2 - d1)*power;
                l++;
                r--;
            }
            if(d1 > d2) {
                n += (d1 - d2)*power;
                n -= 10*power;
            }
        }
        
        return n;
          
    }
    
    static int getClosestPaliCeil(int n, int len){
        if(n == (int) Math.pow(10,len) - 1) return n + 2;
 
        int l = 0, r = len - 1;
        
        while(l <= r){
            int power = (int)Math.pow(10, l);
            int d1 = (n/ (int) Math.pow(10, r)) % 10;
            int d2 = (n/ power) % 10;
            
            if(d1 >= d2) {
                n += (d1 - d2)*power;
                l++;
                r--;
            }
            if(d1 < d2) {
                n -= (d2 - d1)*power;
                n += 10*power;
            }
        }
        
        return n;
    }
    
    static int getClosestPali(int n){
        if(n < 10) return n;
        int len = (int)Math.log10(n) + 1;
        int paliFloor = getClosestPaliFloor(n, len);
        int paliCeil = getClosestPaliCeil(n, len);
        if((n - paliFloor) < (paliCeil - n)) return paliFloor;
        return paliCeil;
    }
    
}
