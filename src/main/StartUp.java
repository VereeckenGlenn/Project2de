package main;

import domein.DomeinController;
import gui.Applicatie;
import ui.Regenworm;

public class StartUp {

    public static void main(String[] args) {
        DomeinController dc = new DomeinController();
        
        new Regenworm().start();
        
        Applicatie app = new Applicatie();
        app.start(primaryStage);
    }

}
