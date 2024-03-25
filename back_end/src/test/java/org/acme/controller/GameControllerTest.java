package org.acme.controller;

import org.acme.model.Game;
import org.acme.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private GameController gameController;

    @BeforeEach
    void setUp() {
        GameService gameService = new GameService();
        gameService.restartGame();
        gameController = new GameController(gameService);
    }

    @Test
    void restartGame() {
        Game previousGame = Game.getInstance();
        gameController.restartGame();
        Game newGame = Game.getInstance();

        assertNotSame(previousGame, newGame);
        assertEquals(previousGame, newGame);
    }
}