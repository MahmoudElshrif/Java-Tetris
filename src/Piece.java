public class Piece {
    int currot = 0;
    boolean[][][] rots = new boolean[4][3][3];

    public void RotateRight(){
        currot += 1;
        currot = currot % 3;
    }

    public void RotateLeft(){
        currot -= 1;
        if(currot == -1){
            currot = 3;
        }
    }
}
