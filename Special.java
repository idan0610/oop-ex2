import oop.ex2.SpaceShipPhysics;


public class Special extends SpaceShip {
	
	private final double DISTANCE_TO_FIRE = 0.4;
	private final double DISTANCE_TO_STOP_FIRE = 0.2;
	private final double DISTANCE_TO_SHIELD_ON = 0.1;
	private final double DISTANCE_TO_TELEPORT = 0.2;
	
	private int fireCount = 0;

	/**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
	public void doAction(SpaceWars game) {
		shieldOff();
		
		if (fireCount > 0) {
			// If there is a fire pause, reduce 1 from the counter for this round
			fireCount--;
		}
		
		SpaceShipPhysics physics = getPhysics();
		
		SpaceShip closeSpaceShip = game.getClosestShipTo(this);
		SpaceShipPhysics closeShipPhysics = closeSpaceShip.getPhysics();
		
		if (physics.distanceFrom(closeShipPhysics) < DISTANCE_TO_TELEPORT) {
			// If the spaceship is on the required distance to teleport, attempt.
			teleport();
		}
		
		int turn;
		
		// Check if the spaceship should torn left or right, according its angle to its closest spaceship.
		if (physics.angleTo(closeShipPhysics) < 0) {
			turn = RIGHT;
		}
		else {
			turn = LEFT;
		}
		
		physics.move(true, turn); // The Aggressive always accelerate.
		
		if (physics.distanceFrom(closeShipPhysics) < DISTANCE_TO_SHIELD_ON) {
			// If the spaceship is on the required distance to turn the shield on, attempt.
			shieldOn();
		}
		
		if (physics.distanceFrom(closeShipPhysics) > DISTANCE_TO_STOP_FIRE && 
				physics.distanceFrom(closeShipPhysics) < DISTANCE_TO_FIRE && fireCount == 0) {
			// If the spaceship is on the required distance to fire, attempt.
			fire(game);
			fireCount = FIRE_PAUSE;
		}
		
		addEnergy(ENERGY_TO_ADD_EACH_MOVE);
	}
	
}
