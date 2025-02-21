import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Board board = new Board();
        Scanner s = new Scanner(System.in);


        while(true){
            board.UpdateBoard();
        }
    }
}

