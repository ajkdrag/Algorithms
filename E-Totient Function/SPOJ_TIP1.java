/*

  Problem at : https://www.spoj.com/problems/TIP1/

*/

import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;

public class Main {
    public static void main(String args[]) throws IOException {
        // precompute till the max limit
        Solution_TIP1.solve(10000002);
        Scan input = new Scan();
        Print output = new Print();
        int t = input.scanInt();
        int in = 0;
        int res = 0;
        while (t-- > 0) { in = input.scanInt();
            res = Solution_TIP1.min_isPerm[ in ];
            if (res == 0) output.println("No solution.");
            else output.println(res);
        }
        output.close();
    }
}

class Solution_TIP1 {
    static int[] phiSieve;
    static long mask;
    static int[] min_isPerm;
    static int limit;

    static void solve(int N) {
        limit = N;
        fillPhiSieve();
        fill_min_isPerm();
    }

    // modified sieve for filling φ values
    static void fillPhiSieve() {
        phiSieve = new int[limit + 1];
        phiSieve[0] = 1;
        phiSieve[1] = 1;

        for (int i = 2; i <= limit; ++i) {
            if (phiSieve[i] == 0) {
                phiSieve[i] = i - 1;
                for (int j = i << 1; j <= limit; j += i) {
                    if (phiSieve[j] == 0) phiSieve[j] = j;
                    phiSieve[j] -= phiSieve[j] / i;
                }
            }
        }
    }

    // scan linearly such that min_isPerm[i] contains N, such that 1 < N < i and N/φ(N) is smallest (satisfying the permutation cond.)
    // note that in case if we have multiple such N, we take the larger one
    // if 'i' doesn't itself produce f(i) = i/φ(i) such that f(i) is <= current minimum, then min_isPerm[i+1] = min_isPerm[i]
    // otherwise, min_isPerm[i+1] = i; (min_isPerm array helps us prodcue the query results in constant time)
    static void fill_min_isPerm() {
        min_isPerm = new int[limit + 1];
        long min_N = 21 L;
        long min_D = 12 L;
        // it is known that till 21, there is no such required number, thus array slots remain filled with 0's
        // i = 21 produces a φ value of 12, which satisfies the permutation condtion, and 21/12 is the first 'minimum' 
        // so min_isPerm[22] = 21
        // till i = 63, we fail to find any more numbers as such; when we hit i = 63, we have φ(63) = 36, and 63/36 <= 21/12
        // thus, min_isPerm[i+1] i.e min_isPerm[64] = 63 and so on...
        min_isPerm[22] = 21;
        for (int i = 22; i < limit; ++i) {
            min_isPerm[i + 1] = min_isPerm[i];
            int phi = phiSieve[i];
            if (isPerm(i, phi) && (i * min_D) <= (phi * min_N)) {
                min_isPerm[i + 1] = i;
                min_N = i;
                min_D = phi;
            }
        }
    }

    // check if 'a' is a permutation of 'b' or not
    static boolean isPerm(int a, int b) {
        int r1 = 0;
        int r2 = 0;
        mask = 0 L;
        while (a > 0 && b > 0) {
            r1 = a % 10;
            r2 = b % 10;
            mask += (1 L << (r1 << 2 L));
            mask -= (1 L << (r2 << 2 L));
            a /= 10;
            b /= 10;

        }
        if (a > 0 || b > 0) return false;
        if (mask == 0 L) return true;
        return false;
    }

    // alternate function (similar to above) for checking if 'a' is a permutation of 'b' or not
    // this one uses two loops, while the above one uses only one.
    static boolean isPerm2(int a, int b) {
        int r = 0;
        mask = 0 L;
        while (a > 0) {
            r = a % 10;
            mask += (1 L << (r << 2 L));
            a /= 10;

        }
        while (b > 0) {
            r = b % 10;
            if ((mask & (15 L << (r << 2 L))) == 0 L) return false;
            mask -= (1 L << (r << 2 L));
            b /= 10;
        }

        if (mask == 0 L) return true;
        return false;
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
    public Print() {
        this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public void print(Object object) throws IOException {
        bw.append("" + object);
    }
    public void println(Object object) throws IOException {
        print(object);
        bw.append("\n");
    }
    public void close() throws IOException {
        bw.close();
    }
}


