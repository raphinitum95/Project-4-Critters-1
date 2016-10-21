package assignment4;

	/* An average but smart critter, Lugia is tactical when using energy.
	Meaning Lugia only runs and reproduces when they have a surplus of energy.
	They stay still when low on energy and walk otherwise. They usually are
	willing to fight only if their mood is good, which is dependent on their
	energy. */

public class Lugia extends Critter {

	@Override
	public String toString() { return "☬"; }

	private int direction;
	private boolean mood;

	public Lugia() {
		direction = Critter.getRandomInt(8);
	}

	public boolean fight(String depends) {
		if (depends.equals("@") || depends.equals("☣")){
			return (true);
		}
		run(getRandomInt(8));
		return (false);
	}

	@Override
	public void doTimeStep() {
		if (getEnergy() > 100){
			run(direction);
		}
		else if (getEnergy() < 25){
			/* Too tired to move */
		}
		else {
			walk(direction);
		}
		if (getEnergy() > Params.min_reproduce_energy * 4) {
			Lugia lugi = new Lugia();
			reproduce(lugi, Critter.getRandomInt(8));
		}
		direction = Critter.getRandomInt(8);
	}
}
