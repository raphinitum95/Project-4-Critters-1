package assignment4;
/* CRITTERS Cthulu.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Raphael De Los Santos>
 * <rd23353>
 * <16480>
 * <JohnnyAngel Rojas>
 * <jr52483>
 * <16445>
 * Slip days used: <1>
 * Fall 2016
 */

	/* Possibly the most dangerous critter, they are predators constantly on the search for a meal.
	They are always on the move and can either run when searching, or walk when following prey in their
	immediate vicinity. They rarely reproduce unless they have a massive amount of energy. */

public class Cthulu extends Critter {
	/**
	 * @return a character/string that represents Cthulu on the world map
	 */
	@Override
	public String toString() { return "☣"; }

	private int direction;
	private boolean creep;

	/**
	 * The constructor initializes the flag for whether Cthulu is following someone, and also initializes
	 * the direction and movement of Cthulu
	 */
	public Cthulu() {
		creep = false;
		follow();
	}

	/**
	 * @param for this version of fight the string will not be used
	 * @return true because Cthulu seeks utter chaos and thrives on killing
	 */
	public boolean fight(String ruthless) { return true; }
	/**
	 * implement search and chase mechanic
	 */
	public void follow (){
		int [][] grid = getGrid();
		int x = getX_coord() + Params.world_width;
		int y = getY_coord() + Params.world_height;
		creep = true;
		if(grid[(x + 1) % Params.world_width][y % Params.world_height] > 0) {
			direction = 0;
		}
		else if(grid[(x + 1) % Params.world_width][(y + 1) % Params.world_height] > 0) {
			direction = 1;
		}
		else if(grid[x % Params.world_width][(y - 1) % Params.world_height] > 0) {
			direction = 2;
		}
		else if(grid[(x - 1) % Params.world_width][(y - 1) % Params.world_height] > 0) {
			direction = 3;
		}
		else if(grid[(x - 1) % Params.world_width][y % Params.world_height] > 0) {
			direction = 4;
		}
		else if(grid[(x - 1) % Params.world_width][(y + 1) % Params.world_height] > 0) {
			direction = 5;
		}
		else if(grid[x % Params.world_width][(y + 1) % Params.world_height] > 0) {
			direction = 6;
		}
		else if(grid[(x + 1) % Params.world_width][(y + 1) % Params.world_height] > 0) {
			direction = 7;
		}
		else {
			direction = Critter.getRandomInt(8);
			creep = false;
		}
	}

	/**
	 * detemine the movement and direction of Cthulu's movements
	 * also set requirements for children
	 */
	@Override
	public void doTimeStep() {
		if (creep == true){
			walk(direction);
		}
		else{
			run(direction);
		}
		/* Laughs Maniacally */

		if (getEnergy() > 666) {
			Cthulu cthuly = new Cthulu();
			reproduce(cthuly, Critter.getRandomInt(8));
		}
		follow();
	}
}
