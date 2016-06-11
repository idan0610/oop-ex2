import java.awt.Image;
import oop.ex2.*;

/**
 * This class represents the Human SpaceShip.
 * @author Idan Refaeli
 */
public class Human extends SpaceShip {
	
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
		
		shieldOff(); // Assume the shield is off unless the human chose otherwise.
		GameGUI gameGUI = game.getGUI(); // Used to determine which button the human pressed.
		SpaceShipPhysics physics = getPhysics();
		
		if (gameGUI.isTeleportPressed()) {
			// Human chose to teleport.
			teleport();
		}
		
		boolean accel = false;
		int turn = NO_TURN;
		
		if (gameGUI.isUpPressed()) {
			// Human pressed to accelerate.
			accel = true;
		}
		
		if (gameGUI.isLeftPressed()) {
			// Human pressed to turn left.
			turn = LEFT;
		}
		else if (gameGUI.isRightPressed()) {
			// Human pressed to turn right.
			turn = RIGHT;
		}
		
		physics.move(accel, turn);
		
		if (gameGUI.isShieldsPressed()) {
			// Human pressed to turn the shield on.
			shieldOn();
		}
		
		if (gameGUI.isShotPressed() && fireCount == 0) {
			// Human pressed to fire a shot.
			fire(game);
			fireCount = FIRE_PAUSE;
		}
		
		addEnergy(ENERGY_TO_ADD_EACH_MOVE);
	}
	
	/**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
	public Image getImage() {
		if (getShieldStatus()) {
			return GameGUI.SPACESHIP_IMAGE_SHIELD;
		}
		return GameGUI.SPACESHIP_IMAGE;
	}

}
