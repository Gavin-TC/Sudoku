
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;


public class Grid {
	private static String[][] grid = new String[9][9];
	private static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
	private static ArrayList<Vector<Integer>> stuckSpots = new ArrayList<>();

	public static void fillGrid()
	{
		for (int x = 0; x < grid.length; x++)
		{
			for (int y = 0; y < grid.length; y++)
			{
				grid[y][x] = " ";
			}
		}
	}

	public static void generatePuzzle()
	{
		fillGrid();

		for (int x = 0; x < grid.length; x++)
		{
			for (int y = 0; y < grid.length; y++)
			{
				Random random = new Random();
				boolean isNumber = random.nextBoolean();

				if (isNumber)
				{
					boolean isValid = true;
					String number = String.valueOf(random.nextInt(1, 9));

					// Check if the number is already in the row
					for (int i = 0; i < grid[x].length; i++)
					{
						if (grid[x][i].equals(Color.TEXT_RED + number)) {
							isValid = false;
							break;
						}
					}

					// Check if the number is already in the column 
					for (int i = 0; i < grid[0].length; i++)
					{
						if (grid[i][y].equals(Color.TEXT_RED + number))
						{
							isValid = false;
							break;
						}
					}

					// Check if the number is already in the 3x3 grid
					int gridX = (int) x / 3;
					int gridY = (int) y / 3;

					for (int i = gridX * 3; i < (gridX + 1) * 3; i++)
					{
						for (int j = gridY * 3; j < (gridY + 1) * 3; j++)
						{
							if (grid[i][j].equals(Color.TEXT_RED + number))
							{
								isValid = false;
								break;
							}
						}
					}

					if (isValid)
					{
						Vector<Integer> vector = new Vector<>();
						vector.add(x);
						vector.add(y);
						stuckSpots.add(vector);

						grid[x][y] = Color.TEXT_RED + number;
					}
					else
						grid[x][y] = Color.TEXT_RESET + " ";
				}
				else
					grid[x][y] = Color.TEXT_RESET + " ";
			}
		}
	}

	public static boolean validCoorindates(String coordinates)
	{
		if (coordinates.length() != 2) return false;

		char firstChar = coordinates.toLowerCase().charAt(0);
		int lastChar = Character.getNumericValue(coordinates.charAt(1));

		if (!Arrays.asList(letters).contains(String.valueOf(firstChar).toUpperCase())) return false;
		if (Character.isDigit(firstChar)) return false;
		if (lastChar > 9 || lastChar <= 0) return false;

		Vector<Integer> position = new Vector<>();
		position.add(firstChar - 'a');
		position.add(lastChar - 1);

		for (int i = 0; i < stuckSpots.size(); i++)
		{
			if (position.equals(stuckSpots.get(i)))
				return false;
		}
		return true;
	}

	public static Vector<Integer> getSpotVector(String coordinates)
	{
		char firstChar = coordinates.charAt(0);
		int lastChar = Character.getNumericValue(coordinates.charAt(1));

		Vector<Integer> vector = new Vector<>();
		vector.add(firstChar - 'a');
		vector.add(lastChar - 1);
		
		return vector;
	}

	public static void placeNumber(Vector<Integer> vector, int number)
	{
		int x = vector.get(1);
		int y = vector.get(0);

		grid[y][x] = String.valueOf(number);
	}

	public static boolean isWin()
	{
		
		for (int x = 0; x < grid.length; x++)
		{
			for (int y = 0; y < grid.length; y++)
			{
				if (grid[y][x] == " ")
					// Board isn't empty yet
					return false;
			}
		}

		boolean isValid = true;

		for (int x = 0; x < grid.length; x++)
		{
			for (int y = 0; y < grid.length; y++)
			{
				Random random = new Random();
				boolean isNumber = random.nextBoolean();

				if (isNumber)
				{
					String number = String.valueOf(random.nextInt(1, 9));

					// Check if the number is already in the row
					for (int i = 0; i < grid[x].length; i++)
					{
						if (grid[x][i].equals(Color.TEXT_RED + number)) {
							isValid = false;
							break;
						}
					}

					// Check if the number is already in the column 
					for (int i = 0; i < grid[0].length; i++)
					{
						if (grid[i][y].equals(Color.TEXT_RED + number))
						{
							isValid = false;
							break;
						}
					}

					// Check if the number is already in the 3x3 grid
					int gridX = (int) x / 3;
					int gridY = (int) y / 3;

					for (int i = gridX * 3; i < (gridX + 1) * 3; i++)
					{
						for (int j = gridY * 3; j < (gridY + 1) * 3; j++)
						{
							if (grid[i][j].equals(Color.TEXT_RED + number))
							{
								isValid = false;
								break;
							}
						}
					}
				}
			}
		}
		return isValid;
	}

	public static void printGrid()
	{
		System.out.println("\t" + Color.TEXT_YELLOW + "   SUDOKU!\n\t   =======\n");
		System.out.println("    1 2 3   4 5 6   7 8 9");
		System.out.println("    _____________________");
		for (int x = 0; x < grid.length; x++)
		{
			System.out.print(Color.TEXT_YELLOW + letters[x] + " | " + Color.TEXT_RESET);
			for (int y = 0; y < grid.length; y++)
			{

				if (y % 3 == 0 && y != 0)
					System.out.print(Color.TEXT_YELLOW + "| " + Color.TEXT_RESET);

				// Text color reset
				System.out.print(grid[x][y] + " " + Color.TEXT_RESET);
			}
			System.out.println();

			if ((x + 1) % 3 == 0 && x + 1 != grid.length)
				System.out.println(Color.TEXT_YELLOW + "    ------+-------+------");
		}
	}
}