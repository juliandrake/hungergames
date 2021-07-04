/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame.generators;

import javagame.EnumAttackType;

/**
 *
 * @author julian
 */
public class WeaponTemplate {
    
    private String name;
    private EnumAttackType type;
    
    public WeaponTemplate(String name, EnumAttackType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public EnumAttackType getType() {
        return type;
    }
    
    
    
}
