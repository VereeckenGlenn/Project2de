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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class RegenwormenApplicatie extends Application {
    
    DomeinController dc;
    Stage window;
    
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
            BorderPane speelGrid = new BorderPane();
            spelerGrid.setAlignment(Pos.CENTER);
            Scene speelScene = new Scene(speelGrid, 300, 200);
            
            HBox dobbelBox = new HBox();
            
            Image dobbelsteen1 = new Image("https://imgur.com/Hb1yzNy.png");
            Image dobbelsteen2 = new Image("https://imgur.com/Hb1yzNy.png");
            Image dobbelsteen3 = new Image("https://imgur.com/sLvt8uQ.png");
            Image dobbelsteen4 = new Image("https://imgur.com/sLvt8uQ.png");
            Image dobbelsteen5 = new Image("https://imgur.com/oKnxkzL.png");
            Image dobbelsteenRegenworm = new Image("https://imgur.com/WTqfvL1.png");
            Label aanBeurt = new Label();
            aanBeurt.setFont(Font.font(STYLESHEET_MODENA,50));
            Insets aanBeurtInset = new Insets(0,10,0,0);
            aanBeurt.setPadding(aanBeurtInset);
            Button rolDobbelstenen = new Button("rol dobbelstenen");
            Button eindeBeurt = new Button("eindig beurt");
            Alert dobbelAlert = new Alert(AlertType.INFORMATION);
            dobbelAlert.setHeaderText("het geselecteerde getal is al is gekozen, probeer opniew");
            Button naarTegels = new Button("kies tegel");
            Label totaalScore = new Label();
            HBox topBox = new HBox();
            HBox bottemBox = new HBox();
            topBox.getChildren().addAll(aanBeurt);
            ImageView roll1 = new ImageView();
            ImageView roll2 = new ImageView();
            ImageView roll3 = new ImageView();
            ImageView roll4 = new ImageView();
            ImageView roll5 = new ImageView();
            ImageView roll6 = new ImageView();
            ImageView roll7 = new ImageView();
            ImageView roll8 = new ImageView();
            dobbelBox.getChildren().addAll(roll1,roll2,roll3,roll4,roll5,roll6,roll7,roll8);
            bottemBox.getChildren().addAll(rolDobbelstenen,eindeBeurt,naarTegels);
            bottemBox.setAlignment(Pos.CENTER_RIGHT);
            speelGrid.setTop(topBox);
            speelGrid.setRight(totaalScore);
            speelGrid.setCenter(dobbelBox);
            speelGrid.setBottom(bottemBox);
            Alert eindeBeurtAlert = new Alert(AlertType.INFORMATION);
            eindeBeurtAlert.setHeaderText("als je op ok drukt, eindig je de beurt zonder tegel.");
            
            
            
            //tegelscherm
            BorderPane tegelPane = new BorderPane();
            Scene tegelScene = new Scene(tegelPane,300,200);
            HBox tileBox = new HBox();
            ImageView tile21 = new ImageView("https://imgur.com/aQNJujv.png");
            ImageView tile22 = new ImageView("https://imgur.com/cEUpShY.png");
            ImageView tile23 = new ImageView("https://imgur.com/7Zm7IWm.png");
            ImageView tile24 = new ImageView("https://imgur.com/w3oBE3g.png");
            ImageView tile25 = new ImageView("https://imgur.com/6bgMl8y.png");
            ImageView tile26 = new ImageView("https://imgur.com/DVLkdFW.png");
            ImageView tile27 = new ImageView("https://imgur.com/fSwAMeD.png");
            ImageView tile28 = new ImageView("https://imgur.com/tLXhm1y.png");
            ImageView tile29 = new ImageView("https://imgur.com/xKP3OUz.png");
            ImageView tile30 = new ImageView("https://imgur.com/Iq11Tbt.png");
            ImageView tile31 = new ImageView("https://imgur.com/5xdaSvB.png");
            ImageView tile32 = new ImageView("https://imgur.com/5xdaSvB.png");
            ImageView tile33 = new ImageView("https://imgur.com/aWmyCnb.png");
            ImageView tile34 = new ImageView("https://imgur.com/rp9NOjp.png");
            ImageView tile35 = new ImageView("https://imgur.com/ZGBhNJy.png");
            ImageView tile36 = new ImageView("https://imgur.com/ZGBhNJy.png");
            tileBox.getChildren().addAll(tile21,tile22,tile23,tile24,tile25,tile26,tile27,tile28,tile29,tile30,tile31,tile32,tile33,tile34,tile35,tile36);
            ImageView tile1 = new ImageView();
            ImageView tile2 = new ImageView();
            ImageView tile3 = new ImageView();
            ImageView tile4 = new ImageView();
            ImageView tile5 = new ImageView();
            ImageView tile6 = new ImageView();
            ImageView tile7 = new ImageView();
            ImageView tile8 = new ImageView();
            
            
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
                dc.SorteerSpelersOpGeboorteDatum(dc.getAantalSpelers(), LocalDate.now());
                dc.maakTegels();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                window.setScene(speelScene);
                    });
            eindeBeurt.setOnMouseClicked(e -> {
                   eindeBeurtAlert.showAndWait().ifPresent(response -> {
                       if (response == ButtonType.OK) {
                           dc.EindeBeurt();
                           aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                             }
                        });
                    });    
            
            rolDobbelstenen.setOnMouseClicked(e->{
                dc.stelDobbelstenenArrayIn();
                ArrayList dobbelstenen = dc.getDobbelstenen();
                 int aantalDobbelstenen = dobbelstenen.size();
                 while(aantalDobbelstenen>0){
                 if(aantalDobbelstenen == 8){
                     int d = (int) dobbelstenen.get(7);
                      switch(d){
                     case 1: roll8.setImage(dobbelsteen1); break;
                     case 2: roll8.setImage(dobbelsteen2); break;
                     case 3: roll8.setImage(dobbelsteen3); break;
                     case 4: roll8.setImage(dobbelsteen4); break;
                     case 5: roll8.setImage(dobbelsteen5); break;
                     default: roll8.setImage(dobbelsteenRegenworm); break;
                 }
                      aantalDobbelstenen--;
                      
                  }  
                if(aantalDobbelstenen == 7){
                     int d = (int) dobbelstenen.get(6);
                      switch(d){
                     case 1: roll7.setImage(dobbelsteen1); break;
                     case 2: roll7.setImage(dobbelsteen2); break;
                     case 3: roll7.setImage(dobbelsteen3); break;
                     case 4: roll7.setImage(dobbelsteen4); break;
                     case 5: roll7.setImage(dobbelsteen5); break;
                     default: roll7.setImage(dobbelsteenRegenworm); break;
                 }
                      aantalDobbelstenen--;
                  }  
                if(aantalDobbelstenen == 6){
                     int d = (int) dobbelstenen.get(5);
                      switch(d){
                     case 1: roll6.setImage(dobbelsteen1); break;
                     case 2: roll6.setImage(dobbelsteen2); break;
                     case 3: roll6.setImage(dobbelsteen3); break;
                     case 4: roll6.setImage(dobbelsteen4); break;
                     case 5: roll6.setImage(dobbelsteen5); break;
                     default: roll6.setImage(dobbelsteenRegenworm); break;
                 }
                      aantalDobbelstenen--;
                  } 
                 if(aantalDobbelstenen == 5){
                     int d = (int) dobbelstenen.get(4);
                      switch(d){
                     case 1: roll5.setImage(dobbelsteen1); break;
                     case 2: roll5.setImage(dobbelsteen2); break;
                     case 3: roll5.setImage(dobbelsteen3); break;
                     case 4: roll5.setImage(dobbelsteen4); break;
                     case 5: roll5.setImage(dobbelsteen5); break;
                     default: roll5.setImage(dobbelsteenRegenworm); break;
                 }
                      aantalDobbelstenen--;
                  } 
                 if(aantalDobbelstenen == 4){
                     int d = (int) dobbelstenen.get(3);
                      switch(d){
                     case 1: roll4.setImage(dobbelsteen1); break;
                     case 2: roll4.setImage(dobbelsteen2); break;
                     case 3: roll4.setImage(dobbelsteen3); break;
                     case 4: roll4.setImage(dobbelsteen4); break;
                     case 5: roll4.setImage(dobbelsteen5); break;
                     default: roll4.setImage(dobbelsteenRegenworm); break;
                 }
                      aantalDobbelstenen--;
                  } 
                 if(aantalDobbelstenen == 3){
                     int d = (int) dobbelstenen.get(2);
                      switch(d){
                     case 1: roll3.setImage(dobbelsteen1); break;
                     case 2: roll3.setImage(dobbelsteen2); break;
                     case 3: roll3.setImage(dobbelsteen3); break;
                     case 4: roll3.setImage(dobbelsteen4); break;
                     case 5: roll3.setImage(dobbelsteen5); break;
                     default: roll3.setImage(dobbelsteenRegenworm); break;
                 }
                      aantalDobbelstenen--;
                  } 
                 if(aantalDobbelstenen == 2){
                     int d = (int) dobbelstenen.get(1);
                      switch(d){
                     case 1: roll2.setImage(dobbelsteen1); break;
                     case 2: roll2.setImage(dobbelsteen2); break;
                     case 3: roll2.setImage(dobbelsteen3); break;
                     case 4: roll2.setImage(dobbelsteen4); break;
                     case 5: roll2.setImage(dobbelsteen5); break;
                     default: roll2.setImage(dobbelsteenRegenworm); break;
                 }
                      aantalDobbelstenen--;
                  } 
                 if(aantalDobbelstenen == 1){
                     int d = (int) dobbelstenen.get(0);
                      switch(d){
                     case 1: roll1.setImage(dobbelsteen1); break;
                     case 2: roll1.setImage(dobbelsteen2); break;
                     case 3: roll1.setImage(dobbelsteen3); break;
                     case 4: roll1.setImage(dobbelsteen4); break;
                     case 5: roll1.setImage(dobbelsteen5); break;
                     default: roll1.setImage(dobbelsteenRegenworm); break;
                 }
                      aantalDobbelstenen--;
                  } 
                 }
                 });       
            
            dobbelsteen1.setOnMouseClicked(e->{
              if(dc.vergelijkGekozenGetalMetArrayDobbelstenen(1)){
              dc.voegScoreSpelerToeAanTotaalScore(dc.berekenScore(1)); 
              totaalScore.setText(String.format("%d",dc.getTotaalScore()));
              dobbelBox.getChildren().clear();
              }else{
                    dobbelAlert.show();                        
              }
            });
            dobbelsteen2.setOnMouseClicked(e->{
              if(dc.vergelijkGekozenGetalMetArrayDobbelstenen(2)){
              dc.voegScoreSpelerToeAanTotaalScore(dc.berekenScore(2)); 
              totaalScore.setText(String.format("%d",dc.getTotaalScore()));
              dobbelBox.getChildren().clear();
              }else{
                    dobbelAlert.show();                        
              }
            });
            dobbeelsteen3.setOnMouseClicked(e->{
              if(dc.vergelijkGekozenGetalMetArrayDobbelstenen(3)){
              dc.voegScoreSpelerToeAanTotaalScore(dc.berekenScore(3)); 
              totaalScore.setText(String.format("%d",dc.getTotaalScore()));
              dobbelBox.getChildren().clear();
              }else{
                    dobbelAlert.show();                        
              }
            });
            dobbelsteen4.setOnMouseClicked(e->{
              if(dc.vergelijkGekozenGetalMetArrayDobbelstenen(4)){
              dc.voegScoreSpelerToeAanTotaalScore(dc.berekenScore(4)); 
              totaalScore.setText(String.format("%d",dc.getTotaalScore()));
              dobbelBox.getChildren().clear();
              }else{
                    dobbelAlert.show();                        
              }
            });
            dobbelsteen5.setOnMouseClicked(e->{
              if(dc.vergelijkGekozenGetalMetArrayDobbelstenen(5)){
              dc.voegScoreSpelerToeAanTotaalScore(dc.berekenScore(5)); 
              totaalScore.setText(String.format("%d",dc.getTotaalScore()));
              dobbelBox.getChildren().clear();
              }else{
                    dobbelAlert.show();                        
              }
            });
            dobbelsteen6.setOnMouseClicked(e->{
              if(dc.vergelijkGekozenGetalMetArrayDobbelstenen(6)){
              dc.voegScoreSpelerToeAanTotaalScore(dc.berekenScore(6)); 
              totaalScore.setText(String.format("%d",dc.getTotaalScore()));
              dobbelBox.getChildren().clear();
              }else{
                    dobbelAlert.show();                        
              }
            });
            
            tile21.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=21){
                    tileBox.getChildren().remove(tile21);
                    dc.voegTegelToeAanSpeler(21);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile22.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=22){
                    tileBox.getChildren().remove(tile22);
                    dc.voegTegelToeAanSpeler(22);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile23.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=23){
                    tileBox.getChildren().remove(tile23);
                    dc.voegTegelToeAanSpeler(23);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile24.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=24){
                    tileBox.getChildren().remove(tile24);
                    dc.voegTegelToeAanSpeler(24);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile25.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=25){
                    tileBox.getChildren().remove(tile25);
                    dc.voegTegelToeAanSpeler(25);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile26.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=26){
                    tileBox.getChildren().remove(tile26);
                    dc.voegTegelToeAanSpeler(26);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile27.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=27){
                    tileBox.getChildren().remove(tile27);
                    dc.voegTegelToeAanSpeler(27);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile28.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=21){
                    tileBox.getChildren().remove(tile28);
                    dc.voegTegelToeAanSpeler(28);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile29.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=29){
                    tileBox.getChildren().remove(tile29);
                    dc.voegTegelToeAanSpeler(29);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile30.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=30){
                    tileBox.getChildren().remove(tile30);
                    dc.voegTegelToeAanSpeler(30);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile31.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=31){
                    tileBox.getChildren().remove(tile31);
                    dc.voegTegelToeAanSpeler(31);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile32.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=32){
                    tileBox.getChildren().remove(tile32);
                    dc.voegTegelToeAanSpeler(32);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile33.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=33){
                    tileBox.getChildren().remove(tile33);
                    dc.voegTegelToeAanSpeler(33);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile34.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=34){
                    tileBox.getChildren().remove(tile34);
                    dc.voegTegelToeAanSpeler(34);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile35.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=35){
                    tileBox.getChildren().remove(tile35);
                    dc.voegTegelToeAanSpeler(35);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                }
            });
            tile36.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=36){
                    tileBox.getChildren().remove(tile36);
                    dc.voegTegelToeAanSpeler(36);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
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
