/*
  Problem at : https://www.codechef.com/problems/COZIE
  Algo : The problem basically reduces down to finding numbers within a range whose φ value is a prime number
         We already know that φ of any number is always even, except for 1, 2 φ(1) = 1, φ(2) = 1,
         And the only even prime is '2'. In other words, we care about numbers such that their φ is 2.
         Now, only, for 3, 4 and 6 we have their φ values as 2.
         Next comes the tast of efficiently performing the range queries.
         A range [a,b] will be given such that we need to find the count of numbers whose φ values are 2.
         In order words, count of numbers from {3, 4, 6} falling in this range.
         We use a special trick that deals with this. Suppose the given array N is : 3, 6, 8, 2, 4, 9, 11, 3
         We maintain another array of the same size filled with 0's
         As we scan the array filled with values, we copy elements from previous index and increment the value
         of this current index if we happen to encounter a value from {3, 4, 6} 
         Thus we will get something like this :
         1, 2, 2, 2, 2, 2, 2, 3
         In simple words, when we read this array from left to right, each value represents the number of elements we have
         seen so far from {3, 4, 6} 
         index[0] has '1' indicating the 1st '3' we saw at index[0]
         index[1] has '2' indicating that we have seen 2 occurences from {3, 4, 6} in the array.
         index[2] has '2' for similar reasons and so on...
         After having populated this array, we can easily perform the range queries by doing arr[b] - arr[a - 1]
*/

import java.io.*;
import java.util.InputMismatchException;
public class Main {
    
    public static void main(String args[]) throws IOException{
        int[] arr = new int[100005];
        Scan input = new Scan();
        Print output = new Print();
        int n = input.scanInt();
        int q = input.scanInt();
        
        for(int i = 1; i <= n; ++i){
            arr[i] = arr[i-1];
            int val = input.scanInt();
            if(val == 3 || val == 4 || val == 6) ++arr[i];
        }
        
        while(q-- > 0){
            int l = input.scanInt();
            int r = input.scanInt();
            int res = arr[r] - arr[l-1];
            output.println(res);
        }
        
        output.close();
    }
}


class Scan
{
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

