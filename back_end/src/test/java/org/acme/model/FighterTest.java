package org.acme.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FighterTest {

    Fighter fighter;

    @BeforeEach
    void setUp() {
        fighter = new Fighter("fighter1", 100, 0, false, false, false, false, "idle");
    }

    @Test
    void moveForward() {
        fighter.moveForward();
        assertEquals(1, fighter.getPos());
    }

    @Test
    void moveForwardOutOfBounds() {
        fighter.setPos(Game.DEFAULT_ARENA_SIZE);
        fighter.moveForward();
        assertEquals(Game.DEFAULT_ARENA_SIZE, fighter.getPos());
    }

    @Test
    void moveBackward() {
        fighter.moveBackward();
        assertEquals(-1, fighter.getPos());
    }

    @Test
    void moveBackwardOutOfBounds() {
        fighter.setPos(-Game.DEFAULT_ARENA_SIZE);
        fighter.moveBackward();
        assertEquals(-Game.DEFAULT_ARENA_SIZE, fighter.getPos());
    }

    @Test
    void takeDamage() {
        int damage = Game.DEFAULT_ATTACK_DAMAGE;
        fighter.takeDamage();
        assertEquals(100 - damage, fighter.getHp());

    }

    @Test
    void takeDamageWithLowHp() {
        fighter.setHp(Game.DEFAULT_ATTACK_DAMAGE - 1);
        fighter.takeDamage();
        assertEquals(0, fighter.getHp());
    }

    @Test
    void isDead() {
        assertFalse(fighter.isDead());

        fighter.setHp(0);
        assertTrue(fighter.isDead());
    }

    @Test
    void toJson() {
        String json =
                "{" +
                        "\"name\":\"fighter1\"," +
                        "\"hp\":100," +
                        "\"pos\":0," +
                        "\"direction\":false," +
                        "\"status\":\"idle\"" +
                "}";
        assertEquals(fighter.toJson().toString(), json);
    }

    @Test
    void testConstructor() {
        Fighter fighter = new Fighter("fighter1", 100, 0, false, false, false, false, "idle");
        assertEquals("fighter1", fighter.getName());
        assertEquals(100, fighter.getHp());
        assertEquals(0, fighter.getPos());
        assertFalse(fighter.isDefend());
        assertFalse(fighter.isAttack());
        assertFalse(fighter.isDirection());
        assertFalse(fighter.isCooldown());
        assertEquals("idle", fighter.getStatus());
    }

    @Test
    void testSetters() {
        fighter.setName("fighter2");
        fighter.setHp(50);
        fighter.setPos(1);
        fighter.setDefend(true);
        fighter.setAttack(true);
        fighter.setDirection(true);
        fighter.setCooldown(true);
        fighter.setStatus("attacking");

        assertEquals("fighter2", fighter.getName());
        assertEquals(50, fighter.getHp());
        assertEquals(1, fighter.getPos());
        assertTrue(fighter.isDefend());
        assertTrue(fighter.isAttack());
        assertTrue(fighter.isDirection());
        assertTrue(fighter.isCooldown());
        assertEquals("attacking", fighter.getStatus());
    }

    @Test
    void testToString() {
        assertEquals("Fighter(name=fighter1, hp=100, pos=0, defend=false, attack=false, direction=false, isCooldown=false, status=idle)", fighter.toString());
    }

    @Test
    void testEquals() {
        Fighter fighter2 = new Fighter("fighter1", 100, 0, false, false, false, false, "idle");
        assertEquals(fighter, fighter2);
    }

}