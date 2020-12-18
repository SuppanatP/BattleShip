package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MyPane extends ShipPane {
	private MyCell[][] myCells = new MyCell[8][8];

	public MyPane(SetupPane setupPane) {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				MyCell myCell = new MyCell(i, j);
				myCells[i][j] = myCell;
				myCell.setFilled(setupPane.getSetupCells()[i][j].isFilled());
				this.add(myCell, j, i);
			}
		}
		
	}

	public void setFiredByRC(int row, int column) {
		myCells[row][column].setFired();
		// TODO Auto-generated method stub
		
	}

}
