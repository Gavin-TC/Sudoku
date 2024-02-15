import java.util.Scanner;
import java.util.Vector;

public class Main {
  private static boolean gameRunning = true;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    // Generate puzzle before playing
    // 100 seems to make around 10 solutions
    Grid.generatePuzzle(100);

    while (gameRunning) {
      Grid.printGrid();

      // player chooses their spot
      System.out.print("(Letter, number): ");
      String spot = in.next();

      while (!Grid.validCoorindates(spot)) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Grid.printGrid();
        System.out.println("Invalid spot. Spot might be taken (red numbers). Otherwise, valid inputs: a1, b9.");
        System.out.print("(Letter, number): ");
        spot = in.next();
      }
      Vector<Integer> newSpot = Grid.getSpotVector(spot);

      // player chooses their number, and make sure the number is between 0-9.
      System.out.print("Number to place: ");
      int number = Math.max(1, Math.min(in.nextInt(), 9));

      Grid.placeNumber(newSpot, number);

      if (Grid.isWin()) {
        System.out.println("You win!");
        in.close();
        gameRunning = false;
      }
    }
  }
}
