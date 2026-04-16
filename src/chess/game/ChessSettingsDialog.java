package chess.game;

import javax.swing.*;
import java.awt.*;

public class ChessSettingsDialog extends JDialog {

    private final JComboBox<String> boardThemeBox;
    private final JComboBox<String> pieceStyleBox;
    private final JComboBox<String> boardSizeBox;

    public ChessSettingsDialog(JFrame parent, ChessGUI gui) {
        super(parent, "Settings", true);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        boardThemeBox = new JComboBox<>(new String[] {"Classic", "Pink", "Blue"});
        pieceStyleBox = new JComboBox<>(new String[] {"Default", "Bold", "Large"});
        boardSizeBox = new JComboBox<>(new String[] {"Small", "Medium", "Large"});

        formPanel.add(new JLabel("Board Theme:"));
        formPanel.add(boardThemeBox);

        formPanel.add(new JLabel("Piece Style:"));
        formPanel.add(pieceStyleBox);

        formPanel.add(new JLabel("Board Size:"));
        formPanel.add(boardSizeBox);

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(e -> {
            gui.applySettings(
                (String) boardThemeBox.getSelectedItem(),
                (String) pieceStyleBox.getSelectedItem(),
                (String) boardSizeBox.getSelectedItem()
            );
            dispose();

            });

        add(formPanel, BorderLayout.CENTER);
        add(applyButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }
}