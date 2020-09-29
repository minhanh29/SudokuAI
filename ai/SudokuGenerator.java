package ai;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuGenerator {
	int[][] sudoku;

	public SudokuGenerator(String fileName)
	{
		sudoku = new int[9][9];
		loadSudoku("sudoku1.txt");
	}


	private void loadSudoku(String fileName)
	{
		try {
			Scanner scan = new Scanner(new File(fileName));

			for (int row = 0; row < 9; row++)
			{
				String cells[] = scan.nextLine().split(" ");
				for (int col = 0; col < 9; col++)
					sudoku[row][col] = Integer.parseInt(cells[col]);
			}
		}
		catch (FileNotFoundException e)
		{
			for (int row = 0; row < 9; row++)
				for (int col = 0; col < 9; col++)
					sudoku[row][col] = 0;
			e.printStackTrace();
		}
	}


	public int[][] getSudoku()
	{
		return sudoku;
	}
}
