
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameOverPanel extends JPanel {
    public GameOverPanel(String playerName, int score, List<String> topScores) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Game Over", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        add(title, BorderLayout.NORTH);

        JLabel scoreLabel = new JLabel("Your Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        add(scoreLabel, BorderLayout.CENTER);

        JTextArea scoreBoard = new JTextArea();
        scoreBoard.setEditable(false);
        scoreBoard.setFont(new Font("Monospaced", Font.PLAIN, 16));
        scoreBoard.append("High Scores:\n");
        for (String s : topScores) {
            scoreBoard.append(s + "\n");
        }

        add(scoreBoard, BorderLayout.SOUTH);
    }
}
