package assignment4;
/* CRITTERS Lugia.java
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

	/* An average but smart critter, Lugia is tactical when using energy.
	Meaning Lugia only runs and reproduces when they have a surplus of energy.
	They stay still when low on energy and walk otherwise. They usually are
	willing to fight only if their mood is good, which is dependent on their
	energy. */

public class Lugia extends Critter {
	/**
	 * @return a character/string that represents Lugia on the world map
	 */
	@Override
	public String toString() { return "☬"; }

	private int direction;
	private boolean mood;
	/**
	 * The constructor initializes the direction and movement of Cthulu
	 */
	public Lugia() {
		direction = Critter.getRandomInt(8);
	}
	/**
	 * @param for this version of fight the string will not be used
	 * @return true if only Lugia encounters Algae or Cthulu, otherwise false
	 */
	public boolean fight(String depends) {
		if (depends.equals("@") || depends.equals("☣")){
			return (true);
		}
		return (false);
	}

	/**
	 * detemine the movement and direction of Lugia's movements
	 * also set requirements for children
	 */
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
