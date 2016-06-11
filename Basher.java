import oop.ex2.*;

/**
 * This class represents the Basher SpaceShip.
 * @author Idan Refaeli
 */
public class Basher extends SpaceShip {
	
	private final double DISTANCE_TO_SHIELD_ON = 0.2;
	
	/**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
	public void doAction(SpaceWars game) {
		shieldOff(); // Assume the shield is off unless otherwise.
		
		SpaceShipPhysics physics = getPhysics();
		
		SpaceShip closeSpaceShip = game.getClosestShipTo(this);
		SpaceShipPhysics closeShipPhysics = closeSpaceShip.getPhysics();
		
		int turn;
		
		// Check if the spaceship should torn left or right, according its angle to its closest spaceship.
		if (physics.angleTo(closeShipPhysics) < 0) {
			turn = RIGHT;
		}
		else {
			turn = LEFT;
		}
		
		physics.move(true, turn); // The Basher always accelerate.
		
		if (physics.distanceFrom(closeShipPhysics) < DISTANCE_TO_SHIELD_ON) {
			// If the spaceship is on the required distance to turn the shield on, attempt.
			shieldOn();
		}
		
		addEnergy(ENERGY_TO_ADD_EACH_MOVE);
	}

}
