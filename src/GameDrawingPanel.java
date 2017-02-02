import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GameDrawingPanel extends JComponent{
    public ArrayList<Rock> rocks = new ArrayList<Rock>();

    int[] polyXArray = Rock.sPolyXArray;
    int[] polyYArray = Rock.sPolyYArray;

    int width = BoardFrame.boardWidth;
    int height = BoardFrame.boardHeight;

    static SpaceShip spaceShip = new SpaceShip();

    @SuppressWarnings("serial")
    public GameDrawingPanel(){
        for(int i = 0; i<50; i++){
            int randomStartXPos = (int) (Math.random()*(BoardFrame.boardWidth-40) + 1);
            int randomStartYPos = (int) (Math.random()*(BoardFrame.boardHeight-40) + 1);

            rocks.add(new Rock(Rock.getsPolyXArray(randomStartXPos), Rock.getsPolyYArray(randomStartYPos), 13, randomStartXPos, randomStartYPos));
            Rock.rocks = rocks;
        }
    }
    public void paint(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D)graphics;

        AffineTransform identity = new AffineTransform();

        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0,0,getWidth(),getHeight());

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setPaint(Color.WHITE);

        for(Rock rock : rocks){
            if(rock.onScreen){
                rock.move(spaceShip, BoardFrame.shootTorpedos);
                graphics2D.draw(rock);
            }
        }

        if(BoardFrame.keyHeld && BoardFrame.keyHeldCode == 68){
            spaceShip.increaseRotationAngle();
            System.out.println("Ship angle: " + spaceShip.getRotationAngle());
        }else if(BoardFrame.keyHeld && BoardFrame.keyHeldCode == 65){
            spaceShip.decreaseRotationAngle();
            System.out.println("Ship angle: " + spaceShip.getRotationAngle());
        }else if(BoardFrame.keyHeld && BoardFrame.keyHeldCode == 87){
            spaceShip.setMovingAngle(spaceShip.getRotationAngle());
            spaceShip.increaseXVelosity(spaceShip.shipXMoveAngle(spaceShip.getMovingAngle())*0.1);
            spaceShip.increaseYVelosity(spaceShip.shipYMoveAngle(spaceShip.getMovingAngle())*0.1);
        }
        spaceShip.move();
        graphics2D.setTransform(identity);
        graphics2D.translate(BoardFrame.boardWidth/2, BoardFrame.boardHeight/2);
        graphics2D.rotate(Math.toRadians(spaceShip.getRotationAngle()));
        graphics2D.draw(spaceShip);

        for(ShootTorpedo shootTorpedo : BoardFrame.shootTorpedos){
            shootTorpedo.move();
            if(shootTorpedo.onScreen){
                graphics2D.setTransform(identity);
                graphics2D.translate(shootTorpedo.getxCenter(), shootTorpedo.getyCenter());
                graphics2D.draw(shootTorpedo);
            }
        }
    }
}
