import java.awt.*;

@SuppressWarnings("serial")
public class SpaceShip extends Polygon {

    private double xVelocity = 0, yVelocity = 0;

    int bFWidth = BoardFrame.boardWidth;
    int bFHeight = BoardFrame.boardHeight;

    private double xCenter = bFWidth/2, yCenter = bFHeight/2;

    public static int[] polyXArray = {-13, 14, -13, -5, -13};
    public static int[] polyYArray = {-15, 0, 15, 0, -15};

    private int shipWidth = 27, shipHeight = 30;

    private double uLeftXPos = getXCenter() + this.polyXArray[0];
    private double uLeftYPos = getYCenter() + this.polyYArray[0];


    private double rotationAngle = 0, movingAngle = 0;

    public boolean onScreen = true;

    public SpaceShip(){
        super(polyXArray, polyYArray, 5);
    }

    public double getXCenter() {
        return xCenter;
    }

    public void setXCenter(double xCenter) {
        this.xCenter = xCenter;
    }

    public double getYCenter() {
        return yCenter;
    }

    public void setYCenter(double yCenter) {
        this.yCenter = yCenter;
    }

    public void increaseXPos(double incAmt){
        this.xCenter += incAmt;
    }

    public void increaseYPos(double incAmt){
        this.yCenter += incAmt;
    }

    public double getuLeftXPos() {
        return uLeftXPos;
    }

    public void setuLeftXPos(double uLeftXPos) {
        this.uLeftXPos = uLeftXPos;
    }

    public double getuLeftYPos() {
        return uLeftYPos;
    }

    public void setuLeftYPos(double uLeftYPos) {
        this.uLeftYPos = uLeftYPos;
    }

    public int getShipWidth() {
        return shipWidth;
    }

    public int getShipHeight() {
        return shipHeight;
    }

    public void setShipWidth(int shipWidth) {
        this.shipWidth = shipWidth;
    }

    public void setShipHeight(int shipHeight) {
        this.shipHeight = shipHeight;
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

    public void increaseXVelosity(double xVelInc){
        this.xVelocity += xVelInc;
    }

    public void increaseYVelosity(double yVelInc){
        this.yVelocity += yVelInc;
    }

    public void decreaseXVelosity(double xVelInc){
        this.xVelocity -= xVelInc;
    }

    public void decreaseYVelosity(double yVelInc){
        this.yVelocity -= yVelInc;
    }

    public double getMovingAngle() {
        return movingAngle;
    }

    public void setMovingAngle(double movingAngle) {
        this.movingAngle = movingAngle;
    }

    public void increaseMovingAngle(double moveAngle){
        this.movingAngle += moveAngle;
    }

    public double shipXMoveAngle(double xMoveAngle){
        return (double) Math.cos(xMoveAngle * Math.PI / 180);
    }
    public double shipYMoveAngle(double yMoveAngle){
        return (double) Math.sin(yMoveAngle * Math.PI / 180);
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public void increaseRotationAngle(){
        if(getRotationAngle() >= 355){
            rotationAngle = 0;
        }else{
            rotationAngle += 5;
        }
    }

    public void decreaseRotationAngle(){
        if(getRotationAngle() < 0){
            rotationAngle = 355;
        }else{
            rotationAngle -= 5;
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(getShipWidth() - 14, getShipHeight() - 15, getShipWidth(), getShipHeight());
    }

    public double getShipNoseX(){
        return getXCenter() + Math.cos(rotationAngle)*14;
    }

    public double getShipNoseY(){
        return getYCenter() + Math.sin(rotationAngle)*14;
    }

    public void move(){
        this.increaseXPos(this.getxVelocity());
        if(this.getXCenter() < 0){
            this.setXCenter(bFWidth);
        }else if(this.getXCenter() > bFWidth){
            this.setXCenter(0);
        }
        this.increaseYPos(this.getyVelocity());
        if(this.getYCenter() < 0){
            this.setYCenter(bFHeight);
        }else if(this.getYCenter() > bFHeight){
            this.setYCenter(0);
        }
    }
}
