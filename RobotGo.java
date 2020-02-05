
//Aymen Rumi
//260661663
import java.util.Arrays;
import java.util.Random;

public class RobotGo {
	public static Random r = new Random(58);

	public static void main(String[] args) {

		char[][] currentBoard = createRandomObstacle(createBoard(10, 20), 45);
		displayBoard(startNavigation(currentBoard));

		System.out.println(Arrays.deepToString(currentBoard));

	}

	public static int[] getRandomCoordinate(int maxY, int maxX) {
		int[] coor = new int[2];
		int y = r.nextInt(maxY);
		int x = r.nextInt(maxX);
		coor[0] = y;
		coor[1] = x;
		return coor;
	}

	// ========================
	// Enter your code below

	// Global variables to keep track of position of the robot '.' in the maze and
	// the direction to which it is facing to move forward
	// I also make a global variable of free spaces in the maze, to count if
	// obstacles places are too much or not
	static int position1 = 1;
	static int position2 = 1;
	static String direction;
	static int free_spaces = 0;

	// I create a method to make my board, where i place the start and end position
	public static char[][] createBoard(int row, int column) {

		if (row < 5 || column < 5) {
			throw new IllegalArgumentException("Both x and y axes must be greater than or equal to 5.");
		}
		char[][] board = new char[row][column];

		for (int i = 0; i < column; i++) {
			if (i == 0 || i == column - 1) {
				for (int j = 0; j < row; j++) {
					board[j][i] = '#';
				}
			} else if (i == 1) {
				for (int j = 0; j < row; j++) {
					if (j == 0 || j == row - 1) {
						board[j][i] = '#';
					} else if (j == 1) {
						board[j][i] = '.';
					} else {
						board[j][i] = ' ';
						free_spaces++;
					}

				}
			} else if (i == column - 2) {
				for (int z = 0; z < row; z++) {
					if (z == 0 || z == row - 1) {
						board[z][i] = '#';
					} else if (z == row - 2) {
						board[z][i] = 'x';
					} else {
						board[z][i] = ' ';
						free_spaces++;
					}

				}
			} else {
				for (int z = 0; z < row; z++) {
					if (z == 0 || z == row - 1) {
						board[z][i] = '#';
					} else {
						board[z][i] = ' ';
						free_spaces++;
					}
				}
			}

		}

		return board;
	}

	// places the exact number of obstacles in the maze, if a position is created
	// where a '#' already exists i ignore it and place them only in empty positions
	public static char[][] createRandomObstacle(char[][] board, int obstacle) {
		int count = 0;
		int coordinate[] = new int[2];
		if (obstacle >= free_spaces) {
			throw new IllegalArgumentException("Too many obstacles.");
		}

		while (count != obstacle) {
			coordinate = getRandomCoordinate(board.length, board[0].length);
			if (board[coordinate[0]][coordinate[1]] == ' ') {
				board[coordinate[0]][coordinate[1]] = '#';
				count++;
			}

		}

		return board;

	}

	// method that prints out the board
	public static void displayBoard(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println("");

		}
	}

	// method for the right hand maze rule to turn the facing direction of the robot
	// towards the left
	public static void turnLeft() {
		if (direction == "left") {
			direction = "up";
		} else if (direction == "down") {
			direction = "left";
		} else if (direction == "up") {
			direction = "right";
		} else {
			direction = "down";
		}
	}

	// method for the right hand maze rule to turn the facing direction of the robot
	// towards the right
	public static void turnRight() {
		if (direction == "right") {
			direction = "up";
		} else if (direction == "up") {
			direction = "left";
		} else if (direction == "left") {
			direction = "down";
		} else {
			direction = "right";
		}
	}

	// method that makes the robot move forward, if the forward position is blocked
	// by a wall, face towards the left, and if the right side of the robot is empty
	// turn towards the right and move forward
	public static void moveForward(String head, char[][] board, int x, int y) {
		if (direction == "down") {
			if (board[x][y - 1] == ' ') {
				turnRight();
				return;
			}

			if (board[x + 1][y] == ' ') {
				board[x + 1][y] = '.';
				position1++;
				return;
			}

			if (board[x][y - 1] == '.') {
				turnRight();
				position2--;
				return;
			}

			if (board[x + 1][y] == '.') {
				board[x + 1][y] = '.';
				position1++;
				return;
			}

			if (board[x + 1][y] == '#') {
				turnLeft();
				return;
			}

		} else if (direction == "up") {
			if (board[x][y + 1] == ' ') {
				turnRight();
				return;
			}

			if (board[x - 1][y] == ' ') {
				board[x - 1][y] = '.';
				position1--;
				return;
			}

			if (board[x][y + 1] == '.') {
				turnRight();
				position2++;
				return;
			}

			if (board[x - 1][y] == '.') {
				board[x - 1][y] = '.';
				position1--;
				return;
			}

			if (board[x - 1][y] == '#') {
				turnLeft();
				return;

			}

		} else if (direction == "left") {
			if (board[x + 1][y] == ' ') {
				turnRight();
				return;
			}

			if (board[x][y + 1] == ' ') {
				board[x][y + 1] = '.';
				position2++;
				return;
			}

			if (board[x + 1][y] == '.') {
				turnRight();
				position1++;
				return;
			}

			if (board[x][y + 1] == '.') {
				board[x][y + 1] = '.';
				position2++;
				return;
			}

			if (board[x][y + 1] == '#') {
				turnLeft();
				return;
			}

		} else {
			if (board[x - 1][y] == ' ') {
				turnRight();
				return;
			}

			if (board[x][y - 1] == ' ') {
				board[x][y - 1] = '.';
				position2--;
				return;
			}

			if (board[x - 1][y] == '.') {
				turnRight();
				position1--;
				return;
			}

			if (board[x][y - 1] == '.') {
				board[x][y - 1] = '.';
				position2--;
				return;
			}

			if (board[x][y - 1] == '#') {
				turnLeft();
				return;
			}

		}

	}

	// method to start navigation, it stops if the x position was found or the robot
	// has over 1000000 iterations
	public static char[][] startNavigation(char[][] board) {

		// global variables
		int lock = 1;
		int iteration = 0;

		// starting direction of the robot
		direction = "down";

		while (lock != 0) {

			iteration++;

			if (board[position1 + 1][position2] == 'x' || board[position1 - 1][position2] == 'x'
					|| board[position1][position2 + 1] == 'x' || board[position1][position2 - 1] == 'x'
					|| iteration > 1000000) {

				break;
			}

			moveForward(direction, board, position1, position2);

		}

		return board;
	}

	// Enter your code above
	// ========================
}
