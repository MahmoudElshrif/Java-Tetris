import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    boolean[][] grid = new boolean[25][10];
    int x = 0;
    Scanner input;

    Piece cur;


    public void setBlock(int row,int col,boolean val){
        grid[row][col] = val;
    }

    public boolean getBlock(int row,int col){
        return grid[row][col];
    }
    public Board(){
        cur = new Stick();
        input = new Scanner(System.in);
        for(int i = 0;i < 25;i++){
            for(int j = 0;j < 10;j++){
                setBlock(i,j,false);
            }
        }
    }

    public boolean CheckMove(int dir,ArrayList<ArrayList<Integer>> poses){
        for(var p : poses){
            if(p.get(0) + dir < 0 || p.get(0) + dir > 9) return false;
            if(getBlock(p.get(1),p.get(0) + dir)){
                return false;
            }
        }
        return true;
    }

    public void HandleInput(){
        String move = input.nextLine();
        cur.MoveDown();
        var poses = cur.GetAllPositions();
        if(move.equals("d") && CheckMove(1,poses)){
            cur.MoveRight();
        }
        else if(move.equals("a") && CheckMove(-1,poses)){
            cur.MoveLeft();
        }
        else if(move.equals("e")){
            cur.RotateRight();
        }
        else if(move.equals("q")){
            cur.RotateLeft();
        }
        else if(!move.isEmpty()){
            return;
        }


    }

    public void UpdateBoard() {

        HandleInput();

        ArrayList<ArrayList<Integer>> poses;
        while(true){
            poses = cur.GetAllPositions();
            boolean flag = false;
            for(var p : poses){
                if(p.get(0) < 0){
                    cur.MoveRight();
                    flag = true;
//                    System.out.println(p);
                    break;

                }
                else if(p.get(0) > 9){
                    cur.MoveLeft();
//                    System.out.println(p);
                    flag = true;
                    break;
                }
                else if(p.get(1) < 0 || getBlock(p.get(1),p.get(0))){
                    cur.MoveUp();
                    flag = true;
                    break;
                }
            }

            if(!flag)
                break;
        }


        boolean flag = false;
        for(var p : poses){
            if(p.get(1) == 0 || getBlock(p.get(1) - 1,p.get(0))){
                flag = true;
//                break;
            }
        }

        if(flag){
            for(var p : poses){
                setBlock(p.get(1),p.get(0),true);
            }
            cur = new Stick();
        }

        while (true) {
            boolean full = true;
            for (int j = 0; j < 10; j++) {
                if (!getBlock(0, j)) {
                    full = false;
                }
            }
            if (full) {
                for(int i = 0;i < 24;i++){
                    for(int j = 0;j < 10;j++){
                        setBlock(i,j,getBlock(i + 1,j));
                    }
                }
            }
            else{
                break;
            }
        }

        this.ShowBoard();
    }

    public void ShowBoard(){
        for(int i = 19;i >= 0;i--){
            System.out.print("| ");
            for(int j = 0;j < 10;j++){
                if(cur.isBlock(i,j)){
                    System.out.print("@");
                }
                else {
                    if (!getBlock(i, j))
                        System.out.print("□");
                    else
                        System.out.print("■");

                }
                System.out.print(" ");
            }
            System.out.print("|\n");
        }
        System.out.println(" _____________________");
    }
}


