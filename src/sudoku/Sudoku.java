package sudoku;

import java.util.Random;
import java.util.ArrayList;
import javax.swing.UIManager;

public class Sudoku {
    
    public static int[][] solution;
    public int[][] game;

    Sudoku(){
        int[][] gr = generate(grid);
        solution = finalGrid(gr);
        game = board(solution, "easy");
    }

    public static Random rand = new Random();
    public static ArrayList<Integer> numArr = new ArrayList<>();
    public static int[][] grid = new int[9][9];
    public static String newline = new String(System.lineSeparator());
    public static String finalGrid = "";

    public static void main(String[] args){
        Sudoku mySudoku = new Sudoku();
        for (int i=0; i<9; i++){
            System.out.println();
            for(int y=0; y<9; y++){
                System.out.print(mySudoku.game[i][y] + " ");
            }
        }
        
        //Start GUI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        GUI gui = new GUI();
        gui.setVisible(true);
        
    }
    
    public static int[][] board(int[][] grid, String level){
        int counter;
        if ("easy".equals(level)){counter=49;}
        else if ("medium".equals(level)){counter=51;}
        else{counter=53;}
        
        for (int i=0;i<counter;i++){
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (grid[row][col]!=0){
                grid[row][col]=0;
            }else{
                i--;
            }
        }
        return grid;
    }

    public static int[][] generate(int[][] grid){

        int counter = 0;
        int counter2 = 0;

        for (int row=0; row<9; row++){
            for(int col=0; col<9; col++){
                try{grid[row][col] = nextNumber(row, col, grid);}catch(Exception e){
                    if (counter2 == 500) {
                        for (row = 0; row < 9; row++) {
                            for (col = 0; col < 9; col++) {
                                grid[row][col] = 0;
                            }
                        }
                        row = 0;
                        col = 0;
                        counter2 = 0;
                    }else {
                        counter2++;
                        if (counter == 100) {
                            for (int x = 0; x < 9; x++) {
                                grid[row][x] = 0;
                            }
                            col = 0;
                            counter = 0;
                        } else {
                            grid[row][col] = 0;
                            col--;
                            counter++;
                        }
                    }
                }
            }
        }

        return grid;
    }

    public static int[][] finalGrid(int[][] grid){

        for (int x=0;x<9;x++){
            for (int y=0;y<9;y++){
                if (grid[x][y] == 0){
                    grid[x][y] = nextNumber(x, y, grid);
                }
            }
        }

        return grid;
    }

    public static int nextNumber(int row, int col, int[][] grid){

        numArr.clear();
        for (int i=1;i<10;i++){numArr.add(i);}

        //row
        for (int x=0; x<9;x++){
            for (int num=0;num<numArr.size();num++) {
                if (grid[row][x] == numArr.get(num)){
                    numArr.remove(num);
                }
            }
        }

        //column
        for (int y=0;y<9;y++){
            for (int num=0;num<numArr.size();num++) {
                if (grid[y][col] == numArr.get(num)){
                    numArr.remove(num);
                }
            }
        }

        //3x3 square
        int squareR = (row<3)?0:(row<6)?3:6;
        int squareC = (col<3)?0:(col<6)?3:6;

        for(int i=squareR; i<(squareR+3); i++){
            for(int j=squareC; j<(squareC+3); j++){
                for (int num=0;num<numArr.size();num++) {
                    if (grid[i][j] == numArr.get(num)){
                        numArr.remove(num);
                    }
                }
            }
        }

        return numArr.get(rand.nextInt(numArr.size()));
    }

}
