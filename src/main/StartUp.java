package main;

import domein.DomeinController;
import ui.Regenworm;

public class StartUp {

    public static void main(String[] args) {
        DomeinController dc = new DomeinController();
        
        new Regenworm().start();
        
    }

}
