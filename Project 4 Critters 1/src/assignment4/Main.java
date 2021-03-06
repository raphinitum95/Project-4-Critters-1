/* CRITTERS Main.java
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
package assignment4; // cannot be in default package
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console
    private static String input;


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name,
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */

        Critter.setGrid();

        System.out.print("critters> ");

        int number = 0;
        input = kb.nextLine();
        String [] split = input.split("\\s+");

        while(true){

            if(split[0].equals("quit")) break;

            else if(split[0].equals("show")) {
                if(split.length == 1){
                    Critter.displayWorld();
                }
                else{
                    System.out.println("error processing: " + input);
                }
            }

            else if(split[0].equals("step")){
                if(split.length == 2){
                    try {
                        number = Integer.parseInt(split[1]);
                        while(number != 0) {
                            Critter.worldTimeStep();
                            number--;
                        }
                    } catch (NumberFormatException e){
                        System.out.println("error processing: " + input);
                    }
                } else if(split.length > 2){
                    System.out.println("invalid command: " + input);
                } else{
                    Critter.worldTimeStep();
                }
            }

            else if(split[0].equals("seed")){
                if(split.length == 2){
                    try{
                        Critter.setSeed(Integer.parseInt(split[1]));
                    } catch(NumberFormatException e){
                        System.out.println("error processing: " + input);
                    }
                } else{
                    System.out.println("error processing: " + input);
                }
            }

            else if(split[0].equals("make")){
                if(split.length == 2){
                    try {
                        Critter.makeCritter(split[1]);
                    } catch (InvalidCritterException e) {
                        e.printStackTrace();
                    }
                }
                else if(split.length == 3){
                    try{
                        number = Integer.parseInt(split[2]);
                        for(int i = 0; i < number; i++){
                            Critter.makeCritter(split[1]);
                        }
                    } catch (NumberFormatException e){
                        System.out.println("error processing: " + input);
                    } catch (InvalidCritterException e) {
                        e.printStackTrace();
                    }
                } else{
                    System.out.println("error processing: " + input);
                }

            }

            else if(split[0].equals("stats")) {
                if(split.length == 2){
                    Object crit = null;
                    List<Critter> a = null;
                    try {
                        a = Critter.getInstances(split[1]);
                        crit = Class.forName(myPackage + "." + split[1]).newInstance();
                        Class[] cArg = new Class[1];
                        cArg[0] = List.class;
                        Method mthd = crit.getClass().getDeclaredMethod("runStats", cArg);
                        mthd.invoke(crit, a);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        Critter temp = (Critter) crit;
                        temp.runStats(a);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("error processing: " + input);
                }
            } else{
                System.out.println("invalid command: " + input);
            }




            System.out.print("critters> ");
            input = kb.nextLine();
            split = input.split("\\s+");

        }

        /* Write your code above */
        System.out.flush();

    }

}
