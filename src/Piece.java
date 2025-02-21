import java.util.ArrayList;

public class Piece {
    int currot = 0;
    boolean[][][] rots = new boolean[4][4][4];
    public int x = 3;
    public int y = 20;

    public void MoveRight(){
        x += 1;
    }

    public void MoveLeft(){
        x -= 1;
    }

    public void MoveDown(){
        y -= 1;
    }

    public void MoveUp(){
        y += 1;
    }

    public void RotateRight(){
        currot += 1;
        currot = currot % 4;
    }

    public void RotateLeft(){
        currot -= 1;
        if(currot == -1){
            currot = 3;
        }
    }

    public boolean isBlock(int j, int i) {
        if (i < x || i > x + 3 || j < y || j > y + 3) {
            return false;
        }

        return rots[currot][i - x][j - y];
    }

    public String getRow(int row){
        StringBuilder s = new StringBuilder();
        for(int i = 0;i < 4;i++){
            if(rots[0][row][i]){
                s.append("@ ");
            }
            else{
                s.append("  ");
            }
        }
        return s.toString();
    }
    public ArrayList<ArrayList<Integer>> GetAllPositions(){
        ArrayList<ArrayList<Integer>> poses = new ArrayList<ArrayList<Integer>>();
        for(int i = 0;i < 4;i++){
            for(int j = 0;j < 4;j++){
                if(!rots[currot][i][j]) continue;
                ArrayList<Integer> pos = new ArrayList<Integer>();
                pos.add(x + i);
                pos.add(y + j);
                poses.add(pos);
            }
        }
        return poses;
    }

}

class OPiece extends Piece {
    public OPiece(){
        rots[0] = new boolean[][]{{false,false,false,false},{false,true,true,false},{false,true,true,false},{false,false,false,false}};
        rots[1] = new boolean[][]{{false,false,false,false},{false,true,true,false},{false,true,true,false},{false,false,false,false}};
        rots[2] = new boolean[][]{{false,false,false,false},{false,true,true,false},{false,true,true,false},{false,false,false,false}};
        rots[3] = new boolean[][]{{false,false,false,false},{false,true,true,false},{false,true,true,false},{false,false,false,false}};
    }
}

class IPiece extends Piece {
    public IPiece(){
        rots[0] = new boolean[][]{{false,false,false,false},{true,true,true,true},{false,false,false,false},{false,false,false,false}};
        rots[1] = new boolean[][]{{false,false,true,false},{false,false,true,false},{false,false,true,false},{false,false,true,false}};
        rots[2] = rots[0];
        rots[3] = rots[1];
    }
}

class LPiece extends Piece {
    public LPiece(){
        rots[0] = new boolean[][]{{false,false,false,false},{true,true,true,false},{false,false,true,false},{false,false,false,false}};
        rots[1] = new boolean[][]{{false,true,false,false},{false,true,false,false},{true,true,false,false},{false,false,false,false}};
        rots[2] = new boolean[][]{{true,false,false,false},{true,true,true,false},{false,false,false,false},{false,false,false,false}};
        rots[3] = new boolean[][]{{false,true,true,false},{false,true,false,false},{false,true,false,false},{false,false,false,false}};
    }
}

class LRPiece extends Piece {
    public LRPiece(){
        rots[0] = new boolean[][]{{false,false,false,false},{true,true,true,false},{true,false,false,false},{false,false,false,false}};
        rots[1] = new boolean[][]{{true,true,false,false},{false,true,false,false},{false,true,false,false},{false,false,false,false}};
        rots[2] = new boolean[][]{{false,false,true,false},{true,true,true,false},{false,false,false,false},{false,false,false,false}};
        rots[3] = new boolean[][]{{false,true,false,false},{false,true,false,false},{false,true,true,false},{false,false,false,false}};
    }
}

class JPiece extends Piece {
    public JPiece(){
        rots[0] = new boolean[][]{{false,true,false,false},{false,true,true,false},{false,false,true,false},{false,false,false,false}};
        rots[1] = new boolean[][]{{false,false,false,false},{false,true,true,false},{true,true,false,false},{false,false,false,false}};
        rots[2] = rots[0];
        rots[3] = rots[1];
    }
}

class JRPiece extends Piece {
    public JRPiece(){
        rots[0] = new boolean[][]{{false,false,true,false},{false,true,true,false},{false,true,false,false},{false,false,false,false}};
        rots[1] = new boolean[][]{{false,false,false,false},{true,true,false,false},{false,true,true,false},{false,false,false,false}};
        rots[2] = rots[0];
        rots[3] = rots[1];
    }
}

class TPiece extends Piece {
    public TPiece(){
        rots[0] = new boolean[][]{{false,false,false,false},{true,true,true,false},{false,true,false,false},{false,false,false,false}};
        rots[1] = new boolean[][]{{false,true,false,false},{true,true,false,false},{false,true,false,false},{false,false,false,false}};
        rots[2] = new boolean[][]{{false,true,false,false},{true,true,true,false},{false,false,false,false},{false,false,false,false}};
        rots[3] = new boolean[][]{{false,true,false,false},{false,true,true,false},{false,true,false,false},{false,false,false,false}};
    }
}