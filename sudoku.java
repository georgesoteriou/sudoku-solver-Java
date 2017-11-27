/*
HARD PUZZLE

9 1 0 0 0 0 0 5 0
0 0 3 0 0 9 0 2 1
0 0 0 4 0 2 0 0 0
0 8 0 0 4 0 9 0 2
0 0 0 0 7 0 0 0 0
5 0 4 0 6 0 0 1 0
0 0 0 5 0 6 0 0 0
2 5 0 7 0 0 8 0 0
0 3 0 0 0 0 0 9 5

------------------
EASY PUZZLE

6 3 0 2 0 8 0 1 0
2 0 0 0 5 0 0 8 9
1 0 9 0 6 0 0 3 0
0 0 8 0 0 6 0 5 0
0 0 0 1 8 7 0 0 0
0 6 0 5 0 0 9 0 0
0 9 0 0 7 0 1 0 6
8 1 0 0 2 0 0 0 5
0 2 0 4 0 3 0 9 7

-----------------
EMPTY PUZZLE

0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0

-----------------
*/

import java.util.Scanner;

public class sudoku {
  public static void main(String[] args) {
    int[][] grid = new int[9][9];
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter your puzzle:");
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j] = sc.nextInt();
      }
    }
    System.out.println();
    System.out.println("Solution:");
    print(nextGrid(grid));
  }

  //print 2D array
  private static void print(int grid[][]){
    System.out.println();
    for (int[] gr : grid){
      for(int g : gr){
        System.out.print(g + " ");
      }
      System.out.println();
    }
  }

  //nextGrid will return the next filled position of the grid
  //after x,y. If x,y are not specified, the first grid position is returned
  private static int[][] nextGrid(int[][] grid) {
    if(valid(grid)){
      return grid;
    }
    int x = 0;
    int y = 0;
    while (grid[x][y] != 0) {
      if (x < 8) {
        x++;
      } else {
        x = 0;
        y++;
      }
    }
    return loop(x, y, grid);
  }
  private static int[][] nextGrid(int x, int y, int[][] grid) {
    if(valid(grid)){
      return grid;
    }
    do{
      if (x < 8) {
        x++;
      } else {
        x = 0;
        y++;
      }
    }while (grid[x][y] != 0);
    return loop(x, y, grid);
  }

  //valid will return true if grid(x,y) is not 0 and is valid
  //if no x and y are specified, checks whole puzzle
  private static boolean valid(int[][] grid){
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if(!valid(i,j,grid)){
          return false;
        }
      }
    }
    return true;
  }
  private static boolean valid(int x, int y, int[][] grid){
    if(grid[x][y] == 0){
      return false;
    }
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      if (grid[x][i] == grid[x][y]) count++;
      if (grid[i][y] == grid[x][y]) count ++;
      if (grid[((x/3) * 3) + i/3][((y/3) * 3) + i%3] == grid[x][y]) count++;
      if(count>3) return false;
    }
    return true;
  }

  //main recursion
  private static int[][] loop(int x, int y, int[][] grid){
    int count = 0;
    while (!valid(grid)) {
      if (count > 9) {
        grid[x][y] = 0;
        return grid;
      }
      if (grid[x][y] < 9) {
        grid[x][y]++;
        count++;
      } else {
        grid[x][y] = 0;
        count++;
      }
      if (valid(x, y, grid)) {
        grid = nextGrid(x, y, grid);
      }
    }
    return grid;
  }
}
