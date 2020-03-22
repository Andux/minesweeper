import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(myStartUp());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    public static Scene myStartUp() {
        BorderPane myRootPane = new BorderPane();
        myRootPane.setTop(drawHeader());
        myRootPane.setCenter(drawArenaV2(0));
        Scene myScene = new Scene(myRootPane);

        return myScene;
    }

    public static StackPane drawHeader() {
        StackPane myHeader = new StackPane();

        HBox mineCounter = new HBox();
        Label myLabel = new Label("Mine Counter");
        //myLabel.setAlignment(Pos.CENTER);
        mineCounter.getChildren().add((myLabel));
        myHeader.getChildren().add(mineCounter);


        Text playerTimer = new Text("Player Timer");
        myHeader.getChildren().add(playerTimer);

        Face face = new Face();

        myHeader.getChildren().add(face);


        StackPane.setAlignment(mineCounter, Pos.CENTER_LEFT);
        StackPane.setAlignment(face, Pos.CENTER);
        StackPane.setAlignment(playerTimer, Pos.CENTER_RIGHT);

        return myHeader;
    }

    public static GridPane drawArenaV2(int mineCount) {
        Board theBoard = new Board(10, 10);
        theBoard.addMines(91, 1, 1);
        //theBoard.addMinesFixed();
        theBoard.solveProximityCount();
        return theBoard.returnGridPane();
    }

}
