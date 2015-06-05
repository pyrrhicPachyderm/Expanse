package com.expanse.fleetCombat;

import com.expanse.fleet.Fleet;
import com.expanse.module.weapon.WeaponGroup;
import com.expanse.ship.Position;
import com.expanse.ship.Ship;

public class FleetCombat {
	
	public static void printShipDamage(Ship attacker, Ship defender, int dmg){//Placeholder output
        System.out.print(attacker.getNameAndType() + " dealt " + dmg + " damage to " + defender.getNameAndType());
        if(defender.getHealth() == 0){System.out.println(", destroying it.");}
        else{System.out.println(", leaving it on " + defender.getHealth() + " health.");}
    }
	public static void printGroupDamage(Ship attacker, Ship defender, int destroyed){//Placeholder output
        System.out.print(attacker.getNameAndType() + " destroyed ");
        if(defender.count == 0){System.out.println("all of the " + defender.typeName + "s in the squadron " + defender.getNameAndType());}
        else{System.out.println(destroyed + " of the " + defender.typeName + "s in the squadron " + defender.getNameAndType());}
    }
	
	public static void removeShip(){
		//TODO functionality
	}
	
	public static void beginCombat(Fleet attackingFleet, Fleet defendingFleet){
		int turn = 1;
		while(true){
			System.out.println("Turn " + turn + ":");			
			System.out.println("Player 1:"); 
			takeCombatTurn(attackingFleet, defendingFleet);
			if(defendingFleet.ships.size() == 0){
				System.out.println("Player 1 wins");//placeholder
				break;
			}
			
			System.out.println("Player 2:");
			takeCombatTurn(defendingFleet, attackingFleet);
			if(attackingFleet.ships.size() == 0){
				System.out.println("Player 2 wins");//placeholder
				break;
			}
			turn ++;
		}	
	}
	
	public static void takeCombatTurn(Fleet attackFleet, Fleet targetFleet){
		int targetIndex;
		Ship targetShip = new Ship();
		Ship currentShip = new Ship();
		for(int i = 0; i < attackFleet.ships.size(); i++){//Iterating through ships in fleet
			currentShip = attackFleet.ships.get(i);
			targetIndex = (int)(Math.random() * targetFleet.ships.size());
			targetShip = targetFleet.ships.get(targetIndex);//placeholder target selection
			if(targetShip.isSquad){
				fireAtGroup(currentShip, targetShip);
				if(targetShip.count <= 0){//Ships are kill
					targetFleet.destroyShip(targetIndex);			
				}
			}else{
				fireAtShip(currentShip, targetShip);
				if(targetShip.getHealth() <= 0){//Ship is kill
					targetFleet.destroyShip(targetIndex);
				}
			}	
			if(targetFleet.ships.size() == 0){
				break;
			}
		}
	}
	
	public static void fireAtShip(Ship attacker, Ship defender){
		int cumulativeDmg = 0;//Total damage from attacker to defender
		WeaponGroup weaponGroup = new WeaponGroup();
		for(int i = 0; i < attacker.count; i++){
	    	for(int j = 0; j < attacker.weapons.size(); j++){//Iterating through weaponGroups in ship
	        	weaponGroup = attacker.weapons.get(j);
	        	int dmg = weaponGroup.getDamage();
	        	for(int k = 0; k < weaponGroup.count; k++){//Iterating over weapons in group					
	        		if(isHit(attacker.getPosition(), defender.getPosition())){//TODO Position based hit chance
	        			cumulativeDmg += dmg;
					}
				}
	    	}
		}
    	defender.decrHealth(cumulativeDmg);
		printShipDamage(attacker, defender, cumulativeDmg);		
	}
	
	public static void fireAtGroup(Ship attacker, Ship defenders){
		WeaponGroup weaponGroup = new WeaponGroup();
		int destroyed = 0;//Number of fighters etc killed so far
		for(int i = 0; i < attacker.count; i++){
			for(int j = 0; j < attacker.weapons.size(); j++){//Iterating through weaponGroups in ship
	        	weaponGroup = attacker.weapons.get(j);
	        	int dmg = weaponGroup.getDamage();
	        	for(int k = 0; k < weaponGroup.count; k++){//Iterating over weapons in group					
	        		if(isHit(attacker.getPosition(), defenders.getPosition())){//TODO Position based hit chance
	        			if(defenders.damageSubship(dmg)){
	        				destroyed ++;        				
	        			}
					}
				}
	    	}
		}
		printGroupDamage(attacker, defenders, destroyed);
	}
	
	public static boolean isHit(Position attPos, Position defPos){
		double dist = attPos.getDistTo(defPos);
		double hitChance = 100 - (Math.pow(dist, 2)/3.3);
		if(hitChance < 0){
			hitChance = 0;
		}
		if(Math.random() * 100 <= hitChance){
			return true;
		}else{
			return false;
		}	
	}	
}