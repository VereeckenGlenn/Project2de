package domein;

import java.util.ArrayList;

public class Speler {

    private String naam;
    private int score;
    private int aantalDobbelstenen = 8;
    ArrayList dobbelstenenArrayList = new ArrayList();

    public ArrayList toonDobbelstenen() {
        dobbelstenenArrayList.clear();
        for (int i = 0; i < aantalDobbelstenen; i++) {
            Dobbelsteen dobbelsteen = new Dobbelsteen();
            dobbelsteen.rolDobbelsteen();
            dobbelstenenArrayList.add(dobbelsteen.getGeroldeOgen());
        }
        return dobbelstenenArrayList;
    }

    public int kiesDobbelsteen(int geroldeOgen) {
        boolean laatDoor = false;
        while (laatDoor = false) {
            for (Object dobbelsteen : dobbelstenenArrayList) {
                if (dobbelsteen.equals(geroldeOgen)) {
                    laatDoor = true;
                }
            }
        }
        return geroldeOgen;
    }
    
    
   
}
