package domein;

import java.util.ArrayList;
import java.time.LocalDate;

public class DomeinController {

    ArrayList tegelArrayList = new ArrayList<Tegel>();
    int[] arrayMetTegelsEnkelNummers = new int[16];
    private Speler speler;
    ArrayList kiesbareTegels = new ArrayList<>();
    ArrayList spelerLijst = new ArrayList<Speler>();
    ArrayList gesorteerdeSpelerLijst = new ArrayList<Speler>();

    public DomeinController() {
        this.speler = new Speler();
    }

    public void maakSpelers(int aantal, String[] namen, LocalDate[] geboorteDatums, LocalDate huidigeDatum) {
        for (int i = 0; i < aantal; i++) {
            Speler player = new Speler();
            player.setNaam(namen[i]);
            player.setGeboorteDatum(geboorteDatums[i]);
            spelerLijst.add(player);
        }
        this.SorteerSpelersOpGeboorteDatum(aantal, huidigeDatum);

    }

    public void SorteerSpelersOpGeboorteDatum(int aantal, LocalDate huidigeDatum) {
        int[] leeftijden = new int[aantal];
        int x = 0;

        for (Object speler : spelerLijst) {
            Speler s = (Speler) speler;
            leeftijden[x] = s.berekenLeeftijd(s.getGeboorteDatum(), huidigeDatum);
            x++;
        }
        for (int i = leeftijden.length - 1; i >= 1; i--) {
            int positie = i;
            int max = leeftijden[i];
            for (int j = i - 1; i > 0; i--) {
                if (leeftijden[j] > max) {
                    positie = j;
                    max = leeftijden[j];
                }
            }
            leeftijden[positie] = leeftijden[i];
            leeftijden[i] = max;
        }
            for (int i = 0; i <= leeftijden.length - 1; i++) {
                for (Object speler : spelerLijst) {
                Speler s = (Speler) speler;
                if (s.berekenLeeftijd(s.getGeboorteDatum(), huidigeDatum) == leeftijden[i]) {
                    gesorteerdeSpelerLijst.add(s);
                }
            }
        }
    }

    public ArrayList getGesorteerdeSpelerLijst() {
        return gesorteerdeSpelerLijst;
    }
    
    public String getGesorteerdeSpelerNaam(int nummer){
       ArrayList gesorteerd = getGesorteerdeSpelerLijst();
       Object player = gesorteerd.get(nummer);
        Speler s = (Speler) player;
        return s.getNaam();
    }

    public ArrayList getSpelerLijst() {
        return spelerLijst;
    }

    public void setNaam(String naam) {
        speler.setNaam(naam);
    }

    public String getNaam(int nummer) {
        Speler s = (Speler) spelerLijst.get(nummer);
        return s.getNaam();
    }

    public LocalDate getGeboorteDatum() {
        return speler.getGeboorteDatum();
    }

    public void maakTegels() {
        int aantalWormen;
        for (int nummer = 21; nummer <= 36; nummer++) {
            if (nummer >= 21 && nummer < 25) {
                aantalWormen = 1;
            } else {
                if (nummer >= 25 && nummer < 29) {
                    aantalWormen = 2;
                } else {
                    if (nummer >= 29 && nummer < 33) {
                        aantalWormen = 3;

                    } else {
                        aantalWormen = 4;
                    }
                }
            }
            Tegel tegel = new Tegel(nummer, aantalWormen);
            tegelArrayList.add(tegel);
        }

    }

    public ArrayList toonTegels(int score) {
        for (Object tegel : tegelArrayList) {
            Tegel t = (Tegel) tegel;
            for (int nummer = 21; nummer <= score; nummer++) {
                if (t.getNummer() == nummer) {
                    kiesbareTegels.add(nummer);
                }
            }
        }
        return kiesbareTegels;
    }

    public int kiesTegel(int gekozenTegel) {
        int returnValue = 0;
        if (kiesbareTegels.contains(gekozenTegel)) {
            returnValue = gekozenTegel;
        } else {
            throw new IllegalArgumentException("De gekozen tegel is niet beschikbaar.");
        }
        this.verwijderTegel(gekozenTegel);
        return returnValue;
    }

    public void verwijderTegel(int gekozenTegel) {
        for (Object tegel : tegelArrayList) {
            if (tegel.equals(gekozenTegel)) {
                tegelArrayList.remove(tegel);
            }
        }
    }

    public void DCvergelijkGekozenGetalMetArrayDobbelstenen(int gekozenGetal) {
        speler.vergelijkGekozenGetalMetArrayDobbelstenen(gekozenGetal);
    }

    public int berekenScore(int gekozenGetal) {
        return speler.berekenScore(gekozenGetal);
    }

    public void stelDobbelstenenArrayIn() {
        speler.stelDobbelstenenArrayIn();
    }

    public String getDobbelstenenString() {
        return speler.getDobbelstenenString();
    }

    public void voegScoreSpelerToeAanTotaalScore(int scoreBeurt) {
        speler.voegScoreSpelerToeAanTotaalScore(scoreBeurt);
    }

    public int getTotaalScore() {
        return speler.getTotaalScore();
    }

    public int getAantalDobelstenen() {
        return speler.getAantalDobbelstenen();
    }

    public boolean checkOfEindeSpel(int guard, int gekozenGetal) {
        return speler.checkOfEindeSpel(guard, gekozenGetal);
    }

    public boolean vergelijkGekozenGetalMetArrayDobbelstenen(int gekozenGetal) {
        return speler.vergelijkGekozenGetalMetArrayDobbelstenen(gekozenGetal);
    }

    public boolean isEindeSpel() {
        int counter = 0;
        for (int a = 0; a < arrayMetTegelsEnkelNummers.length; a++) {
            if (arrayMetTegelsEnkelNummers[a] == -1) {
                counter++;
            }
        }
        if (counter == 8) {
            return true;
        }
        return false;
    }
}
