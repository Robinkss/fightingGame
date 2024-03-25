package org.acme.controller;

import io.restassured.response.Response;
import org.acme.service.FighterService;
import org.acme.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

class FighterControllerTest {

    private FighterController fighterController;
    private FighterService fighterService;
    private GameService gameService;

    @BeforeEach
    void setUp() {
        fighterService = new FighterService();
        gameService = new GameService();
        gameService.restartGame();
        fighterController = new FighterController(fighterService, gameService);
    }


    @Test
    void moveRight() {
        int pos = gameService.getFighter(0).getPos();
        fighterController.moveRight(0);
        assertEquals(pos + 1, gameService.getFighter(0).getPos());
    }

    @Test
    void moveLeft() {
        int pos = gameService.getFighter(0).getPos();
        fighterController.moveLeft(0);
        assertEquals(pos - 1, gameService.getFighter(0).getPos());
    }

    @Test
    void attack() {
        fighterController.attack(0);
        assertEquals("attack", gameService.getFighter(0).getStatus());
    }

    @Test
    void defend() {
        fighterController.defend(0);
        assertEquals("jump", gameService.getFighter(0).getStatus());
    }
}