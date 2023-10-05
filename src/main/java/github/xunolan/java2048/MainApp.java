package github.xunolan.java2048;

public class MainApp {
    public static void init(){
        Board board = new Board(4).boardInit().randomInit(2, 2);
        System.out.println(board);
        board.upOrDown(Direction.up);
        System.out.println(board);
        board.upOrDown(Direction.down);
        System.out.println(board);
        board.upOrDown(Direction.up);
        System.out.println(board);
        board.upOrDown(Direction.up);
        System.out.println(board);
        board.upOrDown(Direction.down);
        System.out.println(board);
        board.upOrDown(Direction.up);
        System.out.println(board);
        board.upOrDown(Direction.up);
        System.out.println(board);
        board.upOrDown(Direction.up);
        System.out.println(board);
    }

    public static void main(String[] args) {
        MainApp.init();
    }
}
