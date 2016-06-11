import oop.ex2.*;
import java.lang.Math;

/**
 * This class represents the Runner SpaceShip.
 * @author Idan Refaeli
 */
public class Runner extends SpaceShip {
	
	private final double DANGER_DISTANCE = 0.2;
	private final double DANGER_ANGLE = 0.2;
	
	/**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
	public void doAction(SpaceWars game) {
		// Get the physics object of this spaceship.
		SpaceShipPhysics physics = getPhysics();
		
		// Get the closest spaceship and its physics objects.
		SpaceShip closeShip = game.getClosestShipTo(this);
		SpaceShipPhysics closeShipPhysics = closeShip.getPhysics();
		
		// Find the distance from the closest spaceship and its angle to this spaceship.
		double distanceFromCloseShip = physics.distanceFrom(closeShipPhysics);
		double angleCloseToThis = closeShipPhysics.angleTo(physics);
		
		if (distanceFromCloseShip < DANGER_DISTANCE && Math.abs(angleCloseToThis) < DANGER_ANGLE) {
			// If the closest spaceship is in danger distance and angle to this ship, try to teleport.
			teleport();
		}
		
		// Find the angle of this spaceship to the closest, and turn AWAY from it.
		double angleToRun = physics.angleTo(closeShipPhysics);
		int turn;
		if (angleToRun < 0) {
			turn = LEFT;
		}
		else {
			turn = RIGHT;
		}
		
		physics.move(true, turn); // The Runner always accelerates.
		
		addEnergy(ENERGY_TO_ADD_EACH_MOVE);
		
	}

}
