package aiAssignmentOne;
import java.util.Arrays;
import java.util.Comparator;
public class localBeamSearchAlgo {


    public int[] solve(int n, int numOfStates) {  //inputs the n, it. numb., and number of states
        int[][] states = new int[numOfStates][];      //space for initial states (example 3)

        for (int i = 0; i < numOfStates; i++)                //for the number of states
            states[i] = SolverUtils.generateRandomState(n);  //generate these states


        while (true) { 

            int[][] newStates = new int[n*numOfStates][];    //space for n new states per initial states (4x3=12)
            for (int i = 0; i < numOfStates; i++) {          //for every number of state 
                int costToBeat = SolverUtils.getHeuristicCost(states[i]);   //get heuristic cost for states

                // if solved
                if (costToBeat == 0)   //if the heuristic cost is 0
                    return states[i];  //return the state

                for (int col = 0; col < n; col++) {   //for each column
                    newStates[i*n + col] = makeMove(states[i], col, costToBeat); //make a move for every (12) states just
                                                                                 //by changing row position once per 
                    // if stuck                                                  //column and leaving everything the same
                    if (newStates[i*n + col] == null)  //if new state is stuck
                        newStates[i*n + col] = SolverUtils.generateRandomState(n); //make a new state
                }

            }
            Arrays.sort(newStates, Comparator.comparingInt(SolverUtils::getHeuristicCost)); //arranges newStates by heuristic cost

            states = Arrays.copyOfRange(newStates, 0, numOfStates); //gets 3 best ones and starts everything again
                                                                    //now these are the initial (example 3) states
        }

    }

    private int[] makeMove(int r[], int col, int costToBeat) { //inputs the board setup, column number from initial state
                                                               // and current costToBeat
        int n = r.length;  //sets n as lenght of array

        for (int row = 0; row < n; row++) {  //for every row
            // we do not need to evaluate because we already know current cost by costToBeat.
            if (row == r[col])  //if real row is the same as searched one
                continue;       //skip it and get back to for loop

            int tmpRow = r[col];  //temporary row is the searchable row (just in case the heuristic cost is the same or less)
            r[col] = row;         //searchable row is the current row
            int cost = SolverUtils.getHeuristicCost(r);  //gets the cost for this board setup
            if (costToBeat > cost) {  //if the new cost is lower than existing one
                r[col] = row;   //assign new row as the current one
                return r;       //return the board new setup
            }
            r[col] = tmpRow;    //else, return the same board setum
        }

        return null;
    }


}
