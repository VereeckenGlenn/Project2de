/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author Glenn
 */
public class Tegel {

    private int nummer;
    private int aantalWormen;

    public Tegel(int nummer, int aantalWormen ) {
        setNummer(nummer);
        setAantalWormen(aantalWormen);
    }

    public int getNummer() {
        return nummer;
    }


    public final void setNummer(int nummer) {
        if (nummer >= 21 && nummer <= 36) {
            this.nummer = nummer;
        } else {
            throw new IllegalArgumentException("Het nummer moet tussen 21 en 36 liggen.");
        }
    }

    public int getAantalWormen() {
        return aantalWormen;
    }

    public void setAantalWormen(int aantalWormen) {
        this.aantalWormen = aantalWormen;
    }


}
