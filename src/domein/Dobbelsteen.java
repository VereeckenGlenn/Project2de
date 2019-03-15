package domein;

import java.security.SecureRandom;

public class Dobbelsteen {

    private int geroldeOgen;

    public Dobbelsteen() {

    }

    public int getGeroldeOgen() {
        return this.geroldeOgen;
    }

    private void rolDobbelsteen() {
        SecureRandom sr = new SecureRandom();
        this.geroldeOgen = (sr.nextInt(6) + 1);
    }

}
