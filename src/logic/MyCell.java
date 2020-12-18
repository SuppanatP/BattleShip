package logic;

import gameController.GameController;
import gui.GuiController;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class MyCell extends ShipCell {
	private boolean isFired;

	public MyCell(int i, int j) {
		super(i, j);
		this.isFired = false;
		// TODO Auto-generated constructor stub
	}
	
	public void setFilled(boolean isFilled) {
		
		this.isFilled = isFilled;
		
		if(isFilled)
			setColor(Color.DARKGREEN);		
	}
	
	public void setFired() {
		this.isFired = true;
		if(isFilled) {
			bomb.play();
			if(GameController.isPlayer1()) {
				GameController.setCount2(GameController.getCount2()+1);
			}else {
				GameController.setCount1(GameController.getCount1()+1);
			}
			if(GameController.getCount1()==14 || GameController.getCount2() == 14) {
				GuiController.endScene();
			}
			setColor(Color.DARKRED);
		}else {
			water.play();
			setColor(Color.TEAL);
		}
	}


	
}
