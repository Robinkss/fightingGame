package org.acme.model;

public class Game {

    private static Game instance;

    private Combattant player1;
    private Combattant player2;

    private int timeRemaining;

    private Game(Combattant player1, Combattant player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.timeRemaining = 60;
    }

    public static Game getInstance(Combattant player1, Combattant player2) {
        if (instance == null) {
            instance = new Game(player1, player2);
        }
        return instance;
    }
}
