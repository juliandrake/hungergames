/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame.entities;

import java.awt.Color;
import javagame.JavaGame;
import javagame.display.Colors;

/**
 *
 * @author julia
 */
public class ItemFood extends Item {
    
    private String name;
    private EnumItemTier tier;
    private int healAmount;
    
    public ItemFood(String name, EnumItemTier tier, int healAmount) {
        super(name, tier);
        this.name = name;
        this.tier = tier;
        this.healAmount = healAmount;
    }

    public String getName() {
        return name;
    }

    public String getUseButtonText() {
        return "Eat";
    }
    
    @Override
    public void useItem(JavaGame game) {
        game.getPlayer().heal(healAmount);
        game.getGameWindow().consoleLogColor("You eat the " + name + " and gain " + healAmount + " health.", Colors.DARK_GREEN);
        game.getPlayerInventory().remove(game.getGameWindow().getSelectedItem());
        game.getGameWindow().updateInventory();
        game.getGameWindow().updateStats();
    }
    
    public EnumItemTier getTier() {
        return tier;
    }
    
    
    
}
