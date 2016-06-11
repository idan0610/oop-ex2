import oop.ex2.*;

import java.util.Random;

public class Drunkard extends SpaceShip {
	
	private final int MOVE_INTERVAL = 50;
	
	private int moveCount = 0;
	private int turn = 0;
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
		
		Random random = new Random();
		
		if (moveCount == 0) {
			// The Drunkard will decide in random choice which side to turn, and will turn in that direction
			// for 50 rounds. Only if the last 50-rounds turn is over, the Drunkard will decide on a new turn.
			turn = random.nextInt(2); // 0 means right, 1 means left
			if (turn == 0) turn--;
			moveCount = MOVE_INTERVAL; // Restart the rounds counter for the move
		}
		else {
			moveCount--;
		}
		
		physics.move(true, turn); // The drunkard always accelerate
	
		if (random.nextBoolean() && fireCount == 0) {
			// The Drunkard also decide in random choice if to fire or not.
			fire(game);
			fireCount = FIRE_PAUSE;
		}
	
		addEnergy(ENERGY_TO_ADD_EACH_MOVE);
	}

}
