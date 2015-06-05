package com.expanse.ship;

public class Frigate extends Ship {                    
    public Frigate (String name, int x, int y) {
       this.name = name;
       this.typeName = "Frigate";
       this.baseHealth = 6000;
       this.currentHealth = baseHealth;
       this.position.x = x;
       this.position.y = y;
   }
}
