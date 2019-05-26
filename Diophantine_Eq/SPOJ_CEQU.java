/*
    Problem at : https://www.spoj.com/problems/CEQU/
    Basic application of Euclidean algorithm for finding GCD.
*/

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;

public class Main {
    public static void main(String args[]) throws IOException {
        Scan input = new Scan();
        Print output = new Print();
        int t = input.scanInt();
        int a = 0;
        int b = 0;
        int c = 0;
        while (t-- > 0) { 
            a = input.scanInt();
            b = input.scanInt();
            c = input.scanInt();
            output.println(Solution_CEQU.solve(a, b, c));
        }
        output.close();
    }
}

class Solution_CEQU{
    private static final String YES = "Yes";
    private static final String NO = "No";
    static String solve(int a, int b, int c){
        int d = gcd(a, b);
        if((c % d) != 0)
            return NO;
        return YES;
    }
    
    static int gcd(int a, int b){
        if(b == 0)
            return a;
        return gcd(b, a%b);
    }
    
}

// fast I/O
class Scan {
    private byte[] buf = new byte[1024]; //Buffer of Bytes
    private int index;
    private InputStream in ;
    private int total;
    public Scan() { in = System.in;
    }
    public int scan() throws IOException //Scan method used to scan buf
    {
        if (total < 0)
            throw new InputMismatchException();
        if (index >= total) {
            index = 0;
            total = in .read(buf);
            if (total <= 0)
                return -1;
        }
        return buf[index++];
    }
    public int scanInt() throws IOException {
        int integer = 0;
        int n = scan();
        while (isWhiteSpace(n)) //Removing starting whitespaces
            n = scan();
        int neg = 1;
        if (n == '-') //If Negative Sign encounters
        {
            neg = -1;
            n = scan();
        }
        while (!isWhiteSpace(n)) {
            if (n >= '0' && n <= '9') {
                integer *= 10;
                integer += n - '0';
                n = scan();
            } else throw new InputMismatchException();
        }
        return neg * integer;
    }
    private boolean isWhiteSpace(int n) {
        if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
            return true;
        return false;
    }
}

class Print {
    private final BufferedWriter bw;
    private static int i = 1;
    public Print() {
        this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public void print(Object object) throws IOException {
        bw.append("Case " + i + ": " + object);
        ++i;
    }
    public void println(Object object) throws IOException {
        print(object);
        bw.append("\n");
    }
    public void close() throws IOException {
        bw.close();
    }
}


