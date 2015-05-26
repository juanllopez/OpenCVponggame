/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Max
 */
public class Player2 {

    private int y = Pong.WINDOW_HEIGHT / 2;
    private int yVelocity = 0;
    private int width = 10;
    private int height = 40;

    public Player2() {
    }

    public void update() {
        y = y + yVelocity;
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(Pong.WINDOW_WIDTH - (35 + width), y, width, height);
    }

    public void setYVelocity(int speed) {
        y = speed;
    }

    public int getX() {
        return Pong.WINDOW_WIDTH - 6 - (35 + width);
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

