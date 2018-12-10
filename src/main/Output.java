package main;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Output extends Application{

	private Stage stage;

	static int x, y;
	static int integerPixel, red, green, blue;
	static String hexadecimal;

	static SimpleDateFormat dateFormat;

	//yeye i know that the code looks horrible but it works
	@Override
	public void start(Stage arg0) throws Exception {
		stage = arg0;
		stage.setWidth(300);
		stage.setHeight(400);
		stage.setTitle("Color Picker Output");
		stage.setResizable(false);

		Scene scene = new Scene(new Group());
		BorderPane root = new BorderPane();

		VBox top = new VBox();
		top.setAlignment(Pos.CENTER);
		top.setSpacing(18);
		root.setTop(top);

		Label header = new Label("Color Picker Output:");
		prepLabel(header, 25);
		header.setPadding(new Insets(15, 10, 0, 0));
		top.getChildren().add(header);

		top.getChildren().add(new Separator());

		VBox center = new VBox(10);
		center.setPadding(new Insets(10, 10, 10, 10));
		root.setCenter(center);

		//display the output

		//first grid
		GridPane grid1 = new GridPane();
		grid1.setVgap(10);
		grid1.setHgap(20);
		grid1.setAlignment(Pos.TOP_LEFT);

		Label coordsLabel = new Label("Pixel Coords(x, y):");
		prepLabel(coordsLabel, 14);
		TextField coordsField = new TextField(x + ", " + y);
		coordsField.setMaxWidth(80);
		coordsField.setEditable(false);

		Label integerPixelLabel = new Label("Integer Pixel:");
		prepLabel(integerPixelLabel, 14);
		TextField integerPixelField = new TextField(Integer.toString(integerPixel));
		integerPixelField.setMaxWidth(80);
		integerPixelField.setEditable(false);

		Label hexadecimalLabel = new Label("Hexadecimal (HEX):");
		prepLabel(hexadecimalLabel, 14);
		TextField hexadecimalField = new TextField(hexadecimal);
		hexadecimalField.setMaxWidth(80);
		hexadecimalField.setEditable(false);



		//second grid
		HBox grid2divider = new HBox(10);
		grid2divider.setMinWidth(150);
		grid2divider.setFillHeight(true);
		center.setFillWidth(true);

		GridPane grid2 = new GridPane();
		grid2.setVgap(10);
		grid2.setHgap(20);
		grid2.setAlignment(Pos.TOP_LEFT);

		Label RGBheader = new Label("RGB Values:");
		prepLabel(RGBheader, 18);

		Label redLabel = new Label("RED:");
		TextField redField = new TextField(Integer.toString(red));
		redField.setMaxWidth(80);
		redField.setEditable(false);

		Label greenLabel = new Label("GREEN:");
		TextField greenField = new TextField(Integer.toString(green));
		greenField.setMaxWidth(80);
		greenField.setEditable(false);

		Label blueLabel = new Label("BLUE:");
		TextField blueField = new TextField(Integer.toString(blue));
		blueField.setMaxWidth(80);
		blueField.setEditable(false);

		VBox colorBox = new VBox();
		colorBox.setStyle("-fx-background-color: #" + hexadecimal+";"
				+ "-fx-background-radius: 10 10 10 10;");
		HBox.setHgrow(colorBox, Priority.ALWAYS);

		grid2divider.getChildren().addAll(grid2, colorBox);



		//add all the components
		center.getChildren().add(grid1);
		grid1.addRow(0, coordsLabel, coordsField);
		grid1.addRow(1, integerPixelLabel, integerPixelField);
		grid1.addRow(2, hexadecimalLabel, hexadecimalField);
		center.getChildren().add(new Separator());
		center.getChildren().add(RGBheader);
		center.getChildren().add(grid2divider);
		grid2.addRow(0, redLabel, redField);
		grid2.addRow(1, greenLabel, greenField);
		grid2.addRow(2, blueLabel, blueField);
		center.getChildren().add(new Separator());

		//just because why not :D
		Label copyright = new Label("© 2018 Jompda. All rights reserved.");
		center.getChildren().add(copyright);

		//display the stage and do some little tweaking..
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();

		stage.setOnCloseRequest(e -> {
			printInfo("Exiting..");
			//System.exit(0);
		});

	}

	private void prepLabel(Label label, int fontSize) {
		label.setFont(Font.font("Liberation Serif", FontWeight.BOLD, fontSize));
	}

	static void printInfo(String str) {
		Date date = new Date();
		System.out.println("[" + dateFormat.format(date) + "] INFO: " + str);
	}

	static void init(String[] args) {
		launch(args);
	}

}
