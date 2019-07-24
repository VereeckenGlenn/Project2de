package gui;

import domein.DomeinController;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import images.*;
import javafx.scene.image.*;
import javafx.geometry.Insets;


public class RegenwormenApplicatie extends Application {
    
    DomeinController dc;
    Stage window;
    Scene speelScherm;
    Scene spelerScherm;
    
   public static void main(String[] args) {
        launch(args);
    } 

    public RegenwormenApplicatie() {
        try {
            this.dc = new DomeinController();
        } catch (ClassNotFoundException ex) {
            System.err.print("class not found");
        }
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
        welkomBox.getChildren().addAll(welkomLabel, startButton, spelerButton);

        startGrid.getChildren().addAll(welkomBox);
        welkomBox.setAlignment(Pos.CENTER);
        Scene startScene = new Scene(startGrid, 300, 200);
        window.setScene(startScene);
        window.show();

        //spelerScherm
        GridPane spelerGrid = new GridPane();
        spelerGrid.setAlignment(Pos.CENTER);
        Scene spelerScene = new Scene(spelerGrid, 300, 200);
        Button voegToe = new Button("voeg speler toe");
        voegToe.setAlignment(Pos.BASELINE_RIGHT);
        Label vraagNaam = new Label("Geef de naam in van de nieuwe speler");
        TextField spelerNaam = new TextField("naam");
        Label vraagGeboorteDatum = new Label("geef de geboorteDatum in van de nieuwe speler");
        TextField spelerGeboorteDatum = new TextField("dd-mm-yyyy");
        Label vraagWachtwoord = new Label("geef een wachtwoord voor de nieuwe speler");
        PasswordField spelerWachtwoord = new PasswordField();
        Button gaTerug = new Button("startscherm");
        VBox spelerBox = new VBox();
        spelerBox.getChildren().addAll(vraagNaam, spelerNaam, vraagWachtwoord, spelerWachtwoord, vraagGeboorteDatum, spelerGeboorteDatum, voegToe, gaTerug);
        spelerGrid.getChildren().addAll(spelerBox);
        Alert SpelerAangemaakt = new Alert(AlertType.INFORMATION);
        SpelerAangemaakt.setHeaderText("Speler is aangemaakt!");
        spelerButton.setOnMouseClicked(e -> window.setScene(spelerScene));
        gaTerug.setOnMouseClicked(e -> window.setScene(startScene));
        voegToe.setOnMouseClicked(e -> {
                dc.maakSpelerAan(spelerNaam.getText(),spelerWachtwoord.getText(),spelerGeboorteDatum.getText());
                SpelerAangemaakt.show();
        });

         //selecteerSpelersScherm
        GridPane selecteerGrid = new GridPane();
        selecteerGrid.setAlignment(Pos.CENTER);
        Scene selecteerScene = new Scene(selecteerGrid, 300, 200);   
        VBox selecteerBox1 = new VBox();
        selecteerBox1.setAlignment(Pos.CENTER_LEFT);
        VBox selecteerBox2 = new VBox();
        selecteerBox2.setAlignment(Pos.CENTER);
        VBox selecteerBox3 = new VBox();
        selecteerBox3.setAlignment(Pos.BOTTOM_RIGHT);
        Insets selectPadding = new Insets(50,50,50,50);
        selecteerBox2.setPadding(selectPadding);
        Label speler1 =  new Label("");
        Label speler2 =  new Label("");
        Label speler3 =  new Label("");
        Label speler4 =  new Label("");
        Label speler5 = new Label("");
        Label speler6 = new Label("");
        Label speler7 =  new Label("");
        Label speler8 = new Label("");
        Button voegSpelerToe = new Button("voeg speler toe");
        Button naarSpel = new Button("begin spel");
        selecteerBox1.getChildren().addAll(speler1, speler3,speler5,speler7);
        selecteerBox2.getChildren().addAll(speler2,speler4,speler6,speler8);
        selecteerBox3.getChildren().addAll(voegSpelerToe,naarSpel);
        selecteerGrid.getChildren().addAll(selecteerBox1,selecteerBox2,selecteerBox3);
        
            // selecteerSpelerScherm part 2
            GridPane selecteerSpelerGrid = new GridPane();
            selecteerSpelerGrid.setAlignment(Pos.CENTER);
            Scene selecteerSpelerScene = new Scene(selecteerSpelerGrid,300,200);
            VBox SelecteerSpelerBox = new VBox();
            Label spelerNaamLabel = new Label("geef de naam in voor de speler");
            TextField spelerNaamField = new TextField("naam");
            Label wwLabel = new Label("geef het wachtwoord in voor de speler");
            PasswordField wwField = new PasswordField();
            Button confirm  = new Button("voeg Toe");
            Alert spelerToegevoegd = new Alert(AlertType.INFORMATION);
            Alert spelerNietToegevoegd = new Alert(AlertType.ERROR);
            spelerToegevoegd.setHeaderText("speler toegevoegd");
            spelerNietToegevoegd.setHeaderText("speler bestaat niet of wachtwoord is onjuist");
            selecteerSpelerGrid.getChildren().addAll(SelecteerSpelerBox);
            SelecteerSpelerBox.getChildren().addAll(spelerNaamLabel,spelerNaamField,wwLabel,wwField,confirm);
            

            //speelscherm
            GridPane speelGrid = new GridPane();
            spelerGrid.setAlignment(Pos.CENTER);
            Scene speelScene = new Scene(speelGrid, 300, 200);
            //Image t21 = new Image("..\\images\21.png");
            //Button tile21 = new Button();
            Button tile22 = new Button();
            Button tile23 = new Button();
            Button tile24 = new Button();
            Button tile25 = new Button();
            Button tile26 = new Button();
            Button tile27 = new Button();
            Button tile28 = new Button();
            Button tile29 = new Button();
            Button tile30 = new Button();
            Button tile32 = new Button();
            Button tile33 = new Button();
            Button tile34 = new Button();
            Button tile35 = new Button();
            Button tile36 = new Button();
            Button dobbelsteen1 = new Button();
            Button dobbelsteen2 = new Button();
            Button dobbelsteen3 = new Button();
            Button dobbelsteen4 = new Button();
            Button dobbelsteen5 = new Button();
            Button dobbelsteenRegenworm = new Button();
            Label aanBeurt = new Label();
            Button rolDobbelstenen = new Button("rol dobbelstenen");
            Button eindeBeurt = new Button("eindig beurt");
            VBox speelBox = new VBox();
            speelBox.getChildren().addAll();
            speelGrid.getChildren().addAll(speelBox);
            
            confirm.setOnMouseClicked(e -> {
                int count = dc.getAantalSpelers();
                if(dc.ControleerSpelerNaamEnWachtwoord(spelerNaamField.getText(),wwField.getText())){
                    switch(count){
                    case 1 : speler2.setText(spelerNaamField.getText()); break;
                    case 2 : speler3.setText(spelerNaamField.getText()); break;
                    case 3 : speler4.setText(spelerNaamField.getText()); break;
                    case 4 : speler5.setText(spelerNaamField.getText()); break;
                    case 5 : speler6.setText(spelerNaamField.getText()); break;
                    case 6 : speler7.setText(spelerNaamField.getText()); break;
                    case 7 : speler8.setText(spelerNaamField.getText()); break;
                    default: speler1.setText(spelerNaamField.getText()); break;
                }
                    dc.setAantalSpelers(dc.getAantalSpelers()+1);
                    spelerToegevoegd.show();
                    window.setScene(selecteerScene);
                }else{
                    spelerNietToegevoegd.show();
                }
            });
            voegSpelerToe.setOnMouseClicked(e -> window.setScene(selecteerSpelerScene));
            startButton.setOnMouseClicked(e -> window.setScene(selecteerScene));
            naarSpel.setOnMouseClicked(e ->{
                dc.maakTegels();
                window.setScene(speelScene);
                    });
            eindeBeurt.setOnMouseClicked(e -> dc.EindeBeurt());    
            rolDobbelstenen.setOnMouseClicked(e->{
                dc.stelDobbelstenenArrayIn();
                ArrayList dobbelstenen = dc.getDobbelstenen();
                for (Object dobbelsteen : dobbelstenen) {
                 int d = (int) dobbelsteen;
                 switch(d){
                     case 1:
                     case 2:
                     case 3:
                     case 4:
                     case 5:
                     default:
                 }
            }
            });        
            
            //effective spel
            
        
           
            
     
       /**
         * 
         *
         * while (!dc.isEindeSpel()) { for (int i = 0; i < namen.length; i++) {
         * boolean eindBeurtChecker = false;
         * System.out.printf("het is %s's beurt%n", dc.getGesorteerdeSpelerNaam(i));
         *
         * while (!eindBeurtChecker) {
         * 
         * System.out.println("Je rolde: ");
         * System.out.println(dc.getDobbelstenenString());
         *
         * System.out.print("Geef het getal in dat je wil nemen (6 voor regenworm): ");
         * boolean vergelijkChecker = false;
         * int gekozenGetal = input.nextInt();
         * while (!vergelijkChecker) {
         * vergelijkChecker = dc.vergelijkGekozenGetalMetArrayDobbelstenen(gekozenGetal);
         * if (vergelijkChecker) {
         * int score = dc.berekenScore(gekozenGetal);
         * System.out.printf("De score van deze worp is %d%n", score);
         * dc.voegScoreSpelerToeAanTotaalScore(score);
         * System.out.printf("Je totaal score bedraagt nu: %d%n", dc.getTotaalScore());
         * int guard = 2;
         * if (dc.getTotaalScore() >= 21) { System.out.print("Wenst u te
         * stoppen? Geef 1 in voor ja, een ander getal voor nee: "); guard =
         * input.nextInt(); } eindBeurtChecker = dc.checkOfEindeSpel(guard,
         * gekozenGetal); } else { System.out.println("Het getal is al gekozen,
         * geef een nieuw getal in: "); gekozenGetal = input.nextInt(); } } if
         * (eindBeurtChecker) { System.out.printf("de spelers hebben volgende
         * tegels: %s", dc.toonTegelsVanSpelers().toString());
         * System.out.printf("beschikbare tegels: %s",
         * dc.toonTegels(dc.getTotaalScore()).toString());
         * System.out.print("Geef het getal in van de tegel die je wil nemen:
         * "); int gekozenTegel = input.nextInt(); dc.kiesTegel(gekozenTegel);
         * dc.isEindeSpel(); } } } }
         */
        }}
