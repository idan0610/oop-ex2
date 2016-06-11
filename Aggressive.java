import oop.ex2.*;

/**
 * This class represents the Aggressive SpaceShip.
 * @author Idan Refaeli
 */
public class Aggressive extends SpaceShip {
	
	private final double DISTANCE_TO_FIRE = 0.2;
	
	private int fireCount = 0;
	
	/**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
	public void doAction(SpaceWars game) {
		if (fireCount > 0) {
			// If there is a fire pause, reduce 1 from the counter for this round
			fireCount--;
		}
		
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
		
		physics.move(true, turn); // The Aggressive always accelerate.
		
		if (physics.distanceFrom(closeShipPhysics) < DISTANCE_TO_FIRE && fireCount == 0) {
			// If the spaceship is on the required distance to fire, attempt.
			fire(game);
			fireCount = FIRE_PAUSE;
		}
		
		addEnergy(ENERGY_TO_ADD_EACH_MOVE);
	}

}
