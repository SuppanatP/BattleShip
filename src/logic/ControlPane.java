package logic;

import gameController.GameController;
import gui.GuiController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ControlPane extends DashBoard {

	private Button clearButton;
	private Pane changeDirection;
	private ObservableList<ShipButton> shipButtonList = FXCollections.observableArrayList();

	public ControlPane() {
		// TODO Auto-generated constructor stub
		super();
		setPrefWidth(300);

		clearButton = new Button();
		ImageView clear = GuiController.imageViewCreator("clear.png",100,32);
		clearButton.setGraphic(clear);
		clearButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));

		

		changeDirection = new Pane();
		changeDirection.getChildren().add(new ImageView("413500.png"));

		HBox hBox = new HBox();
		hBox.getChildren().addAll(changeDirection, clearButton, doneButton);
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(10, 0, 0, 0));

		Label title = new Label("Please select a ship");
		title.setFont(new Font(16));

		ShipButton speedboatbtn = new ShipButton("Speedboat");
		ShipButton yachtbtn = new ShipButton("Yacht");
		ShipButton cruisebtn = new ShipButton("Cruise");
		ShipButton containerShipbtn = new ShipButton("Container Ship");

		shipButtonList.addAll(speedboatbtn, yachtbtn, cruisebtn, containerShipbtn);
		getChildren().add(getPlayerTurn());
		getChildren().add(title);
		getChildren().addAll(speedboatbtn, yachtbtn, cruisebtn, containerShipbtn);
		getChildren().add(hBox);

		doneButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				click.play();
				boolean checker = false;
				for (ShipButton shipButton : shipButtonList) {
					if (!shipButton.isPlaced()) {
						checker = true;
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Error");
						alert.setHeaderText(null);
						alert.setContentText("Please place remaining ship(s) into the sea.");
						alert.showAndWait();
						break;
					}
				}
				if (!checker && GameController.isPlayer1()) {
					GameController.saveSetupPane();
					changeDirection.getChildren().add(new ImageView("413500.png"));
					setPlayerTurnText();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Welcome Player 2");
					alert.setHeaderText(null);
					alert.setContentText("Player 2 turn.");
					alert.showAndWait();
					//GameController.clearFieldPane();
					
					
				}else if(!checker && !GameController.isPlayer1()) {
					GameController.saveSetupPane();
					changeDirection.getChildren().add(new ImageView("413500.png"));
					setPlayerTurnText();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Welcome Player 2");
					alert.setHeaderText(null);
					alert.setContentText("Let's start.");
					alert.showAndWait();
					GuiController.changeToWaitScene();
					
				}
			}

		});
//
		clearButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				click.play();
				GameController.clearFieldPane();
				changeDirection.getChildren().add(new ImageView("413500.png"));
			}
		});
		
		clearButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImageView clear = GuiController.imageViewCreator("clear.png",110,35);
				clearButton.setGraphic(clear);
			}
		});

		clearButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImageView clear = GuiController.imageViewCreator("clear.png",100,32);
				clearButton.setGraphic(clear);
			}
		});
		
		changeDirection.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				// TODO Auto-generated method stub
				click.play();
				if (GameController.getIsShipButtonHorizontal()) {
					changeDirection.getChildren().add(new ImageView("413400.png"));
					GameController.setIsShipButtonHorizontal(false);
					
				} else {
					changeDirection.getChildren().add(new ImageView("413500.png"));
					GameController.setIsShipButtonHorizontal(true);
					
				}
			}
		});
		yachtbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				// TODO Auto-generated method stub
				if (!yachtbtn.isPlaced()) {
					click.play();
					setSelectedButton(yachtbtn);
				}
			}
		});

		cruisebtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				if (!cruisebtn.isPlaced()) {
					click.play();
					setSelectedButton(cruisebtn);
				}
				// TODO Auto-generated method stub

			}
		});

		containerShipbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				if (!containerShipbtn.isPlaced()) {
					click.play();
					setSelectedButton(containerShipbtn);
				}
				// TODO Auto-generated method stub

			}
		});

		speedboatbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				if (!speedboatbtn.isPlaced()) {
					click.play();
					setSelectedButton(speedboatbtn);
				}
				// TODO Auto-generated method stub

			}
		});
		// TODO complete the ControlPane's constructor

		this.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(4), BorderWidths.DEFAULT)));

	}

	public void setSelectedButton(ShipButton selectedshipButton) {
		GameController.setSelectedShipButton(selectedshipButton);
		//resetButtonsBackGroundColor();
		resetSomeButtonBackGroundColor();
		selectedshipButton.highlight();

	}

	public void resetButtonsBackGroundColor() {
		for (ShipButton shipButton : shipButtonList) {
			shipButton.unhighlight();
		}

	}
	public void resetSomeButtonBackGroundColor() {
		if (GameController.getIsShipButtonPlaced().size() == 0) {
			resetButtonsBackGroundColor();
			return;
		}

		for (ShipButton shipButton : shipButtonList) {
			boolean checker = false;

			for (ShipButton shipButton2 : GameController.getIsShipButtonPlaced()) {
				if (shipButton == shipButton2) {
					checker = true;
					break;
				}
			}
			if (checker == false) {
				shipButton.unhighlight();
			}

		}
	}

	public ObservableList<ShipButton> getShipButtonList() {
		return shipButtonList;
	}

	
}
