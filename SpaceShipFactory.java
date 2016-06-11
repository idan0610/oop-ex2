// import oop.ex2.*;

/**
 * This class contains one static method that creates the spaceship objects requested by the command
 * arguments.
 * @author Idan Refaeli
 */
public class SpaceShipFactory {
	
	private final static String HUMAN = "h";
	private final static String RUNNER = "r";
	private final static String BASHER = "b";
	private final static String AGGRESSIVE = "a";
	private final static String DRUNKARD = "d";
	private final static String SPECIAL = "s";
	
	/**
	 * This static method returns an array of SpaceShip objects, each Spaceship is actually an instance of a
	 * class inheriting from the abstract SpaceShip class, and depend on the arguments given on command line.
	 * @param args Array of string arguments given on command line.
	 * @return Array of SpaceShip objects that will take part in the game.
	 */
    public static SpaceShip[] createSpaceShips(String[] args) {
    	
    	SpaceShip[] spaceShips = new SpaceShip[args.length];
    	int index = 0; // The index where each new SpaceShip object will be located on array. Start with 0
    				   // grows by 1 after each SpaceShip is added.
    	
        for (String arg : args){
        	// For each argument, check the requested SpaceShip type and add to spaceShips array the right
        	// type.
        	switch (arg) {
        		case HUMAN:
        			spaceShips[index] = new Human();
        			break;
        		
        		case RUNNER:
        			spaceShips[index] = new Runner();
        			break;
        			
        		case BASHER:
        			spaceShips[index] = new Basher();
        			break;
        			
        		case AGGRESSIVE:
        			spaceShips[index] = new Aggressive();
        			break;
        			
        		case DRUNKARD:
        			spaceShips[index] = new Drunkard();
        			break;
        			
        		case SPECIAL:
        			spaceShips[index] = new Special();
        			break;
        	}
        	
        	index++;
        }
        
        return spaceShips;
    }
}
