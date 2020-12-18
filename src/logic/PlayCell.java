package logic;

import gameController.GameController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PlayCell extends ShipCell implements Clickable {

	private boolean isFired;

	public PlayCell(int i, int j) {
		super(i, j);
		// TODO Auto-generated constructor stub
		isFilled = false;
		isFired = false;
		setColor(Color.TOMATO);

		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// TODO fill in this method
				if (!isFired && !GameController.isClicked) {
					onClickHandler();
				}
			}
		});

		this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// TODO fill in this method
				if (!isFired) {
					onEnterHandler();
				}
			}
		});
		this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				// TODO fill in this method
				if (!isFired) {
					onExitHandler();
				}
			}
		});
	}

	@Override
	public void onClickHandler() {
		// TODO Auto-generated method stub
		
		if (isFilled)
			setColor(Color.DARKRED);
		else
			setColor(Color.STEELBLUE);
		isFired = true;
		if (GameController.isPlayer1()) {
			GameController.isClicked = true;
			GameController.setCellFired(false, row, column);

		} else {
			GameController.isClicked = true;
			GameController.setCellFired(true, row, column);

		}
	}

	@Override
	public void onEnterHandler() {
		// TODO Auto-generated method stub
		setColor(Color.DARKGREY);
	}

	@Override
	public void onExitHandler() {
		// TODO Auto-generated method stub
		setColor(Color.TOMATO);
	}

}
