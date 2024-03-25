package org.acme.controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;

import org.acme.service.GameService;

import java.util.logging.Logger;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameController {

    Logger logger = Logger.getLogger(getClass().getName());

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @POST
    @Path("/restart")
    public void restartGame() {
        logger.info("Restarting game...");
        gameService.restartGame();
    }

}