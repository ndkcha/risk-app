package Game.Model;

import java.util.Observable;

public class PhaseData extends Observable {
    public static final int REINFORCEMENT_PHASE = 0;
    public static final int ATTACK_PHASE = 1;
    public static final int FORTIFICATION_PHASE = 2;

    private int currentPhase;
    private int playerTurn;
    private int totalPlayers;

    /** Switch the control to the next player */
    private void nextPlayer() {
        this.playerTurn++;
        if (this.playerTurn == this.totalPlayers)
            this.playerTurn = 0;
    }

    /** Initializes the new instance of the class. */
    public PhaseData() {
        this.currentPhase = -1;
        this.playerTurn = 0;
        this.totalPlayers = 0;
    }

    /**
     * Set total number of players
     * @param totalPlayers number players playing the game.
     */
    public void setTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    /** Return the index of the current phase */
    public int getCurrentPhase() {
        return currentPhase;
    }

    /** Returns the index of the active player */
    public int getPlayerTurn() {
        return playerTurn;
    }

    /** Changes player turn */
    public void changeTurn() {
        this.nextPlayer();
        this.notifyObservers("change:turn");
    }

    /**
     * Changes the phases in each turn.
     * It automatically changes the player turn
     */
    public void changePhase() {
        if (this.currentPhase == 2) {
            this.nextPlayer();
            this.currentPhase = -1;
        }
        this.currentPhase++;
        this.notifyObservers("change:phase");
    }
}