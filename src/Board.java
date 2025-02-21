public class Board {
    int[][] grid = new int[25][10];
    int x = 0;

    public void setBlock(int row,int col,int val){
        grid[row][col] = val;
    }

    public int getBlock(int row,int col){
        return grid[row][col];
    }
    public Board(){
        for(int i = 0;i < 25;i++){
            for(int j = 0;j < 10;j++){
                setBlock(i,j,0);
            }
        }
        for(int i = 0;i < 10;i++){
            setBlock(24,i,2);
        }
    }

    public void UpdateBoard() {
        x++;
        if (x == 5) {
            x = 0;
            for (int i = 0; i < 5; i++) {
                setBlock(24, i, 2);
            }
            for (int i = 5; i < 10; i++) {
                setBlock(23, i, 2);
            }
        }
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 10; j++) {
                if (getBlock(i, j) == 2) {
                    if (i == 0) setBlock(i, j, 1);
                    else if (getBlock(i - 1, j) != 0) {
                        setBlock(i, j, 1);
                    } else {
                        setBlock(i, j, 0);
                        setBlock(i - 1, j, 2);
                    }
                }
            }
        }


        boolean flag = true;
        while (flag) {
            for (int j = 0; j < 10; j++) {
                if (getBlock(0, j) != 1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                for(int i = 0;i < 24;i++){
                    for(int j = 0;j < 10;j++){
                        if(getBlock(i,j) == 2) continue;
                        setBlock(i,j,getBlock(i + 1,j) == 1 ? 1 : 0);
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
                switch(getBlock(i,j)){
                    case 0 -> System.out.print("□");
                    case 1 -> System.out.print("■");
                    case 2 -> System.out.print("B");
                }
                System.out.print(" ");
            }
            System.out.print("|\n");
        }
        System.out.println("______________________");
    }
}


