package org.acme.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.service.FighterService;
import org.acme.service.GameService;

@Path("/fighter")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FighterController {

    private final FighterService fighterService;
    private final GameService gameService;

    public FighterController(FighterService fighterService, GameService gameService) {
        this.fighterService = fighterService;
        this.gameService = gameService;
    }

    @POST
    @Path("/{fighterId}/moveRight")
    public void moveRight(@PathParam("fighterId") int fighterId) {
        fighterService.moveRight(gameService.getFighter(fighterId));
    }

    @POST
    @Path("/{fighterId}/moveLeft")
    public void moveLeft(@PathParam("fighterId") int fighterId) {
        fighterService.moveLeft(gameService.getFighter(fighterId));
    }

    @POST
    @Path("/{fighterId}/attack/")
    public void attack(@PathParam("fighterId") int fighterId) {
        fighterService.attack(gameService.getFighter(fighterId), gameService.getFighter(fighterId == 0 ? 1 : 0));
    }

    @POST
    @Path("/{fighterId}/defend/")
    public void defend(@PathParam("fighterId") int fighterId) {
        fighterService.defend(gameService.getFighter(fighterId));
    }

}