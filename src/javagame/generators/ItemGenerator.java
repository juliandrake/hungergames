/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame.generators;

import java.util.ArrayList;
import java.util.Random;
import javagame.EnumAttackType;
import javagame.entities.EnumItemTier;
import javagame.entities.Item;
import javagame.entities.ItemFood;
import javagame.entities.ItemWeapon;

/**
 *
 * @author julia
 */
public class ItemGenerator {
    
    static Random rand = new Random();
    static ArrayList<WeaponTemplate> weaponNames = new ArrayList<>();
    static ArrayList<Item> itemList = new ArrayList<>();
    static ArrayList<Item> itemListTier1 = new ArrayList<>();
    static ArrayList<Item> itemListTier2 = new ArrayList<>();
    static ArrayList<Item> itemListTier3 = new ArrayList<>();
    static boolean weaponsDefined = false;
    public ItemGenerator() {
        
    }
    
    static {
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new ItemFood("Bread", EnumItemTier.TIER1, 20));
        defineItem(new Item("Awl", EnumItemTier.TIER1));
        defineItem(new Item("Backpack", EnumItemTier.TIER1));
        defineItem(new Item("Belt", EnumItemTier.TIER1));
        defineItem(new Item("Body Armor", EnumItemTier.TIER3));
        defineItem(new Item("Fishing Gear", EnumItemTier.TIER2));
        defineItem(new Item("Night-vision Glasses", EnumItemTier.TIER3));
        defineItem(new Item("Plastic Sheeting", EnumItemTier.TIER1));
        defineItem(new Item("Riot Shield", EnumItemTier.TIER3));
        defineItem(new Item("Rope", EnumItemTier.TIER1));
        defineItem(new Item("Sleeping Bag", EnumItemTier.TIER2));
        defineItem(new Item("Spile", EnumItemTier.TIER1));
        defineItem(new Item("Wire", EnumItemTier.TIER2));
    }
    
    private static void defineItem(Item item) {
        itemList.add(item);
        if (null != item.getTier()) switch (item.getTier()) {
            case TIER1:
                itemListTier1.add(item);
                break;
            case TIER2:
                itemListTier2.add(item);
                break;
            case TIER3:
                itemListTier3.add(item);
                break;
            default:
                break;
        }
    }
    
    public static ItemWeapon getRandomWeapon() {
        initWeaponList();
        WeaponTemplate weaponType = weaponNames.get(rand.nextInt(weaponNames.size()));
        ItemWeapon weapon = new ItemWeapon(weaponType.getName(),rand.nextInt(20),EnumItemTier.TIER3,weaponType.getType());
        return weapon;
    }
    
    public static Item getRandomItem() {
        Item generatedItem = new Item("Item " + rand.nextInt(1000), EnumItemTier.TIER1);
        return generatedItem;
    }
    
    public static Item getRandomItemTier1() {
        Item foundItem = null;
        if (rand.nextInt(11)==0) {
            foundItem = getRandomWeaponDamageRange(3,10);
        } else {
            int listSize = itemListTier1.size();
            foundItem = itemListTier1.get(rand.nextInt(listSize));
        }
        return foundItem;
    }
    public static Item getRandomItemTier2() {
        Item foundItem = null;
        if (rand.nextInt(5)==0) {
            foundItem = getRandomWeaponDamageRange(7,15);
        } else {
            int listSize = itemListTier2.size();
            foundItem = itemListTier2.get(rand.nextInt(listSize));
        }
        return foundItem;
    }
        public static Item getRandomItemTier3() {
        Item foundItem = null;
        if (rand.nextBoolean()) {
            foundItem = getRandomWeaponDamageRange(10,25);
        } else {
            int listSize = itemListTier3.size();
            foundItem = itemListTier3.get(rand.nextInt(listSize));
        }
        return foundItem;
    }
    
    
    public static ItemWeapon getRandomWeaponDamageRange(int min, int max) {
        initWeaponList();
        int damage = min + rand.nextInt((max+1)-min);
        WeaponTemplate weaponType = weaponNames.get(rand.nextInt(weaponNames.size()));
        ItemWeapon weapon = new ItemWeapon(weaponType.getName(),damage,EnumItemTier.TIER3,weaponType.getType());
        return weapon;
    }
    
    private static void initWeaponList() {
        if (!weaponsDefined) {
            weaponNames.add(new WeaponTemplate("Sword",EnumAttackType.BLUDGEONING));
            weaponNames.add(new WeaponTemplate("Spear",EnumAttackType.STABBING));
            weaponNames.add(new WeaponTemplate("Club",EnumAttackType.BLUDGEONING));
            weaponNames.add(new WeaponTemplate("Knife",EnumAttackType.STABBING));
            weaponNames.add(new WeaponTemplate("Dagger",EnumAttackType.STABBING));
            weaponNames.add(new WeaponTemplate("Broadsword",EnumAttackType.SLASHING));
            weaponNames.add(new WeaponTemplate("Shortsword",EnumAttackType.SLASHING));
            weaponNames.add(new WeaponTemplate("Mace",EnumAttackType.BLUDGEONING));
            weaponNames.add(new WeaponTemplate("Shovel",EnumAttackType.SLASHING));
            weaponNames.add(new WeaponTemplate("Staff",EnumAttackType.BLUDGEONING));
            weaponNames.add(new WeaponTemplate("Shield",EnumAttackType.BLUDGEONING));
            weaponNames.add(new WeaponTemplate("Katana",EnumAttackType.SLASHING));
            weaponNames.add(new WeaponTemplate("Axe",EnumAttackType.BLUDGEONING));
            weaponNames.add(new WeaponTemplate("Hammer",EnumAttackType.BLUDGEONING));
        }
    }
    
}
