package org.acme.service;

import org.acme.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    GameService gameService;
    Game game;

    @BeforeEach
    void setUp() {
        Game.resetInstance();
        gameService = new GameService();
        game = Game.getInstance();
    }

    @Test
    void restartGame() {
        gameService.restartGame();
        Game game2 = Game.getInstance();

        // The two instances should are equal but not the same (different memory addresses)
        assertNotSame(game, game2);
        assertEquals(game, game2);
    }

    @Test
    void getFighter() {
        assertEquals(game.getFighters().x(), gameService.getFighter(0));
        assertEquals(game.getFighters().y(), gameService.getFighter(1));
        assertNull(gameService.getFighter(2));
    }
}