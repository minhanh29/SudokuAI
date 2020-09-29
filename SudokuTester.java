import ai.*;

public class SudokuTester {
	public static void main(String[] args)
	{
		String path = "src/main/resources/sudoku1.txt";
		SudokuGenerator generator = new SudokuGenerator(path);
		int[][] sudoku = generator.getSudoku();
		SudokuSolver solver = new BackTrackSolver(sudoku);

		System.out.println("The sudoku:");
		printSudoku(sudoku);

		System.out.println("\nThe solution:");
		if (solver.solve())
			printSudoku(solver.getSolution());
		else
			System.out.println("No solution found.");
	}


	public static void printSudoku(int[][] sudoku)
	{
		// print the upper line
		System.out.print('┌');
		for (int i = 0; i < 9 * 2 + 5; i++)
			System.out.print('─');
		System.out.println('┐');

		for (int row = 0; row < 9; row++)
		{
			if (row % 3 == 0 && row != 0)
			{
				System.out.print("|");
				for (int i = 0; i < 9 * 2 + 5; i++)
				{
					if (i == 7 || i == 15)
						System.out.print(' ');
					else
						System.out.print('─');
				}
				System.out.println("|");
			}

			for (int col = 0; col < 9; col++)
			{
				if (col % 3 == 0)
					System.out.print("| ");

				if (sudoku[row][col] == 0)
					System.out.print("  ");
				else
					System.out.printf("" + sudoku[row][col] + " ");
			}
			System.out.println("|");
		}

		// print the bottom line
		System.out.print('└');
		for (int i = 0; i < 9 * 2 + 5; i++)
			System.out.print('─');
		System.out.println('┘');
	}
}
