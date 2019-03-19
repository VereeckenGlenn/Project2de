package ui;

import domein.DomeinController;
import domein.Speler;
import java.util.Scanner;

public class Regenworm {

    DomeinController dc = new DomeinController();
    Speler speler = new Speler();
    Scanner input = new Scanner(System.in);

    public void start() {
        speler.stelDobbelstenenArrayIn();
        System.out.println("Je rolde: ");
        System.out.println(speler.getDobbelstenenString());
        
        System.out.print("Geef het getal in dat je wil nemen (6 voor regenworm): ");
        int gekozenGetal = input.nextInt();
        dc.vergelijkGekozenGetalMetArrayDobbelstenen(gekozenGetal);
    }
    
}
