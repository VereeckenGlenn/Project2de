/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;

/**
 *
 * @author loren
 */
public class DomeinController {

    ArrayList tegelArrayList = new ArrayList();

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
            Tegel tegel = new Tegel(nummer,aantalWormen);
            tegelArrayList.add(tegel);
        }

    }

}