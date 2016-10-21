package assignment4;
import util.lang.math;

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

	public void follow (void){
		for(int i = 0; i < population.size(); i++){
			Critter a = population.get(i);
			if ((Math.abs(a.x_coord - this.x_coord) == 1) || (Math.abs(a.y_coord - this.y_coord) == 1)) {
				if ((a.x_coord - this.x_coord) == 1) && (a.y_coord - this.y_coord) == 0)){
					direction = 0;
				}
				else if ((a.x_coord - this.x_coord) == 1) && (a.y_coord - this.y_coord) == 1)){
					direction = 1;
				}
				else if ((a.x_coord - this.x_coord) == 0) && (a.y_coord - this.y_coord) == 1)){
					direction = 2;
				}
				else if ((a.x_coord - this.x_coord) == -1) && (a.y_coord - this.y_coord) == 1)){
					direction = 3;
				}
				else if ((a.x_coord - this.x_coord) == -1) && (a.y_coord - this.y_coord) == 0)){
					direction = 4;
				}
				else if ((a.x_coord - this.x_coord) == -1) && (a.y_coord - this.y_coord) == -1)){
					direction = 5;
				}
				else if ((a.x_coord - this.x_coord) == 0) && (a.y_coord - this.y_coord) == -1)){
					direction = 6;
				}
				else{
					direction = 7;
				}
				creep = true;
			}
			else {
				direction = Critter.getRandomInt(8);
				creep = false;
			}
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
