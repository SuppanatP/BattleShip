package logic;

import gameController.GameController;
import gui.GuiController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DisplayPane extends DashBoard {
	public DisplayPane() {
		// TODO Auto-generated constructor stub
		super();
		setPrefWidth(150);
		
		doneButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(GameController.isClicked) {
					click.play();
					GuiController.changeToWaitScene();
					GameController.isClicked = false;
				}
				
			}
		});
		getChildren().addAll(playerTurn, doneButton);
	}
}
