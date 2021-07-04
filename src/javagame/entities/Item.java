/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame.entities;

import javagame.JavaGame;

/**
 *
 * @author julia
 */
public class Item {
    
    private String name;
    private EnumItemTier tier;
    
    public Item(String name, EnumItemTier tier) {
        this.name = name;
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public String getUseButtonText() {
        return "Use";
    }
    
    public void useItem(JavaGame game) {
        System.out.println("Item " + name + " used!");
    }
    
    public EnumItemTier getTier() {
        return tier;
    }
    
    
    
}
