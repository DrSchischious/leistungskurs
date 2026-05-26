package kursarbeit03;

import javax.swing.*;
import java.awt.*;


public class PokeGUI extends JFrame {

    public PokeGUI() {
        setTitle("PokeGUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel imagePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        imagePanel.setPreferredSize(new Dimension(900, 420));

        JLabel leftImage = new JLabel("Bild links", SwingConstants.CENTER);
        JLabel rightImage = new JLabel("Bild rechts", SwingConstants.CENTER);

        leftImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rightImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        imagePanel.add(leftImage);
        imagePanel.add(rightImage);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setPreferredSize(new Dimension(900, 180));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 1; i <= 6; i++) {
            buttonPanel.add(new JButton("Button " + i));
        }

        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PokeGUI::new);
    }

}

