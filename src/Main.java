import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Board board = new Board();
        Scanner s = new Scanner(System.in);

        //Tetris game, complete a row to remove it and gain score, the game ends when the top most row has blocks

        //Controls:
        // "a" and "d" for moving left and right
        // "q" and "e" for rotation clockwise and counterclockwise
        // "s" for slamming the piece immediately
        // Don't type anything to move it one space down

        while(board.UpdateBoard()){

        }

        System.out.println("GAME OVER");
        System.out.println(board.score);
    }
}

