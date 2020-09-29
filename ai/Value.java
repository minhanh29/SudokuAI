package ai;
import java.util.ArrayList;

public class Value implements Comparable<Value>
{
	private static int nextOrder = 0;
	private int arrivalOrder;
	private int row, col;
	ArrayList<Integer> values;

	public Value(int row, int col)
	{
		this.row = row;
		this.col = col;
		values = new ArrayList<Integer>();
		arrivalOrder = nextOrder;
		nextOrder++;
	}

	public void add(int value)
	{
		values.add(value);
	}

	public int size()
	{
		return values.size();
	}

	public ArrayList<Integer> getValues()
	{
		return values;
	}


	public int compareTo(Value val)
	{
		if (values.size() > val.size())
			return 1;
		else if (values.size() < val.size())
			return -1;
		else if (arrivalOrder < val.arrivalOrder)
			return -1;
		return 1;
	}


	public int getRow()
	{
		return row;
	}

	public int getCol()
	{
		return col;
	}
}
