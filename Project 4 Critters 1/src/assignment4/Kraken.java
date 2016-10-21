package assignment4;
/* CRITTERS Kraken.java
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

	/* The Kraken is a patient and deadly. They only move when attempting to pounce on prey
	and are willing to both walk or run depending on the prey's distance. They fear no Critter and will
	attack anything they see. Although the Kraken does reproduce, it's not common because it will likely
	just eat it's child. */

public class Kraken extends Critter {
	/**
	 * @return a character/string that represents Kraken on the world map
	 */
	@Override
	public String toString() { return "§"; }

	private int direction;
	private boolean lingering;
	private boolean far;
	/**
	 * The constructor initializes the flag for whether Kraken is following someone, and also initializes
	 * the direction and movement of Kraken
	 */
	public Kraken() {
		lingering = false;
		Pounce();
	}
	/**
	 * @param for this version of fight the string will not be used
	 * @return true if the Kraken ever encounter Algae, and also true for all
	 * critters unless the Kraken is low on energy.
	 */

	public boolean fight(String killer) {
		if (killer.equals("@")){
			return (true);
		}
		if (getEnergy() < 20){
			walk(getRandomInt(8));
			return (false);
		}
		else{
			return true;
		}
	}

	/**
	 * implement search and chase mechanic
	 */

	public void Pounce (){
		int[][] grid = getGrid();
		int x = getX_coord() + Params.world_width;
		int y = getY_coord() + Params.world_height;
		lingering = false;
		if(grid[(x + 1) % Params.world_width][y % Params.world_height] > 0) {
			direction = 0;
			far = false;
		}
		if(grid[(x + 1) % Params.world_width][(y + 1) % Params.world_height] > 0) {
			direction = 1;
			far = false;
		}
		if(grid[x % Params.world_width][(y - 1) % Params.world_height] > 0) {
			direction = 2;
			far = false;
		}
		if(grid[(x - 1) % Params.world_width][(y - 1) % Params.world_height] > 0) {
			direction = 3;
			far = false;
		}
		if(grid[(x - 1) % Params.world_width][y % Params.world_height] > 0) {
			direction = 4;
			far = false;
		}
		if(grid[(x - 1) % Params.world_width][(y + 1) % Params.world_height] > 0) {
			direction = 5;
			far = false;
		}
		if(grid[x % Params.world_width][(y + 1) % Params.world_height] > 0) {
			direction = 6;
			far = false;
		}
		if(grid[(x + 1) % Params.world_width][(y + 1) % Params.world_height] > 0) {
			direction = 7;
			far = false;
		}

		//deciding to run
		if(grid[(x + 2) % Params.world_width][y % Params.world_height] > 0) {
			direction = 0;
			far = true;
		}
		if(grid[(x + 2) % Params.world_width][(y - 2) %  Params.world_height] > 0) {
			direction = 1;
			far = true;
		}
		if(grid[x % Params.world_width][(y - 2) %  Params.world_height] > 0) {
			direction = 2;
			far = true;
		}
		if(grid[(x - 2) % Params.world_width][(y - 2) %  Params.world_height] > 0) {
			direction = 3;
			far = true;
		}
		if(grid[(x - 2) % Params.world_width][y % Params.world_height] > 0) {
			direction = 4;
			far = true;
		}
		if(grid[(x - 2) % Params.world_width][(y + 2) % Params.world_height] > 0) {
			direction = 5;
			far = true;
		}
		if(grid[x % Params.world_width][(y + 2) % Params.world_height] > 0) {
			direction = 6;
			far = true;
		}
		if(grid[(x + 2) % Params.world_width][(y + 2) % Params.world_height] > 0) {
			direction = 7;
			far = true;
		}
		else {
			lingering = true;
		}
	}

	/**
	 * detemine the movement and direction of Kraken's movements
	 * also set requirements for children
	 */

	@Override
	public void doTimeStep(){
		if (lingering){
			//wait for a pour soul to approach
		}
		else{
			if (far){
				run(direction);
			}
			else{
				walk(direction);
			}
		}

		if (getEnergy() > 200) {
			reproduce(new Kraken(), Critter.getRandomInt(8));
		}
		Pounce();
	}
}
