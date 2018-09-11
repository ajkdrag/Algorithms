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
