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

    public int nummer;
    public int aantalWormen;

    public Tegel(int nummer, int aantalWormen) {
        setNummer(nummer);
        setAantalWormen(aantalWormen);
    }

    public int getNummer() {
        return nummer;
    }

    public int getAantalWormen() {
        return aantalWormen;
    }

    public final void setNummer(int nummer) {
        if (nummer >= 21 && nummer <= 36) {
            this.nummer = nummer;
        } else {
            throw new IllegalArgumentException("Het nummer moet tussen 21 en 36 liggen.");
        }
    }

    public final void setAantalWormen(int aantalWormen) {
        if (aantalWormen >= 1 && aantalWormen <= 4) {
            this.aantalWormen = aantalWormen;
        } else {
            throw new IllegalArgumentException("Het aantal wormen moet tussen 1 en 4 liggen.");
        }
    }

}
