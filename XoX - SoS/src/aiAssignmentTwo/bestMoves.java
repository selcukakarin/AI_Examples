package aiAssignmentTwo;

public class bestMoves {
    int move;
    int score;
    
    public bestMoves(int move,int score){
        this.move=move;					//Global move is local move
        this.score=score;				//GLobal score is current score
    }
}
