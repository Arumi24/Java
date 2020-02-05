//Aymen Rumi
//260661663

//imported classes
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class GuessTheString {
	
	static String used = "";

	// main method with playGame method
	public static void main(String[] args) {
		playGame();
	}

	// method that takes 2 inputs, length and seed and generates a random string of
	// int length,
	public static String createString(int length, int seed) {
		
		//define range of strings u want to be used
		String charRange = "abcdefghijklmnopqrstuvwxyz";
		String string = "";
		char letter = 0;

		//generate random
		Random rand = new Random();
		for (int i = 0; i < length; i++){
			letter = charRange.charAt(rand.nextInt(length));
			
			//add characters to end of string length times
			string = string + letter;
		}

		return string;

	}

	// a method given a guessed string input, checks if it is a valid input, if it
	// is it is put in a used list for the future
	public static String checkGuess(String guess) {
		//check to see if guessed string is greater than 1, used before, or outside of range
		if (guess.length() > 1) {
			return "You should not enter more than 1 character";

		} else if (checkUsed(guess, used) == true) {
			guess= guess + " has been chosen before, try again";
			return guess;
		} else if (!guess.matches(".*[a-z].*")) {
			return "Your input should be between a-z";
			

		} else {

			return guess;
		}

	}

	//method that keeps track of guessed strings, this method checks if a given character was before or not
	public static boolean checkUsed(String guess, String used) {
		for (int i = 0; i < used.length(); i++) {
			if (used.charAt(i) == guess.charAt(0)) {
				return true;
			}
		}

		return false;
	}

	//a method that returns the string after each round, it takes input a history of guessed characters and checks the random string,
	//and generates only the characters guessed properly, and returns a count of how many were guessed correctly
	public static int returnGuessed(String randomString, String used) {
		//i use a lock to print out "_" when a given character is not yet guessed
		int lock;
		int count = 0;

		for (int i = 0; i < randomString.length(); i++) {
			lock = 0;

			for (int j = 0; j < used.length(); j++) {
				if (randomString.charAt(i) == used.charAt(j)) {
					
					//print out character from the random string that matches with the used characters
					System.out.print(used.charAt(j) + " ");
					count++;
					lock = 1;

				}

			}

			if (lock == 0) {
				System.out.print("_ ");
			}

		}

		return count;

	}

	public static void playGame() {
		//initializing all the variables i need to run game simulation
		Scanner scanner = new Scanner(System.in);
		
		String guess;
		int length;
		int seed = 1;
		int count = 0;
		int round = 1;
		String randomString;

		System.out.print("Enter the String length: ");
		
		//get string length and generate string
		length = scanner.nextInt();
		randomString = createString(length, seed);

		System.out.println("Start guessing the String:");
		
		//while loop of game, stops after 8 rounds or when player has guessed the string
		while (true) {

			System.out.println("Guessing(round " + round + "): Choosing your character from a-z:");

			//takes in input of guessed string
			guess = scanner.next();
			
			
			guess = checkGuess(guess);
			
			
			

			//if valid add to used list
			if (guess.length() == 1) {
				used = used + guess;
			}
			else {
				System.out.println(guess);
			}

			//if not valid, skip this round, ask for another input
			if (guess == "Your input should be between a-z" || guess == "You should not enter more than 1 character" || guess.contains("has been chosen before, try again")) {
				continue;
			} else {

				//return the string with the characters guessed so far
				count = returnGuessed(randomString, used);

			}
			
			System.out.println("");

			System.out.println("End (round " + round + ")");
			System.out.println("");

			//if the whole string was guessed correctly, end the game
			if (count == length) {
				System.out.println("Congradulations. You take " + round + " rounds to complete the game");
				break;
			}

			//increment rounds
			round++;

			
			//if it is more than 8 rounds then end the game
			if (round > 8) {
				System.out.println("You lose!");
				break;
			}

		}

	}

}
