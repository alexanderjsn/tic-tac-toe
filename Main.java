import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static Font universalFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
    private static boolean playerOne = true;


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



    //container för knappar ( för layout )
    JPanel buttonContainer = new JPanel();
    buttonContainer.setLayout(new GridLayout(3,3));

    // array av knappar
    JButton[] gameButtons = new JButton[9];
    // for loop som skapar knapparna och lägger in i buttonContainer
    for(int i=0;i<9;i++){
        gameButtons[i] = new JButton();
        gameButtons[i].setFont(universalFont);
        int finalI = i;

        // ActionListener till knapparna
        gameButtons[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // om playerOne = true och knappen inte är tagen kan de lägga sitt drag
                if (playerOne & gameButtons[finalI].getText().isEmpty()){
                    gameButtons[finalI].setText("X");
                    // playOne = false ( alltså O's tur efter dreaget )
                    playerOne = false;
                }
                // vice versa
                else if (!playerOne & gameButtons[finalI].getText().isEmpty()){
                    gameButtons[finalI].setText("O");
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
