import java.util.Random;
import java.util.Scanner;

public class RazzleDazzle {
    public int score;
    public int molesLeft;
    public int attemptsLeft;
    public int gridDimension;
    public char moleGrid[][];

    public RazzleDazzle(int numAttempts, int gridDimension) {
	this.attemptsLeft = numAttempts;
	this.gridDimension = gridDimension;
	this.moleGrid = new char[gridDimension][gridDimension];
	fillMoleGrid();
    }

    public boolean place(int x, int y) {
	if (moleGrid[x][y] != 'M') {
	    moleGrid[x][y] = 'M';
	    molesLeft++;
	    return true;
	}
	return false;
    }

    public void whack(int x, int y) {
	if (moleGrid[x][y] == 'M') {
	    moleGrid[x][y] = 'W';
	    score++;
	    attemptsLeft--;
	    molesLeft--;
	    System.out.println(String.format("You got one! %d more to go", molesLeft));
	} else {
	    System.out.println("Miss!");
	    moleGrid[x][y] = 'x';
	    attemptsLeft--;
	}
    }

    public void printGridToUser() {
	for (int r = 0; r < moleGrid.length; r++) {
	    for (int c = 0; c < moleGrid.length; c++) {
		if (moleGrid[r][c] == 'M') {
		    System.out.print('*' + " ");
		} else {
		    System.out.print(moleGrid[r][c] + " ");
		}
	    }
	    System.out.println();
	}
    }

    public void printGrid() {
	for (int r = 0; r < moleGrid.length; r++) {
	    for (int c = 0; c < moleGrid.length; c++) {
		System.out.print(moleGrid[r][c] + " ");
	    }
	    System.out.println();
	}
    }

    public int randomNum() {
	Random r = new Random();
	return r.nextInt(gridDimension);
    }

    public void fillMoleGrid() {
	for (int r = 0; r < moleGrid.length; r++) {
	    for (int c = 0; c < moleGrid.length; c++) {
		moleGrid[r][c] = '*';
	    }
	}
    } 

    public static void main(String[] args) {
	int inputX;
	int inputY;
	RazzleDazzle wam = new RazzleDazzle(50, 10);
	Scanner scanner = new Scanner(System.in);
	while (wam.molesLeft < wam.gridDimension) {
	    wam.place(wam.randomNum(), wam.randomNum());
	}
	System.out.println(
		String.format("Welcome to Whack-A-Mole. You have %d attempts to get all the moles", wam.attemptsLeft));
	System.out.println("Enter 0 for Row and 0 for Column to give up");
	while (wam.molesLeft != 0 && wam.attemptsLeft != 0) {
	    System.out.println(String.format("You have %d attempts left", wam.attemptsLeft));
	    wam.printGridToUser();
	    System.out.println("Enter a Row and Column number");
	    inputX = scanner.nextInt() - 1;
	    inputY = scanner.nextInt() - 1;
	    if (inputX == -1 && inputY == -1) {
		System.out.println("Giving up, eh?");
		break;
	    } else if (inputX < 0 || inputX > wam.gridDimension - 1 || inputY < 0 || inputY > wam.gridDimension - 1) {
		System.out.println(String.format("Please enter a number between 1 and %d", wam.gridDimension));
	    } else {
		wam.whack(inputX, inputY);
	    }
	}
	if (wam.score == wam.gridDimension) {
	    System.out.println("Congratulations, you got them all!");
	    System.out.println(String.format("Your Score: %d", wam.score));
	    wam.printGrid();
	} else {
	    System.out.println("Game Over, you lose");
	    wam.printGrid();
	    System.out.println(String.format("Your Score: %d ", wam.score));
	}
	scanner.close();
    }
}
