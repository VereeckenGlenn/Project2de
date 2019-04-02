package ui;

import domein.DomeinController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Regenworm {

    DomeinController dc = new DomeinController();
    Scanner input = new Scanner(System.in);

    public void start() {

        dc.maakTegels();
        int aantalSpelers = 0;
        boolean exceptionGegooid = false;

        do {
            try {
                System.out.println("Geef het aantal spelers in.");

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
        Date[] leeftijden = new Date[aantalSpelers];
        for (int i = 0; i < namen.length; i++) {
            System.out.printf("geef de naam voor speler %d: ", i + 1);
            namen[i] = input.next();

            System.out.printf("geef de geboortedatum in voor speler %d, dd-mm-yyyy:", i + 1);
            String date = input.nextLine();

            SimpleDateFormat format = new SimpleDateFormat("dd - mm - yyyy");

            format.parse(date);

        }
        dc.maakSpelers(aantalSpelers, namen, leeftijden);
        ArrayList spelerLijst = dc.getSpelerLijst();

        while (!dc.isEindeSpel()) {
            for (int i = 0; i < namen.length; i++) {
                boolean eindBeurtChecker = false;
                System.out.printf("het is %s's beurt%n", dc.getNaam(i));

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
                            System.out.printf("de score is %d%n", score);
                            dc.voegScoreSpelerToeAanTotaalScore(score);
                            System.out.println(dc.getTotaalScore());
                            int guard = 2;
                            if (dc.getTotaalScore() >= 21) {
                                System.out.println("Wenst u te stoppen? geef 1 in voor ja, een ander getal voor nee.");
                                guard = input.nextInt();
                            }
                            eindBeurtChecker = dc.checkOfEindeSpel(guard, gekozenGetal);
                        } else {
                            System.out.println("het getal is al gekozen,geef een nieuw getal in.");
                            gekozenGetal = input.nextInt();
                        }
                    }
                    if (eindBeurtChecker) {
                        System.out.println(dc.toonTegels(dc.getTotaalScore()).toString());
                        System.out.println("geef het getal in van de tegel die je wil nemen.");
                        int gekozenTegel = input.nextInt();
                        dc.kiesTegel(gekozenTegel);
                        dc.isEindeSpel();
                    }
                }
            }
        }
    }
}
