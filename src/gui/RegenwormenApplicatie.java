package gui;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import domein.DomeinController;
import javafx.event.EventType;
import javafx.scene.control.TextField;
        
        
public class RegenwormenApplicatie extends Application {
    
    DomeinController dc = new DomeinController();
    Stage window;
    Scene speelScherm;
    Scene spelerScherm;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Regenwormen");
        
        //welkomscherm
        GridPane startGrid = new GridPane();
        startGrid.setAlignment(Pos.CENTER);
        Label welkomLabel = new Label("Welkom bij het spel \"Regenwormen\"");
        Button startButton = new Button("speel");
        Button spelerButton = new Button("Spelers");
        
        
        VBox welkomBox = new VBox();
        welkomBox.getChildren().addAll(welkomLabel, startButton,spelerButton);

        startGrid.getChildren().addAll(welkomBox);
        welkomBox.setAlignment(Pos.CENTER);
        Scene startScene = new Scene(startGrid, 300, 200);
        window.setScene(startScene);
        window.show();
        
        //spelerScherm
        GridPane spelerGrid = new GridPane();
        spelerGrid.setAlignment(Pos.CENTER);
        Scene spelerScene = new Scene(spelerGrid,300,200);
        Button voegToe = new Button("voeg speler toe");
        voegToe.setAlignment(Pos.BASELINE_RIGHT);
        Label vraagNaam = new Label("Geef de naam in van de nieuwe speler");
        TextField SpelerNaam = new TextField("naam");
        Label vraagGeboorteDatum = new Label("geef de geboorteDatum in van de nieuwe speler");
        TextField spelerGeboorteDatum = new TextField("dd-mm-yyyy");
        Button gaTerug = new Button("startscherm");
        VBox spelerBox = new VBox();
        spelerBox.getChildren().addAll(vraagNaam,SpelerNaam,vraagGeboorteDatum,spelerGeboorteDatum,voegToe,gaTerug);
        spelerGrid.getChildren().addAll(spelerBox);
        spelerButton.setOnMouseClicked(e -> window.setScene(spelerScene));
        gaTerug.setOnMouseClicked(e -> window.setScene(startScene));
        
        //speelscherm
        GridPane speelGrid = new GridPane();
        spelerGrid.setAlignment(Pos.CENTER);
        Scene speelScene = new Scene(speelGrid, 300, 200);
        Button stopSpel = new Button("stop spel");
        VBox speelBox = new VBox();
        speelBox.getChildren().addAll(stopSpel);
        speelGrid.getChildren().addAll(speelBox);
        startButton.setOnMouseClicked(e -> window.setScene(speelScene));
        stopSpel.setOnMouseClicked(e -> window.setScene(startScene));
    }
   
    
            
            
            
            
            
            
            
            
            
  
}
