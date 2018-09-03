package aiAssignmentOne;

import aiAssignmentOne.SolverUtils;

public class simulatedAnnealingAlgo {

    public int[] solve(int n, double temperature, double coolingFactor) { 
                                                       //inputs max iteration amount, temperature and cooling factor
        int[] r = SolverUtils.generateRandomState(n);  //generates a new random board setup

        int costToBeat = SolverUtils.getHeuristicCost(r);  //gets the cost for this setup

        // terminate when problem is solved.
        while (costToBeat > 0) {  
            r = makeMove(r, costToBeat, temperature);
            costToBeat = SolverUtils.getHeuristicCost(r);              //gets a new cost to beat
            temperature = Math.max(temperature * coolingFactor, 0.01); //gets a new temperature. Lowest temperature = 0.01
        }

        return costToBeat == 0 ? r : null; // return solution if solved
    }

    private int[] makeMove(int r[], int costToBeat, double temp) { //inputs board setup, current cost to beat and temperature
        int n = r.length; //gets the lenght of board

        while (true) {
            int nCol = (int) (Math.random() * n); //chooses a random column from 0 to n
            int nRow = (int) (Math.random() * n); //chooses a random row from 0 to n
            int tmpRow = r[nCol];                 //sets temporary row to real row nr. at random column
            r[nCol] = nRow;                       //sets real row at random column to a random row

            int cost = SolverUtils.getHeuristicCost(r); //gets the cost of new setup
            if (cost < costToBeat)                      //if this new setup is better
                return r;                               //return this

            int dE = costToBeat - cost;            //else difference = previous (better cost) - this cost
            double acceptProb = Math.min(1, Math.exp(dE / temp)); //gets the probability for e^(de/temp)

            if (Math.random() < acceptProb)  //the better the probability, the closer it is to 1
                return r;                    //if the probability is really good (closing to 1) return the board setup

            r[nCol] = tmpRow;                //else return the same board setup as before and start random change again
        }


    }

}
