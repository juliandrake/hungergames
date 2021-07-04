/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame.generators;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javagame.entities.Combatant;

/**
 *
 * @author julian
 */
public class CombatantGenerator {
    
    Random rand = new Random();
    BufferedReader bufferedReader;
    ArrayList<String> maleFirstNames = new ArrayList<>();
    ArrayList<String> femaleFirstNames = new ArrayList<>();
    ArrayList<String> lastNames = new ArrayList<>();
    
    public CombatantGenerator() throws IOException {
        // male first names
        try {
            this.bufferedReader = new BufferedReader(new FileReader("assets/malefirstnames.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CombatantGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        String line = bufferedReader.readLine();
        while (line != null) {
            maleFirstNames.add(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        
        // female first names
        try {
            this.bufferedReader = new BufferedReader(new FileReader("assets/femalefirstnames.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CombatantGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        line = bufferedReader.readLine();
        while (line != null) {
            femaleFirstNames.add(line);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        
//        // last names
//        try {
//            this.bufferedReader = new BufferedReader(new FileReader("assets/lastnames.txt"));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(CombatantGenerator.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        line = bufferedReader.readLine();
//        while (line != null) {
//            lastNames.add(line);
//            line = bufferedReader.readLine();
//        }
//        bufferedReader.close();
//        System.out.println(lastNames.get(rand.nextInt(lastNames.size())));
    }
    // Combatant enemy = new Combatant("Bob", 100, 5+(xp/50), 5, 5, ItemGenerator.getRandomWeaponDamageRange(5, 5+(int) Math.ceil(15*(xp/100))),
    //"He", "his", "his");
    public Combatant generateCombatant() {
        boolean male = rand.nextBoolean();
        String firstName;
        String pronoun1;
        String pronoun2;
        String pronoun3;
        if (male) {
            firstName = maleFirstNames.get(rand.nextInt(maleFirstNames.size()));
            pronoun1 = "He"; pronoun2 = "his"; pronoun3 = "his";
        } else {
            firstName = femaleFirstNames.get(rand.nextInt(femaleFirstNames.size()));
            pronoun1 = "She"; pronoun2 = "her"; pronoun3 = "her";
        }
        firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
        // String lastName = lastNames.get(rand.nextInt(lastNames.size()));
        Combatant combatant = new Combatant(firstName, 100, 5+rand.nextInt(8),
                5+rand.nextInt(8),5+rand.nextInt(8), ItemGenerator.getRandomWeaponDamageRange(5,20),
                pronoun1, pronoun2, pronoun3);
        return combatant;
    }
    
    
    
    
}
