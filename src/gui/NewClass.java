import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewClass extends Application {

    Stage window;
    Scene speelScherm;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Regenwormen");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Label welkomLabel = new Label("Welkom bij het spel \"Regenwormen\"");
        Button startButton = new Button("Klik hier om te beginnen");
        startButton.setOnAction(e -> window.setScene(speelScherm));
        VBox test = new VBox();
        test.getChildren().addAll(welkomLabel, startButton);

        grid.getChildren().addAll(test);
        test.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();
    }
    
    public void welkomScherm(){
                GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Label welkomLabel = new Label("Welkom bij het spel \"Regenwormen\"");
        Button startButton = new Button("Klik hier om te beginnen");
        VBox test = new VBox();
        test.getChildren().addAll(welkomLabel, startButton);

        grid.getChildren().addAll(test);
        test.setAlignment(Pos.CENTER);
        speelScherm = new Scene(grid, 300, 200);
                
    }
}
