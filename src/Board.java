import java.util.*;

public class Board {
    boolean[][] grid = new boolean[25][10];
    int x = 0;
    Scanner input;

    Piece cur;
    Piece next;
    int score = 0;
    int cleared = 0;
    final int[] scores = {40,100,300,1200};
    ArrayList<Integer> pieces = new ArrayList<Integer>();
    int nextpieceind = 0;

    public void setBlock(int row,int col,boolean val){
        grid[row][col] = val;
    }

    public boolean getBlock(int row,int col){
        return grid[row][col];
    }


    public void ResetBuffer(){
        Collections.shuffle(pieces);
        nextpieceind = 0;
    }

    public Piece GetNextPiece(){
        Piece p = switch(pieces.get(nextpieceind)){
            case 0 -> new OPiece();
            case 1 -> new IPiece();
            case 2 -> new LPiece();
            case 3 -> new LRPiece();
            case 4 -> new JPiece();
            case 5 -> new JRPiece();
            case 6 -> new TPiece();
            default -> new OPiece();
        };

        nextpieceind++;
        if(nextpieceind == 7){
            ResetBuffer();
        }

        return p;
    }

    public ArrayList<ArrayList<Integer>> GetSlam(ArrayList<ArrayList<Integer>> poses){
        int x = 0;
        while(true){
            boolean flag = false;
            for(var p : poses){
                if(p.get(1) - x == 0){
                    flag = true;
                    break;
                }
                if(getBlock(p.get(1) - x - 1,p.get(0))){
                    flag = true;
                    break;
                }
            }

            if(flag){
                var newposes = new ArrayList<ArrayList<Integer>>();

                for(var p : poses){
                    var pos = new ArrayList<Integer>();

                    pos.add(p.get(0));
                    pos.add(p.get(1) - x);

                    newposes.add(pos);
                }
                return newposes;
            }
            x++;
        }

    }

    public Board(){
        for(int i = 0;i < 7;i++) pieces.add(i);
        ResetBuffer();
        cur = GetNextPiece();
        next = GetNextPiece();
        input = new Scanner(System.in);
        for(int i = 0;i < 25;i++){
            for(int j = 0;j < 10;j++){
                setBlock(i,j,false);
            }
        }


        ShowBoard();
    }

    public void FillSpace(ArrayList<ArrayList<Integer>> poses){
        for(var p : poses){
            setBlock(p.get(1),p.get(0),true);
        }
        cur = next;
        Random r = new Random();

        next = GetNextPiece();
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
        else if(move.equals("e") || move.equals("w")){
            cur.RotateRight();
        }
        else if(move.equals("q")){
            cur.RotateLeft();
        }
        else if(move.equals("s")){
            FillSpace(GetSlam(poses));
        }
        else if(!move.isEmpty()){
            return;
        }


    }

    public boolean UpdateBoard() {

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
            FillSpace(poses);
        }

        for(int h = 0;h < 20;h++){
            boolean full = true;
            boolean empty = true;
            for (int j = 0; j < 10; j++) {
                if (!getBlock(h, j)) {
                    full = false;
                    break;
                }
                else{
                    empty = false;
                }
            }
            if (full) {
                cleared++;
                for(int i = h;i < 24;i++){
                    for(int j = 0;j < 10;j++){
                        setBlock(i,j,getBlock(i + 1,j));
                    }
                }
                h -= 1;
            }
            if(empty){
                break;
            }
        }

        this.ShowBoard();
        for(int i = 0;i < 10;i++){
            if(getBlock(19,i)){
                return false;
            }
        }
        return true;
    }

    public void ShowBoard(){
        var ghost = GetSlam(cur.GetAllPositions());
        for(int i = 19;i >= 0;i--){
            System.out.print("| ");
            for(int j = 0;j < 10;j++){
                boolean flag = false;
                for(var g : ghost){
                    if(g.get(0) == j && g.get(1) == i){
                        flag = true;
                        break;
                    }
                }
                if(cur.isBlock(i,j)){
                    System.out.print("#");
                }
                else if(flag){
                    System.out.print("x");
                }
                else {
                    if (!getBlock(i, j))
                        System.out.print("-");
                    else
                        System.out.print("■");

                }
                System.out.print(" ");
            }
            System.out.print("|");
            if(i == 18){
                System.out.print(" Next");
            }
            else if(i == 17){
                System.out.print(" --------- ");
            }
            else if(i <= 16 && i >= 14) {
                System.out.print("| ");
                System.out.print(next.getRow(16 - i));
                System.out.print("|");

            }
            else if(i == 13){
                System.out.print(" --------- ");
            }
            else if(i == 12){
                System.out.print(" Score:");
            }
            else if(i == 11){
                System.out.print("     " + score);
                if(cleared != 0){
                    System.out.print("  +" + scores[cleared - 1]);
                    score += scores[cleared - 1];
                    cleared = 0;
                }
            }
            System.out.print("\n");
        }
        System.out.println(" _____________________");
    }
}


