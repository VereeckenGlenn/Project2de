package gui;

import domein.DomeinController;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;



public class RegenwormenApplicatie extends Application {
    
    DomeinController dc;
    Stage window;
    ImageView dobbelsteen1 = new ImageView("https://imgur.com/Hb1yzNy.png");
            ImageView dobbelsteen2 = new ImageView("https://imgur.com/Hb1yzNy.png");
            ImageView dobbelsteen3 = new ImageView("https://imgur.com/sLvt8uQ.png");
            ImageView dobbelsteen4 = new ImageView("https://imgur.com/sLvt8uQ.png");
            ImageView dobbelsteen5 = new ImageView("https://imgur.com/oKnxkzL.png");
            ImageView dobbelsteenRegenworm = new ImageView("https://imgur.com/WTqfvL1.png");
            HBox dobbelBox = new HBox();
            Alert dobbelAlert = new Alert(AlertType.INFORMATION);
            Label totaalScore = new Label();
            
            
            
            
            
            
            
    public void addImage(MouseEvent e) {
        dc.stelDobbelstenenArrayIn();
        ArrayList dobbelstenen = dc.getDobbelstenen();
        for (Object dobbelsteen : dobbelstenen) {
            int d = (int) dobbelsteen;
            if(d==1){
                dobbelBox.getChildren().add(dobbelsteen1);
                dobbelsteen1 = new ImageView("https://imgur.com/Hb1yzNy.png");
                dobbelsteen1.setOnMouseClicked(r->{
              if(dc.vergelijkGekozenGetalMetArrayDobbelstenen(1)){
              dc.voegScoreSpelerToeAanTotaalScore(dc.berekenScore(1)); 
              totaalScore.setText(String.format("%d",dc.getTotaalScore()));
              dobbelBox.getChildren().clear();
              }else{
                    dobbelAlert.show();                        
              }
            });
            } else if(d==2){
                dobbelBox.getChildren().add(dobbelsteen2);
                dobbelsteen2 = new ImageView("https://imgur.com/Hb1yzNy.png");
                dobbelsteen2.setOnMouseClicked(r->{
              if(dc.vergelijkGekozenGetalMetArrayDobbelstenen(2)){
              dc.voegScoreSpelerToeAanTotaalScore(dc.berekenScore(2)); 
              totaalScore.setText(String.format("%d",dc.getTotaalScore()));
              dobbelBox.getChildren().clear();
              }else{
                    dobbelAlert.show();                        
              }
            });
            }else if(d==3){
                dobbelBox.getChildren().add(dobbelsteen3);
                dobbelsteen3 = new ImageView("https://imgur.com/sLvt8uQ.png");
                dobbelsteen3.setOnMouseClicked(r->{
              if(dc.vergelijkGekozenGetalMetArrayDobbelstenen(3)){
              dc.voegScoreSpelerToeAanTotaalScore(dc.berekenScore(3)); 
              totaalScore.setText(String.format("%d",dc.getTotaalScore()));
              dobbelBox.getChildren().clear();
              }else{
                    dobbelAlert.show();                        
              }
            });
            }else if(d==4){
                dobbelBox.getChildren().add(dobbelsteen4);
                dobbelsteen4 = new ImageView("https://imgur.com/sLvt8uQ.png");
                dobbelsteen4.setOnMouseClicked(r->{
              if(dc.vergelijkGekozenGetalMetArrayDobbelstenen(4)){
              dc.voegScoreSpelerToeAanTotaalScore(dc.berekenScore(4)); 
              totaalScore.setText(String.format("%d",dc.getTotaalScore()));
              dobbelBox.getChildren().clear();
              }else{
                    dobbelAlert.show();                        
              }
            });
            }else if(d==5){
                dobbelBox.getChildren().add(dobbelsteen5);
                dobbelsteen5 = new ImageView("https://imgur.com/oKnxkzL.png");
                dobbelsteen5.setOnMouseClicked(r->{
              if(dc.vergelijkGekozenGetalMetArrayDobbelstenen(5)){
              dc.voegScoreSpelerToeAanTotaalScore(dc.berekenScore(5)); 
              totaalScore.setText(String.format("%d",dc.getTotaalScore()));
              dobbelBox.getChildren().clear();
              }else{
                    dobbelAlert.show();                        
              }
            });
            }else if(d==6){
                dobbelBox.getChildren().add(dobbelsteenRegenworm);
                dobbelsteenRegenworm = new ImageView("https://imgur.com/WTqfvL1.png");
                dobbelsteenRegenworm.setOnMouseClicked(r->{
              if(dc.vergelijkGekozenGetalMetArrayDobbelstenen(6)){
              dc.voegScoreSpelerToeAanTotaalScore(dc.berekenScore(6)); 
              totaalScore.setText(String.format("%d",dc.getTotaalScore()));
              dobbelBox.getChildren().clear();
              }else{
                    dobbelAlert.show();                        
              }
            });
            }
            
            
        }
    } 
    
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
            Button goBack = new Button("keer terug");
            Alert spelerToegevoegd = new Alert(AlertType.INFORMATION);
            Alert spelerNietToegevoegd = new Alert(AlertType.ERROR);
            spelerToegevoegd.setHeaderText("speler toegevoegd");
            spelerNietToegevoegd.setHeaderText("speler bestaat niet of wachtwoord is onjuist");
            selecteerSpelerGrid.getChildren().addAll(SelecteerSpelerBox);
            SelecteerSpelerBox.getChildren().addAll(spelerNaamLabel,spelerNaamField,wwLabel,wwField,confirm,goBack);
            

