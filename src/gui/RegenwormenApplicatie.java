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
        spelerButton.setOnMouseClicked(e -> window.setScene(spelerScene));
        gaTerug.setOnMouseClicked(e -> window.setScene(startScene));
        voegToe.setOnMouseClicked(e -> dc.maakSpelerAan(spelerNaam.getText(),spelerWachtwoord.getText(),spelerGeboorteDatum.getText()));
        
        //speelscherm
        GridPane speelGrid = new GridPane();
        spelerGrid.setAlignment(Pos.CENTER);
        Scene speelScene = new Scene(speelGrid, 300, 200);
        Button stopSpel = new Button("stop spel");
        VBox speelBox = new VBox();
        speelBox.getChildren().addAll(stopSpel);
        speelGrid.getChildren().addAll(speelBox);
        startButton.setOnMouseClicked(e -> window.setScene(speelScene));
      
        
        
        //selecteerSpelersScherm
        GridPane selecteerGrid = new GridPane();
        spelerGrid.setAlignment(Pos.CENTER);
        Scene selecteerScene = new Scene(selecteerGrid, 300, 200);
        Button selecteerSpeler = new Button();
    /**    ArrayList<String> Spelers = dc.toonSpelers();
        if(Spelers.isEmpty()){
            Spelers.add("geen spelers");
        }*/
        ChoiceBox spelerCB = new ChoiceBox(); 
        PasswordField geefWW = new PasswordField();
        Label ww = new Label("geef het wachtwoord");
        selecteerGrid.getChildren().addAll(spelerCB,ww,geefWW,selecteerSpeler);
        
        selecteerSpeler.setOnMouseClicked(e->{
            try {
                dc.voegSpelerToe(spelerCB.toString(),geefWW.getText());
                window.setScene(speelScene);
            } catch (Exception ex) {
                Logger.getLogger(RegenwormenApplicatie.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
 
        //effective spel
        dc.maakTegels();
        int aantalSpelers = 0;
        boolean exceptionGegooid = false;
      
       
     /**  
        do {
            try {
                System.out.print("Geef het aantal spelers in: ");

                aantalSpelers = input.nextInt();
                if (aantalSpelers < 2 || aantalSpelers > 7) {
                    throw new IllegalArgumentException();
                }
                exceptionGegooid = false;
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Het aantal spelers ligt niet tussen 2 en 7");
                exceptionGegooid = true;
                input.next();
            }
        } while (exceptionGegooid);
        String[] namen = new String[aantalSpelers];
        LocalDate[] leeftijden = new LocalDate[aantalSpelers];
        for (int i = 0; i < namen.length; i++) {

            System.out.printf("geef de naam voor speler %d: ", i + 1);
            namen[i] = input.next();
            do {

                System.out.printf("geef de geboortedatum in voor speler %d, dd-mm-yyyy:", i + 1);
                String date = input.next();

                SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

                Date dateRaw = null;
                try {
                    dateRaw = format.parse(date);
                    exceptionGegooid = false;

                    LocalDate dateConverted = LocalDate.ofInstant(dateRaw.toInstant(), ZoneId.systemDefault());
                    leeftijden[i] = dateConverted;
                } catch (ParseException ex) {
                    System.out.println("De ingegeven datum klopt niet. Probeer het opnieuw.");
                    exceptionGegooid = true;
                }
            } while (exceptionGegooid);

        }
        dc.maakSpelers(aantalSpelers, namen, leeftijden, LocalDate.now());
        ArrayList spelerLijst = dc.getSpelerLijst();

        while (!dc.isEindeSpel()) {
            for (int i = 0; i < namen.length; i++) {
                boolean eindBeurtChecker = false;
                System.out.printf("het is %s's beurt%n", dc.getGesorteerdeSpelerNaam(i));

                while (!eindBeurtChecker) {
                    dc.stelDobbelstenenArrayIn();
                    System.out.println("Je rolde: ");
                    System.out.println(dc.getDobbelstenenString());

                    System.out.print("Geef het getal in dat je wil nemen (6 voor regenworm): ");
                    boolean vergelijkChecker = false;
                    int gekozenGetal = input.nextInt();
                    while (!vergelijkChecker) {
                        vergelijkChecker = dc.vergelijkGekozenGetalMetArrayDobbelstenen(gekozenGetal);
                        if (vergelijkChecker) {
                            int score = dc.berekenScore(gekozenGetal);
                            System.out.printf("De score van deze worp is %d%n", score);
                            dc.voegScoreSpelerToeAanTotaalScore(score);
                            System.out.printf("Je totaal score bedraagt nu: %d%n", dc.getTotaalScore());
                            int guard = 2;
                            if (dc.getTotaalScore() >= 21) {
                                System.out.print("Wenst u te stoppen? Geef 1 in voor ja, een ander getal voor nee: ");
                                guard = input.nextInt();
                            }
                            eindBeurtChecker = dc.checkOfEindeSpel(guard, gekozenGetal);
                        } else {
                            System.out.println("Het getal is al gekozen, geef een nieuw getal in: ");
                            gekozenGetal = input.nextInt();
                        }
                    }
                    if (eindBeurtChecker) {
                        System.out.printf("de spelers hebben volgende tegels: %s", dc.toonTegelsVanSpelers().toString());
                        System.out.printf("beschikbare tegels: %s", dc.toonTegels(dc.getTotaalScore()).toString());
                        System.out.print("Geef het getal in van de tegel die je wil nemen: ");
                        int gekozenTegel = input.nextInt();
                        dc.kiesTegel(gekozenTegel);
                        dc.isEindeSpel();
                    }
                }
            }
        }
        */
        
        
        
      

    }

}
