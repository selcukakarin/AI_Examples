package aiAssignmentTwo;

public class location {
    int X;
    int Y;
    public location(int X,int Y){
        this.X=X;						//Global X is local X
        this.Y=Y;						//Global Y is local Y
    }
    public static location getPos(int x){
         return new location(x/3, x%3);	//Calculates X by division, Y by remainder of division
    }
}
