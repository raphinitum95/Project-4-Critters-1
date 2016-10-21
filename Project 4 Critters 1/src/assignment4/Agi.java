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

	/* Fraile and pathetic critter, incapable of fighting due to them being easily intimidated
	and being paralyzed with fear. They cannot reproduce and only moves when not excessively crying.
	They only can walk or stay put and move in random directions */

public class Agi extends Critter {

	@Override
	public String toString() { return "£"; }

	private int direction;
	private int cry;

	public Agi() {
		direction = Critter.getRandomInt(8);
	}

	public boolean fight(String scared) { return false; }

	@Override
	public void doTimeStep() {
		if (cry == 1){
			cry = Critter.getRandomInt(2);
			//takes 5 minutes to sit and cry about their impending death
		}
		else{
			//cries while walking
			cry = Critter.getRandomInt(2);
			walk(direction);
		}
		direction = Critter.getRandomInt(8);
	}
}
