package com.rpg.game;

import com.rpg.entities.Entity;
import com.rpg.game.utils.IOUtil;

public class Arena {

    private String name;
    private boolean isRunning = true;

    public Arena(String name) {
        this.name = name;
    }

    public void fight(Entity player, Entity enemy) {
        System.out.println("Nachazite se v arene " + name);
        while(isRunning) {
            printEntities(player, enemy);
            round(player, enemy);
        }
    }

    private void printEntities(Entity player, Entity enemy) {
        IOUtil.printText(player.toString());
        IOUtil.printText(enemy.toString());
    }

    private void round(Entity player, Entity enemy) {
        System.out.println("1. Utok");
        System.out.println("2. Obrana");
        System.out.print("Zadejte cislo: ");
        int i;
        try {
            i = Integer.parseInt(IOUtil.getInput());
            switch (i) {
                case 1 -> {
                    IOUtil.printText(player.getName() + " zautocil");
                    double d = Math.random();
                    if (d < 0.5) {
                        //Enemy utoci
                        IOUtil.printText(enemy.getName() + " zautocil");
                        enemy.setHp(Math.floor(enemy.getHp() - player.getDmg()));
                        if (isEnemyDead(player, enemy)) {
                            return;
                        }
                        player.setHp(Math.floor(player.getHp() - enemy.getDmg()));
                        if(isPlayerDead(player)) {
                            return;
                        }
                        return;
                    }
                    //Enemy se brani
                    IOUtil.printText(enemy.getName() + " se brani");
                    enemy.setHp(Math.floor(enemy.getHp() - (player.getDmg() / 3)));
                    isEnemyDead(player, enemy);
                }
                case 2 -> {
                    IOUtil.printText(player.getName() + " se brani");
                    double d = Math.random();
                    if (d < 0.5) {
                        // Enemy utoci
                        IOUtil.printText(enemy.getName() + " zautocil");
                        player.setHp(Math.floor(player.getHp() - (enemy.getDmg() / 3)));
                        if(isPlayerDead(player)) {
                            return;
                        }
                        return;
                    }
                    // Enemy se brani
                    IOUtil.printText(enemy.getName() + " se brani");
                    IOUtil.printText("Nic se nestalo :/");
                }
                default -> round(player, enemy);
            }
        } catch (Exception e) {
            round(player, enemy);
        }

    }

    private boolean isPlayerDead(Entity player) {
        if (player.getHp() <= 0) {
            isRunning = false;
            IOUtil.printText("Nepritel te porazil");
            return true;
        }
        return false;
    }

    private boolean isEnemyDead(Entity player, Entity enemy) {
        if (enemy.getHp() <= 0) {
            isRunning = false;
            IOUtil.printText(player.getName() + " vyhrava");
            return true;
        }
        return false;
    }

}
