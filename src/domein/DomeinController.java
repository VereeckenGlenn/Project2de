package domein;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import persistentie.spelermapper;

public class DomeinController {

    ArrayList tegelArrayList = new ArrayList<Tegel>();
    int[] arrayMetTegelsEnkelNummers = new int[16];
    private Speler speler;
    ArrayList kiesbareTegels = new ArrayList<>();
    ArrayList spelerLijst = new ArrayList<Speler>();
    ArrayList gesorteerdeSpelerLijst = new ArrayList<Speler>();
    ArrayList spelerTegels = new ArrayList<>();
    spelermapper sm = new spelermapper();
    int aantalSpelers = 0;
    List<String> spelerNamen = new ArrayList<>();
    private int spelerAanBeurt = 0;
    
    
    public boolean ControleerSpelerNaamEnWachtwoord(String naam, String ww){
        boolean returnwaarde = false;
        Speler s = sm.zoekSpeler(naam);
        if(s.getNaam().equals(naam)){
            if(s.getWachtwoord().equals(ww)){
                spelerLijst.add(s);
                returnwaarde = true;
            }
        }
        
        return returnwaarde;
    }
  
    public DomeinController() throws ClassNotFoundException {
        this.speler = new Speler();
        sm.StartDriver();
    }

    public int getAantalSpelers() {
        return aantalSpelers;
    }

    public void setAantalSpelers(int aantalSpelers) {
        this.aantalSpelers = aantalSpelers;
    }
    
    public LocalDate StringDateConvert(String geboorteDatum){
            boolean exceptionGegooid= true;
            LocalDate returnWaarde; 
        do{
            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
            
                Date dateRaw = null;
                try {
                    dateRaw = format.parse(geboorteDatum);
                    exceptionGegooid = false;

                    LocalDate dateConverted = LocalDate.ofInstant(dateRaw.toInstant(), ZoneId.systemDefault());
                    returnWaarde = dateConverted;
                } catch (ParseException ex) {
                    exceptionGegooid = true;
                    throw new IllegalArgumentException("de ingegeven datum voldoet niet aan de verwachting. dd-mm-yyyy");
                }
            } while (exceptionGegooid);
        return returnWaarde;
    }
    public void maakTegels() {
        speler.maakTegels();
    }
    public ArrayList<String> toonSpelers(){
       ArrayList<String> spelernamen = new ArrayList<>();
       List<String> namen = sm.zoekAlleGebruikers();
       namen.forEach((naam) -> {
           spelernamen.add(naam);
        });
       if(spelernamen.isEmpty()){
           spelernamen.add("default");
       }
     return spelernamen;   
    }
    public void voegSpelerToe(String speler,String wachtwoord) throws Exception{
      Speler s = sm.zoekSpeler(speler);
      if(wachtwoord.equals(s.getWachtwoord())){
           spelerLijst.add(s);
      }else{
      throw new Exception("Het opgegeven wachtwoord is onjuist");
      }
     
    }
    
    public void maakSpelerAan(String naam, String wachtwoord, String geboorteDatum){
        Speler player = new Speler();
        player.setNaam(naam);
        player.setGeboorteDatum(StringDateConvert(geboorteDatum));
        player.setWachtwoord(wachtwoord);
        player.setHighscore(0);
        sm.voegSpelerToe(player);
    }

    public void maakSpelers(int aantal, String[] namen, LocalDate[] geboorteDatums, LocalDate huidigeDatum) {
        for (int i = 0; i < aantal; i++) {
            Speler player = new Speler();
            player.setNaam(namen[i]);
            player.setGeboorteDatum(geboorteDatums[i]);
            spelerLijst.add(player);
        }
        this.SorteerSpelersOpGeboorteDatum(aantal, huidigeDatum);

    }

    public void SorteerSpelersOpGeboorteDatum(int aantal, LocalDate huidigeDatum) {
        int[] leeftijden = new int[aantal];
        int x = 0;

        for (Object speler : spelerLijst) {
            Speler s = (Speler) speler;
            leeftijden[x] = s.berekenLeeftijd(s.getGeboorteDatum(), huidigeDatum);
            x++;
        }
        for (int i = leeftijden.length - 1; i >= 1; i--) {
            int positie = i;
            int max = leeftijden[i];
            for (int j = i - 1; i > 0; i--) {
                if (leeftijden[j] > max) {
                    positie = j;
                    max = leeftijden[j];
                }
            }
            leeftijden[positie] = leeftijden[i];
            leeftijden[i] = max;           
        } 
        int[] leeftijdenReverse = new int[leeftijden.length];
        int j = leeftijden.length; 
        for (int i = 0; i < leeftijden.length; i++) { 
            leeftijdenReverse[j - 1] = leeftijden[i]; 
                 j = j - 1;
        }
        for (Object s : spelerLijst) {
            Speler p = (Speler) s;
        for (int i : leeftijdenReverse) {
           if(p.berekenLeeftijd(p.getGeboorteDatum(), huidigeDatum)==i){
               gesorteerdeSpelerLijst.add(p);
           }
        
        }
           }     
        
    }
    public ArrayList getDobbelstenen(){
        return speler.getDobbelstenenArray();
    }

