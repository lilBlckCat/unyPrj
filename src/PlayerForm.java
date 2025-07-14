import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//این یکی هم دریاقت اطلاعات و این داستانها
public class PlayerForm extends JFrame {
    private JTextField nameField;
    private JTextField idField;
    private String playerName;
    private String playerId;
    private boolean submitted = false;

    public PlayerForm() {
        setTitle("Player Info");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        nameField = new JTextField();
        idField = new JTextField();
        JButton submitButton = new JButton("Start Game");

        add(new JLabel("Enter your name:"));
        add(nameField);

        add(new JLabel("Enter your ID:"));
        add(idField);

        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText().trim();
                playerId = idField.getText().trim();

                if (playerName.isEmpty()) {
                    JOptionPane.showMessageDialog(PlayerForm.this, "Please enter your name.");
                    return;
                }

                submitted = true;
                dispose();
            }
        });
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public boolean isSubmitted() {
        return submitted;
    }
}
