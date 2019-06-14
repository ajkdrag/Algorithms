/*
  Problem at : https://codeforces.com/problemset/problem/154/B
*/

// accepted (optimized version)
import java.io.IOException;
import java.util.InputMismatchException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.InputStream;

public class Main {
    static Solution sol;
    public static void main(String[] args) throws IOException{
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);

        if(sol == null){
            sol = new Solution();
            sol.fillDivisors();
        }
        else
            sol.reset();
        
        int n = in.readInt();
        int m = in.readInt();
        
        while(m-- > 0){
            if(in.readCharacter() == '+')
                out.printLine(sol.insert(in.readInt()));
            else
                out.printLine(sol.remove(in.readInt()));
        }
        out.close();
    }
}

class Solution {
    int[][] divisors;
    int[] inserted;
    int val;
    
    int limit = 100001;
    
    void fillDivisors(){
        inserted = new int[limit + 1];
        divisors = new int[limit + 1][6];
        
        divisors[1][0] = 1;

        for(int i = 2; i<<1 <= limit; ++i){
            if(divisors[i][0] == 0){
                for(int j = i<<1; j <= limit; j+=i){
                    val = 0;
                    while(divisors[j][val] != 0)
                        ++val;
                    divisors[j][val] = i;
                }
            }
        }
    }
    
    String insert(int x){
        if(inserted[x] == 1)
            return "Already on";
        
        val = divisors[x][0];
        if(val > x){
            if(inserted[val] == 1)
                    return "Conflict with " + val;
            divisors[x][0] = x;
        }
        else {
            for(int d : divisors[x]){
                    if(d == 0)
                        break;
                    val = divisors[d][0];
                    if(inserted[d] == 1)
                        return "Conflict with " + d;
                    if(inserted[val] == 1)
                        return "Conflict with " + val;
            }
            
            for(int i = 0, end = 6; i < end; ++i){
                val = divisors[x][i];
                if(val == 0)
                    break;
                divisors[val][0] = x;
            }
        }
        
        inserted[x] = 1;
        return "Success";
    }
    
    void reset(){
        inserted = new int[limit + 1];
    }
    
    String remove(int x){
        if(inserted[x] == 0)
            return "Already off";
        inserted[x] = 0;
        return "Success";
    }
    
}


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

	public void printLine(Object...objects) {
		print(objects);
		writer.println();
	}

	public void close() {
		writer.close();
	}
}


// unaccepted (naive approach)
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Solution sol = new Solution();
        Print pt = new Print();
        String line = br.readLine();
        while((line = br.readLine()) != null){
            String[] ip = line.split(" ");
            if(ip[0].equals("+"))
                pt.println(sol.insert(Integer.parseInt(ip[1])));
            else
                pt.println(sol.remove(Integer.parseInt(ip[1])));
        }
        pt.close();
    }
}

class Solution {
    HashSet<Integer> inserted;
    
    int comp_gcd(int a, int b){
        if(b == 0)
            return a;
        return comp_gcd(b, a%b);
    }
    
    
    String insert(int a){
        if(inserted.contains(a))
            return "Already on";
        
        for(int x : inserted){
            if(comp_gcd(x, a) != 1)
                return "Conflict with " + x;
        }
        inserted.add(a);
        return "Success";
    }
    
    String remove(int a){
        if(inserted.contains(a)){
            inserted.remove(a);
            return "Success";
        }
        return "Already off";
    }
    
    Solution() {
        inserted = new HashSet<Integer>();
    }
}

class Print
{
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


