package org.acme.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game;

    @BeforeEach
    void setUp() {
        Game.resetInstance();
        game = Game.getInstance();
    }

    @Test
    void getInstance() {
        Game game1 = Game.getInstance();
        Game game2 = Game.getInstance();
        assertEquals(game1, game2);
    }

    @Test
    void resetInstance() {
        Game game1 = Game.getInstance();
        Game.resetInstance();
        Game game2 = Game.getInstance();

        // The two instances should are equal but not the same (different memory addresses)
        assertNotSame(game1, game2);
        assertEquals(game1, game2);
    }

    @Test
    void gameOverWithX() {
        assertFalse(game.gameOver());

        game.getFighters().x().setHp(0);
        assertTrue(game.gameOver());
    }

    @Test
    void gameOverWithY() {
        assertFalse(game.gameOver());

        game.getFighters().y().setHp(0);
        assertTrue(game.gameOver());
    }

    @Test
    void toJson() {
        String json =
                "{" +
                        "\"gameOver\":" + game.gameOver() + "," +
                        "\"DEFAULT_HP\":" + Game.DEFAULT_HP + "," +
                        "\"DEFAULT_ATTACK_DAMAGE\":" + Game.DEFAULT_ATTACK_DAMAGE + "," +
                        "\"fighter1\":" + game.getFighters().x().toJson().toString() + "," +
                        "\"fighter2\":" + game.getFighters().y().toJson().toString() +
                "}";

        assertEquals(game.toJson().toString(), json);
    }
}