package logic;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

public abstract class ShipCell extends Pane {

	protected int row, column;
	protected boolean isFilled;
	protected Boolean[][] cellIsFilled = new Boolean[8][8];
	protected AudioClip click,bomb,water;

	public ShipCell(int i, int j) {
		// TODO Auto-generated constructor stub
		AudioClip click = new AudioClip(ClassLoader.getSystemResource("PaneActionSound.mp3").toString());
		click.setVolume(0.25);
		this.click = click;
		AudioClip bomb = new AudioClip(ClassLoader.getSystemResource("bombSound.mp3").toString());
		bomb.setVolume(0.5);
		this.bomb = bomb;
		AudioClip water = new AudioClip(ClassLoader.getSystemResource("WaterSound.mp3").toString());
		water.setVolume(0.5);
		this.water = water;
		
		setPrefWidth(50);
		setPrefHeight(50);
		setMinWidth(50);
		setMinHeight(50);
		setPadding(new Insets(3));
		
		this.row = i;
		this.column = j;
		
		this.setBorder(new Border(
				new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,new BorderWidths(0.6))));

		setColor(Color.STEELBLUE);
		this.isFilled = false;

		
	}
	
	public void setColor(Color c) {
		this.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}
	
}
