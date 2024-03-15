package org.acme.ctrl;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.model.Combattant;
import org.acme.service.CombattantService;

@Path("/combattant")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CombattantController {

    private CombattantService combattantService;

    public CombattantController(CombattantService combattantService) {
        this.combattantService = combattantService;
    }

    @POST
    @Path("/{combattantId}/moveRight")
    public void moveRight(@PathParam("combattantId") int combattantId) {
        combattantService.moveRight(combattantId);
    }

    @POST
    @Path("/{combattantId}/moveLeft")
    public void moveLeft(@PathParam("combattantId") int combattantId) {
        combattantService.moveLeft(combattantId);
    }

    @POST
    @Path("/{attackerId}/attack/{targetId}")
    public void attack(@PathParam("attackerId") int attackerId, @PathParam("targetId") int targetId) {
        combattantService.attack(attackerId, targetId);
    }
}
