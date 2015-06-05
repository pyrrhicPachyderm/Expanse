package com.expanse.ship;

public class Destroyer extends Ship {                    
    public Destroyer(String name, int x, int y){
       this.name = name;
       this.typeName = "Destroyer";
       this.baseHealth = 12000;
       this.currentHealth = baseHealth;
       this.position.x = x;
       this.position.y = y;
   }
}
