import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BoardFrame extends JFrame{

    private static final long serialVersionUID = 1L;

    public static int boardWidth = 1000;
    public static int boardHeight = 800;

    public static boolean keyHeld = false;

    public static int keyHeldCode;

    public static ArrayList<ShootTorpedo> shootTorpedos = new ArrayList<ShootTorpedo>();

    String thrustFile = "file:./src/thrust.au";
    String laserFile = "file:./src/laser.aiff";

    public static void main(String[] args){
        new BoardFrame();
    }

    public BoardFrame(){
        this.setSize(boardWidth, boardHeight);
        this.setTitle("Asteroids");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 87){
                    System.out.println("Forward");

                    BoardFrame.playSoundEffect(thrustFile);
                    keyHeldCode = e.getKeyCode();
                    keyHeld = true;
                }else if(e.getKeyCode() == 83){
                    System.out.println("Backward");
                    keyHeldCode = e.getKeyCode();
                    keyHeld = true;
                }else if(e.getKeyCode() == 68){
                    System.out.println("Rotate right");

                    keyHeldCode = e.getKeyCode();
                    keyHeld = true;
                }else if(e.getKeyCode() == 65){
                    System.out.println("Rotate left");

                    keyHeldCode = e.getKeyCode();
                    keyHeld = true;
                }else if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    System.out.println("Shoot");
                    playSoundEffect(laserFile);
                    shootTorpedos.add(new ShootTorpedo(GameDrawingPanel.spaceShip.getShipNoseX(),
                            GameDrawingPanel.spaceShip.getShipNoseY(),
                            GameDrawingPanel.spaceShip.getRotationAngle()));
                    System.out.println("Rotation angle: " + GameDrawingPanel.spaceShip.getRotationAngle());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyHeld = false;
            }
        });

        GameDrawingPanel gameDrawingPanel = new GameDrawingPanel();

        this.add(gameDrawingPanel, BorderLayout.CENTER);

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);

        scheduledThreadPoolExecutor.scheduleAtFixedRate(new RepaintTheBoard(this), 0L, 20L, TimeUnit.MILLISECONDS);

        this.setVisible(true);
    }

    public static void playSoundEffect(String soundToPlay){
        URL soundLocation;
        try{
            soundLocation = new URL(soundToPlay);
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundLocation);
            clip.open(inputStream);
            clip.loop(0);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException e1){
            e1.printStackTrace();
        } catch (LineUnavailableException e1){
            e1.printStackTrace();
        }
    }
}
