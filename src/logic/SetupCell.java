package logic;

import gameController.GameController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class SetupCell extends ShipCell implements Clickable{

	public SetupCell(int i, int j) {
		super(i, j);
		// TODO Auto-generated constructor stub
		
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// TODO fill in this method
				onClickHandler();
			}
		});

		this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// TODO fill in this method
				onEnterHandler();
			}
		});
		this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// TODO fill in this method
				onExitHandler();
			}
		});
	}

	@Override
	public void onClickHandler() {
		// TODO Auto-generated method stub
		if (GameController.getSelectedShipButton() != null) {
			Ship selectedShip = GameController.getSelectedShipButton().getShip();
			boolean isFillable = GameController.isFillable(selectedShip, row, column);
			if (isFillable) {
				click.play();
				GameController.fillCells(row, column, GameController.getIsShipButtonHorizontal(),
						selectedShip.getSize());
				GameController.placeShip();
	
			}
		}
	}

	@Override
	public void onEnterHandler() {
		// TODO Auto-generated method stub
		if (GameController.getSelectedShipButton() != null) {
			Ship selectedShip = GameController.getSelectedShipButton().getShip();
			boolean isFillable = GameController.isFillable(selectedShip, row, column);
			if (isFillable) {
				GameController.fillBorderCells(row, column, GameController.getIsShipButtonHorizontal(),
						selectedShip.getSize());
	
			}
		}
	}

	@Override
	public void onExitHandler() {
		// TODO Auto-generated method stub
		GameController.removeBorderSetupPane();
	}
	
	public void changeBorderColor() {
		// TODO Auto-generated method stub
		this.setStyle( 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 1.5;" +
                "-fx-border-insets: 2;" + 
                "-fx-border-radius: 15;" + 
                "-fx-border-color: 	CORNFLOWERBLUE;");
	}
	
	public void clearFieldCell() {
		isFilled = false;
		setColor(Color.STEELBLUE);;
	}
}
