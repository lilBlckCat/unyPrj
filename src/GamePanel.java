import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private PlayerShip player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private Random random;
    private int score = 0;
    private boolean gameOver = false;

    public GamePanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        player = new PlayerShip(370, 500);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        random = new Random();

        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.draw(g);

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 25);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over", getWidth() / 2 - 90, getHeight() / 2);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Final Score: " + score, getWidth() / 2 - 70, getHeight() / 2 + 30);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();

        // تولد دژمنهای جدید
        if (random.nextInt(100) < 1) { // زمان تولید
            int x = random.nextInt(750); // اندازه دژمن
            enemies.add(new Enemy(x, 0));
        }

        // حرکت دشمنهای موزی
        Iterator<Enemy> enemyIter = enemies.iterator();
        while (enemyIter.hasNext()) {
            Enemy enemy = enemyIter.next();
            enemy.update();

            if (enemy.getY() > 600) {
                timer.stop();
                SoundPlayer.playSound("sound/Jump4.wav");//صداهای ناراحت کننده
                JOptionPane.showMessageDialog(this, "Game Over!\nyour final score:"+score);
                System.exit(0);//اینجا هم بازی تموم میشه دیگه
            }
        }

        // در اینجا حرکت تیر رو داریم
        Iterator<Bullet> bulletIter = bullets.iterator();
        while (bulletIter.hasNext()) {
            Bullet bullet = bulletIter.next();
            bullet.update();
            if (bullet.getY() < 0) {
                bulletIter.remove();
            }
        }

        // ایا دشمنی کوشته شده یا نه
        bulletIter = bullets.iterator();
        while (bulletIter.hasNext()) {
            Bullet bullet = bulletIter.next();
            enemyIter = enemies.iterator();
            while (enemyIter.hasNext()) {
                Enemy enemy = enemyIter.next();
                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    bulletIter.remove();
                    enemyIter.remove();
                    score += 10;

                    break;
                }
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bullets.add(new Bullet(player.getX() + 20, player.getY()));
            SoundPlayer.playSound("sound/Random5.wav");

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
