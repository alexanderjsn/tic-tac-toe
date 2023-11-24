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
    private static boolean aiMode = true;

    // array av knappar
    private static JButton[] gameButtons = new JButton[9];

    public static void main(String[] args) {


        ;
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // extern skärm för val av spel
        JFrame externalFrame = new JFrame("Choose game mode");
        externalFrame.setSize(700, 400);
        externalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        externalFrame.setLayout(new FlowLayout());

        JLabel menuLabel = new JLabel("Choose character");
        menuLabel.setFont(universalFont);
        JPanel menuContainer = new JPanel();
        menuContainer.setLayout(new GridLayout(3, 2));
        menuContainer.add(menuLabel);


        // array med string för två knappar
        String[] buttonText = {"X", "O"};
        // array med knappar ( 2 )
        JButton[] menuButtons = new JButton[2];

        // itererar genom knapparna längd, lägger på text från array
        for (int i = 0; i < menuButtons.length; i++) {
            // skapar knapp för varje iterering
            menuButtons[i] = new JButton();
            // lägger från text från buttonText array
            menuButtons[i].setText(buttonText[i]);
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
            int finalI = i;
            Random random = new Random();
            int randomNumber = random.nextInt(gameButtons.length);
            // ActionListener till knapparna
            gameButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // om playerOne = true och knappen inte är tagen kan de lägga sitt drag
                    if (playerOne & gameButtons[finalI].getText().isEmpty()) {
                        gameButtons[finalI].setText(chosenButton);
                        gameButtons[finalI].setForeground(Color.BLUE);
                        check();
                        // playOne = false ( alltså O's tur efter dreaget )
                        playerOne = false;

                    }
                    // vice versa
                    else if (!playerOne & gameButtons[finalI].getText().isEmpty()) {
                        gameButtons[finalI].setText(opponentButton);
                        gameButtons[finalI].setForeground(Color.RED);
                        check();
                        playerOne = true;
                    }

                    // AI MODE
                    else if (aiMode) {
                        gameButtons[finalI].setText(chosenButton);
                        gameButtons[finalI].setForeground(Color.BLUE);
                        //När spelaren trycker in sin knapp, trycks en random knapp in.
                        gameButtons[randomNumber].setText(opponentButton);
                        gameButtons[finalI].setForeground(Color.RED);
                    }

                }
            });
            // lägger in i container
            buttonContainer.add(gameButtons[i]);
        }


        JPanel gameMenu = new JPanel();
        gameMenu.setLayout(new GridLayout(3, 2));
        JLabel turns = new JLabel(" turns");
        JButton resetGame = new JButton("Reset Game");
        JButton startGame = new JButton("Start Game");
        gameMenu.add(resetGame);
        gameMenu.add(startGame);
        gameMenu.add(turns);
        frame.add(gameMenu, BorderLayout.NORTH);

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random randomTurn = new Random();
                int randomNumber = randomTurn.nextInt(2);
                playerOne = randomTurn.nextInt(2) == 0;

                if (playerOne) {
                    turns.setText(chosenButton + "turn");
                    turns.setForeground(Color.BLUE);
                    startGame.setEnabled(false);

                } else {
                    turns.setText(opponentButton + "turns");
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
            System.out.println(opponentButton + " wins");
        } else if ((gameButtons[3].getText().equals(opponentButton)) &&
                (gameButtons[4].getText().equals(opponentButton)) &&
                (gameButtons[5].getText().equals(opponentButton))) {
            System.out.println(opponentButton + " wins");
        } else if ((gameButtons[6].getText().equals(opponentButton)) &&
                (gameButtons[7].getText().equals(opponentButton)) &&
                (gameButtons[8].getText().equals(opponentButton))) {
            System.out.println(opponentButton + " wins");
        }

// Columns
        if ((gameButtons[0].getText().equals(opponentButton)) &&
                (gameButtons[3].getText().equals(opponentButton)) &&
                (gameButtons[6].getText().equals(opponentButton))) {
            System.out.println(opponentButton + " wins");
        } else if ((gameButtons[1].getText().equals(opponentButton)) &&
                (gameButtons[4].getText().equals(opponentButton)) &&
                (gameButtons[7].getText().equals(opponentButton))) {
            System.out.println(opponentButton + " wins");
        } else if ((gameButtons[2].getText().equals(opponentButton)) &&
                (gameButtons[5].getText().equals(opponentButton)) &&
                (gameButtons[8].getText().equals(opponentButton))) {
            System.out.println(opponentButton + " wins");
        }

        // Diagonals
        if ((gameButtons[0].getText().equals(opponentButton)) &&
                (gameButtons[4].getText().equals(opponentButton)) &&
                (gameButtons[8].getText().equals(opponentButton))) {
            System.out.println(opponentButton + " wins");
        } else if ((gameButtons[2].getText().equals(opponentButton)) &&
                (gameButtons[4].getText().equals(opponentButton)) &&
                (gameButtons[6].getText().equals(opponentButton))) {
            System.out.println(opponentButton + " wins");
        }

        //// spelare 1
        // Rows
        // Rows
        if ((gameButtons[0].getText().equals(chosenButton)) &&
                (gameButtons[1].getText().equals(chosenButton)) &&
                (gameButtons[2].getText().equals(chosenButton))) {
            System.out.println(chosenButton + " wins");
        } else if ((gameButtons[3].getText().equals(chosenButton)) &&
                (gameButtons[4].getText().equals(chosenButton)) &&
                (gameButtons[5].getText().equals(chosenButton))) {
            System.out.println(chosenButton + " wins");
        } else if ((gameButtons[6].getText().equals(chosenButton)) &&
                (gameButtons[7].getText().equals(chosenButton)) &&
                (gameButtons[8].getText().equals(chosenButton))) {
            System.out.println(chosenButton + " wins");
        }

// Columns
        if ((gameButtons[0].getText().equals(chosenButton)) &&
                (gameButtons[3].getText().equals(chosenButton)) &&
                (gameButtons[6].getText().equals(chosenButton))) {
            System.out.println(chosenButton + " wins");
        } else if ((gameButtons[1].getText().equals(chosenButton)) &&
                (gameButtons[4].getText().equals(chosenButton)) &&
                (gameButtons[7].getText().equals(chosenButton))) {
            System.out.println(chosenButton + " wins");
        } else if ((gameButtons[2].getText().equals(chosenButton)) &&
                (gameButtons[5].getText().equals(chosenButton)) &&
                (gameButtons[8].getText().equals(chosenButton))) {
            System.out.println(chosenButton + " wins");
        }

// Diagonals
        if ((gameButtons[0].getText().equals(chosenButton)) &&
                (gameButtons[4].getText().equals(chosenButton)) &&
                (gameButtons[8].getText().equals(chosenButton))) {
            System.out.println(chosenButton + " wins");
        } else if ((gameButtons[2].getText().equals(chosenButton)) &&
                (gameButtons[4].getText().equals(chosenButton)) &&
                (gameButtons[6].getText().equals(chosenButton))) {
            System.out.println(chosenButton + " wins");
        }



    }
}

