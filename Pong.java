/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

/**
 *
 * @author Max
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.opencv.core.Core;

public class Pong extends JFrame {

    final static int WINDOW_WIDTH = 406;
    final static int WINDOW_HEIGHT = 278;

    public Pong() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new GamePanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new Pong();
        
    }
}

