
package sudoku;

public class checker {
    
     public static boolean isValid(int[][] grid, int row, int col){
        
     if(grid[row][col]!=0){
               
        for (int i=0; i<9; i++){
            if (i!=col){
                if (grid[row][col]==grid[row][i]){
                    return false;
                }
            }
        }
        
        for (int y=0; y<9; y++){
            if (y!=row){
                if(grid[row][col]==grid[y][col]){
                    return false;
                }
            }
        }
        
        int sR = (row<3)?0:(row<6)?3:6;
        int sC = (col<3)?0:(col<6)?3:6;
        
        for(int i=sR; i<(sR+3); i++){
            for(int j=sC; j<(sC+3); j++){
                if (sR!=row && sC!=col){
                    if(grid[row][col]==grid[sR][sC]){
                        return false;
                    }
                }
            }
        }
        
     }
      
        return true;        
    }
    
}
