package project2;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/*******************************************************
Some basic Junit tests for the 1024 game

@auther Roger Ferguson
@version Winter 2017
*******************************************************/
public class BasicTesting {
    private final static int REPEAT_COUNT = 500;
    private final static int GAME_GOAL = 1024;
    private static NumberSlider gameLogic;
    private static Random gen;
    private static int NROWS, NCOLS;

    @BeforeClass
    public static void globalSetup(){
        gen = new Random();
        
        // TO DO: Instantiate an object of type Number Game
        gameLogic = new NumberGame();        
    }

    @Before
    public void setUp() throws Exception {
        NROWS = 4;
        NCOLS = 4;
        gameLogic.resizeBoard(NROWS, NCOLS, GAME_GOAL);
    }

    @Test
    public void testShiftRight() {
        gameLogic.resizeBoard(4, 4, 16);
        
        // initial board
        int[][] board1 = {
            { 0, 2, 2, 0},
            { 2, 2, 2, 0},
            { 2, 2, 2, 2},
            { 0, 0, 0, 2},
        };  
        
        // this is what it should be after slide right
        int[][] board3 = {
            { 0, 0, 0, 4},
            { 0, 0, 2, 4},
            { 0, 0, 4, 4},
            { 0, 0, 0, 2},
        };  
        gameLogic.setValues(board1);
        System.out.println("Before:");
        gameLogic.printBoard();
        
        gameLogic.slide(SlideDirection.RIGHT);
        System.out.println("After:");
        gameLogic.printBoard();
        int [][] board2 = new int[4][4];

        for (Cell c : gameLogic.getNonEmptyTiles())
            board2[c.getRow()][c.getCol()] = c.getValue();
        
        gameLogic.printBoard();
        
        int total = 0;
        for (int r = 0; r < board2.length; r++)
            for (int c = 0; c < board2[r].length; c++)
                if (board2[r][c] != board3[r][c])
                    total++;
        gameLogic.printBoard();
        
        // only one tile should be different
        // the newly inserted tile
        assertEquals("problems shifting right", 1, total); 
        assertEquals("The score is not correct", 16, gameLogic.getScore()); 
    }   

    @Test(expected = IllegalArgumentException.class)
    public void testNotAPowerOf2() {
        gameLogic.resizeBoard(8, 8, 15);        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWinningValueNegative() {
        gameLogic.resizeBoard(4, 4, -1);        
    }

    @Test(expected = IllegalStateException.class)
    public void testFullBoard() {
        gameLogic.resizeBoard(2, 2, 4);
        gameLogic.setValues(new int[][] {
            { 2, 2 },
            { 2, 2 }
        });         
        gameLogic.placeRandomValue();       
    }


    @Test
    public void testWinningValue() {
        gameLogic.resizeBoard(2, 2, 4);
        gameLogic.setValues(new int[][] {
            { 2, 2 },
            { 4, 2 }
        });         
        assertEquals("player should have won with 4", GameStatus.USER_WON, gameLogic.getStatus());
    }

    @Test
    public void testLosing() {
        gameLogic.resizeBoard(2, 2, 8);
        gameLogic.setValues(new int[][] {
            { 2, 4 },
            { 4, 2 }
        });         
        assertEquals("player should have lost", GameStatus.USER_LOST, gameLogic.getStatus());
    }
    
    @Test
    public void testContinue() {
        gameLogic.resizeBoard(2, 2, 8);
        gameLogic.setValues(new int[][] {
            { 2, 4 },
            { 2, 2 }
        });         
        assertEquals("board is full but valid slide exists", GameStatus.IN_PROGRESS, gameLogic.getStatus());
    }    
}
