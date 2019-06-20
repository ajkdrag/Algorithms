/*
  Problem at : https://codeforces.com/contest/906/problem/D
*/

// Solution 1 : (Iterative)
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        
        int n = in.readInt();
        int m = in.readInt();
        
        Solution sol = new Solution(n, m);
        for(int i=1; i<=n; ++i){
            sol.push(i, in.readInt());
        }
        
        int q = in.readInt();
        while(q-- > 0){
            int l = in.readInt();
            int r = in.readInt();
            if(m==1){
                out.println(0);
                continue;
            }
            out.println(sol.solve(l,r,0)%m);
        }
        out.close();
    }
}

class Solution {
    int[] v = new int[100009];
    int[] a;
    int n;
    int m;
    int cnt;
    boolean ovf;
    
    int power(int base, int b, int m){
        ovf = false;
        if(m == 0)
            return 0;
        if(b == 0 || base == 1)
            return 1;

        long x = 1;
        long a = base; 
        while(b > 0){
            if((b&1) == 1){
                if(a >= m || x*a >= m)
                    ovf = true;
                x = (x*a) % m;
            }
            if(a*a >=m && b > 1)
                ovf = true;
            a = (a*a)%m;
            b >>=1;
        }
        return (int) x;
    }
    
    int phi(int n){
        int ret=n;
        for(int i=2;i*i<=n;i++){
            if(n%i==0){
                while(n%i==0)n/=i;
                ret-=ret/i;
            }
        }
        if(n>1)ret-=ret/n;
        return ret;
    }
    
    int solve(int l, int r, int k){
        boolean usehack = false;
        int running = 1;
        int i = (r - l + 1 < cnt ? r : l + cnt - 1);
        for(; i >= l; --i){
            if(i - l + 1 >= cnt){
                v[i-l+1] = 1;
            }
            if(a[i] == 1){
                running = 1;
                usehack = false;   
                continue;
            }
            else if(!usehack && running < v[i-l+1]){
                running = power(a[i], running, v[i-l]);
                if(ovf == true)
                    usehack = true;
                continue;
            }
            else {
                usehack = true;
                running = power(a[i], v[i-l+1] + running%v[i-l+1], v[i-l]);
                continue;
            }
        }
        return running;
    }
    
    void push(int id, int x){
        a[id] = x;
    }
    
    Solution(int n, int m){
        this.m = m;
        a = new int[n + 1];
        v[0] = m;
        cnt = 1;
        while(m!=1){
            m = phi(m); 
            v[cnt++] = m;
        }
    }
}

// fast I/O
class InputReader {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int readInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public static boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public char readCharacter() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		return (char) c;
	}
}

class OutputWriter {
	private final PrintWriter writer;

	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(outputStream);
	}

	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void print(Object...objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}

	public void println(Object...objects) {
		print(objects);
		writer.println();
	}

	public void close() {
		writer.close();
	}
}


// Solution 2 : (Recursive)
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        
        int n = in.readInt();
        int m = in.readInt();
        
        Solution sol = new Solution(n, m);
        for(int i=1; i<=n; ++i){
            sol.push(i, in.readInt());
        }
        
        int q = in.readInt();
        while(q-- > 0){
            int l = in.readInt();
            int r = in.readInt();
            if(m==1){
                out.println(0);
                continue;
            }
            out.println(sol.solve(l,r,0)%m);
        }
        out.close();
    }
}

class Solution {
    int[] v = new int[100009];
    int[] a;
    int n;
    int m;
    int cnt;

    int phi(int n){
        int ret=n;
        for(int i=2;i*i<=n;i++){
            if(n%i==0){
                while(n%i==0)n/=i;
                ret-=ret/i;
            }
        }
        if(n>1)ret-=ret/n;
        return ret;
    }
    
    long solve(int l, int r, int k){
        if (l>r || k + 1 >= cnt)
		    return 1;
    	long temp = solve(l+1, r, k+1);
    	long x = a[l];
    	long ret = 1;
    	while(temp > 0)
    	{
    		if ((temp&1) == 1)
    			ret = modulo(ret*x, v[k]);
    		x = modulo(x*x, v[k]);
    		temp>>=1;
    	}
    	return ret;
    }
    
    long modulo(long a, long b){
        if (a<b)
		    return a;
	    return (a%b)+b;
    }
    
    void push(int id, int x){
        a[id] = x;
    }
    
    Solution(int n, int m){
        this.m = m;
        a = new int[n + 1];
        v[0] = m;
        cnt = 1;
        while(m!=1){
            m = phi(m); 
            v[cnt++] = m;
        }
    }
}

// fast I/O
class InputReader {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int readInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public static boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public char readCharacter() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		return (char) c;
	}
}

class OutputWriter {
	private final PrintWriter writer;

	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(outputStream);
	}

	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void print(Object...objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}

	public void println(Object...objects) {
		print(objects);
		writer.println();
	}

	public void close() {
		writer.close();
	}
}


