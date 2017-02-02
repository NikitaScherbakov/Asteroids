public class RepaintTheBoard implements Runnable{
    BoardFrame theBoard;

    public RepaintTheBoard(BoardFrame theBoard){
        this.theBoard = theBoard;
    }
    public void run(){
        theBoard.repaint();
    }
}
