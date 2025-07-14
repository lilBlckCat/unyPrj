import javax.swing.JFrame;
//سلام.این رشته کدها کار صدف،به شماره دانشجویی40317553 هستش.
//!اگر فکر میکنید بازی مشکلی داره باید بگم که مشکل از خودتونه.
public class Main {
    public static void main(String[] args) {

        PlayerForm form = new PlayerForm();
        form.setVisible(true);

        while (!form.isSubmitted()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String name = form.getPlayerName();
        String id = form.getPlayerId();

        JFrame frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
