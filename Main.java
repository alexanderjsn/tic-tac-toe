import javax.swing.*;
import java.awt.*;

public class Main {
public static void main(String[] args) {
    JFrame frame = new JFrame("Tic Tac Toe");
    frame.setSize(600,500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.setVisible(true);

    //container för knappar ( för layout )
    JPanel buttonContainer = new JPanel();
    buttonContainer.setLayout(new GridLayout(3,3));
    // skapar 9 knappar i en array
    JButton[] gameButtons = new JButton[9];

    // itererar genom listan och skriver ut 9 knappar till containern
    for (int i = 0; i < 9; i++){
    gameButtons[i] = new JButton("");
    buttonContainer.add(gameButtons[i]);
    }
    frame.add(buttonContainer);





    }
}
