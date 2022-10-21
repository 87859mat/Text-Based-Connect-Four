package connectfour;


/**
 * ConnectFour: 
 * 
 * @author Eyoel Matiwos
 */
public class ConnectFour{
    private enum gameState {P1TURN,P2TURN,P1WIN,P2WIN,TIE};
    private gameState state;
    private TextUI ui;
    private Board gameBoard;

    public static void main(String[] args) {  
        
    }


    @Override
    public String toString() {

        return null;
    }

    //Accessors and Mutators
    private gameState getState() {
        return this.state;
    }
    private void setState(gameState gState) {
        this.state = gState;
    }

}