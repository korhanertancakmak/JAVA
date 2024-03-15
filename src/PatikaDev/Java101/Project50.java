/* Pratice50 - Transpose of a multi-dimensional array

Write a program that finds the transpose of a multi-dimensional array.

Transpose of a matrix is the replacement of rows and colomns. So transpose of the matrix with the dimension of k x n is another matrix with the dimension of n x k. 

Scenario
Matrix : 
2    3    4    
5    6    4    
Transpose : 
2    5    
3    6    
4    4    


Matrix : 
1    2    3    
4    5    6    
Transpose : 
1    4    
2    5    
3    6    

*/


public class Project50 {
    
    public static void main(String[] args) {

        int[][] arr = {{1, 2, 3}, {4, 5, 6}};
        int colomn = arr[0].length;
        int row = arr.length;
        
        int[][] arrTr = new int[colomn][row];

        for (int i = 0; i < colomn; i++) {
            for (int j = 0; j < row; j++) {
                arrTr[i][j] = arr[j][i];
            }
        }
        
        System.out.println("Matrix : ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colomn; j++) {
                System.out.printf("%d    ", arr[i][j]);
            }
            System.out.println();
        }

        System.out.println("Transpose : ");
        for (int i = 0; i < colomn; i++) {
            for (int j = 0; j < row; j++) {
                System.out.printf("%d    ", arrTr[i][j]);
            }
            System.out.println();
        }
    }
}


