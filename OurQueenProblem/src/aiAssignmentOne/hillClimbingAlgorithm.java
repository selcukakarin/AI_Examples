package aiAssignmentOne;
import java.util.Arrays;
public class hillClimbingAlgorithm {

    // make a move for hill climbing
    public int[] firstChoiceHillClimbing(int n) {  //inputs the n and iteration number
        int[] r = SolverUtils.generateRandomState(n);      //Generates a n-long column with random rows
        int costToBeat = SolverUtils.getHeuristicCost(r);  //Gets the cost to beat

        // terminate when it reaches max num of iterations or problem is solved.
        while (costToBeat > 0) {  //until we have iterations AND score is > 0, cycle

            boolean flag = true;                           //flag
            int tempCostToBeat = costToBeat;               //temporary cost, to check if anything changed at all
            for (int col = 0; col < n && flag; col++) {    //loop through every column AND check if flag is true

                for (int row = 0; row < n; row++) {        //loop through every row
                    // we do not need to evaluate because we already know current cost by costToBeat.
                    if (row == r[col])                     //if row = the row from column
                        continue;                          //loop again

                    // init new copy
                    int[] rc = Arrays.copyOf(r, n);        //copies array into a new one (rc)
                    rc[col] = row;                         //assigns row number to the new array (every possible)
                    int cost = SolverUtils.getHeuristicCost(rc);  //gets a new cost for that potential position
                    if (costToBeat > cost) {              //if this new cost is lower than the current one
                        r[col] = row;                     //temporary row is the new row
                        costToBeat = cost;                //temporary cost is the new cost
                        flag = false;                     //flag is reset
                        break;                            //next column
                    }
                }
            }

            // if it gets stuck at local maxima
            if (tempCostToBeat == costToBeat)             //if it gets stuck at the local maxima
                r = SolverUtils.generateRandomState(n);   //we get a whole new board situat

        }

        return costToBeat == 0 ? r : null; // return solution if solved
    }


    public int[] solve(int n) {

        return firstChoiceHillClimbing(n);
    }

}
