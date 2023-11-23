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




    //container för knappar ( för layout )
    JPanel buttonContainer = new JPanel();
    buttonContainer.setLayout(new GridLayout(3,3));

    // itererar genom listan och skriver ut 9 knappar till containern
    for (int i = 0; i < 9; i++){
        buttonContainer.add(createBoard());
    }
    frame.add(buttonContainer);



    frame.setVisible(true);
    }


    public static JButton createBoard(){
    JButton gameButtons = new JButton();
    gameButtons.setFont(universalFont);


        gameButtons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // om playerOne = true
                if (playerOne & gameButtons.getText().isEmpty()){
                    gameButtons.setText("X");
                    // playOne = false ( alltså O's tur )
                    playerOne = false;
                }
                else if (!playerOne & gameButtons.getText().isEmpty()){
                    gameButtons.setText("O");
                    playerOne = true;
                }


            }
        });

        return gameButtons;
    }

}
