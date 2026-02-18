import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private JLabel statusLabel;
    private JButton resetButton;
    private int xWins = 0;
    private int oWins = 0;
    private int draws = 0;
    private JLabel scoreLabel;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3, 5, 5));
        boardPanel.setBackground(Color.WHITE);
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(Color.BLACK);

                final int row = i;
                final int col = j;

                 buttons[i][j].addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e) {
                         buttonClicked(row, col);
                     }
                 });

                 boardPanel.add(buttons[i][j]);
            }
        }

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));

        scoreLabel = new JLabel("X: 0 | O: 0 | Draws: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        resetButton = new JButton("New Game");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(statusLabel);
        topPanel.add(scoreLabel);

        statusPanel.add(topPanel, BorderLayout.CENTER);
        statusPanel.add(resetButton, BorderLayout.SOUTH);

        add(boardPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);

        setSize(500, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buttonClicked(int row, int col) {
        if (!buttons[row][col].getText().equals("")) {
            return;
        }

        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setEnabled(false);

        if (currentPlayer == 'X') {
            buttons[row][col].setForeground(new Color(52, 152, 219));
        } else {
            buttons[row][col].setForeground(new Color(231, 76, 60));
        }

        if (checkWinner()) {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            if (currentPlayer == 'X') {
                xWins++;
            } else {
                oWins++;
            }
            updateScoreLabel();
            disableAllButtons();
            return;
        }

        if (isBoardFull()) {
            statusLabel.setText("It's a draw!");
            draws++;
            updateScoreLabel();
            return;
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        statusLabel.setText("Player " + currentPlayer + "'s turn");
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                highlightWinningCombination(i, 0, i, 1, i, 2);
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                highlightWinningCombination(0, 0, 1, 1, 2, 2);
                return true;
            }
        }
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            highlightWinningCombination(0, 0, 1, 1, 2, 2);
            return true;
        }

        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            highlightWinningCombination(0, 2, 1, 1, 2, 0);
            return true;
        }

        return false;
    }

    private void highlightWinningCombination(int r1, int c1, int r2, int c2, int r3, int c3) {
        Color highlightColor = new Color(46, 204, 113);
        buttons[r1][c1].setBackground(highlightColor);
        buttons[r2][c2].setBackground(highlightColor);
        buttons[r3][c3].setBackground(highlightColor);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(Color.WHITE);
            }
        }
        currentPlayer = 'X';
        statusLabel.setText("Player X's turn");
    }

    private void updateScoreLabel() {
        scoreLabel.setText("X: " + xWins + " | O: " + oWins + " | Draws: " + draws);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToe();
            }
        });
    }
}
