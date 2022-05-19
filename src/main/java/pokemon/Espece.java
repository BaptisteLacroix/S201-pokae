/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IEspece.java
 */
package main.java.pokemon;

import main.interfaces.ICapacite;
import main.interfaces.IEspece;
import main.interfaces.IType;
import main.interfaces.IStat;
import main.java.pokedex.Pokedex;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Leo Donati
 */
public class Espece implements IEspece {
    private IStat baseStat;
    private String nom;
    private int niveauDepart;
    private int baseExp;
    private IStat gainsStat; //  Stats EV
    private ICapacite[] capSet;
    private IStat evolution;
    private IType[] types;

    public Espece(IStat baseIStat, String nom, int niveauDepart, int baseExp, IStat gainsStat, IType[] types) {
        this.baseStat = baseIStat;
        this.nom = nom;
        this.niveauDepart = niveauDepart;
        this.baseExp = baseExp;
        this.gainsStat = gainsStat;
        this.types = types;
    }

    public static void main(String[] args) {
        Pokedex p = new Pokedex();
        p.engendreRanch();
    }

    @Override
    public IStat getBaseStat() {
        return baseStat;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public int getNiveauDepart() {
        return niveauDepart;
    }

    @Override
    public int getBaseExp() {
        return baseExp;
    }

    @Override
    public IStat getGainsStat() {
        return gainsStat;
    }

    @Override
    public ICapacite[] getCapSet() {
        throw new UnsupportedOperationException();
    } //ensemble des capacités disponibles pour cette espèce

    @Override
    public IType[] getTypes() {
        return types;
    } //une espece de pokemon peut avoir un ou deux types

    @Override
    public IEspece getEvolution(int niveau) {
        if (niveau >= 32)
            return null;
        String Evolution;
        String url;
        try {
            JSONObject obj = requestHTTP("");
            JSONArray modules = (JSONArray) obj.get("moves");
            for (Object m : modules) {
                JSONObject jsonObj = (JSONObject) m;
                JSONObject obj2 = (JSONObject) new JSONParser().parse(String.valueOf(jsonObj.get("move")));
                Evolution = (String) obj2.get("name");
                url = (String) obj2.get("url");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException();
    }  //renvoie null si aucune evolution possible

    public int recupPokemonUrl(String url) throws IOException, ParseException {
        int id;
        JSONObject obj = requestHTTP(url);
        id = (int) obj.get("id");
        System.out.println(id);
        return id;
    }

    private JSONObject requestHTTP(String url) throws IOException, ParseException {
        URL http = new URL(url);
        HttpURLConnection hpCon = (HttpURLConnection) http.openConnection();
        hpCon.connect();

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(hpCon.getInputStream()));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr = "";
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        inputStr = responseStrBuilder.toString();
        streamReader.close();

        JSONObject obj = (JSONObject) new JSONParser().parse(inputStr);
        return obj;
    }

}
