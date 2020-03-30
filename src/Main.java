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

public class Main extends Application {

    private static Face face = new Face();
   //private static Face face = null;
    /*
        private static Board theBoard;

        public static Face getFace() {
            return face;
        }
        public static Board getTheBoard() {
            return theBoard;
        }
    */
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
        myRootPane.setCenter(drawArenaV2(10, 10, 20));
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


        myHeader.getChildren().add(face);


        StackPane.setAlignment(mineCounter, Pos.CENTER_LEFT);
        StackPane.setAlignment(face, Pos.CENTER);
        StackPane.setAlignment(playerTimer, Pos.CENTER_RIGHT);

        return myHeader;
    }

    public static GridPane drawArenaV2(int cols, int rows, int mines) {
        Board theBoard = new Board(cols, rows, mines, face);
        //theBoard.addMines(30, 1, 1);
        //theBoard.addMinesFixed();
        //theBoard.solveProximityCount();
        return theBoard.returnGridPane();
    }




}
