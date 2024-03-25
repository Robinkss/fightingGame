package org.acme.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

@Data
@lombok.AllArgsConstructor(access = lombok.AccessLevel.PUBLIC)
public class Fighter {

    private String name;
    private int hp;
    private int pos;
    private boolean defend;
    private boolean attack;
    private boolean direction; // true = right, false = left
    private boolean isCooldown;
    private String status; // "idle", "attacking", "defending", "cooldown", "death", "run"

    public void moveForward() {
        if(this.pos < Game.DEFAULT_ARENA_SIZE) {
            this.pos += 1;
            this.direction = true;
        }
    }

    public void moveBackward() {
        if (this.pos > -Game.DEFAULT_ARENA_SIZE) {
            this.pos -= 1;
            this.direction = false;
        }
    }

    public void takeDamage() {
        if(this.hp - Game.DEFAULT_ATTACK_DAMAGE <= 0) {
            this.hp = 0;
            this.status = "death";
        } else {
            this.hp -= Game.DEFAULT_ATTACK_DAMAGE;
        }
    }

    public boolean isDead() {
        return this.hp <= 0;
    }

    public ObjectNode toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode json = objectMapper.createObjectNode();

        json.put("name", this.name);
        json.put("hp", this.hp);
        json.put("pos", this.pos);
        json.put("direction", this.direction);
        json.put("status", this.status);

        return json;
    }

}
