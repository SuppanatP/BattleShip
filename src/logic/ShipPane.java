package logic;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public abstract class ShipPane extends GridPane {

	public ShipPane() {
		// TODO Auto-generated constructor stub
		this.setPrefWidth(500);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(5));
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(15), Insets.EMPTY)));
		this.setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 1.5;" + "-fx-border-insets: 2;"
				+ "-fx-border-radius: 15;" + "-fx-border-color: 	MEDIUMSLATEBLUE;");
		

		// this.getChildren().addAll(fieldCells);

		// TODO complete the FieldPane's constructor
		this.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}
}
