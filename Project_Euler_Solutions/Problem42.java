/*

  Problem at : https://projecteuler.net/problem=42

*/

import java.io.InputStream;
import java.io.IOException;
import java.util.InputMismatchException;

class Problem42{
	public static void main(String[] args) throws IOException{
		Scan in = new Scan();
		int s = 0;
		int count = 0;
		while((s = in.scanString()) != 0)
			if(Solution42.isTriangular(s)) ++count;
		System.out.println(count);
	}
}

class Solution42{
	static boolean isTriangular(int n){
		double sq = Math.sqrt(1 + (n<<3));
		if(sq == (int)sq) return true;
		return false;
	}
}

class Scan
{
    private byte[] buf=new byte[1024];    //Buffer of Bytes
    private int index;
    private InputStream in;
    private int total;
    int sum = 0;
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
    
    public int scanString() throws IOException{
    	sum = 0;
    	int n = scan();
    	if(n == -1) return 0;
    	if(n == ',') n = scan();
    	if(n == '"') n = scan();
    	while(n != '"'){
    		sum += n - 'A' + 1;
    		n = scan();
    	}
    	return sum;
    }
}


