
/**
 * A class that successfully executes the PigGame
 *
 * @author AJ Natzic
 * @version October 24 2017
 */
public class PigGame
{
    int playScore = 0;
    int compScore = 0;
    int roundScore = 0;
    final int winScore = 100;
    GVdie die1 = new GVdie();
    GVdie die2 = new GVdie();
    boolean isPlayerTurn;

    public PigGame(){
        die1 = new GVdie();
        die2 = new GVdie();
        playScore = 0;
        compScore = 0;
        roundScore = 0;
        System.out.println("Welcome to Pig Game! Let's have some fun... Good luck!");
        System.out.println("");
    }

    public int getRoundScore(){
        return roundScore;
    }

    public int getPlayerScore(){
        return playScore; 
    }

    public int getComputerScore(){
        return compScore;   
    }

    public boolean playerWon(){
        if(playScore >= winScore){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean computerWon(){
        if(compScore >= winScore){
            return true;
        }
        else{
            return false;   
        }
    }

    private void rollDice(){

        die1.roll();
        die2.roll();

        if(die1.getValue() == 1 && die2.getValue() == 1){
            roundScore = 0;
            System.out.print(die1.getValue() + " ");
            System.out.print(die2.getValue() + " ");
            System.out.println("Round Score: " + roundScore);
        }
        else if(die1.getValue() == 1 || die2.getValue() == 1){
            roundScore = 0;
            System.out.print(die1.getValue() + " ");
            System.out.print(die2.getValue() + " ");
            System.out.println("Round Score: " + roundScore);
        }
        else{
            roundScore = roundScore + die1.getValue() + die2.getValue();
            System.out.print(die1.getValue() + " ");
            System.out.print(die2.getValue() + " ");
            System.out.println("Round Score: " + roundScore);
        }
    }

    public void playerRolls(){
        isPlayerTurn = true;
        while(playerWon() == false && computerWon() == false){
            rollDice();
            if(die1.getValue() == 1 && die2.getValue() == 1){
                playScore = 0;
                playerHolds();
                isPlayerTurn = false;
                break;
            }
            else if (die1.getValue() == 1 || die2.getValue() == 1){
                roundScore = 0;
                playerHolds();
                isPlayerTurn = false;
                break;
            }
            else{
                break;
            }
        }
    }

    public void playerHolds(){
        playScore = playScore + getRoundScore();
        System.out.println("---- Your Score: " + playScore);
        System.out.println("");
        if(getPlayerScore() >= winScore){
            System.out.println("You beat the computer!");
        }
        roundScore = 0;
        isPlayerTurn = false;
    }

    public void computerTurn(){
        isPlayerTurn = false;
        while(computerWon() == false && playerWon() == false && getRoundScore() < 19){
            rollDice();
            if(die1.getValue() == 1 && die2.getValue() == 1){
                compScore = 0;
                break;
            }
            else if (die1.getValue() == 1 || die2.getValue() == 1){
                break;
            }
        }
        compScore = compScore + getRoundScore();
        System.out.println("---- Computer Score: " + compScore);
        System.out.println("");
        if(getComputerScore() >= winScore){
            System.out.println("Computer has won.");
        }
        roundScore = 0;
        isPlayerTurn = true;
    }

    public void restart(){
        isPlayerTurn = true;
        playScore = 0;
        compScore = 0;
        roundScore = 0;
    }

    private void playerTurn(){
        isPlayerTurn();
        playerRolls();
        playerHolds();
    }

    public void autoGame(){
        restart();
        int playTurns = 0;
        int compTurns = 0;
        while(playerWon() == false && computerWon() == false){
            playerTurn();
            ++playTurns;
            if(playerWon() == true){
                break;
            }
            while(computerWon() == false){
                computerTurn();
                ++compTurns;
                break;
            }
        }
        if(getPlayerScore() >= winScore){
            System.out.println("Total Turns: " + playTurns);
        }
        else{
            System.out.println("Total turns: " + compTurns);
        }
    }

    public boolean isPlayerTurn(){
        return isPlayerTurn;
    }

    public GVdie getDie(int num){
        if(num == 1){
            return die1;
        }
        else if (num == 2){
            return die2;
        }
        else{
            return null;
        }
    }

}