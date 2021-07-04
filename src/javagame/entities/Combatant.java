/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame.entities;

import java.util.Random;
import javagame.EnumBodyPart;

/**
 *
 * @author julia
 */
public class Combatant {
    
    // member variable declarations
    String name;
    int health;
    int strength;
    int speed;
    int intelligence;
    ItemWeapon heldWeapon;
    Random rand = new Random();
    String pronoun1; // he
    String pronoun2; // him
    String pronoun3; // his
    
    public Combatant(String name, int health, int strength, int speed, int intelligence, ItemWeapon heldWeapon,
            String pronoun1, String pronoun2, String pronoun3) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.speed = speed;
        this.intelligence = intelligence;
        this.heldWeapon = heldWeapon;
        this.pronoun1 = pronoun1;
        this.pronoun2 = pronoun2;
        this.pronoun3 = pronoun3;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getSpeed() {
        return speed;
    }

    public int getIntelligence() {
        return intelligence;
    }
    
    public void kill() {
        health = 0;
    }
    
    public void heal(int amount) {
        health += amount;
        if (health > 100) {
            health = 100;
        }
    }

    public String getPronoun1() {
        return pronoun1;
    }

    public String getPronoun2() {
        return pronoun2;
    }

    public String getPronoun3() {
        return pronoun3;
    }
    
    
    
    public int takeDamage(int amount, EnumBodyPart hitPart) {
        int trueAmount = (int) Math.ceil((float)amount*hitPart.damageMultiplier);
        health -= trueAmount;
        return trueAmount;
    }
    
    public int getDamage() {
        int damage = heldWeapon.getDamage();
        //damage *= Math.ceil(strength/10);
        return damage;
    }
    
    public ItemWeapon getWeapon() {
        return heldWeapon;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    
    
    
}
