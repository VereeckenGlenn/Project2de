package ui;

import domein.DomeinController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Regenworm {

    Scanner input = new Scanner(System.in);

    public void start() {


        try {
               DomeinController dc = new DomeinController();

           
    

        dc.maakTegels();
        int aantalSpelers = 0;
        boolean exceptionGegooid = false;

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
                            eindBeurtChecker = dc.CheckOfEindeBeurt(guard, gekozenGetal);
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
        } } catch (ClassNotFoundException e) {
               System.err.print("klasse spelermapper niet gevonden.") ;
            }
    }
}
