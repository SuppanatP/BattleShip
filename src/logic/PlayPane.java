package logic;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class PlayPane extends ShipPane {

	private PlayCell[][] playCells = new PlayCell[8][8];
	
	public PlayPane(SetupPane setupPane) {
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				PlayCell playCell = new PlayCell(i, j);
				playCells[i][j] = playCell;
				playCell.setFilled(setupPane.getSetupCells()[i][j].isFilled());
				add(playCell, j, i);
			}
		}
		setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 1.5;" + "-fx-border-insets: 2;"
				+ "-fx-border-radius: 15;" + "-fx-border-color: TOMATO;");
		setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, new CornerRadii(15), Insets.EMPTY)));
	}
	// TODO Auto-generated constructor stub
}
