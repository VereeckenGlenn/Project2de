package ui;

import domein.DomeinController;
import java.util.Scanner;

public class Regenworm {

    DomeinController dc = new DomeinController();
    Scanner input = new Scanner(System.in);

    public void start() {
        dc.stelDobbelstenenArrayIn();
        System.out.println("Je rolde: ");
        System.out.println(dc.getDobbelstenenString());
        
        System.out.print("Geef het getal in dat je wil nemen (6 voor regenworm): ");
        int gekozenGetal = input.nextInt();
        int score = dc.berekenScore(gekozenGetal);
        System.out.printf("%d",score);
    }
    
}
