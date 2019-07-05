/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistentie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import domein.*;
import java.time.LocalDate;
import java.time.ZoneId;

public class spelermapper {

    
   public void StartDriver() throws ClassNotFoundException{
       Class.forName ("com.mysql.jdbc.Driver");
   }
    
   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/spelers?user=Regenwormen&password=Regenwormen&serverTimezone=UTC";
    public boolean voegSpelerToe(Speler speler)
    {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement queryNieuweSpeler = conn.prepareStatement("INSERT INTO spelerinfo VALUES (?,?,?,?)");
            queryNieuweSpeler.setString(1,speler.getNaam());
            queryNieuweSpeler.setString(2, speler.getWachtwoord());
            queryNieuweSpeler.setDate(3, java.sql.Date.valueOf(speler.getGeboorteDatum()));
            queryNieuweSpeler.setInt(4, speler.getHighscore());
            queryNieuweSpeler.executeUpdate();
            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }

    /*
     * Zoekt de gebruiker met de opgegeven naam op in de databank.
     * Geeft null terug indien er geen gebruiker met deze naam werd gevonden.
     */
    public Speler zoekSpeler(String naam)
    {
        Speler speler = null;

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement queryZoekGebruiker = conn.prepareStatement("SELECT * FROM spelerinfo WHERE naam = ?");
            queryZoekGebruiker.setString(1, naam);
            try (ResultSet rs = queryZoekGebruiker.executeQuery()) {
                if (rs.next()) { // Als er een resultaat gevonden is.
                    String spelernaam = rs.getString("naam");
                    Date spelerLeeftijd = rs.getDate("geboortedatum");
                    String spelerWachtwoord = rs.getString("wachtwoord");
                    speler = new Speler();
                    speler.setNaam(spelernaam);
                    LocalDate leeftijd = spelerLeeftijd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    speler.setGeboorteDatum(leeftijd);
                    speler.setWachtwoord(spelerWachtwoord);
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }

        return speler;
    }

    /*
     * Zoekt alle spelerNamen op in de databank.
     */
    public List<String> zoekAlleGebruikers()
    {
        List<String> spelerNamen = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement queryAlleGebruikers = conn.prepareStatement("SELECT * FROM spelerlijst");
            try (ResultSet rs = queryAlleGebruikers.executeQuery()) {
                while (rs.next()) {
                    String naam = rs.getString("naam");
                    spelerNamen.add(naam);
                }
            }
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }

        return spelerNamen;
    }
    

    /*
     * Past een bestaande gebruiker aan in de databank.
     * De returnwaarde geeft aan of de bewerking geslaagd is.
     */
    public boolean pasGebruikerAan(Speler gebruiker)
    {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement queryUpdateGebruiker = conn.prepareStatement("UPDATE spelerlijst SET geboortedatum = ? WHERE naam = ?");
            queryUpdateGebruiker.setDate(1, java.sql.Date.valueOf(gebruiker.getGeboorteDatum()));
            queryUpdateGebruiker.setString(2, gebruiker.getNaam());
            queryUpdateGebruiker.executeUpdate();
            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }

    /*
     * Verwijdert de opgegeven gebruiker uit de databank.
     * De returnwaarde geeft aan of de bewerking geslaagd is.
     */
    public boolean verwijderGebruiker(Speler gebruiker)
    {
        return verwijderGebruiker(gebruiker.getNaam());
    }

    /*
     * Verwijdert de gebruiker met de opgegeven gebruikersnaam uit de databank.
     * De returnwaarde geeft aan of de bewerking geslaagd is.
     */
    public boolean verwijderGebruiker(String gebruikersnaam)
    {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            PreparedStatement queryVerwijderGebruiker = conn.prepareStatement("DELETE FROM spelerlijst WHERE naam = ?");
            queryVerwijderGebruiker.setString(1, gebruikersnaam);
            queryVerwijderGebruiker.executeUpdate();
            return true;
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
            return false;
        }
    }
   
}
