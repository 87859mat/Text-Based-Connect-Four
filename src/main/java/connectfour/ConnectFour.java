package connectfour;

import java.util.Scanner;

/**
 * ConnectFour: 
 * 
 * @author Eyoel Matiwos
 */
public class ConnectFour{
    private enum GameState {P1TURN,P2TURN,P1WIN,P2WIN,TIE};
    private GameState state;
    private TextUI ui;
    private Board gameBoard;

    public static void main(String[] args) {  
        ConnectFour currentGame = new ConnectFour();
        currentGame.play();
    }

    public ConnectFour() {
        this.state = GameState.P1TURN;
        this.ui = new TextUI();
        this.gameBoard = new Board();
    }

    public void play() {
        //for readability
        TextUI gameUI = this.getUI();
        Board currentBoard = this.getGameBoard();
        Scanner inputScanner = new Scanner(System.in);

        gameUI.welcomeUser();
        processInitialInput(inputScanner);


        while(!gameIsOver()) {
            gameUI.printBoard(currentBoard);
            makeMove(inputScanner);
            checkForWinner();
            checkForTie();
            switchTurn();
        }
        inputScanner.close();
    }

    public void processInitialInput(Scanner inputScanner) {
        String userInput = this.getUI().readUserInput(inputScanner);

        if(userInput.equalsIgnoreCase("n")) {
            this.getUI().printCustomMessage("Starting new game...\n");
        } else {
            beginLoading(gameBoard, this.getUI(), userInput);
        }
        
    }

    private void beginLoading(Board currentBoard, TextUI currentUI, String userInput) {
        boolean loadSuccessful;

        loadSuccessful = currentBoard.loadBoard(userInput);

        if(loadSuccessful) {
            currentUI.printLoadingSuccessMessage(userInput);
        } else {
            currentUI.printLoadingErrorMessage();
        }
    }

    private void makeMove(Scanner inputScanner) {
        String player;
        int columnSelected;
        
        if(this.getState() == GameState.P1TURN) {
            player = "1";
        } else if(this.getState() == GameState.P2TURN) {
            player = "2";
        } else {
            return;
        }
        columnSelected = this.getUI().promptPlayerToMove(player, this.getGameBoard(), inputScanner);
        this.getGameBoard().dropPiece(columnSelected, Integer.parseInt(player));
    }

    private void checkForWinner() {
        gameBoard = this.getGameBoard();
        if(gameBoard.diagonalWinFound(1) || gameBoard.horizontalWinFound(1)
        || gameBoard.verticalWinFound(1)) {
            this.setState(GameState.P1WIN);
        } else if (gameBoard.diagonalWinFound(2) || gameBoard.horizontalWinFound(2)
        || gameBoard.verticalWinFound(2)) {
            this.setState(GameState.P2WIN);
        }
    }

    private void checkForTie() {
        if(this.getGameBoard().boardIsFull() && this.getState() != GameState.P1WIN
        && this.getState() != GameState.P2WIN) {
            this.setState(GameState.TIE);
        }
    }

    private void switchTurn() {
        GameState gState = this.getState();
        if(gState == GameState.P1TURN) {
            this.setState(GameState.P2TURN);
        } else if(gState == GameState.P2TURN) {
            this.setState((GameState.P1TURN));
        }
    }

    private boolean gameIsOver() {
        return (this.state == GameState.P1WIN || this.state == GameState.P2WIN
                || this.state == GameState.TIE);
    }

    @Override
    public String toString() {
        String connectFourString = "Instance of ConnectFour class\nID: @" 
                                    + Integer.toHexString(hashCode());
        return connectFourString;
    }

    //Accessors and Mutators
    
    private GameState getState() {
        return this.state;
    }

    private void setState(GameState gState) {
        this.state = gState;
    }

    private TextUI getUI() {
        return this.ui;
    }

    private void setUI(TextUI newUI) {
        this.ui = newUI;
    }

    private Board getGameBoard() {
        return this.gameBoard;
    }

    private void setGameBoard(Board gBoard) {
        this.gameBoard = gBoard;
    }

}