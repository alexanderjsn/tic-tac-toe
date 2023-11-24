import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.random.*;

public class Main {
    private static Font universalFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
    private static boolean playerOne = true;
    private static String chosenButton;
    private static String opponentButton;
    //private static boolean aiMode = true;

    // array av knappar
    private static  JButton[] gameButtons = new JButton[9];
   private static  JButton startGame = new JButton("Start Game");


    public static void main(String[] args) {

        //Frame settings

        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Extern Frame settings
        JFrame externalFrame = new JFrame("Choose game mode");
        externalFrame.setSize(700, 400);
        externalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        externalFrame.setLayout(new FlowLayout());

        // Meny till extern skärm
        JLabel menuLabel = new JLabel("Choose symbol");
        menuLabel.setFont(universalFont);
        JPanel menuContainer = new JPanel();
        menuContainer.setLayout(new GridLayout(3, 2));
        menuContainer.add(menuLabel);

        // Meny som håller Reset och Start
        JPanel gameMenu = new JPanel();
        gameMenu.setLayout(new GridLayout(3, 2));
        JLabel turns = new JLabel(" turns");
        JButton resetGame = new JButton("Reset Game");
        gameMenu.add(resetGame);
        gameMenu.add(startGame);
        gameMenu.add(turns);
        turns.setFont(universalFont);
        frame.add(gameMenu, BorderLayout.NORTH);

        // Array med string för två knappar
        String[] buttonText = {"X", "O"};
        // array med knappar ( 2 )
        JButton[] menuButtons = new JButton[2];

        // itererar genom knapparna längd, lägger på text från array
        for (int i = 0; i < menuButtons.length; i++) {
            // skapar knapp för varje iterering
            menuButtons[i] = new JButton();
            // lägger från text från buttonText array
            menuButtons[i].setText(buttonText[i]);
            menuButtons[i].setFont(universalFont);


            //action listener för att kunna välja symbol + visa skärmar
            menuButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //logik för att hämta knappens string här:
                    JButton playerButton = (JButton) e.getSource();
                    //sätter variabeln på texterna av knapparna
                    chosenButton = playerButton.getText();
                    opponentButton = "";

                    if (chosenButton.equals("X")) {
                        opponentButton = ("O");
                        frame.setVisible(true);
                        externalFrame.setVisible(false);

                    } else if (chosenButton.equals("O")) {
                        opponentButton = ("X");
                    }
                    frame.setVisible(true);
                    externalFrame.setVisible(false);

                }
            });

            //lägger in i meny
            menuContainer.add(menuButtons[i]);
        }

        externalFrame.add(menuContainer);
        externalFrame.setVisible(true);

        //container för knappar ( för layout )
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new GridLayout(3, 3));




        // for loop som skapar knapparna och lägger in i buttonContainer
        for (int i = 0; i < gameButtons.length; i++) {

            gameButtons[i] = new JButton();
            gameButtons[i].setFont(universalFont);
            int buttonIndex = i;


            // ActionListener till knapparna
            gameButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // om playerOne = true och knappen inte är tagen kan de lägga sitt drag
                    if (playerOne & gameButtons[buttonIndex].getText().isEmpty()) {
                        gameButtons[buttonIndex].setText(chosenButton);
                        gameButtons[buttonIndex].setForeground(Color.BLUE);
                        check();
                        // playOne = false ( alltså O's tur efter dreaget )
                        playerOne = false;
                        turns.setText(opponentButton + "turn");
                        turns.setForeground(Color.RED);

                    }
                    // vice versa
                    else if (!playerOne & gameButtons[buttonIndex].getText().isEmpty()) {
                        gameButtons[buttonIndex].setText(opponentButton);
                        gameButtons[buttonIndex].setForeground(Color.RED);
                        turns.setText(chosenButton + "turn");
                        turns.setForeground(Color.BLUE);
                        check();
                        playerOne = true;
                    }


                }
            });
            // lägger in i container
            gameButtons[i].setEnabled(false);
            buttonContainer.add(gameButtons[i]);
        }


        //restart knapp
        resetGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton button : gameButtons) {
                    button.setText("");
                    startGame.setEnabled(true);
                }
            }
        });


        // random spelare börjar
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random randomTurn = new Random();
                int randomNumber = randomTurn.nextInt(2);
                playerOne = randomTurn.nextInt(2) == 0;
                for (JButton button : gameButtons) {
                    button.setEnabled(true);
                    button.setText("");
                }

                if (playerOne) {
                    turns.setText(chosenButton + "turn");
                    turns.setForeground(Color.BLUE);
                    startGame.setEnabled(false);

                } else {
                    turns.setText(opponentButton + "turn");
                    startGame.setEnabled(false);
                    turns.setForeground(Color.RED);
                }
            }
        });

        frame.add(buttonContainer);
        frame.setVisible(false);
    }



    public static void check() {

        // Rows
        if ((gameButtons[0].getText().equals(opponentButton)) &&
                (gameButtons[1].getText().equals(opponentButton)) &&
                (gameButtons[2].getText().equals(opponentButton))) {
            opponentVictory();
        } else if ((gameButtons[3].getText().equals(opponentButton)) &&
                (gameButtons[4].getText().equals(opponentButton)) &&
                (gameButtons[5].getText().equals(opponentButton))) {
            opponentVictory();
        } else if ((gameButtons[6].getText().equals(opponentButton)) &&
                (gameButtons[7].getText().equals(opponentButton)) &&
                (gameButtons[8].getText().equals(opponentButton))) {
            opponentVictory();
        }

        // Columns
        if ((gameButtons[0].getText().equals(opponentButton)) &&
                (gameButtons[3].getText().equals(opponentButton)) &&
                (gameButtons[6].getText().equals(opponentButton))) {
            opponentVictory();
        } else if ((gameButtons[1].getText().equals(opponentButton)) &&
                (gameButtons[4].getText().equals(opponentButton)) &&
                (gameButtons[7].getText().equals(opponentButton))) {
            opponentVictory();
        } else if ((gameButtons[2].getText().equals(opponentButton)) &&
                (gameButtons[5].getText().equals(opponentButton)) &&
                (gameButtons[8].getText().equals(opponentButton))) {
            opponentVictory();
        }

        // Diagonals
        if ((gameButtons[0].getText().equals(opponentButton)) &&
                (gameButtons[4].getText().equals(opponentButton)) &&
                (gameButtons[8].getText().equals(opponentButton))) {
            opponentVictory();
        } else if ((gameButtons[2].getText().equals(opponentButton)) &&
                (gameButtons[4].getText().equals(opponentButton)) &&
                (gameButtons[6].getText().equals(opponentButton))) {
            opponentVictory();
        }

        // Player 1
        // Rows
        if ((gameButtons[0].getText().equals(chosenButton)) &&
                (gameButtons[1].getText().equals(chosenButton)) &&
                (gameButtons[2].getText().equals(chosenButton))) {
            playerVictory();
        } else if ((gameButtons[3].getText().equals(chosenButton)) &&
                (gameButtons[4].getText().equals(chosenButton)) &&
                (gameButtons[5].getText().equals(chosenButton))) {
            playerVictory();
        } else if ((gameButtons[6].getText().equals(chosenButton)) &&
                (gameButtons[7].getText().equals(chosenButton)) &&
                (gameButtons[8].getText().equals(chosenButton))) {
            playerVictory();
        }

        // Columns
        if ((gameButtons[0].getText().equals(chosenButton)) &&
                (gameButtons[3].getText().equals(chosenButton)) &&
                (gameButtons[6].getText().equals(chosenButton))) {
            playerVictory();
        } else if ((gameButtons[1].getText().equals(chosenButton)) &&
                (gameButtons[4].getText().equals(chosenButton)) &&
                (gameButtons[7].getText().equals(chosenButton))) {
            playerVictory();
        } else if ((gameButtons[2].getText().equals(chosenButton)) &&
                (gameButtons[5].getText().equals(chosenButton)) &&
                (gameButtons[8].getText().equals(chosenButton))) {
            playerVictory();
        }

        // Diagonals
        if ((gameButtons[0].getText().equals(chosenButton)) &&
                (gameButtons[4].getText().equals(chosenButton)) &&
                (gameButtons[8].getText().equals(chosenButton))) {
            playerVictory();
        } else if ((gameButtons[2].getText().equals(chosenButton)) &&
                (gameButtons[4].getText().equals(chosenButton)) &&
                (gameButtons[6].getText().equals(chosenButton))) {
            playerVictory();
        }
    }



    public static void opponentVictory() {
        JOptionPane.showMessageDialog(null, opponentButton + " won!", "Alert", JOptionPane.INFORMATION_MESSAGE);
        // itererar genom knapp listan för att kunna hömta och ändra dem utanför sitt scope
        for (JButton button : gameButtons) {
            button.setEnabled(false);
        }
        startGame.setEnabled(true);

        //start game enabled

    }
    public static void playerVictory() {
        JOptionPane.showMessageDialog(null, chosenButton + " won!", "Alert", JOptionPane.INFORMATION_MESSAGE);
        for (JButton button : gameButtons) {
            button.setEnabled(false);
        }
        startGame.setEnabled(true);
    //start game enabled
    }

}


