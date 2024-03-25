package org.acme.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

@Data
public class Game {

    public static final int DEFAULT_ATTACK_RANGE = 4;
    public static final int DEFAULT_COOLDOWN = 300; // 300ms

    public static final int DEFAULT_HP = 100;
    public static final int DEFAULT_ATTACK_DAMAGE = 10;
    public static final int DEFAULT_ARENA_SIZE = 38; // -38 to 38 inclusive (total 77 positions)

    private static Game instance;

    private Tuple<Fighter, Fighter> fighters;

    private Game() {
        Fighter fighter1 = new Fighter("P1", 100, -10, false, false, true, false, "idle");
        Fighter fighter2 = new Fighter("P2", 100, 10, false, false, false, false, "idle");
        this.fighters = new Tuple<>(fighter1, fighter2);
    }

    public static synchronized Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public boolean gameOver() {
        return fighters.x().isDead() || fighters.y().isDead();
    }

    public ObjectNode toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode json = objectMapper.createObjectNode();

        json.put("gameOver", gameOver());
        json.put("DEFAULT_HP", DEFAULT_HP);
        json.put("DEFAULT_ATTACK_DAMAGE", DEFAULT_ATTACK_DAMAGE);

        json.set("fighter1", fighters.x().toJson());
        json.set("fighter2", fighters.y().toJson());

        return json;
    }
}
