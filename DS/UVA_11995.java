/*
  Problem at : https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3146
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		Sol sol = null;
		while ((s=br.readLine())!=null){
		    sol = new Sol();
		    int opCount=Integer.parseInt(s);
			while(opCount-- > 0) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				String op=st.nextToken();
				int value=Integer.parseInt(st.nextToken());
				if (op.equals("1")) {
				    sol.add(value);
				}
				else if(!sol.get(value)){
				    break;
				}
			}
			while(opCount-- > 0){
			    br.readLine();
			}
			System.out.println(sol.res);
		}
    }
}

class Sol {
    Stack<Integer> st;
    Queue<Integer> q;
    PriorityQueue<Integer> pq;
    String res;
    int[] flag;
    
    Sol (){
        st = new Stack<Integer>();
        q = new LinkedList<Integer>();
        pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
                public int compare(Integer a, Integer b){
                    return b.compareTo(a);
                }
            });
        res = "not sure";
        flag = new int[3];
    }
    
    void add(int x){
        st.add(x);
        q.add(x);
        pq.add(x);
    }
    
    boolean get(int x){
        int getSt = st.size() > 0 ? st.peek() : -x;
        int getQ = q.size() > 0 ? q.peek() : -x;
        int getPq = pq.size() > 0 ? pq.peek() : -x;
        int ct = 0;
        for(int i = 0; i <= 2; ++i){
            if(flag[i] >= 0)
                flag[i] = 0;
        }
        if(getSt == x && flag[0]>=0){
            ++ct;
            res = "stack";
            if(st.size() > 0)
            st.pop();
            flag[0]=1;
        }
        if(getQ == x && flag[1]>=0){
            ++ct;
            res = "queue";
            q.poll();
            flag[1]=1;
        }
        if(getPq == x && flag[2]>=0){
            ++ct;
            res = "priority queue";
            pq.poll();
            flag[2]=1;
        }
        if(ct == 0){
            res = "impossible";
            return false;
        }
        for(int i = 0; i <= 2; ++i){
            if(flag[i] == 0)
                flag[i] = -1;
        }
        if(ct > 1){
            res = "not sure";
        }
        return true;
    }
}


