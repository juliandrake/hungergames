/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame;

/**
 *
 * @author julian
 */
public enum EnumLocation {
    SPAWN(0, "Spawn Plate"),
    LOOTPILE1(30, "Loot Pile I"),
    LOOTPILE2(50, "Loot Pile II"),
    LOOTPILE3(80, "Loot PIle III"),
    FOREST(10, "Forest"),
    POND(20, "Pond");
    
    public final int enemyChance;
    public String name;
    
    private EnumLocation(int enemyChance, String name) {
        this.enemyChance = enemyChance;
        this.name = name;
    }
    
}
