/*
  Problem : Traverse a matrix in a zig-zag fashion.
  eg : Matrix_1 :   1 2 3     Matrix_2 :  1 2 3     Matrix_3 :   1 2
                    4 5 6                 4 5 6                  3 4
                                          7 8 9                  5 6
                                          
       Traversal_1 : 1, 2, 4, 5, 3, 6
       Traversal_2 : 1, 2, 4, 7, 5, 3, 6, 8, 9
       Traversal_3 : 1, 2, 3, 5, 4, 6
*/

import java.util.Scanner;

public class Zig_zag {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the order of the matrix (m x n) : ");
        int m = input.nextInt();
        int n = input.nextInt();
        int[][] matrix = new int[m][n];
        
        System.out.println("Enter the elements of the matrix : ");
        int i = 0,j = 0;
        for( i = 0 ; i < m; ++i){
            for( j = 0 ; j < n; ++j){
                matrix[i][j] = input.nextInt();
            }
        }
        i = 0;
        j = 0;
        
        System.out.print("Zig-Zag traversal : ");
        boolean zig = true;
        for(int s = 0; s <= (m+n-2); ++s){
            if((s&1)==0){ 
                zig = true;
                i = Math.min(s,m - 1);
                j = s - i;
            }
            else{
                zig = false;
                j = Math.min(s,n - 1);
                i = s - j;
            } 
            while(i >= 0 && j >= 0 && i < m && j < n){
                System.out.print(matrix[i][j] + " ");
                if(!zig){
                    i++;
                    j--;
                }
                else {
                    i--;
                    j++;
                }
            }
        }
    }    
}


