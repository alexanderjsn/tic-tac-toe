import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static Font universalFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
    private static boolean playerOne = true;
    private static String chosenButton;
    private static String opponentButton;


public static void main(String[] args) {
    JFrame frame = new JFrame("Tic Tac Toe");
    frame.setSize(600,500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    // extern skärm för val av spel
    JFrame externalFrame = new JFrame("Choose game mode");
    externalFrame.setSize(700,400);
    externalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    externalFrame.setLayout(new FlowLayout());

    JLabel menuLabel = new JLabel("Choose character");
    menuLabel.setFont(universalFont);
    JPanel menuContainer = new JPanel();
    menuContainer.setLayout(new GridLayout(3,2));
    menuContainer.add(menuLabel);
    // array med string för två knappar
    String[] buttonText = {"X", "O"};
    // array med knappar ( 2 )
    JButton[] menuButtons = new JButton[2];
    // itererar genom knapparna längd, lägger på text från array
    for(int i=0;i<menuButtons.length;i++){
        // skapar knapp för varje iterering
        menuButtons[i] = new JButton();
        // lägger från text från buttonText array
        menuButtons[i].setText(buttonText[i]);
        int finalI = i;
        menuButtons[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //logik för att hämta knappens string här:
                JButton playerButton = (JButton) e.getSource();
                //sätter variabeln på texterna av knapparna
                chosenButton = playerButton.getText();

                opponentButton = "";
                if (chosenButton.equals("X")){
                    opponentButton = ("O");
                }
                else if (chosenButton.equals("O")) {
                    opponentButton = ("X");}
            }
        });

        //lägger in i meny
        menuContainer.add(menuButtons[i]);
    }


    externalFrame.add(menuContainer);
    externalFrame.setVisible(true);

    //container för knappar ( för layout )
    JPanel buttonContainer = new JPanel();
    buttonContainer.setLayout(new GridLayout(3,3));

    // array av knappar
    JButton[] gameButtons = new JButton[9];
    // for loop som skapar knapparna och lägger in i buttonContainer
    for(int i=0;i<gameButtons.length;i++){
        gameButtons[i] = new JButton();
        gameButtons[i].setFont(universalFont);
        int finalI = i;

        // ActionListener till knapparna
        gameButtons[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // om playerOne = true och knappen inte är tagen kan de lägga sitt drag
                if (playerOne & gameButtons[finalI].getText().isEmpty()){
                    gameButtons[finalI].setText(chosenButton);
                    gameButtons[finalI].setForeground(Color.BLUE);
                    // playOne = false ( alltså O's tur efter dreaget )
                    playerOne = false;
                }
                // vice versa
                else if (!playerOne & gameButtons[finalI].getText().isEmpty()){
                    gameButtons[finalI].setText(opponentButton);
                    gameButtons[finalI].setForeground(Color.RED);
                    playerOne = true;
                }
            }
        });
        buttonContainer.add(gameButtons[i]);
    }

    frame.add(buttonContainer);
    frame.setVisible(true);
    }


}
