/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javagame.generators.ItemGenerator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javagame.display.Colors;
import javagame.entities.Combatant;
import javagame.entities.EnumItemTier;
import javagame.entities.Item;
import javagame.entities.ItemWeapon;
import javagame.generators.CombatantGenerator;
import javagame.generators.EventGenerator;
import javagame.generators.SceneryGenerator;

/**
 *
 * @author julia
 */
public class JavaGame {
    // TODO: time, thirst, food, end of day recap
    int turn = 0;
    int xp = 0;
    GameWindow gameWindow;
    Date time = new Date(2000, 2, 17, 12,0);
    Battle currentBattle = null;
    DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
    private EnumGameState gameState = EnumGameState.LOOTING;
    private int enemyChance = 0;
    public Random rand = new Random();
    EnumLocation location = EnumLocation.SPAWN;
    CombatantGenerator combatantGenerator;
    ArrayList<Combatant> combatantList = new ArrayList<>();
    ItemGenerator itemGenerator = new ItemGenerator();
    private ArrayList<Item> playerInventory = new ArrayList<>();
    Combatant player = new Combatant("you", 100, 10, 10, 15, new ItemWeapon("Bare Hands", 5, EnumItemTier.TIER0, EnumAttackType.BLUDGEONING),"You","your","your");
    public JavaGame(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        try {
            combatantGenerator = new CombatantGenerator();
        } catch (IOException ex) {
            Logger.getLogger(JavaGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // generate combatants
        for (int i=0; i < 99; i++) {
            Combatant combatant = combatantGenerator.generateCombatant();
            combatantList.add(combatant);
        }
    }
    
    public void advanceTurn(){
        incrementTurn();
        if (gameState == EnumGameState.EXPLORING) {
            explore();
        } else if (gameState == EnumGameState.FIGHTING) {
            fight();
        }
        gameWindow.updateStats();
    }
    
    public Combatant getPlayer() {
        return player;
    }
    
    public void setGameState(EnumGameState state) {
        gameState = state;
        if (state == EnumGameState.EXPLORING) {
            currentBattle = null;
        }
        gameWindow.updateStats();
    }
    
    public EnumGameState getGameState() {
        return gameState;
    }
    
    private boolean rollCombatChance() {
        int discovery = rand.nextInt(101);
        float chanceNumber = location.enemyChance/(float)combatantList.size();
        System.out.println(chanceNumber*100);
        System.out.println(discovery);
        if (discovery <= (chanceNumber*100)) {
            fight();
            return true;
        } else {
            return false;
        }
    }
    
    public void explore() {
        if (!rollCombatChance()) {
            gameWindow.consoleLog(SceneryGenerator.generateScenery(location));
        }
        if (rand.nextBoolean()) {
            killOffCombatant();
        }
    }
    
    public void fight() {
        //if (currentBattle == null) {
            EnumGameState oldGameState = gameState;
            gameState = EnumGameState.FIGHTING;
            // battle hasn't started yet but we should start one
            int combatantNumber = rand.nextInt(combatantList.size());
            Combatant enemy = combatantList.get(combatantNumber);
            gameWindow.consoleLog("You encounter " + enemy.getName() + "!");
            currentBattle = new Battle(player, enemy, gameWindow, oldGameState, combatantNumber);
            gameWindow.updateStats();
        //} else {
            // battle in progress
            
        //}
    }
    
    public void lootPileContinue() {
        Item lootedItem = null;
        if (location == EnumLocation.LOOTPILE2 || location == EnumLocation.LOOTPILE3) {
            
            lootedItem = ItemGenerator.getRandomItemTier3();
            gameWindow.consoleLog("You run into the pile of loot and pick up a "+
                    lootedItem.getName()+".");
            gameWindow.consoleLog("You feel that you will probably be killed if you stay much longer.");
            location = EnumLocation.LOOTPILE3;
        }
        if (location == EnumLocation.LOOTPILE1) {
            lootedItem = ItemGenerator.getRandomItemTier2();
            gameWindow.consoleLog("You run into the pile of loot and pick up a "+
                    lootedItem.getName()+".");
            gameWindow.consoleLog("Run deeper into the loot pile?");
            location = EnumLocation.LOOTPILE2;
        }
        if (location == EnumLocation.SPAWN) {
            lootedItem = ItemGenerator.getRandomItemTier1();
            gameWindow.consoleLog("You run into the pile of loot and pick up a "+
                    lootedItem.getName()+".");
            gameWindow.consoleLog("Run deeper into the loot pile?");
            location = EnumLocation.LOOTPILE1;
        }
        rollCombatChance();
        addToInventory(lootedItem);
    }
    
    public void lootPileAbandon() {
        gameWindow.consoleLog("You cut your losses and run from the loot pile.");
        location = EnumLocation.FOREST;
        setGameState(EnumGameState.EXPLORING);
    }
    
    public void doBattleOption(EnumBattleOption battleOption) {
        currentBattle.advanceBattle(battleOption);
    }
    
    public int getTurn() {
        return turn;
    }
    
    public Battle getCurrentBattle() {
        return currentBattle;
    }
    
    public ArrayList<Item> getPlayerInventory() {
        return playerInventory;
    }
    
    public void incrementTurn() {
        turn++;
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MINUTE, 5);
        time = cal.getTime();

        gameWindow.updateStats();
    }
    
    public void killOffCombatant() {
        Combatant combatant2 = combatantList.get(rand.nextInt(combatantList.size()));
        combatantList.remove(combatant2);
        Combatant combatant1 = combatantList.get(rand.nextInt(combatantList.size()));
        String event = EventGenerator.getEvent(combatant1, combatant2);
        gameWindow.consoleLogColor(event, Color.BLUE);
    }
    
    public void incrementTurn(int minutes) {
        turn++;
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MINUTE, minutes);
        time = cal.getTime();
        gameWindow.updateStats();
    }
    
    public void reset() {
        turn = 0;
        player = new Combatant("you", 100, 10, 10, 15, new ItemWeapon("Bare Hands", 5, EnumItemTier.TIER0, EnumAttackType.BLUDGEONING),"You","your","your");
        setGameState(EnumGameState.LOOTING);
        gameWindow.updateStats();
        location = EnumLocation.SPAWN;
        playerInventory.clear();
    }
    
    public void rest() {
        int amount = rand.nextInt(200);
        
        gameWindow.consoleLogColor("You manage to rest for "+amount+" minutes.", Colors.DARK_GREEN);
        if (amount < (combatantList.size())) {
            gameWindow.consoleLogColor("You are ambushed in your sleep!", Colors.DARK_RED);
            fight();
        } else {
            player.heal(amount);
        }
        incrementTurn(amount);
    }
    
    public int getXP() {
        return xp;
    }
    
    public String getTimeString() {
        return dateFormat.format(time);
    }
    
    public void addToInventory(Item item) {
        playerInventory.add(item);
        gameWindow.updateInventory();
    }
    
    public EnumLocation getLocation() {
        return location;
    }
    
    public void addXP(int amount) {
        xp += amount;
        int upgradeID = rand.nextInt(3);
        switch (upgradeID) {
            case 1:
                player.setSpeed(10+(xp/50));
                gameWindow.consoleLog("Your speed increased!");
                break;
            case 2:
                player.setIntelligence(10+(xp/50));
                gameWindow.consoleLog("Your intelligence increased!");
                break;
            default:
                player.setStrength(10+(xp/50));
                gameWindow.consoleLog("Your strength increased!");
                break;
        }
        
        gameWindow.updateStats();
    }
    
    public GameWindow getGameWindow() {
        return gameWindow;
    }
    
}
