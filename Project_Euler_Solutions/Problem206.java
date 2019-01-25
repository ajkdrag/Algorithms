/*
  Problem at : https://projecteuler.net/problem=206
*/

public class Problem206 {
    public static void main(String args[]) {
            for(long a = 0; a <= 9; ++a){
                for(long b = 0; b <= 9; ++b){
                    for(long c = 0; c <= 9; ++c){
                        for(long d = 0; d <= 9; ++d){
                            for(long e = 0; e <= 9; ++e){
                                for(long f = 0; f <= 9; ++f){
                                    for(long g = 0; g <= 9; ++g){
                                        long num1 =  (((((((10 + a)*10 + b)*10 + c)*10 + d)*10 + e)*10 + f)*10 + g)*10 + 3;
                                        long num2 = num1 + 4;
                                        if(isValid(num1*num1)){
                                            System.out.println(num1*10);
                                            return;
                                        }
                                        else if(isValid(num2*num2)){
                                            System.out.println(num2*10);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }
    
    static boolean isValid(long num){
        int count = 9;
        while(num > 0){
            if(num%10 != count--)
                return false;
            num/=100;
        }
        return true;
    }
}


