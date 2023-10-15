
package com.mycompany.sudukoproj1;

 import java.util.Scanner; 

public class sudukosolver {
   
 
  public static void main(String[] args) {
  Scanner input = new Scanner(System.in); 
  int[][] board = new int [9][9]; //initialize the 2d array board 
   for(int i=0; i<9; i++)
   { 
       for(int j=0; j<9; j++)
       {
     board[i][j]= input.nextInt();  //take input for all values of board 
   }
   }
    printBoard(board); //print board out before solving suduko for reference 
    
   // if (solveBoard(board)) {
     // System.out.println("Solved successfully!");
   //}
   // else {
      //System.out.println("Unsolvable board :(");
   // }
    solveBoard(board);
    printBoard(board);
    
  }
  
  
  private static void printBoard(int[][] board) {
    for (int row = 0; row < 9; row++) 
    {
      for (int column = 0; column < 9; column++)
      {
        System.out.print(board[row][column] + " ");
      }
      System.out.println();
    }
  }


  private static boolean isNumberInRow(int[][] board, int number, int row) { //make sure this number isnt present in the row
    for (int i = 0; i < 9; i++) { //9 rows so loops through every row
      if (board[row][i] == number) { //if the number is present in the row
        return false; //dont use this number
      }
    }
    return true;
  }
  
  private static boolean isNumberInColumn(int[][] board, int number, int column) { //make sure this number isnt present in the coloumn
    for (int i = 0; i < 9; i++) { //9 coloumns so loops through every coloumn 
      if (board[i][column] == number) { //if the number is present in the coloumn
        return false; //dont use this number
      }
    }
    return true; 
  }
  
  private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
   int BoxRow = row - row % 3; // makes sure the row only advances thre boes forward. we use modulus so when its divisible by 3 it stops. 9-9%3 will give 9-0 so at 9th row it stops
    int BoxColumn = column - column % 3; //same as rows above
   
    
    for (int i = BoxRow; i < BoxRow + 3; i++) {
      for (int j = BoxColumn; j < BoxColumn + 3; j++) {
        if (board[i][j] == number) { //if any box in each 3 by 3 grid has the number do not use that number so return false 
          return false;
  
        }
      }
    }
    return true;
  }
  
  private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
    return isNumberInRow(board, number, row) &&
        isNumberInColumn(board, number, column) &&
        isNumberInBox(board, number, row, column);
  }
  
  private static boolean solveBoard(int[][] board) {
    for (int row = 0; row < 9; row++) {
      for (int column = 0; column < 9; column++) {
        if (board[row][column] == 0) {
          for (int m = 1; m <= 9; m++) {
            if (isValidPlacement(board,m, row, column)) { // if all 3 conditions are met by all three methods then 
              board[row][column] = m; //let m be that number that replaces that cell
              
              if (solveBoard(board)) { //if that number worked it means spot will be filled so return 0
                return true;
              }
              else {
                board[row][column] = 0; //or keep that spot 0 so this method runs again until the spot is filed with the correct number
              }
            } //if statement closing
          }
          return false; //if its no 0 that means the spot is already filled so we leave it as it is and retuRn false
        }
      }
    }
    return true;
  }
}