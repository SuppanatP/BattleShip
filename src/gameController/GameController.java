package gameController;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import logic.ControlPane;
import logic.MyPane;
import logic.PlayPane;
import logic.SetupCell;
import logic.SetupPane;
import logic.Ship;
import logic.ShipButton;

public class GameController {

	private static PlayPane playPane1, playPane2;
	private static MyPane myPane1, myPane2;
	private static boolean isPlayer1 = true;
	private static ControlPane controlPane;
	private static SetupPane setupPane;
	private static int count1, count2;
	private static ShipButton selectedShipButton;
	private static boolean isShipButtonHorizontal = true;
	private static boolean[][] cellIsFilled = new boolean[8][8];
	private static ArrayList<ShipButton> isShipButtonPlaced = new ArrayList<ShipButton>();
	public static boolean isClicked;

	public static void clear() {
		playPane1 = null;
		playPane2 = null;
		myPane1 = null;
		myPane2 = null;
		controlPane = null;
		setupPane = null;
		count1 = 0;
		count2 = 0;
		selectedShipButton = null;
		isShipButtonHorizontal = true;
		clearCellIsFilled();
		isShipButtonPlaced.clear();
		isClicked = false;
		isPlayer1 = true;
		
	}
	public static void clearCellIsFilled() {
		for(int i = 0;  i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				cellIsFilled[i][j] = false;
			}
		}
	}
	public static int getTurn() {
		if (isPlayer1) {
			return 1;
		} else {
			return 2;
		}
	}

	public static boolean isFillable(Ship selectedShip, int row, int column) {
		int length = selectedShip.getSize();

		if (isShipButtonHorizontal) {
			if (column + length > 8) {
				// System.out.println("HI");
				return false;
			}
			for (int i = column; i < column + length; i++) {
				if (cellIsFilled[row][i] == true) {
					// System.out.println(i);
					return false;
				}
			}
		} else {
			if (row + length > 8) {
				return false;
			}
			for (int i = row; i < row + length; i++) {
				if (cellIsFilled[i][column] == true)
					return false;
			}
		}

		return true;
	}

	public static void fillBorderCells(int row, int column, boolean isHorizontal, int length) {

		if (isHorizontal) {
			for (int j = column; j < column + length; j++) {
				SetupCell sc = new SetupCell(row, j);
				sc.changeBorderColor();
				setupPane.setFieldCells(sc, row, j);
				setupPane.changeNodeColorByRowColumn(row, j);
			}
		} else {
			for (int i = row; i < row + length; i++) {
				SetupCell sc = new SetupCell(i, column);
				sc.changeBorderColor();
				setupPane.setFieldCells(sc, i, column);
				setupPane.changeNodeColorByRowColumn(i, column);
			}
		}

	}

	public static void removeBorderSetupPane() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (cellIsFilled[i][j] == false) {
					SetupCell sc = new SetupCell(i, j);
					sc.setColor(Color.STEELBLUE);
					setupPane.setFieldCells(sc, i, j);
					setupPane.changeNodeColorToOriginal(i, j);
				}
			}
		}
	}

	public static void fillCells(int row, int column, boolean isHorizontal, int length) {

		if (isHorizontal) {
			for (int j = column; j < column + length; j++) {
				cellIsFilled[row][j] = true;
				SetupCell sc = new SetupCell(row, j);
				sc.setFilled(true);
				sc.setColor(Color.DARKGREEN);
				setupPane.setFieldCells(sc, row, j);
				setupPane.changeNodeColorByRowColumn(row, j);
			}
		} else {
			for (int i = row; i < row + length; i++) {
				cellIsFilled[i][column] = true;
				SetupCell sc = new SetupCell(i, column);
				sc.setFilled(true);
				sc.setColor(Color.DARKGREEN);
				setupPane.setFieldCells(sc, i, column);
				setupPane.changeNodeColorByRowColumn(i, column);
			}
		}

		setSurroundedToFilled(row, column, isHorizontal, length);
	}

	private static void setSurroundedToFilled(int row, int column, boolean isHorizontal, int length) {

		int upperRow = Math.max(0, row - 1);
		int leftCol = Math.max(0, column - 1);
		int lowerRow, rightCol;

		if (isHorizontal) {
			lowerRow = Math.min(7, row + 1);
			rightCol = Math.min(7, column + length);
		} else {
			lowerRow = Math.min(7, row + length);
			rightCol = Math.min(7, column + 1);
		}

		for (int i = upperRow; i <= lowerRow; i++) {
			for (int j = leftCol; j <= rightCol; j++) {
				cellIsFilled[i][j] = true;
			}
		}
	}

	public static void setCellFired(boolean isPlayer1, int row, int column) {
		if (isPlayer1)
			myPane1.setFiredByRC(row, column);
		else
			myPane2.setFiredByRC(row, column);

	}

	public static void placeShip() {
		isShipButtonPlaced.add(selectedShipButton);
		selectedShipButton.setPlaced(true);
		selectedShipButton.highlightToRed();
		selectedShipButton = null;
	}

	public static ShipButton getSelectedShipButton() {
		return selectedShipButton;
	}

	public static void setSelectedShipButton(ShipButton selectedShipButton) {
		GameController.selectedShipButton = selectedShipButton;
	}

	public static void setControlPane(ControlPane controlPane) {
		// TODO Auto-generated method stub
		GameController.controlPane = controlPane;
	}

	public static void setSetupPane(SetupPane setupPane) {
		// TODO Auto-generated method stub
		GameController.setupPane = setupPane;
	}

	public static boolean isPlayer1() {
		// TODO Auto-generated method stub
		return isPlayer1;
	}

	public static int getCount2() {
		// TODO Auto-generated method stub
		return count2;
	}

	public static void setCount2(int i) {
		// TODO Auto-generated method stub
		count2 = i;
	}

	public static int getCount1() {
		// TODO Auto-generated method stub
		return count1;
	}

	public static void setCount1(int i) {
		// TODO Auto-generated method stub
		count1 = i;
	}

	public static boolean getIsShipButtonHorizontal() {
		// TODO Auto-generated method stub
		return isShipButtonHorizontal;
	}

	public static void setIsShipButtonHorizontal(boolean b) {
		// TODO Auto-generated method stub
		isShipButtonHorizontal = b;
	}

	public static void clearFieldPane() {
		// TODO Auto-generated method stub
		for (SetupCell sc : setupPane.getSetupCellslist()) {
			sc.clearFieldCell();
		}
		initilizeCellIsFilled();
		clearShipButton();
	}

	private static void initilizeCellIsFilled() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				cellIsFilled[i][j] = false;
			}
		}
	}

	private static void clearShipButton() {
		// TODO Auto-generated method stub
		for (ShipButton shipButton : controlPane.getShipButtonList()) {
			shipButton.setPlaced(false);
			isShipButtonHorizontal = true;
			shipButton.unhighlight();
		}
		isShipButtonPlaced.clear();
	}

	public static void saveSetupPane() {
		// TODO Auto-generated method stub
		if (isPlayer1) {
			playPane1 = new PlayPane(setupPane);
			myPane1 = new MyPane(setupPane);
			changeTurn();
			clearFieldPane();

		} else {
			playPane2 = new PlayPane(setupPane);
			myPane2 = new MyPane(setupPane);
			clearFieldPane();

		}
	}

	public static ArrayList<ShipButton> getIsShipButtonPlaced() {
		return isShipButtonPlaced;
	}

	public static PlayPane getPlayPane1() {
		return playPane1;
	}

	public static PlayPane getPlayPane2() {
		return playPane2;
	}

	public static MyPane getMyPane1() {
		return myPane1;
	}

	public static MyPane getMyPane2() {
		return myPane2;
	}

	public static void changeTurn() {
		// TODO Auto-generated method stub
		if (isPlayer1) {
			isPlayer1 = false;
		} else {
			isPlayer1 = true;
		}
	}

}
