import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
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
        return gameButtons;
    }

}
