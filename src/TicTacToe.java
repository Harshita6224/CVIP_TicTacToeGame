import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private boolean playerX;
    private boolean gameFinished;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        playerX = true;
        gameFinished = false;
        buttons = new JButton[3][3];

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }

        JButton startButton = new JButton("Start");
        startButton.addActionListener(this);

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(this);

        JPanel controlPanel = new JPanel();
        controlPanel.add(startButton);
        controlPanel.add(restartButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(boardPanel, BorderLayout.CENTER);
        getContentPane().add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getActionCommand().equals("Start")) {
            playerX = true;
            gameFinished = false;
            clearBoard();
        } else if (e.getActionCommand().equals("Restart")) {
            gameFinished = false;
            clearBoard();
        } else if (!gameFinished) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().isEmpty()) {
            if (playerX) {
                clickedButton.setText("X");
            } else {
                clickedButton.setText("O");
            }
            if (checkWinner()) {
                String winner = playerX ? "X" : "O";
                JOptionPane.showMessageDialog(TicTacToe.this, " Player " + winner +  " Wins! ");
                gameFinished = true;
            } else if (checkDraw()) {
                JOptionPane.showMessageDialog(TicTacToe.this, " It is a draw! ");
                gameFinished = true;
            } else {
                playerX = !playerX;
            }
        }
    }
}
    private void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

        private boolean checkWinner() {
                  for(int i=0;i<3;i++){
                      if(!buttons[i][0].getText().isEmpty() &&
                          buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                          buttons[i][0].getText().equals(buttons[i][2].getText())) {
                          return true;
                      }
                  }

            for(int j=0;j<3;j++){
                    if(!buttons[0][j].getText().isEmpty() &&
                        buttons[0][j].getText().equals(buttons[1][j].getText()) &&
                        buttons[0][j].getText().equals(buttons[2][j].getText())) {
                    return true;
                }
            }

                    if(!buttons[0][0].getText().isEmpty() &&
                        buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                        buttons[0][0].getText().equals(buttons[2][2].getText())) {
                    return true;
                }


                    if(!buttons[0][2].getText().isEmpty() &&
                        buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                        buttons[0][2].getText().equals(buttons[2][0].getText())) {
                    return true;
                }

               return false;
        }


        private boolean checkDraw() {
            for (int i = 0; i < 3; i++) {
                 for (int j = 0; j < 3; j++) {
                    if (buttons[i][j].getText().equals("")) {
                        return false;
                    }
                  }
                }
            return true;
        }



        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true);
        });
    }
}
