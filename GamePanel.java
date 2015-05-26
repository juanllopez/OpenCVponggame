/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;




/**
 *
 * @author Max
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {


VideoCapture webSource = new VideoCapture(0);
    int LowH = 0;
    int LowS = 0;
    int LowV = 0;
    int HighH = 179;
    int HighS = 255;
    int HighV = 255;
    int LowH1 = 0;
    int LowS1 = 0;
    int LowV1 = 0;
    int HighH1 = 179;
    int HighS1 = 255;
    int HighV1 = 255;
    double iLastX=-1;
    double iLastY=-1;
    double iLastX1=-1;
    double iLastY1=-1;
    Mat frame = new Mat();
    Mat prueba = new Mat();
    Mat frame1 = new Mat();
    Mat frame2 = new Mat();
    Mat frame3 = new Mat();
    MatOfByte mem = new MatOfByte();
    MatOfByte mem1 = new MatOfByte();
    MatOfByte mem2 = new MatOfByte();
    MatOfByte mem3 = new MatOfByte();
    
    
    Player player = new Player();
    Player2 player2 = new Player2();
    Ball ball = new Ball();
    

    public GamePanel() {
        Timer time = new Timer(50, this);
        time.start();

        this.addKeyListener(this);
        this.setFocusable(true);
    }

    private void update() {
        player.update();
        ball.update();
        
        player2.update();

        ball.checkCollisionWith(player);
        ball.checkCollisionWith(player2);
        
        ball.hitWall();
        detection();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Pong.WINDOW_WIDTH, Pong.WINDOW_HEIGHT);

        player.paint(g);
        player2.paint(g);
        ball.paint(g);
        

        g.setColor(Color.blue);
        g.drawLine(0, 30, Pong.WINDOW_WIDTH, 30);
        g.drawLine(Pong.WINDOW_WIDTH / 2, 30, Pong.WINDOW_WIDTH / 2, Pong.WINDOW_HEIGHT);
        g.drawOval((Pong.WINDOW_WIDTH / 2) - 30, Pong.WINDOW_HEIGHT / 2 - 30, 60, 60);
        g.setColor(Color.yellow);



    }

    public Ball getBall() {
        return ball;
    }

    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.setYVelocity(-5);
            if (player.getY() < 30) {
                player.setYVelocity(0);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.setYVelocity(5);
            if (player.getY() + 40 > Pong.WINDOW_HEIGHT - 28) {
                player.setYVelocity(0);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
            player.setYVelocity(0);

        }
    }

    public void keyTyped(KeyEvent e) {
    }
    
    public void detection(){
         if (webSource.grab()) {
                        try {
                            webSource.retrieve(frame);
                              
                            Imgproc.cvtColor(frame,prueba,Imgproc.COLOR_RGB2HSV);
                            Core.inRange(prueba,new Scalar(4,159,64),new Scalar(16,255,140),frame1);
                            Core.inRange(prueba,new Scalar(102,119,126),new Scalar(109,161,255),frame2);
                       
                           /*
                            System.out.println("lh"+LowH1);
                            System.out.println("ls"+LowS1);
                            System.out.println("lv"+LowV1);
                            System.out.println("hh"+HighH1);
                            System.out.println("hs"+HighS1);
                            System.out.println("hv"+HighV1);
                            */
                            Moments oMoments = Imgproc.moments(frame1);
                            
                            
                            double dM01 = oMoments.get_m01();
                            double dM10 = oMoments.get_m10();
                            double dArea = oMoments.get_m00();
                            
                            if(dArea > 1000)
                            {
                                double posX = dM10 / dArea;
                                double posY = dM01 / dArea;
                            
                                
                            if (iLastX >= 0 && iLastY >=0 && posX >=0 && posY >=0)
                            {
                                                             
                                                                
                                iLastX=Math.round(iLastX);
                                iLastY=Math.round(iLastY);
                                
                            }
                            iLastX = posX;
                            iLastY = posY;
                            
                            player.setYVelocity((int)(posY*0.63));
                            
                            }
                            
                            Moments oMoments1 = Imgproc.moments(frame2);
                            
                            
                            double d1M01 = oMoments1.get_m01();
                            double d1M10 = oMoments1.get_m10();
                            double d1Area = oMoments1.get_m00();
                            
                            if(d1Area > 1000)
                            {
                                double posX1 = d1M10 / d1Area;
                                double posY1 = d1M01 / d1Area;
                            
                                
                            if (iLastX1 >= 0 && iLastY1 >=0 && posX1 >=0 && posY1 >=0)
                            {
                                                             
                                iLastX1=Math.round(iLastX1);
                                iLastY1=Math.round(iLastY1);
                            }
                            iLastX1 = posX1;
                            iLastY1 = posY1;
                            
                            player2.setYVelocity((int)(posY1*0.63));
                                
                            }
                            
    }
                         catch (Exception ex) {
                            System.out.println("Error");
                        }
}}}

