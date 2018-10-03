/*
  Problem at : https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=5043
  
  The problem get reduced to finding 'X' such that (X*k mod N) gives values in {1, 2 ... N - 1} for 'k' in [1, N - 1]
  Note that when 'k' hits N, (1 + X*N) mod N gives back '1' and the cycle repeats.
  
  eg :  N = 9, X = 4,
        k = 1 ==> (X*k mod N) = 4
        k = 2 ==> (X*k mod N) = 8
        k = 3 ==> (X*k mod N) = 3
        k = 4 ==> (X*k mod N) = 7
        k = 5 ==> (X*k mod N) = 2
        k = 6 ==> (X*k mod N) = 6
        k = 7 ==> (X*k mod N) = 1
        k = 8 ==> (X*k mod N) = 5
        --------------------------
        k = 9 ==> (X*k mod N) = 0
        k = 10 ==> (X*k mod N) = 4 and the cycle will repeat...
  
  Thus, all values in {1, 2, ... N - 1} have been obtained for consecutive values of 'k' in [0, N - 1] for X = 4
  Note that this is always the case, when X and N are coprime. Because in that case, (X*k mod N) = 0 is attained
  when 'k' is a multiple of 'N' and the 1st multiple of 'N' is N itself! 
  Thus at 'k' = 9, we obtained 0 and after that the cycle starts repeating itself from (N + 1) onwards.
  If X and N are not coprime, Let's say their gcd = a, ==> X = a*b (say), and N = a*c (say)  gcd(b, c) will be 1
  ==> X*k mod N = 0
  ==> (a*b)*k mod (a*c) = 0
  ==> b*k mod c = 0
  ==> Since 'b' and 'c' are coprime, thus, 'k' has to be a multiple of 'c' and the smallest positive value = 'c' itself.
  And as c < N, this means that the cycle will start repeating from k = (c + 1) which is less than (N + 1)
  Thus, it implies that all elements in {1, 2, ... N - 1} will not be prodcued from (X*k mod N) if X and N are NOT coprime.
  
  Thus, the problem is now reduced to finding the number of coprimes < N  and this is essentially φ(N) 
  We can use modified sieve to precompute the results and then return the queries in O(1).
*/

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class Main{
    public static void main(String[] args) throws IOException{
        LaserMirrorsSolution.fillPhiSieve(100000);
        Scan input = new Scan();
        Print output = new Print();
        int t = input.scanInt();
        while(t-- > 0){
            int res = LaserMirrorsSolution.phiSieve[input.scanInt()];
            output.println(res);
        }
        output.close();
    }
}

class LaserMirrorsSolution {
    static int[] phiSieve;
    static void fillPhiSieve(int limit){
        int len = limit + 1;
        phiSieve = new int[len];
        
        for(int i = 2; i < len; ++i){
            if(phiSieve[i] == 0){
                phiSieve[i] = i - 1;
                for(int j = i<<1; j < len; j += i){
                    if(phiSieve[j] == 0) phiSieve[j] = j;
                    phiSieve[j] -= phiSieve[j]/i;
                }
            }
        }
    }
}

// fast I/O
class Scan {
    private byte[] buf=new byte[1024];    //Buffer of Bytes
    private int index;
    private InputStream in;
    private int total;
    public Scan()
    {
        in=System.in;
    }
    public int scan()throws IOException    //Scan method used to scan buf
    {
        if(total<0)
        throw new InputMismatchException();
        if(index>=total)
        {
            index=0;
            total=in.read(buf);
            if(total<=0)
            return -1;
        }
        return buf[index++];
    }
    public int scanInt()throws IOException
    {
        int integer=0;
        int n=scan();
        while(isWhiteSpace(n))    //Removing starting whitespaces
        n=scan();
        int neg=1;
        if(n=='-')                //If Negative Sign encounters
        {
            neg=-1;
            n=scan();
        }
        while(!isWhiteSpace(n))
        {
            if(n>='0'&&n<='9')
            {
                integer*=10;
                integer+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        return neg*integer;
    }
    private boolean isWhiteSpace(int n)
    {
        if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
        return true;
        return false;
    }
}

class Print {
    private final BufferedWriter bw;
    public Print()
    {
        this.bw=new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public void print(Object object)throws IOException
    {
        bw.append(""+object);
    }
    public void println(Object object)throws IOException
    {
        print(object);
        bw.append("\n");
    }
    public void close()throws IOException
    {
        bw.close();
    }
}

