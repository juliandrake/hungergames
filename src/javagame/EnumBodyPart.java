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
public enum EnumBodyPart {
    
    HEAD("head",2),
    TORSO("torso",1.4f),
    LEFTLEG("left leg",1),
    RIGHTLEG("right leg",1),
    RIGHTARM("right arm", 1),
    LEFTARM("left arm", 1),
    RIGHTHAND("right hand", 0.75f),
    LEFTHAND("left hand", 0.75f),
    RIGHTFOOT("right foot", 0.75f),
    LEFTFOOT("left foot", 0.75f);
    
    
    public final String name;
    public final float damageMultiplier;
    
    
    private EnumBodyPart(String name, float damageMultiplier) {
        this.name = name;
        this.damageMultiplier = damageMultiplier;
    }
}
