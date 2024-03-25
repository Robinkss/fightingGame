package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Fighter;
import org.acme.model.Game;

import java.util.logging.Logger;


@ApplicationScoped
public class FighterService {
    
    private static final String DEATH = "death";
    Logger logger = Logger.getLogger(FighterService.class.getName());

    public void moveRight(Fighter fighter) {
        if (fighter != null) {
            if (fighter.isDead()) {
                fighter.setStatus(DEATH);
            }
            else if(!fighter.isCooldown()) {
                fighter.moveForward();
                cooldown(fighter);
                fighter.setStatus("run");
            }
        }
    }

    public void moveLeft(Fighter fighter) {
        if (fighter != null) {
            if (fighter.isDead()) {
                fighter.setStatus(DEATH);
            }
            else if(!fighter.isCooldown()) {
                fighter.moveBackward();
                cooldown(fighter);
                fighter.setStatus("run");
            }
        }
    }

    public void attack(Fighter attacker, Fighter opponent) {
        if (attacker != null && opponent != null) {
            if (attacker.isDead()) {
                attacker.setStatus(DEATH);
            }
            else if (!attacker.isCooldown()) {
                cooldown(attacker);
                if (!opponent.isDefend() && Math.abs(attacker.getPos() - opponent.getPos()) <= Game.DEFAULT_ATTACK_RANGE) {
                    opponent.takeDamage();
                }
                attacker.setStatus("attack");
            }
        }
    }

    public void defend(Fighter fighter) {
        if (fighter != null) {
            if (fighter.isDead()) {
                fighter.setStatus(DEATH);
            }
            else if (!fighter.isDefend() && !fighter.isCooldown()) {
                fighter.setDefend(true);
                cooldown(fighter);
                fighter.setStatus("jump");
            }
        }
    }

    protected void cooldown(Fighter fighter) {
        if (fighter != null) {
            fighter.setCooldown(true);

            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(Game.DEFAULT_COOLDOWN);
                    fighter.setCooldown(false);
                    fighter.setDefend(false);
                    fighter.setAttack(false);
                    fighter.setStatus("idle");
                } catch (InterruptedException e) {
                    logger.info("Thread interrupted");
                    Thread.currentThread().interrupt();
                }
            });
            thread.start();
        }
    }

}
