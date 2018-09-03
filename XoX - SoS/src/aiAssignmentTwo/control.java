/*
 * Program to check whether anyone won the game by rows, columns or two diagonals
 * Also checks if there is a tie and no
*/
package aiAssignmentTwo;

public class control {

    public static boolean Win(int player, int matrix[][]) {

        for (int i = 0; i < 3; i++) {		//Loops through all the board
            boolean flg = true;				//Flag for rows
            boolean flg2 = true;			//Flag for columns
            for (int j = 0; j < 3; j++) {
                flg &= (matrix[i][j] == player);  //Check rows if they match the player's symbol
                flg2 &= (matrix[j][i] == player); //Check columns if they match the player's symbol
            }
            if (flg || flg2) {	//If rows or columns match, return as true
                return true;
            }
        }
        boolean flg = true;
        for (int i = 0; i < 3; i++) {
            flg &= (matrix[i][i] == player); //Check mayor diagonal if it matches player's symbol
        }
        if (flg) {
            return true;
        }
        flg = true;
        for (int i = 0; i < 3; i++) {
            flg &= (matrix[i][3 - i - 1] == player); //Check minor diagonal if it matches player's symbol
        }
        if (flg) {
            return true;
        }
        return false;
    }

    public static boolean Tie(int matrix[][]) {		//Checks if its Tie
        int ct = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ct += (matrix[i][j] == 0 ? 1 : 0);	//Checks if all of the values are full
            }
        }
        if (ct == 0) {		//If all the values are filled
            return true;	//Return as tie
        }
        return false;
    }

}
