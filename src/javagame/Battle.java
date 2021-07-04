/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import javagame.display.Colors;
import javagame.entities.Combatant;

/**
 *
 * @author julia
 */
public class Battle {
    
    Random rand = new Random();
    Combatant combatant1;
    Combatant combatant2;
    GameWindow gameWindow;
    EnumGameState previousState;
    boolean combatant1Turn = true;
    boolean stillBlocked = false;
    ArrayList<String> battleMessagesUniversal = new ArrayList<>();
    ArrayList<String> battleMessagesYourTurn = new ArrayList<>();
    ArrayList<String> battleMessagesEnemyTurn = new ArrayList<>();
    int combatantNumber;
    
    
    public Battle(Combatant combatant1, Combatant combatant2, GameWindow gameWindow, EnumGameState previousState, int combatantNumber) {
        battleMessagesUniversal.add("Blood splatters everywhere!");
        battleMessagesUniversal.add("You tangle together and fall over!");
        battleMessagesUniversal.add("Blood sprays from cmb2!");
        
        battleMessagesEnemyTurn.add("You are suprised by the ferocity of cmb1's onslaught!");
        battleMessagesEnemyTurn.add("You leap back!");
        battleMessagesEnemyTurn.add("You become enraged!");
        battleMessagesEnemyTurn.add("You become scared!");
        battleMessagesEnemyTurn.add("You begin to bleed!");
        battleMessagesEnemyTurn.add("You cry out in pain!");
        
        battleMessagesYourTurn.add("cmb2 is suprised by the ferocity of your onslaught!");
        battleMessagesYourTurn.add("cmb2 leaps back!");
        battleMessagesYourTurn.add("cmb2 looks angry!");
        battleMessagesYourTurn.add("cmb2 looks scared!");
        battleMessagesYourTurn.add("cmb2 begins to bleed!");
        battleMessagesYourTurn.add("cmb2 cries out in pain!");
        
        battleMessagesYourTurn.addAll(battleMessagesUniversal);
        battleMessagesEnemyTurn.addAll(battleMessagesUniversal);
        this.combatant1 = combatant1;
        this.combatant2 = combatant2;
        this.gameWindow = gameWindow;
        this.previousState = previousState;
        this.combatantNumber = combatantNumber;
    }
    
    private void takeTurn(Combatant combatant1, Combatant combatant2) {
        String s = "s";
        Color color = Colors.DARK_RED;
        if (combatant1.getPronoun1()=="You") {
            s = "";
            color = Color.RED;
        }
        if (rand.nextInt(9)!=0) {
            EnumBodyPart bodyPart = EnumBodyPart.values()[rand.nextInt(EnumBodyPart.values().length)];
            int damageSustained = combatant2.takeDamage(combatant1.getDamage(), bodyPart);
            gameWindow.consoleLogColor(combatant1.getPronoun1() + " "+combatant1.getWeapon().getVerb()+s+" " + combatant2.getName()
            +" with "+combatant1.getPronoun2()+ " "+ combatant1.getWeapon().getName() +
            " in " +combatant2.getPronoun2()+" "+ bodyPart.name+
            " for " + damageSustained+" damage.", color);
            if (rand.nextInt(3)==0) {
                String battleMessage;
                if (s == "") {
                    battleMessage = battleMessagesYourTurn.get(rand.nextInt(battleMessagesYourTurn.size()));
                } else {
                    battleMessage = battleMessagesEnemyTurn.get(rand.nextInt(battleMessagesEnemyTurn.size()));
                }
                battleMessage = battleMessage.replace("cmb1", combatant1.getName());
                battleMessage = battleMessage.replace("cmb2", combatant2.getName());
                gameWindow.consoleLog(battleMessage);
            }
        } else {
            if (s == "") {
                gameWindow.consoleLog("You miss your attack!");
            } else {
                gameWindow.consoleLog(combatant1.getName()+" misses "+combatant1.getPronoun2()+" attack!");
            }
        }
    }
    
    
    
    public void advanceBattle(EnumBattleOption battleOption) {
        boolean failedFlee = false;
        if (battleOption == EnumBattleOption.FLEE) {
            if (rand.nextInt(6) <= 3) {
                gameWindow.consoleLogColor("You managed to flee safely.", Colors.DARK_GREEN);
                gameWindow.javaGame.setGameState(previousState);
                stillBlocked = true;
            } else {
                gameWindow.consoleLogColor("You tried to flee, but you couldn't escape.", Colors.DARK_RED);
                gameWindow.consoleLogColor(combatant2.getName()+" executes you with "+combatant2.getPronoun2()+" "
                        + combatant2.getWeapon().getName()+
                        ", killing you instantly.", Colors.DARK_RED);
                combatant1.kill();
                failedFlee = true;
            }
        }
        boolean successfulBlock = false;
        if (battleOption == EnumBattleOption.ATTACK) {
            takeTurn(combatant1, combatant2);
        } else if (battleOption == EnumBattleOption.BLOCK) {
            int blockTotal = combatant1.getSpeed();
            if (rand.nextInt(blockTotal) > 4) {
                successfulBlock = true;
            }
            if (successfulBlock) {
                gameWindow.consoleLog("You successfully blocked the oncoming attack!");
                stillBlocked = true;
            } else {
                gameWindow.consoleLog("You failed to block the oncoming attack.");
            }
        }
        if (!successfulBlock && !stillBlocked && !failedFlee) {
            takeTurn(combatant2, combatant1);
//            EnumBodyPart bodyPart = EnumBodyPart.values()[rand.nextInt(EnumBodyPart.values().length)];
//            int damageSustained = combatant1.takeDamage(combatant2.getDamage(), bodyPart);
//            gameWindow.consoleLog("The " + combatant2.getName()
//            +" attacks you with its " + combatant2.getWeapon().getName() +
//            " in the " + bodyPart.name+
//            " for " + damageSustained+" damage.");
        }
        if (battleOption != EnumBattleOption.BLOCK && successfulBlock == false) {
            stillBlocked = false;
        }
            
        // check to see if anyone died
        if (combatant1.getHealth() <= 0 && !failedFlee) {
            //gameWindow.clearConsole();
            gameWindow.consoleLog("You died :(");
            //gameWindow.javaGame.reset();
        } else if (combatant2.getHealth() <= 0) {
            gameWindow.consoleLog(combatant2.getName()+" was defeated!");
            gameWindow.consoleLog("You recieved 50 XP!");
            gameWindow.javaGame.addXP(50);
            gameWindow.javaGame.setGameState(previousState);
            gameWindow.javaGame.combatantList.remove(combatantNumber);
            
        }
        gameWindow.updateStats();
    }
    
    public Combatant getEnemyCombatant() {
        return combatant2;
    }
    
}
