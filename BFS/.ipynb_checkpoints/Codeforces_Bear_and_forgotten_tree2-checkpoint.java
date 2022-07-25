/*
  Problem at : https://codeforces.com/contest/653/problem/E
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String args[]) throws IOException{
        Scan sc = new Scan();
        Print pt = new Print();
        Solution sol = null;
        int N = sc.scanInt();
        sol = new Solution(N);
        int M = sc.scanInt();
        int K = sc.scanInt();
        for(int i = 0; i < M; ++i)
            sol.addForbEdge(sc.scanInt(), sc.scanInt());
        pt.println(sol.solve(K));
        pt.close();
    }
}
 
class Solution {
    Queue<Integer> q;
    HashSet<Integer>[] forbSet;
    LinkedHashSet<Integer> remaining;
    int N;
    int allowedOnFirst;
    
    Solution(int N){
        this.N = N;
        this.allowedOnFirst = N - 1;
        forbSet = new HashSet[N+1];
        for(int i = 1; i <= N; ++i){
            forbSet[i] = new HashSet<Integer>();
        }
        remaining = new LinkedHashSet<Integer>();
    }
    
    void addForbEdge(int a, int b){
        if(a == 1 || b == 1)
            --allowedOnFirst;
        forbSet[a].add(b);
        forbSet[b].add(a);
    }
    
    boolean bfs(int src, int comp){
        q = new LinkedList<Integer>();
        q.add(src);
        remaining.remove(src);
        boolean isConnToFirst = forbSet[1].contains(src) ? false : true;
        while(!q.isEmpty()){
            int curr = q.poll();
            for (Iterator<Integer> itr = remaining.iterator(); itr.hasNext();) {
                Integer i = itr.next();
                if(forbSet[curr].contains(i))
                    continue;
                if(!forbSet[1].contains(i))
                    isConnToFirst = true;
                q.add(i);
                itr.remove();
            }            
        }
        return isConnToFirst;
    }
    
    String solve(int K){
        int C = 0;
        if(allowedOnFirst < K)
             return "impossible";
        for(int i = 2; i <= N; ++i)
            remaining.add(i);
        for(int i = 2; i <= N && !remaining.isEmpty(); ++i){
            if(remaining.contains(i) && !bfs(i, ++C))
                return "impossible";
        }
        if(C <= K)
            return "possible";
        return "impossible";
    }
}
 
 
// fast I/O
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
        int big=0;
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
                big*=10;
                big+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        return neg*big;
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


// Another solution (credits to mmaxio). Notice the use of two arrays to speed up removal while keeping constant time access.
import java.io.*;
import java.util.*;
 
public class E_faster {
 
	BufferedReader br;
	PrintWriter out;
	StringTokenizer st;
	boolean eof;
 
	void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();
		int k = nextInt();
 
		HashSet<Integer>[] g = new HashSet[n];
		for (int i = 0; i < n; i++) {
			g[i] = new HashSet<>();
		}
 
		for (int i = 0; i < m; i++) {
			int v1 = nextInt() - 1;
			int v2 = nextInt() - 1;
			g[v1].add(v2);
			g[v2].add(v1);
		}
 
		int[] restNext = new int[n + 1];
		int[] restPrev = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			restNext[i] = i + 1;
			restPrev[i] = i - 1;
		}
 
		int[] col = new int[n];
		Arrays.fill(col, -1);
 
		int cc = 0;
 
		ArrayDeque<Integer> q = new ArrayDeque<>();
 
		for (int i = 1; i < n; i++) {
			if (col[i] == -1) {
				q.add(i);
				col[i] = cc;
				
				int pr = restPrev[i];
				int ne = restNext[i];
				
				restNext[pr] = ne;
				restPrev[ne] = pr;
 
				while (!q.isEmpty()) {
					int v = q.poll();
					for (int u = restNext[0]; u != n; u = restNext[u]) {
						if (!g[v].contains(u)) {
							q.add(u);
							col[u] = cc;
							
							pr = restPrev[u];
							ne = restNext[u];
							
							restNext[pr] = ne;
							restPrev[ne] = pr;
						}
					}
					
				}
 
				cc++;
			}
		}
 
		if (cc > k) {
			out.println("impossible");
			return;
		}
			
		if (n - 1 - g[0].size() < k) {
			out.println("impossible");
			return;
		}
		
		HashSet<Integer> has = new HashSet<>();
		for (int u = 1; u < n; u++) {
			if (!g[0].contains(u)) {
				has.add(col[u]);
			}
		}
		
		out.println(has.size() == cc ? "possible" : "impossible");
	}
 
	E_faster() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}
 
	public static void main(String[] args) throws IOException {
		new E_faster();
	}
 
	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				eof = true;
				return null;
			}
		}
		return st.nextToken();
	}
 
	String nextString() {
		try {
			return br.readLine();
		} catch (IOException e) {
			eof = true;
			return null;
		}
	}
 
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
 
	long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}
 
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}


