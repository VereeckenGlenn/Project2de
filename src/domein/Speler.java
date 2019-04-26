package domein;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Speler {

    private String naam;
    private int score = 0;
    private int aantalDobbelstenen = 8;
    private String geroldeDobbelstenen;
    private int totaalScore;
    private boolean eindeBeurt = false;
    private int aantalKeer = 0;
    private LocalDate geboorteDatum;

    ArrayList dobbelstenenArrayList = new ArrayList<>();
    ArrayList gekozenGetallen = new ArrayList<>();

    public void stelDobbelstenenArrayIn() {
        geroldeDobbelstenen = "";
        dobbelstenenArrayList.clear();
        for (int i = 0; i < aantalDobbelstenen; i++) {
            Dobbelsteen dobbelsteen = new Dobbelsteen();
            dobbelsteen.rolDobbelsteen();
            dobbelstenenArrayList.add(dobbelsteen.getGeroldeOgen());
            if (dobbelstenenArrayList.get(i).equals(6)) {
                geroldeDobbelstenen += "worm ";
            } else {
                geroldeDobbelstenen += dobbelsteen.getGeroldeOgen() + " ";
            }
        }
    }
    public int berekenLeeftijd(LocalDate geboorteDatum, LocalDate huidigeDatum){
        if((geboorteDatum != null)&&(huidigeDatum != null)){
            int dagen = Period.between(geboorteDatum,huidigeDatum).getDays();
            int maanden = Period.between(geboorteDatum,huidigeDatum).getMonths();
            int jaren = Period.between(geboorteDatum,huidigeDatum).getYears();
            int totaal = 0;
            if(jaren > 0){
                totaal += jaren * 365;
            }
            if(maanden > 0){
                totaal += maanden * 30;
            }
            totaal += dagen;
            
            return totaal;
        }
        return 99;
    }

    public ArrayList getDobbelstenenArray() {
        return dobbelstenenArrayList;
    }

    public String getDobbelstenenString() {
        return geroldeDobbelstenen;
    }

    public boolean vergelijkGekozenGetalMetArrayDobbelstenen(int gekozenGetal) {
        boolean gekozenGetalMatchtDobbelsteenOog = false;
        while (!gekozenGetalMatchtDobbelsteenOog) {
            for (Object dobbelsteen : dobbelstenenArrayList) {
                if (dobbelsteen.equals(gekozenGetal)) {
                   gekozenGetalMatchtDobbelsteenOog = true;}
                    }
                
            } 
         if(gekozenGetalMatchtDobbelsteenOog){      
        if(!gekozenGetallen.contains(gekozenGetal)){
                     gekozenGetallen.add(gekozenGetal);
                    }else{
                        throw new IllegalArgumentException("Het gekozen getal is al is gekozen.");}}
        
            if (!gekozenGetalMatchtDobbelsteenOog) {
                throw new IllegalArgumentException("Je gekozen getal matcht niet met de gerolde dobbelstenen!");
            }
        
        return gekozenGetalMatchtDobbelsteenOog;
    }

    public void setGeboorteDatum(LocalDate geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    
    public int getTotaalScore() {
        return totaalScore;
    }

    public int getScore() {
        return score;
    }

    public LocalDate getGeboorteDatum() {
        return geboorteDatum;
    }
    

    public void voegScoreSpelerToeAanTotaalScore(int scoreBeurt) {
        totaalScore += scoreBeurt;
    }

    public int berekenScore(int gekozenGetal) {
      aantalKeer=0;
        for (Object dobbelsteen : dobbelstenenArrayList) {
            if (dobbelsteen.equals(gekozenGetal)) {
                aantalKeer++;

            }
        }
        if (gekozenGetal == 6) {
            gekozenGetal = 5;
        }
        score = aantalKeer * gekozenGetal;
        return score;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }

    public int getAantalDobbelstenen() {
        return aantalDobbelstenen;
    }

    public boolean checkOfEindeSpel(int guard, int gekozenGetal) {
        if (aantalDobbelstenen == 0 || zijnWaardenHetzelfde()) {
            eindeBeurt = true;
        }
        if (totaalScore > 21) {
            if (guard == 1) {
                eindeBeurt = true;
            }
        }
        if (eindeBeurt) {
            gekozenGetallen.clear();
            aantalDobbelstenen=8;
            totaalScore=0;
        }
        return eindeBeurt;
    }
    
        public boolean zijnWaardenHetzelfde() {
        int nietGelijkCounter = aantalDobbelstenen;
        aantalDobbelstenen-= aantalKeer;
        for (Object dobbelsteen : dobbelstenenArrayList) {
            for (Object getal : gekozenGetallen) {
                if (getal == dobbelsteen) {
                    nietGelijkCounter--;
                }
            }
        }

        if (!gekozenGetallen.isEmpty()) {
            if (nietGelijkCounter == 0) {
                return true;
            }
        }
        return false;
    }
} 