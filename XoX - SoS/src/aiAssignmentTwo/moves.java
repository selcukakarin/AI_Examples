package aiAssignmentTwo;

public class moves {

    private static int mat[][]; //Defines an x and o board
    private static int counter = 0;

    private static bestMoves MiniMax(int curPlayer, int matrix[][], int depth, int alpha, int beta) { //Inputs: current player, board, depth of search
        counter++;
    	if (control.Win(2, matrix)) {          //Checks if AI wins
            return new bestMoves(-1, depth);   //Sets global move as -1 and score (example: 10)
        }

        if (control.Win(1, matrix)) {          //Checks if Human wins
            return new bestMoves(-1, -depth);   //Sets global move as -1 and score (example: -10)
        }
        if (control.Tie(matrix)) {             //Check if Tie
            return new bestMoves(-1, 0);			   //Sets global move -1 and score 0
        }

        bestMoves res = new bestMoves(-1, 0);			   //Set score to 0
        if (curPlayer == 2) {				   //If player is AI
            res.score = -10;				   //Set score to -10
        } else {
            res.score = 10;					   //Set score to 10
        }
        for (int k = 0; k < 9; k++) {		   //For every position on the board
            int i = location.getPos(k).X; 		   //Get x and y from indexes
            int j = location.getPos(k).Y;
            if (depth!=0) {
            	if (matrix[i][j] == 0) {           //If this value is empty
            		if (curPlayer == 2) {          //If player is AI (maximizer)
            			matrix[i][j] = 2;          //Set value to AI (2)
            			bestMoves turn = MiniMax(1, matrix, depth - 1, alpha, beta); //Uses recursive MiniMax function again and increases depth, changes player
            			matrix [i][j] = 0;
            			if (res.score < turn.score) {  //If next move (turn) has a greater score than current one
            				res.score = turn.score;    //Current score is set as next one
            				res.move = k;			   //Save the move position
                        
                        
            			}
                      
            			if (alpha < turn.score) {      //If alpha is less than next move score
            				alpha = turn.score;		   //Alpha is the next move score
                        
            			}
            			if (beta <= alpha) {      //If beta is less or equal to next move score
            				return res;			   //Save the move position
                        
            			}
            		}
            		else {
            			matrix[i][j] = 1;			   //Set value to Human (1)
            			bestMoves turn = MiniMax(2, matrix, depth - 1, alpha, beta); //Uses recursive MiniMax function again and increases depth, changes player
            			matrix[i][j] = 0;
            			if (res.score > turn.score) {  //If next move (turn) has a lower score than current one
            				res.score = turn.score;    //Current score is set as next one
            				res.move = k;
            			}
            			if (beta > turn.score) {     //If alpha is more or equal to next move score
            				beta = turn.score;			   //Save the move position
            			}
            			if (beta <= alpha) {	   //If beta is more than next move score
            				return res; 		   //Beta is the next move score
                    }
                }
            }
        }
    }

        return res;
    }

    public static bestMoves play() {			//Getting the best play
        mat = tictactoe.xoBoard.clone();		//Current board layout is cloned
        int depth = tictactoe.difficulty();	//Minimax depth is defined
        bestMoves cplay = MiniMax(2, mat, depth, -10, 10); //Best move is calculated using MiniMax with alpha-beta pruning
        System.out.println(counter);
        return cplay;					//Returns best move
    }
}