            //speelscherm
            BorderPane speelGrid = new BorderPane();
            spelerGrid.setAlignment(Pos.CENTER);
            Scene speelScene = new Scene(speelGrid, 300, 200);
            Label aanBeurt = new Label();
            aanBeurt.setFont(Font.font(STYLESHEET_MODENA,35));
            totaalScore.setFont(Font.font(STYLESHEET_MODENA,35));
            Insets aanBeurtInset = new Insets(0,10,0,0);
            aanBeurt.setPadding(aanBeurtInset);
            Button rolDobbelstenen = new Button("rol dobbelstenen");
            Button eindeBeurt = new Button("eindig beurt");
            dobbelAlert.setHeaderText("het geselecteerde getal is al is gekozen, probeer opniew");
            Button naarTegels = new Button("kies tegel");
            HBox topBox = new HBox();
            HBox bottemBox = new HBox();
            topBox.getChildren().addAll(aanBeurt);
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
            Scene tegelScene = new Scene(tegelPane,500,300);
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
            Label tspeler1 = new Label();
            Label tspeler2 = new Label();
            Label tspeler3 = new Label();
            Label tspeler4 = new Label();
            Label tspeler5 = new Label();
            Label tspeler6 = new Label();
            Label tspeler7 = new Label();
            Label tspeler8 = new Label();
            Label tile1 = new Label();
            Label tile2 = new Label();
            Label tile3 = new Label();
            Label tile4 = new Label();
            Label tile5 = new Label();
            Label tile6 = new Label();
            Label tile7 = new Label();
            Label tile8 = new Label();
            VBox leftBox = new VBox();
            VBox rightBox = new VBox();
            leftBox.getChildren().addAll(tspeler1,tile1,tspeler3,tile3,tspeler5,tile5,tspeler7,tile7);
            rightBox.getChildren().addAll(tspeler2,tile2,tspeler4,tile4,tspeler6,tile6,tspeler8,tile8);
            Alert tegelAlert = new Alert(AlertType.INFORMATION);
            tegelAlert.setHeaderText("Je moet minstens 21 punten hebben om een tegel te kiezen.");
            tegelPane.setLeft(leftBox);
            tegelPane.setRight(rightBox);
            tegelPane.setCenter(tileBox);
            
            //eindeScherm
            GridPane endGrid = new GridPane();
            Scene endScene = new Scene(endGrid,300,200);
            Label winner = new Label();
            Button home = new Button("startScherm"); 
            
            
            
