/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author loren
 */
public class DomeinController {

    ArrayList tegelArrayList = new ArrayList<Tegel>();
    int[] arrayMetTegelsEnkelNummers = new int[16];
    private Speler speler;
    ArrayList kiesbareTegels = new ArrayList<>();
    ArrayList spelerLijst = new ArrayList<Speler>();

    public DomeinController() {
        this.speler = new Speler();
    }

    public void maakSpelers(int aantal, String[] namen, Date[] leeftijden) {
        for (int i = 0; i < aantal; i++) {
            Speler player = new Speler();
            player.setNaam(namen[i]);
            player.setGeboorteDatum(leeftijden[i]);
            spelerLijst.add(player);
        }
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

    public Date getGeboorteDatum() {
        return speler.getGeboorteDatum();
    }
/**
    public void maakTegels() {
        for (int a = 21; a <= 36; a++) {
            arrayMetTegelsEnkelNummers[a - 21] = a;
        }
    }

    public ArrayList toonTegels(int score) {
        for (int tegel : arrayMetTegelsEnkelNummers) {
            for (int nummer = 21; nummer <= score; nummer++) {
                if (tegel == score) {
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
        for (int a = 0; a < arrayMetTegelsEnkelNummers.length; a++) {
            if (arrayMetTegelsEnkelNummers[a] == gekozenTegel) {
                arrayMetTegelsEnkelNummers[a] = -1;
            }
        }
    }
*/
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
            for (int nummer = 21; nummer <= score; nummer++) {
                if (tegel.equals(nummer)) {
                    kiesbareTegels.add(nummer);
                }
            }
        }
        return kiesbareTegels ;
    }
    
    public int kiesTegel(int gekozenTegel){
        int returnValue = 0;
        if(kiesbareTegels.contains(gekozenTegel)){
            returnValue = gekozenTegel;
        }else{
           throw new IllegalArgumentException("De gekozen tegel is niet beschikbaar."); 
        }
        this.verwijderTegel(gekozenTegel);
        return returnValue;
    }
    public void verwijderTegel(int gekozenTegel){
        for (Object tegel : tegelArrayList) {
           if(tegel.equals(gekozenTegel)){
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
