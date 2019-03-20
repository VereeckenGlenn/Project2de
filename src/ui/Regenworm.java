package ui;

import domein.DomeinController;
import java.util.Scanner;

public class Regenworm {

    DomeinController dc = new DomeinController();
    Scanner input = new Scanner(System.in);

    public void start() {
     boolean eindSpelChecker = false;
     boolean vergelijkChecker = false;  
     
        while(!eindSpelChecker){
        dc.stelDobbelstenenArrayIn();
        System.out.println("Je rolde: ");
        System.out.println(dc.getDobbelstenenString());
        
        System.out.print("Geef het getal in dat je wil nemen (6 voor regenworm): ");
        while (!vergelijkChecker){  
        int gekozenGetal = input.nextInt();
        vergelijkChecker = dc.vergelijkGekozenGetalMetArrayDobbelstenen(gekozenGetal);                
        if(vergelijkChecker){
        int score = dc.berekenScore(gekozenGetal);
        System.out.printf("de score is %d%n",score);
        dc.voegScoreSpelerToeAanTotaalScore(score);
        System.out.println(dc.getTotaalScore());
        eindSpelChecker =dc.checkOfEindeSpel(score, gekozenGetal);
        }else{
            System.out.println("het getal is al gekozen,geef een nieuw getal in.");
        }} 
        
        } 
    }
    
}
