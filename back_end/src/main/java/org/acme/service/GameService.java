package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Fighter;
import org.acme.model.Game;

@ApplicationScoped
public class GameService {

    public void restartGame() {
        Game.resetInstance();
    }

    public Fighter getFighter(int id) {
        if(id == 0) {
            return Game.getInstance().getFighters().x();
        } else if (id == 1) {
            return Game.getInstance().getFighters().y();
        }
        return null;
    }

}
