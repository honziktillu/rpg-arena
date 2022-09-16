package com.rpg;

import com.rpg.entities.Entity;
import com.rpg.game.Arena;

public class Main {

    public static void main(String[] args) {
        Entity player, enemy;
        player = new Entity("Martin", 100, 10);
        enemy = new Entity("Zdenka", 1000, 1000);
        Arena arena = new Arena("Skola");
        arena.fight(player, enemy);
        arena = new Arena("Kiosek");
        player.setHp(100);
        player.setDmg(10);
        enemy.setHp(1000);
        enemy.setDmg(1000);
        arena.fight(enemy, player);
        System.out.println("xxxDDDxxxDDD");
    }
}
