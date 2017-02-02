import java.awt.*;

public class ShootTorpedo extends Polygon{

    private static final long serialVersionUID = 1L;

    int bFWidth = BoardFrame.boardWidth;
    int bFHeight = BoardFrame.boardHeight;

    private double xCenter = 0, yCenter = 0;

    private static int[] polyXArray = {-3,3,3,-3,-3};
    private static int[] polyYArray = {-3,-3,3,3,-3};

    private int torpedoWidth = 6, torpedoHeight = 6;

    public boolean onScreen = false;

    private double movingAngle = 0;

    private double xVelocity = 5, yVelocity = 5;

    public ShootTorpedo(double xCenter, double yCenter, double movingAngle){
        super(polyXArray, polyYArray, 5);

        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.movingAngle = movingAngle;

        onScreen = true;

        setxVelocity(torpedoXMoveAngle(movingAngle)*10);
        setyVelocity(torpedoYMoveAngle(movingAngle)*10);
    }

    public double getxCenter() {
        return xCenter;
    }

    public void setxCenter(double xCenter) {
        this.xCenter = xCenter;
    }

    public double getyCenter() {
        return yCenter;
    }

    public void setyCenter(double yCenter) {
        this.yCenter = yCenter;
    }

    public void changeXPos(double incAmt){
        this.xCenter += incAmt;
    }

    public void changeYPos(double incAmt){
        this.yCenter += incAmt;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getTorpedoWidth() {
        return torpedoWidth;
    }

    public void setTorpedoWidth(int torpedoWidth) {
        this.torpedoWidth = torpedoWidth;
    }

    public int getTorpedoHeight() {
        return torpedoHeight;
    }

    public void setTorpedoHeight(int torpedoHeight) {
        this.torpedoHeight = torpedoHeight;
    }

    public double getMovingAngle() {
        return movingAngle;
    }

    public void setMovingAngle(double movingAngle) {
        this.movingAngle = movingAngle;
    }

    public Rectangle getBounds(){
        return new Rectangle(getTorpedoWidth() - 6, getTorpedoHeight() - 6, getTorpedoWidth(), getTorpedoHeight());
    }

    public double torpedoXMoveAngle(double xMoveAngle){
        return (double) (Math.cos(xMoveAngle * Math.PI/180));
    }

    public double torpedoYMoveAngle(double yMoveAngle){
        return (double) (Math.sin(yMoveAngle * Math.PI/180));
    }

    public void move(){
        if(this.onScreen){
            changeXPos(getxVelocity());
            if(getxCenter()<0){
                onScreen = false;
            }else if(getxCenter() > bFWidth){
                onScreen = false;
            }

            changeYPos(getyVelocity());
            if(getyCenter()<0){
                onScreen = false;
            }else if(getyCenter() > bFHeight){
                onScreen = false;
            }
        }
    }
}
