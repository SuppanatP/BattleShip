package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class SetupPane extends ShipPane {

	private SetupCell[][] setupCells = new SetupCell[8][8];
	private ObservableList<SetupCell> setupCellslist = FXCollections.observableArrayList();

	public SetupPane() {
		// TODO Auto-generated constructor stub
		super();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				SetupCell setupCell = new SetupCell(i, j);
				setupCells[i][j] = setupCell;
				setupCellslist.add(setupCell);
				this.add(setupCell, j, i);
			}
		}
	}

	public void changeNodeColorByRowColumn(int row, int column) {

		ObservableList<Node> childrens = this.getChildren();

		for (Node n : childrens) {
			if (getRowIndex(n) == row && getColumnIndex(n) == column) {
				SetupCell sc = (SetupCell) n;
				sc.setColor(Color.DARKGREEN);

			}
		}
	}

	public void changeNodeColorToOriginal(int row, int column) {

		ObservableList<Node> childrens = this.getChildren();

		for (Node n : childrens) {
			if (getRowIndex(n) == row && getColumnIndex(n) == column) {
				SetupCell fc = (SetupCell) n;
				fc.setColor(Color.STEELBLUE);
			}
		}
	}

	public void setFieldCells(SetupCell setupCells, int i, int j) {
		this.setupCells[i][j] = setupCells;
	}

	public SetupCell[][] getSetupCells() {
		return setupCells;
	}

	public ObservableList<SetupCell> getSetupCellslist() {
		return setupCellslist;
	}
}
