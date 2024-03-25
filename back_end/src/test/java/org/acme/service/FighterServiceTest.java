package org.acme.service;

import org.acme.model.Fighter;
import org.acme.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

class FighterServiceTest {

    FighterService fighterService;
    Fighter fighter;
    Fighter opponent;

    @BeforeEach
    void setUp() {
        fighterService = new FighterService();
        fighter = new Fighter("fighter1", 100, 0, false, false, false, false, "idle");
        opponent = new Fighter("fighter2", 100, 0, false, false, false, false, "idle");
    }

    @Test
    void moveRight() {
        fighterService.moveRight(fighter);
        assertEquals(1, fighter.getPos());
        assertEquals("run", fighter.getStatus());
        assertTrue(fighter.isCooldown());
    }

    @Test
    void moveLeft() {
        fighterService.moveLeft(fighter);
        assertEquals(-1, fighter.getPos());
        assertEquals("run", fighter.getStatus());
        assertTrue(fighter.isCooldown());
    }

    @Test
    void attack() {
        fighterService.attack(fighter, opponent);
        assertEquals(100 - Game.DEFAULT_ATTACK_DAMAGE, opponent.getHp());
        assertEquals("attack", fighter.getStatus());
        assertTrue(fighter.isCooldown());
    }

    @Test
    void attackWithDefend() {
        opponent.setDefend(true);
        fighterService.attack(fighter, opponent);
        assertEquals(100, opponent.getHp());
        assertEquals("attack", fighter.getStatus());
        assertTrue(fighter.isCooldown());
    }

    @Test
    void attackWithCooldown() {
        fighter.setCooldown(true);
        fighterService.attack(fighter, opponent);
        assertEquals(100, opponent.getHp());
        assertEquals("idle", fighter.getStatus());
        assertTrue(fighter.isCooldown());
    }

    @Test
    void attackWithDead() {
        fighter.setHp(0);
        fighterService.attack(fighter, opponent);
        assertEquals(100, opponent.getHp());
        assertEquals("death", fighter.getStatus());
        assertFalse(fighter.isCooldown());
    }

    @Test
    void attackWithOpponentDead() {
        opponent.setHp(0);
        fighterService.attack(fighter, opponent);
        assertEquals(0, opponent.getHp());
        assertEquals("attack", fighter.getStatus());
        assertTrue(fighter.isCooldown());
    }

    @Test
    void attackWithOpponentFar() {
        opponent.setPos(Game.DEFAULT_ATTACK_RANGE + 1);
        fighterService.attack(fighter, opponent);
        assertEquals(100, opponent.getHp());
        assertEquals("attack", fighter.getStatus());
        assertTrue(fighter.isCooldown());
    }

    @Test
    void attackWithOpponentClose() {
        opponent.setPos(Game.DEFAULT_ATTACK_RANGE);
        fighterService.attack(fighter, opponent);
        assertEquals(100 - Game.DEFAULT_ATTACK_DAMAGE, opponent.getHp());
        assertEquals("attack", fighter.getStatus());
        assertTrue(fighter.isCooldown());
    }

    @Test
    void defend() {
        fighterService.defend(fighter);
        assertTrue(fighter.isDefend());
        assertEquals("jump", fighter.getStatus());
        assertTrue(fighter.isCooldown());
    }

    @Test
    void defendWithCooldown() {
        fighter.setCooldown(true);
        fighterService.defend(fighter);
        assertFalse(fighter.isDefend());
        assertEquals("idle", fighter.getStatus());
        assertTrue(fighter.isCooldown());
    }

    @Test
    void defendWithDead() {
        fighter.setHp(0);
        fighterService.defend(fighter);
        assertFalse(fighter.isDefend());
        assertEquals("death", fighter.getStatus());
        assertFalse(fighter.isCooldown());
    }

    @Test
    void cooldown() {
        fighter.setStatus("run");
        fighterService.cooldown(fighter);
        assertTrue(fighter.isCooldown());

        // +100ms to ensure the cooldown is over
        await().atMost(Game.DEFAULT_COOLDOWN + 100, MILLISECONDS).until(endOfCooldown());

        assertFalse(fighter.isCooldown());

        assertEquals("idle", fighter.getStatus());
    }

    private Callable<Boolean> endOfCooldown() {
        return () -> {
            return !fighter.isCooldown();
        };
    }
}