    public ArrayList getGesorteerdeSpelerLijst() {
        return gesorteerdeSpelerLijst;
    }
    public String getNaamVanHoogsteScore(){
        ArrayList hoogsteScore = new ArrayList();
       ArrayList gesorteerd = getGesorteerdeSpelerLijst();
        for (Object s : gesorteerd) {
            Speler p = (Speler) s;
            hoogsteScore.add(p.getTotaalScore());
        }
        for (int i = hoogsteScore.size() - 1; i >= 1; i--) {
            int positie = i;
            int max = (int) hoogsteScore.get(i);
            for (int j = i - 1; i > 0; i--) {
                if ((int) hoogsteScore.get(j) > max) {
                    positie = j;
                    max = (int) hoogsteScore.get(j);
                }
            }
            hoogsteScore.set(positie, hoogsteScore.get(i));
            hoogsteScore.set(i,max);           
        } 
        String naam ="";
        int highscore = (int) hoogsteScore.get(0);
        for (Object s : gesorteerd) {
            Speler p = (Speler) s;
            if(p.getTotaalScore()==highscore){
                naam = p.getNaam();
            }
        }
        return String.format("%s wint met een score van %d",naam,highscore);
    }
    
    public String getGesorteerdeSpelerNaam(int nummer){
       ArrayList gesorteerd = getGesorteerdeSpelerLijst();
       Object player = gesorteerd.get(nummer);
        Speler s = (Speler) player;
        return s.getNaam();
    }

    public ArrayList getSpelerLijst() {
        return spelerLijst;
    }

      public String getWachtwoord(int nummer) {
        Speler s = (Speler) spelerLijst.get(nummer);
        return s.getWachtwoord();
    }

    public void setWachtwoord(String wachtwoord) {
        speler.setWachtwoord(wachtwoord);
    }
        
    public void setNaam(String naam) {
        speler.setNaam(naam);
    }

    public String getNaam(int nummer) {
        Speler s = (Speler) spelerLijst.get(nummer);
        return s.getNaam();
    }

    public LocalDate getGeboorteDatum() {
        return speler.getGeboorteDatum();
    }

    

    public ArrayList toonTegels(int score) {
        return speler.toonTegels(score);
    }
    public ArrayList toonTegelsVanSpelers(){
         for (Object speler : spelerLijst) {
         Speler s = (Speler)speler;      
        spelerTegels.add(s.ToonBovensteTegel());
        }
         
        return spelerTegels;
    }
    
    public int kiesTegel(int gekozenTegel) {
        if(spelerTegels.contains(gekozenTegel)){
            for (Object speler : spelerLijst) {
                Speler s = (Speler)speler;
                if(s.ToonBovensteTegel()==gekozenTegel){
                    s.verwijderTegelVanSpeler(gekozenTegel);
                }
            }
        }
       return speler.kiesTegel(gekozenTegel);
    }

    public void verwijderTegel(int gekozenTegel) {
        speler.verwijderTegel(gekozenTegel);
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
    
    public void EindeBeurt(){
        if(spelerAanBeurt==aantalSpelers-1){
            spelerAanBeurt = 0;
        }else{
           spelerAanBeurt++;
        }
        
    }
    public ArrayList getTegelArrayListVanSpeler(int speler){
            Object s = gesorteerdeSpelerLijst.get(speler);
            Speler p = (Speler) s;
        return p.getGekozenTegels();
        
    }
    
  public ArrayList getTegelArrayList() {
       return speler.getTegelArrayList();
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
    public void verwijderTegelVanSpeler(int speler, int tegel){
        Speler s = (Speler) gesorteerdeSpelerLijst.get(speler);
        s.verwijderTegelVanSpeler(tegel);
    }

    public int getSpelerAanBeurt() {
        return spelerAanBeurt;
    }
    public void voegTegelToeAanSpeler(int tegel){
        speler.voegTegelToeAanSpeler(tegel);
    }
    public void pasHighscoreAan(String naam, int highscore){
        sm.voegHighScoreToeAanSpeler(naam, highscore);
    }
}
