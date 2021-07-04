/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame.entities;

import java.util.ArrayList;
import java.util.Random;
import javagame.EnumAttackType;
import javagame.JavaGame;

/**
 *
 * @author julia
 */
public class ItemWeapon extends Item {
    
    private String name;
    private int damage;
    private EnumItemTier tier;
    private EnumAttackType type;
    
    public ItemWeapon(String name, int damage, EnumItemTier tier, EnumAttackType type) {
        super(name, tier);
        this.name = name;
        this.tier = tier;
        this.damage = damage;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }
    
    @Override
    public void useItem(JavaGame game) {
        System.out.println("Weapon " + name + " equipped!");
        game.getPlayer().heldWeapon = this;
        game.getGameWindow().updateStats();
    }
    
    @Override
    public String getUseButtonText() {
        return "Equip";
    }
    
    public String getVerb() {
        Random rand = new Random();
        
        ArrayList<String> verbs = new ArrayList<>();
        switch(type) {
            case SLASHING:
                verbs.add("cut");
                return verbs.get(rand.nextInt(verbs.size()));
            case STABBING:
                verbs.add("stab");
                return verbs.get(rand.nextInt(verbs.size()));
            default:
                verbs.add("bludgeon"); verbs.add("hit");
                return verbs.get(rand.nextInt(verbs.size()));
        }
    }
    
    
}
