package com.expanse.ship;

public class Cruiser extends Ship {                    
    public Cruiser (String name, int x, int y) {
       this.name = name;
       typeName = "Cruiser";
       baseHealth = 18000;
       currentHealth = baseHealth;
       this.position.x = x;
       this.position.y = y;
   }
}
