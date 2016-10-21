package assignment4;
/* CRITTERS <MyClass.java>
 * EE422C Project 4 submission by Oct. 20, 2016
 * Raphael De Los Santos
 * rd23353
 * JohnnyAngel Rojas
 * jr52483
 * Slip days used: <0>
 * Fall 2016
 */

	/* Possibly the most dangerous critter, they are predators constantly on the search for a meal.
	They are always on the move and can either run when searching, or walk when following prey in their
	immediate vicinity. They rarely reproduce unless they have a massive amount of energy. */

public class Cthulu extends Critter {

	@Override
	public String toString() { return "☣"; }

	private int direction;
	private boolean creep;

	public Cthulu() {
		creep = false;
		follow();
	}

	public boolean fight(String ruthless) { return true; }

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
