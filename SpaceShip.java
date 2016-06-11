import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public abstract class SpaceShip{

	private final int INITIAL_HEALTH = 20;
	private final int MAX_ENERGY = 200;
	private final int RAISE_ENERGY_SHEILD_ON_COLLISION = 20;
	private final int REDUCE_HEALTH_SHIELD_OFF_COLLISION = 1;
	private final int REDUCE_ENERGY_SHEILD_OFF_COLLISION = 10;
	private final int HEALTH_IF_DEAD = 0;
	private final int REDUCE_HEALTH_SHIELD_OFF_SHOT = 1;
	private final int REDUCE_ENERGY_SHIELD_OFF_SHOT = 10;
	private final int FIRE_ENERGY = 20;
	private final int SHIELD_ENERGY = 3;
	private final int TELEPORT_ENERGY = 150;
	protected final int FIRE_PAUSE = 8;
	protected final int RIGHT = -1;
	protected final int LEFT = 1;
	protected final int NO_TURN = 0;
	protected final int ENERGY_TO_ADD_EACH_MOVE = 1;
	
	private SpaceShipPhysics spaceShipPhysics; // The game physics object
	private int maxEnergy; // Maximum energy the spaceship may have
	private int energy; // The current spaceship's energy
	private int health; // The current spaceship's health
	private boolean shield; // If the spaceship's shield is on or off
	
	/**
	 * This method initiates a new SpaceShip object with initial values.
	 */
	protected SpaceShip() {
		reset();
	}
	
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);

    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){
    	if (shield) {
    		// If the shield is on, increase the current and max energy ("bashing")
    		maxEnergy += RAISE_ENERGY_SHEILD_ON_COLLISION;
    		energy += RAISE_ENERGY_SHEILD_ON_COLLISION;
    	}
    	else {
    		// If the shield is off, decrease the current health and max energy
    		health -= REDUCE_HEALTH_SHIELD_OFF_COLLISION;
    		maxEnergy -= REDUCE_ENERGY_SHEILD_OFF_COLLISION;
    		if (energy > maxEnergy) energy = maxEnergy; // If the current energy is above the new max, change
    													// it to the new max
    	}
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
    	spaceShipPhysics = new SpaceShipPhysics();
		health = INITIAL_HEALTH;
		maxEnergy = MAX_ENERGY;
		energy = MAX_ENERGY;
		shield = false;
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (health == HEALTH_IF_DEAD) {
        	return true;
        }
        return false;
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return spaceShipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
    	if (!shield) {
    		// If the shield is off, decrease the current health and max energy
    		health -= REDUCE_HEALTH_SHIELD_OFF_SHOT;
    		maxEnergy -= REDUCE_ENERGY_SHIELD_OFF_SHOT;
    		if (energy > maxEnergy) energy = maxEnergy; // If the current energy is above the new max, change
														// it to the new max
    	}
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){
        if (shield) {
        	return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
       if (energy >= FIRE_ENERGY) {
    	   // Only if there is enough energy to fire the shot and the fire option is not paused
    	   game.addShot(spaceShipPhysics);
    	   energy -= FIRE_ENERGY;
       }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (energy >= SHIELD_ENERGY) {
        	// Only if there is enough energy to make the shield on
        	shield = true;
        	energy -= SHIELD_ENERGY;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
       if (energy >= TELEPORT_ENERGY) {
    	   // Only if there is enough energy to teleport
    	   spaceShipPhysics = new SpaceShipPhysics();
    	   energy -= TELEPORT_ENERGY;
       }
    }
    
    /**
     * Returns the status of the shield.
     * @return true if the shield is on, false otherwise.
     */
    public boolean getShieldStatus() {
    	return shield;
    }
    
    /**
     * Turn off the shield.
     */
    public void shieldOff() {
    	shield = false;
    }
    
    /**
     * 
     * @param energyToAdd
     */
    public void addEnergy(int energyToAdd) {
    	if (energy + energyToAdd <= maxEnergy) {
    		energy += energyToAdd;
    	}
    	else {
    		energy = maxEnergy;
    	}
    }
    
}
