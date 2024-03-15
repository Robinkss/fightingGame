package org.acme.model;

import lombok.Data;

import static java.lang.Thread.sleep;

@Data
public class Combattant {

    private String name;

    private int hp;
    private int attackDamage;

    private int attackSpeed;

    private int movementSpeed;

    private int posX;
    private int posY;

    public void attack(Combattant target) {
        target.hp -= this.attackDamage;
    }

    public void moveForward() {
        this.posX += this.movementSpeed;
    }

    public void moveBackward() {
        this.posX -= this.movementSpeed;
    }

    /*public void jump() throws InterruptedException {
        this.posY += 1;
        sleep(1000);
    }*/

}
