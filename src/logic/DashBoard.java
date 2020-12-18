package logic;

import gameController.GameController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class DashBoard extends VBox {

	protected Button doneButton;
	protected Label playerTurn;
	protected AudioClip click;

	public DashBoard() {
		// TODO Auto-generated constructor stub
		AudioClip click = new AudioClip(ClassLoader.getSystemResource("ClickSound.mp3").toString());
		click.setVolume(0.25);
		this.click = click;
		
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.WHEAT, new CornerRadii(15), Insets.EMPTY)));
		setStyle("-fx-border-style: solid inside;" + "-fx-border-width: 1.5;" + "-fx-border-insets: 2;"
				+ "-fx-border-radius: 15;" + "-fx-border-color: 	PERU;");
		setSpacing(15);
		setFillWidth(true);
		
		playerTurn = new Label();
		playerTurn.setFont(new Font(25));
		setPlayerTurnText();

		doneButton = new Button();
		ImageView done = new ImageView("done.png");
		done.setFitWidth(100);
		done.setFitHeight(32);
		doneButton.setGraphic(done);
		doneButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));

		doneButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImageView done = new ImageView("done.png");
				done.setFitWidth(110);
				done.setFitHeight(35);
				doneButton.setGraphic(done);
			}
		});

		doneButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImageView done = new ImageView("done.png");
				done.setFitWidth(100);
				done.setFitHeight(32);
				doneButton.setGraphic(done);
			}
		});
	}
	
	public void setPlayerTurnText() {
		playerTurn.textProperty().setValue("Player " + GameController.getTurn());
	}
	// TODO Auto-generated constructor stub

	public Label getPlayerTurn() {
		return playerTurn;
	}
}
