import java.awt.*;
import java.awt.event.KeyEvent;
// این کلاس برای حرکت بازیکن بخت برگشته است
public class PlayerShip {
    private int x, y;
    private int speed;
    private boolean left, right;

    public PlayerShip(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 5;
    }

    public void update() {
        if (left && x > 0) x -= speed;
        if (right && x < 750) x += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 50, 20);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 20);
    }
}
