package game;

public class Utils {
	public static void copyArray(int[][] arr1, int[][] arr2)
	{
		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
				arr2[row][col] = arr1[row][col];
	}
}
