/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame.generators;

import java.util.ArrayList;
import java.util.Random;
import javagame.EnumLocation;

/**
 *
 * @author julian
 */
public class SceneryGenerator {
    
    private static Random rand = new Random();
    public static ArrayList<String> sceneryForest = new ArrayList<>();
    private static boolean sceneryInitialized = false;
    
    static {
        
        
    }
    
    public static String generateScenery(EnumLocation location) {
        if (!sceneryInitialized) {
            initializeScenery();
        }
        if (location == EnumLocation.FOREST) {
            return sceneryForest.get(rand.nextInt(sceneryForest.size()));
        } else {
            return "";
        }
    }
    
    private static void initializeScenery() {
        sceneryForest.add("You don't see anything of interest.");
        sceneryForest.add("You notice a few tall trees.");
        sceneryForest.add("You spot some animals running past you.");
        sceneryForest.add("You notice some birds flying above you.");
        sceneryForest.add("You see a swarm of insects.");
        sceneryForest.add("You spot a land formation in the distance.");
        sceneryForest.add("You hear the rustling of leaves.");
        sceneryForest.add("You feel the wind blow in your hair.");
        sceneryForest.add("You hear the roar of a bear in the distance.");
        sceneryForest.add("You see a few spruce trees.");
        sceneryForest.add("You come across a couple cedar trees.");
        sceneryForest.add("You walk past many oak trees.");
        sceneryForest.add("You walk past many poplar trees.");
        sceneryForest.add("You walk between two large pine trees.");
        sceneryForest.add("You notice the crunch of leaves under your feet.");
        sceneryForest.add("You shield your eyes from the bright sun.");
        sceneryForest.add("You hear screams in the distance.");
        sceneryForest.add("You notice the way the trees are arranged.");
        sceneryInitialized = true;
    }
    
    
}
