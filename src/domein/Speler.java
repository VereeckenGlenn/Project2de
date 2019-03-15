package domein;

import java.util.ArrayList;

public class Speler {

    private String naam;
    private int score;
    private int aantalDobbelstenen = 8;

    public ArrayList toonDobbelstenen() {
        ArrayList dobbelstenenArrayList = new ArrayList();
        
        for (int i = 0; i < aantalDobbelstenen; i++) {
            Dobbelsteen dobbelsteen = new Dobbelsteen();
            dobbelstenenArrayList.add(dobbelsteen.getGeroldeOgen());
        }
        return dobbelstenenArrayList;
    }
}