            home.setOnMouseClicked(e ->{
                dc.pasHighscoreAan(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()), dc.getTotaalScore());
                window.setScene(startScene);
                
            });
            
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
                Optional<ButtonType> result = eindeBeurtAlert.showAndWait();
                    if(result.get() == ButtonType.OK){
                        dc.EindeBeurt();
                         aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
                    }
                    });    
        rolDobbelstenen.setOnMouseClicked(e ->{
            addImage(e);
        });
        goBack.setOnMouseClicked(e ->window.setScene(selecteerScene));
        
        naarTegels.setOnMouseClicked(e ->{
        if(dc.getTotaalScore()>21){
            for (int i = 0; i < dc.getAantalSpelers(); i++) {
                if(i == 0){
                    tspeler1.setText(dc.getGesorteerdeSpelerNaam(0)); 
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(i);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile1.setText(tegel);
                  }        
                }
                 if(i == 1){
                    tspeler2.setText(dc.getGesorteerdeSpelerNaam(1)); 
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(i);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile2.setText(tegel);
                  }        
                }
                  if(i == 2){
                    tspeler3.setText(dc.getGesorteerdeSpelerNaam(2)); 
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(i);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile3.setText(tegel);
                  }        
                }
                   if(i == 3){
                    tspeler4.setText(dc.getGesorteerdeSpelerNaam(3)); 
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(i);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile4.setText(tegel);
                  }        
                }
                    if(i == 4){
                    tspeler5.setText(dc.getGesorteerdeSpelerNaam(4)); 
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(i);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile5.setText(tegel);
                  }        
                }
                     if(i == 5){
                    tspeler6.setText(dc.getGesorteerdeSpelerNaam(5)); 
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(i);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile6.setText(tegel);
                  }        
                }
                      if(i == 6){
                    tspeler7.setText(dc.getGesorteerdeSpelerNaam(6)); 
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(i);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile7.setText(tegel);
                  }        
                }
                       if(i == 7){
                    tspeler8.setText(dc.getGesorteerdeSpelerNaam(7)); 
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(i);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile8.setText(tegel);
                  }        
                }
            }
            window.setScene(tegelScene);  
        }else{
           tegelAlert.show(); 
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
            dobbelsteen3.setOnMouseClicked(e->{
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
            dobbelsteenRegenworm.setOnMouseClicked(e->{
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
               
                if(!tileBox.getChildren().isEmpty()){ 
                    dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           }
                }
            });
            tile22.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=22){
                    tileBox.getChildren().remove(tile22);
                    dc.voegTegelToeAanSpeler(22);
                             if(!tileBox.getChildren().isEmpty()){ 
                                 dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));

               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           }
                }
            });
            tile23.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=23){
                    tileBox.getChildren().remove(tile23);
                    dc.voegTegelToeAanSpeler(23);
                
                if(!tileBox.getChildren().isEmpty()){dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           }}
            });
            tile24.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=24){
                    tileBox.getChildren().remove(tile24);
                    dc.voegTegelToeAanSpeler(24);
                               if(!tileBox.getChildren().isEmpty()){ dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));

               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           }}
            });
            tile25.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=25){
                    tileBox.getChildren().remove(tile25);
                    dc.voegTegelToeAanSpeler(25);
               
               if(!tileBox.getChildren().isEmpty()){ dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           }
                }
            });
            tile26.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=26){
                    tileBox.getChildren().remove(tile26);
                    dc.voegTegelToeAanSpeler(26);
              
                if(!tileBox.getChildren().isEmpty()){  dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           }
                }
            });
            tile27.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=27){
                    tileBox.getChildren().remove(tile27);
                    dc.voegTegelToeAanSpeler(27);
              
                if(!tileBox.getChildren().isEmpty()){  dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           }
                }
            });
            tile28.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=21){
                    tileBox.getChildren().remove(tile28);
                    dc.voegTegelToeAanSpeler(28);
              
               if(!tileBox.getChildren().isEmpty()){  dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }
            });
            tile29.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=29){
                    tileBox.getChildren().remove(tile29);
                    dc.voegTegelToeAanSpeler(29);
               
               if(!tileBox.getChildren().isEmpty()){ dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }
            });
            tile30.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=30){
                    tileBox.getChildren().remove(tile30);
                    dc.voegTegelToeAanSpeler(30);
               
               if(!tileBox.getChildren().isEmpty()){ dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }
            });
            tile31.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=31){
                    tileBox.getChildren().remove(tile31);
                    dc.voegTegelToeAanSpeler(31);
            
               if(!tileBox.getChildren().isEmpty()){    dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }
            });
            tile32.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=32){
                    tileBox.getChildren().remove(tile32);
                    dc.voegTegelToeAanSpeler(32);
                
               if(!tileBox.getChildren().isEmpty()){dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }
            });
            tile33.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=33){
                    tileBox.getChildren().remove(tile33);
                    dc.voegTegelToeAanSpeler(33);
                
               if(!tileBox.getChildren().isEmpty()){dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }
            });
            tile34.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=34){
                    tileBox.getChildren().remove(tile34);
                    dc.voegTegelToeAanSpeler(34);
                
               if(!tileBox.getChildren().isEmpty()){dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }
            });
            tile35.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=35){
                    tileBox.getChildren().remove(tile35);
                    dc.voegTegelToeAanSpeler(35);
              
               if(!tileBox.getChildren().isEmpty()){  dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           }}
            });
            tile36.setOnMouseClicked(e->{
                int totaalscore = dc.getTotaalScore();
                if(totaalscore >=36){
                    tileBox.getChildren().remove(tile36);
                    dc.voegTegelToeAanSpeler(36);
              
               if(!tileBox.getChildren().isEmpty()){ 
                   dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }
            });
            tile1.setOnMouseClicked(e->{
                  int totaalscore = dc.getTotaalScore();
                  String t = tile1.getText();
                  int score = Integer.parseInt(t);
                if(totaalscore >=score){
                   dc.verwijderTegelVanSpeler(1,score);
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(1);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile2.setText(tegel);
                    dc.voegTegelToeAanSpeler(score);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               if(!tileBox.getChildren().isEmpty()){
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }}
            });
            tile1.setOnMouseClicked(e->{
                  int totaalscore = dc.getTotaalScore();
                  String t = tile1.getText();
                  int score = Integer.parseInt(t);
                if(totaalscore >=score){
                   dc.verwijderTegelVanSpeler(0,score);
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(0);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile2.setText(tegel);
                    dc.voegTegelToeAanSpeler(score);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               if(!tileBox.getChildren().isEmpty()){
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }}
            });
            tile2.setOnMouseClicked(e->{
                  int totaalscore = dc.getTotaalScore();
                  String t = tile2.getText();
                  int score = Integer.parseInt(t);
                if(totaalscore >=score){
                   dc.verwijderTegelVanSpeler(1,score);
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(1);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile2.setText(tegel);
                    dc.voegTegelToeAanSpeler(score);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               if(!tileBox.getChildren().isEmpty()){
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }}
            });
            tile3.setOnMouseClicked(e->{
                  int totaalscore = dc.getTotaalScore();
                  String t = tile3.getText();
                  int score = Integer.parseInt(t);
                if(totaalscore >=score){
                   dc.verwijderTegelVanSpeler(2,score);
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(2);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile3.setText(tegel);
                    dc.voegTegelToeAanSpeler(score);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               if(!tileBox.getChildren().isEmpty()){
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }}
            });
            tile4.setOnMouseClicked(e->{
                  int totaalscore = dc.getTotaalScore();
                  String t = tile4.getText();
                  int score = Integer.parseInt(t);
                if(totaalscore >=score){
                   dc.verwijderTegelVanSpeler(3,score);
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(3);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile4.setText(tegel);
                    dc.voegTegelToeAanSpeler(score);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               if(!tileBox.getChildren().isEmpty()){
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }}
            });
            tile5.setOnMouseClicked(e->{
                  int totaalscore = dc.getTotaalScore();
                  String t = tile1.getText();
                  int score = Integer.parseInt(t);
                if(totaalscore >=score){
                   dc.verwijderTegelVanSpeler(4,score);
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(4);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile5.setText(tegel);
                    dc.voegTegelToeAanSpeler(score);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               if(!tileBox.getChildren().isEmpty()){
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }}
            });
          tile6.setOnMouseClicked(e->{
                  int totaalscore = dc.getTotaalScore();
                  String t = tile6.getText();
                  int score = Integer.parseInt(t);
                if(totaalscore >=score){
                   dc.verwijderTegelVanSpeler(5,score);
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(5);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile6.setText(tegel);
                    dc.voegTegelToeAanSpeler(score);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               if(!tileBox.getChildren().isEmpty()){
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }}
            });
          tile7.setOnMouseClicked(e->{
                  int totaalscore = dc.getTotaalScore();
                  String t = tile7.getText();
                  int score = Integer.parseInt(t);
                if(totaalscore >=score){
                   dc.verwijderTegelVanSpeler(6,score);
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(6);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile7.setText(tegel);
                    dc.voegTegelToeAanSpeler(score);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               if(!tileBox.getChildren().isEmpty()){
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }}
            });
          tile8.setOnMouseClicked(e->{
                  int totaalscore = dc.getTotaalScore();
                  String t = tile8.getText();
                  int score = Integer.parseInt(t);
                if(totaalscore >=score){
                   dc.verwijderTegelVanSpeler(7,score);
                  ArrayList tegels =  dc.getTegelArrayListVanSpeler(7);
                  if(!tegels.isEmpty()){
                    String tegel = (String) tegels.get(tegels.size()-1);
                    tile8.setText(tegel);
                    dc.voegTegelToeAanSpeler(score);
                dc.EindeBeurt();
                aanBeurt.setText(dc.getGesorteerdeSpelerNaam(dc.getSpelerAanBeurt()));
               if(!tileBox.getChildren().isEmpty()){
               window.setScene(speelScene);
           }else{
               winner.setText(dc.getNaamVanHoogsteScore());   
               window.setScene(endScene);
           } }}
            });
    }
}
   
        
