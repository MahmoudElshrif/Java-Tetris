import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Board board = new Board();
        Scanner s = new Scanner(System.in);


        while(board.UpdateBoard()){

        }

        System.out.println("GAME OVER");
        System.out.println(board.score);
    }
}

