
/*
Write a program to play the game of Tic Tac Toe.

Please submit the program and any images that the program requires in a single zip file.

The program must:
1) Use a GUI: A window with 9 buttons (Hint: use GridLayout(rows, columns) )

2) Use a two dimensional array for the 9 buttons (Hint: JButton b[ ] [ ] )

- use nested for loops to add action listener to the buttons ( b[i][j].addActionListener(buttonsHandler); )

3) Upon a click, a button must change either (Hint: implement ActionListener , use actionPerformed(ActionEvent e)   )
- its text  ...or
- its background image
4) A button can be clicked to be set to X or O only once ( e.g. can't click on a button and change it from X to O )

5) An X is followed by O..., and O is followed by X (e.g. can't have an X button, and then the next is another X button)

6) The program correctly determines the winner (if 3 X's or O's in a diagonal, row, or column)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class TicTacToe extends JFrame{
    private JFrame mainFrame;

    Random rndm = new Random();
    private int turn = rndm.nextInt(2);// 1 = player, 0 = computer (ring of fire)

    // create button objects
    private JButton buttons[][] = {   {new JButton("00"), new JButton("01"), new JButton("02")},
            {new JButton("10"), new JButton("11"), new JButton("12")},
            {new JButton("20"), new JButton("21"), new JButton("22")},
    };



    public TicTacToe() {
        mainFrame = new JFrame("Tic Tac Toe"); // title of window

        // get content pane
        Container c = mainFrame.getContentPane();

        // Grid Layout
        c.setLayout(new GridLayout(3,3) );

        // add buttons to content pane
        for(int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                c.add(buttons[i][j]);
            }
        }

        mainFrame.setSize(600,600);
        mainFrame.setVisible(true);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        PlayerClickedHandler pchandler = new PlayerClickedHandler();
        for(int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                buttons[i][j].addActionListener(pchandler);
            }
        }
    } // end constructor

    // inner class - event handlers
    class PlayerClickedHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //Get button clicked:
            int posi = 0;
            int posj = 0;

            for(int i=0; i<3; i++) {
                for(int j=0; j<3;j++) {
                    if(e.getSource() == buttons[i][j]) {
                        posi = i;
                        posj = j;
                    }
                }
            }

            if(turn == 1) { // water
                if(buttons[posi][posj].getIcon() == null || buttons[posi][posj].getIcon().toString() != "fire.png") {
                    buttons[posi][posj].setIcon(new ImageIcon("water.png"));
                    buttons[posi][posj].setText("");
                    turn = 0; // to ring of fire
                }
            }
            else if(turn ==0 ) { // fire
                if(buttons[posi][posj].getIcon() == null || buttons[posi][posj].getIcon().toString() != "water.png") {
                    buttons[posi][posj].setIcon(new ImageIcon("fire.png"));
                    buttons[posi][posj].setText("");
                    turn = 1; // to water
                }
            }


            // WINNER CHECKER (horizontal):

            for(int i=0; i<3; i++) {
                if(buttons[i][0].getIcon() != null && buttons[i][1].getIcon() != null
                        && buttons[i][2].getIcon() != null) {
                    if(buttons[i][0].getIcon().toString() == buttons[i][1].getIcon().toString()
                            && buttons[i][0].getIcon().toString() == buttons[i][2].getIcon().toString() ) {
                        System.out.println("WINNER!");
                        if(buttons[i][0].getIcon().toString() == "water.png" ) {
                            System.out.println("Water wins!");
                            System.exit(0);
                        }
                        if(buttons[i][0].getIcon().toString() == "fire.png") {
                            System.out.println("Fire wins!");
                            System.exit(0);
                        }
                    }
                }
            }

            // WINNER CHECKER (vertical):
            for(int j=0; j<3; j++) {
                if(buttons[0][j].getIcon() != null && buttons[1][j].getIcon() != null
                        && buttons[2][j].getIcon() != null) {
                    if(buttons[0][j].getIcon().toString() == buttons[1][j].getIcon().toString()
                            && buttons[0][j].getIcon().toString() == buttons[2][j].getIcon().toString()) {
                        System.out.println("WINNER!");
                        if (buttons[0][j].getIcon().toString() == "water.png") {
                            System.out.println("Water wins!");
                            System.exit(0);
                        }
                        if(buttons[0][j].getIcon().toString() == "fire.png") {
                            System.out.println("Fire wins!");
                            System.exit(0);
                        }
                    }
                }
            }

            // WINNER CHECKER (diagonal)
            // top left to bottom right 00, 11, 22
            if(buttons[0][0].getIcon() != null && buttons[1][1].getIcon() != null && buttons[2][2].getIcon() != null) {
                if(buttons[0][0].getIcon().toString() == buttons[1][1].getIcon().toString()
                        && buttons[0][0].getIcon().toString() == buttons[2][2].getIcon().toString() ) {
                    System.out.println("WINNER!");
                    if(buttons[0][0].getIcon().toString() == "water.png") {
                        System.out.println("Water wins!");
                        System.exit(0);
                    }
                    if(buttons[0][0].getIcon().toString() == "fire.png") {
                        System.out.println("Fire wins!");
                        System.exit(0);
                    }
                }
            }
            // top right to bottom left 02, 11, 20
            if(buttons[0][2].getIcon() != null && buttons[1][1].getIcon() != null && buttons[2][0].getIcon() != null) {
                if(buttons[0][2].getIcon().toString() == buttons[1][1].getIcon().toString()
                        && buttons[0][2].getIcon().toString() == buttons[2][0].getIcon().toString() ) {
                    System.out.println("WINNER!");
                    if(buttons[2][0].getIcon().toString() == "water.png") {
                        System.out.println("Water wins!");
                        System.exit(0);
                    }
                    if(buttons[2][0].getIcon().toString() == "fire.png") {
                        System.out.println("Fire wins!");
                        System.exit(0);
                    }
                }
            }

            if (buttons[0][0].getIcon() != null && buttons[0][1].getIcon() != null && buttons[0][2].getIcon() != null
                    && buttons[1][0].getIcon() != null && buttons[1][1].getIcon() != null
                    && buttons[1][2].getIcon() != null && buttons[2][0].getIcon() != null
                    && buttons[2][1].getIcon() != null && buttons[2][2].getIcon() != null) {
                System.out.println("No one wins!");
                System.exit(0);
            }

        }


    }





    public static void main(String[] args) {
        new TicTacToe();
    }

}