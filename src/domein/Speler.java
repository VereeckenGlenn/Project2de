package domein;

import java.util.ArrayList;

public class Speler {

    private String naam;
    private int score;
    private int aantalDobbelstenen = 8;
    private int gekozenAantal = 0;
    private String geroldeDobbelstenen;
    private int scoreSpeler = 0;
    ArrayList dobbelstenenArrayList = new ArrayList();

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

    public void vergelijkGekozenGetalMetArrayDobbelstenen(int gekozenGetal) {
        boolean gekozenGetalMatchtDobbelsteenOog = false;
        while (!gekozenGetalMatchtDobbelsteenOog) {
            for (Object dobbelsteen : dobbelstenenArrayList) {
                if (dobbelsteen.equals(gekozenGetal)) {
                    gekozenGetalMatchtDobbelsteenOog = true;

                }
            }
            if (!gekozenGetalMatchtDobbelsteenOog) {
                throw new IllegalArgumentException("Je gekozen getal matcht niet met de gerolde dobbelstenen!");
            }
        }
    }

    public int toonScoreSpeler() {
        return scoreSpeler;
    }

    public void voegScoreSpelerToe(int scoreBeurt) {
        scoreSpeler += scoreBeurt;
    }
    
    
}
