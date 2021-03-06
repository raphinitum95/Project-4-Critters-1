package assignment4;
/* CRITTERS Critter.java
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private boolean moved = false; //check this functionallity later
	private static int [][] grid;
	private static String [][] world;

	// Gets the package name. This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}


	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }

	private int energy = 0;
	protected int getEnergy() { return energy; }

	private int x_coord;
	private int y_coord;

	protected int getX_coord(){ return  x_coord;}
	protected int getY_coord(){ return y_coord;}

	protected final void walk(int direction) {
		if(!moved) {
			grid[x_coord][y_coord]--;
			move(direction, 1);
		}
		this.energy = this.energy - Params.walk_energy_cost;
		moved = true;
	}

	protected final void run(int direction) {
		if(!moved) {
			grid[x_coord][y_coord]--;
			move(direction, 2);
		}
		this.energy = this.energy - Params.run_energy_cost;
		moved = true;
	}

	private void move(int direction, int steps){
		for(int i = 0; i < steps; i ++) {
			if (direction == 0) x_coord++;
			if (direction == 1) {
				x_coord++;
				y_coord--;
			}
			if (direction == 2) y_coord--;
			if (direction == 3) {
				x_coord--;
				y_coord--;
			}
			if (direction == 4) x_coord--;
			if (direction == 5) {
				x_coord--;
				y_coord++;
			}
			if (direction == 6) y_coord++;
			if (direction == 7) {
				x_coord++;
				y_coord++;
			}
		}

		x_coord = (x_coord + Params.world_width) % Params.world_width;
		y_coord = (y_coord + Params.world_height) % Params.world_height;
		grid[x_coord][y_coord]++;

	}

	protected final void reproduce(Critter offspring, int direction) {
		if (this.energy < Params.min_reproduce_energy){
			return;
		}
		offspring.energy = this.energy / 2;
		this.energy = ((this.energy / 2) + (this.energy % 2));
		offspring.x_coord = this.x_coord;
		offspring.y_coord = this.y_coord;
		offspring.move(direction, 1);
		babies.add(offspring);
	}

	private static void removeDead(){
		for(int i = 0; i < population.size(); i++){
			Critter crit = population.get(i);
			crit.energy = crit.energy - Params.rest_energy_cost;
			if(crit.energy < 1){
				population.remove(crit);
			}
			grid[crit.x_coord][crit.y_coord]--;
		}
	}

	public int [][] getGrid(){
		return grid;
	}

	public static void setGrid(){
		grid = new int[Params.world_width][Params.world_height];
	}


	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);

	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			Critter temp = (Critter) Class.forName(myPackage + "." + critter_class_name).newInstance();
			temp.energy = Params.start_energy;
			temp.x_coord = getRandomInt(Params.world_width);
			temp.y_coord = getRandomInt(Params.world_height);
			int a = Params.world_width;
			int b = Params.world_height;
			temp.grid[temp.x_coord][temp.y_coord]++;
			population.add(temp);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) {
		List<Critter> instances = new ArrayList<>();
		try {
			Critter myCrit = (Critter) Class.forName(myPackage + "." + critter_class_name).newInstance();
			for(Critter a: population){
				if(myCrit.getClass().isInstance(a)){
					instances.add(a);
				}
			}
			return (List) instances;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return instances;
	}

	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();
	}

	/* the TestCritter class allows some critters to "cheat". If you want to
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here.
	 *
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}

		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}

		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}

		protected int getX_coord() {
			return super.x_coord;
		}

		protected int getY_coord() {
			return super.y_coord;
		}


		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}

		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		babies.clear();
	}


	/**
	 * calls the doTimeStep for all of the critters that are alive
	 * then determines if the any critters need to fight
	 */
	public static void worldTimeStep() {
		removeDead();
		for(Critter a: population) {
			a.moved = false;
			a.doTimeStep();
		}
		for(int i = 0; i < population.size(); i++){
			Critter a = population.get(i);
			if(a.energy > 0) {
				for (int j = i + 1; j < population.size(); j++) {
					Critter b = population.get(j);
					if(b.energy > 0) {
						if (a.x_coord == b.x_coord && a.y_coord == b.y_coord && a != b) {
							simulateFight(a, b);
						}
					}
				}
			}
		}
		for(Critter baby: babies){
			if(!population.contains(baby))
				population.add(baby);
		}
		removeDead();


	}

	/**
	 * simulates a fight between two critters
	 * @param two Critters
	 * it rolls the dice and determines who wins and who dies
	 */
	private static void simulateFight(Critter a, Critter b){
		int a_roll = 0;
		int b_roll = 0;
		boolean first = a.fight(b.toString());
		boolean second = b.fight(a.toString());
		if (a.x_coord == b.x_coord && a.y_coord == b.y_coord) {
			if(a.energy <= 0 || b.energy <= 0){
				return;
			}
			if (first) a_roll = a.getRandomInt(a.energy);
			if (second) b_roll = b.getRandomInt(b.energy);
			if (a_roll >= b_roll) {
				a.energy = a.energy + (b.energy / 2);
				b.energy = 0;
			}
			else {
				b.energy = b.energy + (a.energy / 2);
				a.energy = 0;
			}
		}
	}

	public static void displayWorld() {
		world = new String[Params.world_width + 2][Params.world_height];
		displayRow();
		for(Critter a: population){
			world[a.x_coord + 1][a.y_coord] = a.toString();
		}
		for(int i = 0; i < Params.world_height; i++){
			world[0][i] = "|";
			world[Params.world_width + 1][i] = "|";

			for(int k = 0; k < Params.world_width + 2; k++){
				if(world[k][i] == null){
					System.out.print(" ");
				} else {
					System.out.print(world[k][i]);
				}
			}
			System.out.println();
		}
		displayRow();

	}

	/**
	 * Prints out the "+" and the "-" for the top and bottom row of the world
	 */
	private static void displayRow(){
		System.out.print("+");
		for(int i = 0; i < Params.world_width; i++) System.out.print("-");
		System.out.print("+");
		System.out.println();
	}
}
