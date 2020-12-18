package gui;

import gameController.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import logic.ControlPane;
import logic.DisplayPane;
import logic.MyPane;
import logic.PlayPane;
import logic.SetupPane;
import logic.ShipPane;

public class GuiController {
	private static HBox root;
	private static AudioClip click, congratulations, welcome, gameSound;
	// private static AudioClip gameSound;

	public static HBox welcome() {
		AudioClip click = new AudioClip(ClassLoader.getSystemResource("ClickSound.mp3").toString());
		click.setVolume(0.5);
		AudioClip congratulations = new AudioClip(ClassLoader.getSystemResource("CongratulationsSound.mp3").toString());
		congratulations.setVolume(0.5);
		AudioClip welcome = new AudioClip(ClassLoader.getSystemResource("MenuSound.mp3").toString());
		welcome.setVolume(1);
		AudioClip gameSound = new AudioClip(ClassLoader.getSystemResource("GameSound.mp3").toString());
		gameSound.setVolume(1);

		GuiController.click = click;
		GuiController.congratulations = congratulations;
		GuiController.welcome = welcome;
		GuiController.gameSound = gameSound;

		HBox root = new HBox();
		initializeWelcomeScene(root);
		GuiController.root = root;
		return GuiController.root;
	}

	private static ImageView imageViewCreator(String url, double width, double height) {
		ImageView imageView = new ImageView(url);
		imageView.setFitHeight(height);
		imageView.setFitWidth(width);
		return imageView;
	}

