/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame.generators;

import java.util.ArrayList;
import java.util.Random;
import javagame.EnumLocation;
import javagame.entities.Combatant;

/**
 *
 * @author julian
 */
public class EventGenerator {
    
    private static Random rand = new Random();
    public static ArrayList<String> deathEvents = new ArrayList<>();
    private static boolean eventsInitialized = false;
    
    static {
        
        
    }
    
    public static String getEvent(Combatant combatant1, Combatant combatant2) {
        if (!eventsInitialized) {
            initializeEvents();
        }
        String event;
        event = deathEvents.get(rand.nextInt(deathEvents.size()));
        event = event.replace("cmb1", combatant1.getName());
        event = event.replace("cmb2", combatant2.getName());
        return event;
    }
    
    private static void initializeEvents() {
        deathEvents.add("cmb1 throws a knife into cmb2's head.");
        deathEvents.add("cmb1 catches cmb2 off guard and kills them.");
        deathEvents.add("cmb1 strangles cmb2 after engaging in a fist fight");
        deathEvents.add("cmb1 shoots an arrow into cmb2's head.");
        deathEvents.add("cmb1 bashes cmb2's head against a rock several times.");
        deathEvents.add("cmb1 snaps cmb2's neck.");
        deathEvents.add("cmb1 decapitates cmb2 with a sword.");
        deathEvents.add("cmb1 spears cmb2 in the abdomen.");
        deathEvents.add("cmb1 sets cmb2 on fire with a molotov cocktail.");
        deathEvents.add("cmb1 turns on cmb2, stabbing them in the back.");
        deathEvents.add("cmb1 severely injures cmb2 and puts them out of their misery.");
        deathEvents.add("cmb1 severely injures cmb2 and leaves them to die.");
        deathEvents.add("cmb1 bashes cmb2's head in with a mace.");
        deathEvents.add("cmb1 pushes cmb2 off a cliff during a knife fight.");
        deathEvents.add("cmb1 throws a knife into cmb2's chest.");
        deathEvents.add("cmb1 is unable to convince cmb2 to not kill them.");
        deathEvents.add("cmb1 kills cmb2 with their own weapon.");
        deathEvents.add("cmb1 overpowers cmb2, killing them.");
        deathEvents.add("cmb1 sets an explosive off, killing cmb2.");
        deathEvents.add("cmb1 kills cmb2 as they try to run away.");
        deathEvents.add("cmb1 kills cmb2 with a hatchet.");
        deathEvents.add("cmb1 severely slices cmb2 with a sword.");
        deathEvents.add("cmb1 strangles cmb2 with a rope.");
        deathEvents.add("cmb1 kills cmb2 for their supplies.");
        deathEvents.add("cmb1 shoots a poisionous blow dart into cmb2's neck, slowly killing them.");
        deathEvents.add("cmb1 stabs cmb2 with a tree branch.");
        deathEvents.add("cmb1 stabs cmb2 in the back with a trident.");
        deathEvents.add("cmb1 kills cmb2 with a sickle.");
        eventsInitialized = true;
    }
    
    
}
