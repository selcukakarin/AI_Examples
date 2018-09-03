package aiAssignmentTwo;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class tictactoe implements ActionListener {

    public static int xoBoard[][] = new int[3][3]; //XO board is defined as 3x3
    private JFrame window;          			   //New window defined
    private JButton[] gameBtns;     			   //Buttons for game defined
    private final int width = 700;  			   //Application width defined
    private final int height = 700;				   //Application height defined
    private final int cells = 9;    			   //Cell number defined
    private boolean isMyTurn;         			   //isMyTurn defined
    private boolean gameOVER = false; 			   //gameOVER value set to false by default
    private Image X; 							   //Icon for X defined
    private Image O; 							   //Icon for O defined
    private static boolean gameSTART = true;	   //gameSTART is set to true at first
    private static int previousDepth = 10; 		   //Default depth for Minimax

    public tictactoe() throws IOException { 		   	   //Board initialization
    	X = ImageIO.read(new File("C:\\Users\\Selcuk\\source\\repos\\AI_Examples\\XoX - SoS\\src\\aiAssignmentTwo\\X.png")); //Image for X read
        O = ImageIO.read(new File("C:\\Users\\Selcuk\\source\\repos\\AI_Examples\\XoX - SoS\\src\\aiAssignmentTwo\\O.png"));	//Image for O read

        isMyTurn = true; 										//isMyTurn is set to true
        window = new JFrame("Tic Tac Toe"); //Window is labeled
        window.setSize(width, height);           				//Window resolution is set
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //Gets the screen size
		int x = (int) ((dimension.getWidth() - width) / 2);     //Calculates X position for window
		int y = (int) ((dimension.getHeight() - height) / 2);   //Calculates Y position for window
		window.setLocation(x, y);								//Puts the window in the center of the screen
		window.getContentPane().setBackground(java.awt.Color.CYAN); //Sets the color of the window to cyan
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Closing operation defined
        window.setLayout(new GridLayout(3, 3));        			//Layout for window - grid 3x3
        gameBtns = new JButton[cells];							//Game buttons defined
        for (int i = 0; i < cells; i++) {						//9 buttons are created for the tic tac toe game
            gameBtns[i] = new JButton("");						//Default value of button is blank
            gameBtns[i].setBackground(java.awt.Color.lightGray);//Sets the button background to light gray
            gameBtns[i].addActionListener(this);				//Action listeners for buttons are added
            window.add(gameBtns[i]);							//Buttons are added to the window
        }

        window.setVisible(true);								//Window visibility is set to true
        														//Icons for X and O are scaled according to window size
        X = X.getScaledInstance(window.getContentPane().getWidth() / 3, window.getContentPane().getHeight() / 3, Image.SCALE_SMOOTH);
        O = O.getScaledInstance(window.getContentPane().getWidth() / 3, window.getContentPane().getHeight() / 3, Image.SCALE_SMOOTH);
        difficulty(); 											//Difficulty program is run 

    }

    private int getButtonIndex(ActionEvent e) {		//Program for getting the index of the button
        for (int i = 0; i < cells; i++) {			//Checks all cells
            if (e.getSource() == gameBtns[i]) {     //If button object is the same as index
                return i;							//Returns the index of button
            }
        }
        return -1;									//If unable to get index, returns -1
    }

    private boolean check(int player) {				//Checks if the game has ended
    	if (control.Tie(xoBoard)) {					//In case of Draw
            gameOVER = true;						//Sets gameOVER to true
            endGame(0);								//Initializes endGame program
            return true;							//Returns true
        } else if (player == 1) {					//The same, but in this case if Human wins

            if (control.Win(player, xoBoard)) {
                gameOVER = true;
                endGame(1);
                return true;
            }
        } else {
            if (control.Win(player, xoBoard)) {		//Else assumes computer wongameOVER = true;
                endGame(2);
                return true;
            }
        }

        return false;								//If the game has not ended, returns as false

    }

    public static int difficulty() {				//Program for getting difficulty from user and converting it to layer depth
    	if (gameSTART == true) {					//If it is new game
    		String D = JOptionPane.showInputDialog("Please input the difficulty(1-9):"); //Asks user to input difficulty level
    		int Difficulty = Integer.parseInt(D);   //Difficulty converted from string to integer
    		int depth = 10;							//Default depth is 10
    		gameSTART = false;						//gameSTART set to false
    		depth =  Difficulty;				//Depth is calculated
    		previousDepth = depth;					//Previous depth is set as current depth
			return depth;							//Returns depth
    		}
    	else {
    		return previousDepth;					//If it is not start of the game, returns previous depth
    	}
    }
    
    private void endGame(int player) {				//Play again request program
        String message = "";						//Defines message to user as blank
        if (player == 1) {
            message = "Congratulations, you won";	//Defines message to user as Congratulations, you won
        }
        if (player == 2) {
            message = "Unfortunately, you lost";	//Defines message to user as Unfortunately, you lost
        }
        if (player == 0) {
            message = "Game Ended Tie";				//Defines message to user as Game ended Tie
        }
        //prompts a dialog box, letting user choose if they want to play again
        int selectedOption = JOptionPane.showConfirmDialog(null, message + "\nWould you like to play again?", "Choose", JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {	//If yes is clicked
            xoBoard = new int[3][3];	 
            for (int i = 0; i < 9; i++) {				//For every button
                window.remove(gameBtns[i]);				//Buttons are removed
                gameBtns[i] = new JButton("");			//New blank buttons are added
                gameBtns[i].addActionListener(this);	//New action listeners are added
                gameBtns[i].setBackground(java.awt.Color.lightGray);//Sets the new button background to light gray
                window.add(gameBtns[i]);				//New buttons are added to the window

            }
            window.setVisible(true);					//Window is set to visible
            gameOVER = false; 							//gameOVER is reset to false again
            isMyTurn = true;							//isMyTurn is reset to true
            gameSTART = true;							//gameSTART is reset to true
            difficulty();								//Difficulty program is run
        }
        if (selectedOption == JOptionPane.NO_OPTION) {	//If No is selected, do nothing

        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {	//When any button is clicked

        if (gameOVER) {  //if game has ended
            return;	      //return that button was not clicked
        }
        int indx = getButtonIndex(e);				//Get index of the button
        location p = location.getPos(indx);					//Convert index of the button to X and Y coordinates
        if (xoBoard[p.X][p.Y] != 0) {				//If button already has a value, return that it was not clicked
            return;
        }
        if (isMyTurn) {								//If it is players turn
            isMyTurn = false;					    //Set isMyTurn to false
            gameBtns[indx].setIcon(new ImageIcon(X));	//Set the icon of the button as X
            gameBtns[indx].setContentAreaFilled(false);	//Set content area filled as false (prevents changing color)
            xoBoard[p.X][p.Y] = 1;					//Value of the button is set as one (human = X = 1)
            if (check(1)) {							//checks if Human won the game or Tie
                return;								//returns if he did
            }
            bestMoves res = moves.play();			//Best move for AI is found
            gameBtns[res.move].setIcon(new ImageIcon(O));	//Set the icon of the button as O
            gameBtns[res.move].setContentAreaFilled(false); //Set content area filled as false (prevents changing color)
            p = location.getPos(res.move);				//Get where A put a new postion
            xoBoard[p.X][p.Y] = 2;					//Value of the button is set as two (AI = O = 2)
            isMyTurn = true;						//Sets true to isMyTurn
            if (check(2)) {							//Checks if AI won the game or Tie

            }
        }
    }
}
