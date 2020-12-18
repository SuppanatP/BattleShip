package logic;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ShipButton extends Button {
	private Ship ship;
	private boolean isPlaced;

	ShipButton(String shipName) {
		Ship ship  = new Ship(shipName);
		this.ship = ship;
		this.isPlaced = false;
		
		ImageView shipPic = new ImageView(ship.getUrl());
		shipPic.setFitHeight(50);
		shipPic.setFitWidth(250);
		setGraphic(shipPic);
		this.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(10), Insets.EMPTY)));
		this.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,new CornerRadii(10), BorderWidths.DEFAULT)));
		this.setTooltip();
	}

	public boolean isPlaced() {
		return isPlaced;
	}

	public void setPlaced(boolean isPlaced) {
		this.isPlaced = isPlaced;
	}

	public Ship getShip() {
		return ship;
	}

	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.ROYALBLUE, new CornerRadii(10), Insets.EMPTY)));
		
	}
	
	public void highlightToRed() {
		this.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(10), Insets.EMPTY)));
		
	}

	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(10), Insets.EMPTY)));

	}

	private void setTooltip() {
		Tooltip tooltip = new Tooltip();
		tooltip.setFont(new Font(12));
		tooltip.setText(ship.getShipName() +"\n"+"Size : "+ ship.getSize() );
		this.setOnMouseMoved((MouseEvent e) -> {
			if (ship != null) {
				tooltip.show(this, e.getScreenX(), e.getScreenY() + 10);
				
			}
		});
		this.setOnMouseExited((MouseEvent e) -> {
			tooltip.hide();
		});
	}

}

