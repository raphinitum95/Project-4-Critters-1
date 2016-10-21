package assignment4;

	/* The Kraken is a powerful and cunning predator. The only move when attempting to pounce on prey
	and are willing to both walk or run depending on the prey's distance. They fear no Critter and will
	attack anything they see. Although the Kraken does reproduce, it's not common because it will likely
	just eat it's child. */

public class Kraken extends Critter {

	@Override
	public String toString() { return "§"; }

	private int direction;
	private boolean lingering;
	private boolean far;

	public Kraken() {
		lingering = false;
		Pounce();
	}

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

	public void Pounce (){
		int[][] grid = getGrid();
		int x = getX_coord();
		int y = getY_coord();
		lingering = false;
		if(grid[x + 1][y] > 0) {
			direction = 0;
			far = false;
		}
		if(grid[x + 1][y - 1] > 0) {
			direction = 1;
			far = false;
		}
		if(grid[x][y - 1] > 0) {
			direction = 2;
			far = false;
		}
		if(grid[x - 1][y - 1] > 0) {
			direction = 3;
			far = false;
		}
		if(grid[x - 1][y] > 0) {
			direction = 4;
			far = false;
		}
		if(grid[x - 1][y + 1] > 0) {
			direction = 5;
			far = false;
		}
		if(grid[x][y + 1] > 0) {
			direction = 6;
			far = false;
		}
		if(grid[x + 1][y + 1] > 0) {
			direction = 7;
			far = false;
		}

		//deciding to run
		if(grid[x + 2][y] > 0) {
			direction = 0;
			far = true;
		}
		if(grid[x + 2][y - 2] > 0) {
			direction = 1;
			far = true;
		}
		if(grid[x][y - 2] > 0) {
			direction = 2;
			far = true;
		}
		if(grid[x - 2][y - 2] > 0) {
			direction = 3;
			far = true;
		}
		if(grid[x - 2][y] > 0) {
			direction = 4;
			far = true;
		}
		if(grid[x - 2][y + 2] > 0) {
			direction = 5;
			far = true;
		}
		if(grid[x][y + 2] > 0) {
			direction = 6;
			far = true;
		}
		if(grid[x + 2][y + 2] > 0) {
			direction = 7;
			far = true;
		}
		else {
			lingering = true;
		}
	}

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
