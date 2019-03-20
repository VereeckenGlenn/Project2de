package domein;

import java.util.ArrayList;

public class Speler {

    private String naam;
    private int score = 0;
    private int aantalDobbelstenen = 8;
    private String geroldeDobbelstenen;
    private int totaalScore;
    private boolean eindeBeurt = false;

    ArrayList dobbelstenenArrayList = new ArrayList();
    ArrayList gekozenGetallen = new ArrayList();

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
                    gekozenGetalMatchtDobbelsteenOog = true;
                    gekozenGetallen.add(gekozenGetal);
                }
            }
            if (!gekozenGetalMatchtDobbelsteenOog) {
                throw new IllegalArgumentException("Je gekozen getal matcht niet met de gerolde dobbelstenen!");
            }
        }
        return gekozenGetalMatchtDobbelsteenOog;
    }

    public boolean zitGekozenGetalInGekozenGetallenArrayList(int gekozenGetal) {
        boolean checkGekozen = false;
        for (Object getal : gekozenGetallen) {
            if (getal.equals(gekozenGetal)) {
                checkGekozen = true;
            }
        }
        return checkGekozen;
    }

    public int getTotaalScore() {
        return totaalScore;
    }

    public int getScore() {
        return score;
    }

    public void voegScoreSpelerToeAanTotaalScore(int scoreBeurt) {
        totaalScore += scoreBeurt;
    }

    public int berekenScore(int gekozenGetal) {
        int aantalKeer = 0;
        for (Object dobbelsteen : dobbelstenenArrayList) {
            if (dobbelsteen.equals(gekozenGetal)) {
                aantalKeer++;

            }
        }
        if (gekozenGetal == 6) {
            gekozenGetal = 5;
        }
        aantalDobbelstenen -= aantalKeer;
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
        if (aantalDobbelstenen == 0 || zijnWaardenVanDobbelsteenArrayListEnGekozenGetallenHetzelfde()) {
            eindeBeurt = true;
        }
        if (totaalScore > 21) {
            if (guard == 1) {
                eindeBeurt = true;
            }
        }
        if (eindeBeurt) {
            gekozenGetallen.clear();
        }
        return eindeBeurt;
    }
    
        public boolean zijnWaardenVanDobbelsteenArrayListEnGekozenGetallenHetzelfde() {
        int nietGelijkCounter = aantalDobbelstenen;
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