	private static void initializeWelcomeScene(HBox root) {
		// TODO Auto-generated method stub
		welcome.stop();
		congratulations.stop();
		VBox vBox = new VBox();
		StackPane stackPane = new StackPane();
		welcome.play();
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(20);
		vBox.setPadding(new Insets(175, 0, 0, 0));

		Button startButton = new Button();
		//ImageView start = imageViewCreator("start.png",234,75);
		startButton.setGraphic(imageViewCreator("start.png",234,75));
		startButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));
		startButton.setPrefWidth(234);
		startButton.setPrefHeight(75);

		Button instructionsButton = new Button();
		ImageView instructions = imageViewCreator("instructions.png",234,75);
		instructionsButton.setGraphic(instructions);
		instructionsButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));
		instructionsButton.setPrefWidth(234);
		instructionsButton.setPrefHeight(75);

		vBox.getChildren().addAll(startButton, instructionsButton);

		startButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImageView start = new ImageView("start2.png");
				start.setFitHeight(80);
				start.setFitWidth(250);
				startButton.setGraphic(start);
			}
		});

		startButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImageView start = new ImageView("start.png");
				start.setFitHeight(75);
				start.setFitWidth(234);
				startButton.setGraphic(start);
			}
		});
		startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				initialize();
				click.play();

			}
		});

		instructionsButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImageView instructions = new ImageView("instructions2.png");
				instructions.setFitHeight(80);
				instructions.setFitWidth(250);
				instructionsButton.setGraphic(instructions);
			}
		});

		instructionsButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImageView instructions = new ImageView("instructions.png");
				instructions.setFitHeight(75);
				instructions.setFitWidth(234);
				instructionsButton.setGraphic(instructions);
			}
		});

		instructionsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				instruction1();
				click.play();

			}
		});
		Image image = new Image("BG with Title.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(560);
		imageView.setFitWidth(1200);

		stackPane.getChildren().addAll(imageView, vBox);
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(stackPane);

	}

	public static HBox initialize() {
		root.getChildren().remove(0);

		Pane pane = new Pane();
		HBox hBox = new HBox();

		ControlPane controlPane = new ControlPane();
		SetupPane setupPane = new SetupPane();
		hBox.getChildren().addAll(setupPane, controlPane);
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(10, 0, 0, 180));
		hBox.setPrefHeight(550);

		hBox.setSpacing(30);
		ImageView imageView = new ImageView("BG.png");
		pane.getChildren().add(imageView);
		pane.getChildren().add(hBox);

		root.getChildren().add(pane);

		GameController.setControlPane(controlPane);
		GameController.setSetupPane(setupPane);

		GuiController.root = root;
		return root;
	}

	public static HBox changeToWaitScene() {
		welcome.stop();
		gameSound.stop();
		gameSound.play();

		root.getChildren().remove(0);
		VBox vBox = new VBox();
		StackPane waitPane = new StackPane();
		if (!GameController.isPlayer1()) {
			ImageView imageView = new ImageView("Player1Turn.png");
			imageView.setFitHeight(675);
			imageView.setFitWidth(1200);
			waitPane.getChildren().add(imageView);
		} else {
			ImageView imageView = new ImageView("Player2Turn.png");
			imageView.setFitHeight(675);
			imageView.setFitWidth(1200);
			waitPane.getChildren().add(imageView);
		}

		Button continueButton = new Button();
		ImageView continuepic = new ImageView("continue.png");
		continuepic.setFitWidth(234);
		continuepic.setFitHeight(75);
		continueButton.setGraphic(continuepic);
		continueButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));

		continueButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				click.play();
				// TODO Auto-generated method stub
				if (GameController.isPlayer1()) {
					changeToGameScene2();
				} else {
					changeToGameScene1();
				}

			}
		});

		continueButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImageView continuePic = new ImageView("continue.png");
				continuePic.setFitHeight(80);
				continuePic.setFitWidth(250);

				continueButton.setGraphic(continuePic);
			}
		});

		continueButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImageView continuePic = new ImageView("continue.png");
				continuePic.setFitHeight(75);
				continuePic.setFitWidth(234);

				continueButton.setGraphic(continuePic);
			}
		});

		vBox.getChildren().add(continueButton);
		vBox.setPadding(new Insets(150, 0, 0, 0));
		vBox.setAlignment(Pos.CENTER);

		waitPane.getChildren().add(vBox);

		root.getChildren().add(waitPane);
		GuiController.root = root;
		return GuiController.root;
	}

	public static HBox changeToGameScene1() {
		GameController.changeTurn();

		root.getChildren().remove(0);
		Pane pane = new Pane();
		HBox gamePane = new HBox();

		PlayPane playPane2 = GameController.getPlayPane2();
		MyPane myPane1 = GameController.getMyPane1();
		DisplayPane displayPane = new DisplayPane();

		playPane2.setPrefHeight(500);
		myPane1.setPrefHeight(500);

		VBox vBox1 = new VBox();
		VBox vBox2 = new VBox();

		vBox1.setAlignment(Pos.CENTER);
		vBox2.setAlignment(Pos.CENTER);
		vBox1.setSpacing(5);
		vBox2.setSpacing(5);

		ImageView player1 = new ImageView("Player1.png");
		player1.setFitHeight(40);
		player1.setFitWidth(211);

		ImageView player2 = new ImageView("Player2.png");
		player2.setFitHeight(40);
		player2.setFitWidth(211);

		vBox1.getChildren().addAll(player2, playPane2);
		vBox2.getChildren().addAll(player1, myPane1);

		gamePane.getChildren().addAll(vBox1, displayPane, vBox2);

		gamePane.setPadding(new Insets(5, 0, 0, 20));
		gamePane.setSpacing(5);
		ImageView imageView = new ImageView("BG.png");
		pane.getChildren().addAll(imageView, gamePane);

		root.getChildren().add(pane);

		GuiController.root = root;

		return GuiController.root;
	}

	public static HBox changeToGameScene2() {
		GameController.changeTurn();

		root.getChildren().remove(0);
		Pane pane = new Pane();
		HBox gamePane = new HBox();
		PlayPane playPane1 = GameController.getPlayPane1();
		MyPane myPane2 = GameController.getMyPane2();
		DisplayPane displayPane = new DisplayPane();

		playPane1.setPrefHeight(500);
		myPane2.setPrefHeight(500);

		VBox vBox1 = new VBox();
		VBox vBox2 = new VBox();

		vBox1.setAlignment(Pos.CENTER);
		vBox2.setAlignment(Pos.CENTER);
		vBox1.setSpacing(5);
		vBox2.setSpacing(5);

		ImageView player1 = new ImageView("Player1.png");
		player1.setFitHeight(40);
		player1.setFitWidth(211);

		ImageView player2 = new ImageView("Player2.png");
		player2.setFitHeight(40);
		player2.setFitWidth(211);

		vBox1.getChildren().addAll(player1, playPane1);
		vBox2.getChildren().addAll(player2, myPane2);

		gamePane.getChildren().addAll(vBox1, displayPane, vBox2);
		gamePane.setPadding(new Insets(5, 0, 0, 25));
		gamePane.setSpacing(5);
		ImageView imageView = new ImageView("BG.png");
		pane.getChildren().addAll(imageView, gamePane);
		;

		root.getChildren().addAll(pane);

		GuiController.root = root;

		return GuiController.root;

	}

	public static HBox endScene() {
		gameSound.stop();

		StackPane stackPane = new StackPane();
		VBox vBox = new VBox();

		root.getChildren().remove(0);

		congratulations.play();
		Button endButton = new Button();
		ImageView endpic = new ImageView("backToMainMenu.png");
		endpic.setFitWidth(266);
		endpic.setFitHeight(20);
		endButton.setGraphic(endpic);
		endButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));

		endButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImageView endpic = new ImageView("backToMainMenu.png");
				endpic.setFitWidth(292);
				endpic.setFitHeight(22);
				endButton.setGraphic(endpic);
			}
		});

		endButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ImageView endpic = new ImageView("backToMainMenu.png");
				endpic.setFitWidth(266);
				endpic.setFitHeight(20);
				endButton.setGraphic(endpic);
			}
		});

		endButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				GameController.clear();
				welcome2();

			}
		});
		vBox.setAlignment(Pos.BOTTOM_RIGHT);
		vBox.setPadding(new Insets(0, 0, 70, 370));
		vBox.getChildren().add(endButton);

		ImageView player1Win = new ImageView("Player1Win.png");
		ImageView player2Win = new ImageView("Player2Win.png");
		player1Win.setFitHeight(675);
		player1Win.setFitWidth(1200);
		player2Win.setFitHeight(675);
		player2Win.setFitWidth(1200);

		if (GameController.isPlayer1()) {
			stackPane.getChildren().add(player1Win);
		} else {
			stackPane.getChildren().add(player2Win);
		}

		stackPane.getChildren().add(vBox);

		root.getChildren().add(stackPane);
		GuiController.root = root;
		return GuiController.root;
	}

	public static HBox welcome2() {
		root.getChildren().remove(0);
		initializeWelcomeScene(root);
		GuiController.root = root;
		return GuiController.root;
	}

	private static void generateButtomBar(ImageView ins, int page) {
		// TODO Auto-generated method stub
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		// hBox.setPadding(new Insets(50));
		StackPane stackPane = new StackPane();
		Button nextButton = new Button();
		ImageView nextpic = new ImageView("right.png");
		nextpic.setFitWidth(54);
		nextpic.setFitHeight(40);
		nextButton.setGraphic(nextpic);
		nextButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));
		nextButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				click.play();
				if (page == 2) {
					instruction3();
				} else if (page == 3) {
					instruction4();
				} else if (page == 4) {
					instruction5();
				} else {
					instruction6();
				}

			}
		});
		nextButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				nextpic.setFitWidth(61);
				nextpic.setFitHeight(45);
				nextButton.setGraphic(nextpic);
			}
		});
		nextButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				nextpic.setFitWidth(54);
				nextpic.setFitHeight(40);
				nextButton.setGraphic(nextpic);
			}
		});

		Button prevButton = new Button();
		ImageView prevpic = new ImageView("left.png");
		prevpic.setFitWidth(54);
		prevpic.setFitHeight(40);
		prevButton.setGraphic(prevpic);
		prevButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));
		prevButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				click.play();
				if (page == 2) {
					instruction1();
				} else if (page == 3) {
					instruction2();
				} else if (page == 4) {
					instruction3();
				} else {
					instruction4();
				}

			}
		});
		prevButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				prevpic.setFitWidth(61);
				prevpic.setFitHeight(45);
				prevButton.setGraphic(prevpic);
			}
		});
		prevButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				prevpic.setFitWidth(54);
				prevpic.setFitHeight(40);
				prevButton.setGraphic(prevpic);
			}
		});

		Button homeButton = new Button();
		ImageView homepic = new ImageView("home.png");
		homepic.setFitWidth(45);
		homepic.setFitHeight(45);
		homeButton.setGraphic(homepic);
		homeButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));
		homeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				click.play();
				welcome2();
			}
		});
		homeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				homepic.setFitWidth(50);
				homepic.setFitHeight(50);
				homeButton.setGraphic(homepic);
			}
		});
		homeButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				homepic.setFitWidth(45);
				homepic.setFitHeight(45);
				homeButton.setGraphic(homepic);
			}
		});

		hBox.setPadding(new Insets(500, 0, 0, 0));
		hBox.setSpacing(20);
		hBox.getChildren().addAll(prevButton, homeButton, nextButton);

		stackPane.getChildren().addAll(ins, hBox);
		root.getChildren().add(stackPane);
	}

	public static HBox instruction1() {
		root.getChildren().remove(0);
		HBox hBox = new HBox();

		hBox.setAlignment(Pos.CENTER);
		// hBox.setPadding(new Insets(50));
		StackPane stackPane = new StackPane();
		Button nextButton = new Button();
		ImageView nextpic = new ImageView("right.png");
		nextpic.setFitWidth(54);
		nextpic.setFitHeight(40);
		nextButton.setGraphic(nextpic);
		nextButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));
		nextButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				click.play();
				instruction2();
			}
		});
		nextButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				nextpic.setFitWidth(61);
				nextpic.setFitHeight(45);
				nextButton.setGraphic(nextpic);
			}
		});
		nextButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				nextpic.setFitWidth(54);
				nextpic.setFitHeight(40);
				nextButton.setGraphic(nextpic);
			}
		});

		Button homeButton = new Button();
		ImageView homepic = new ImageView("home.png");
		homepic.setFitWidth(45);
		homepic.setFitHeight(45);
		homeButton.setGraphic(homepic);
		homeButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));
		homeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				click.play();
				welcome2();
			}
		});
		homeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				homepic.setFitWidth(50);
				homepic.setFitHeight(50);
				homeButton.setGraphic(homepic);
			}
		});
		homeButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				homepic.setFitWidth(45);
				homepic.setFitHeight(45);
				homeButton.setGraphic(homepic);
			}
		});

		hBox.setPadding(new Insets(500, 0, 0, 0));
		hBox.setSpacing(20);
		hBox.getChildren().addAll(homeButton, nextButton);

		ImageView ins1 = new ImageView("Instruction1.png");
		stackPane.getChildren().addAll(ins1, hBox);
		root.getChildren().add(stackPane);
		GuiController.root = root;
		return GuiController.root;
	}

	public static HBox instruction2() {
		root.getChildren().remove(0);
		ImageView ins2 = new ImageView("Instruction2.png");
		generateButtomBar(ins2, 2);

		GuiController.root = root;
		return GuiController.root;
	}

	public static HBox instruction3() {
		root.getChildren().remove(0);

		ImageView ins3 = new ImageView("Instruction3.png");
		generateButtomBar(ins3, 3);
		GuiController.root = root;
		return GuiController.root;
	}

	public static HBox instruction4() {
		root.getChildren().remove(0);
		ImageView ins4 = new ImageView("Instruction4.png");
		generateButtomBar(ins4, 4);
		GuiController.root = root;
		return GuiController.root;
	}

	public static HBox instruction5() {
		root.getChildren().remove(0);
		ImageView ins5 = new ImageView("Instruction5.png");
		generateButtomBar(ins5, 5);
		GuiController.root = root;
		return GuiController.root;
	}

	public static HBox instruction6() {
		root.getChildren().remove(0);
		HBox hBox = new HBox();

		hBox.setAlignment(Pos.CENTER);
		// hBox.setPadding(new Insets(50));
		StackPane stackPane = new StackPane();
		Button prevButton = new Button();
		ImageView prevpic = new ImageView("left.png");
		prevpic.setFitWidth(54);
		prevpic.setFitHeight(40);
		prevButton.setGraphic(prevpic);
		prevButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));
		prevButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				click.play();
				instruction5();
			}
		});
		prevButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				prevpic.setFitWidth(61);
				prevpic.setFitHeight(45);
				prevButton.setGraphic(prevpic);
			}
		});
		prevButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				prevpic.setFitWidth(54);
				prevpic.setFitHeight(40);
				prevButton.setGraphic(prevpic);
			}
		});

		Button homeButton = new Button();
		ImageView homepic = new ImageView("home.png");
		homepic.setFitWidth(45);
		homepic.setFitHeight(45);
		homeButton.setGraphic(homepic);
		homeButton.setBackground(
				new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(10), Insets.EMPTY)));
		homeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				click.play();
				welcome2();
			}
		});
		homeButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				homepic.setFitWidth(50);
				homepic.setFitHeight(50);
				homeButton.setGraphic(homepic);
			}
		});
		homeButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				homepic.setFitWidth(45);
				homepic.setFitHeight(45);
				homeButton.setGraphic(homepic);
			}
		});

		hBox.setPadding(new Insets(500, 0, 0, 0));
		hBox.setSpacing(20);
		hBox.getChildren().addAll(prevButton, homeButton);

		ImageView ins6 = new ImageView("Instruction6.png");
		stackPane.getChildren().addAll(ins6, hBox);
		root.getChildren().add(stackPane);
		GuiController.root = root;
		return GuiController.root;
	}
}
