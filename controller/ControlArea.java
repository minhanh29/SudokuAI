package controller;

import java.util.ArrayList;

public class ControlArea {
	public static ArrayList<Button> buttons = new ArrayList<Button>();
	public static ArrayList<RadioButton> radioButtons = new ArrayList<RadioButton>();

	public static void addButton(Button button)
	{
		buttons.add(button);
	}


	public static void addRadioButton(RadioButton button)
	{
		radioButtons.add(button);
	}


	public static int checkedButton()
	{
		int mode = 0;
		for (RadioButton b : ControlArea.radioButtons)
			if (b.isChecked())
				mode = b.getMode();
		return mode;
	}
}
