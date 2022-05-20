/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IEspece.java
 */
package main.java.pokemon;

import main.interfaces.*;
import main.java.statsPokemon.Capacite;
import main.java.statsPokemon.Categorie;
import main.java.statsPokemon.Type;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @author Lacroix Baptiste
 */
public class Espece implements IEspece {
    private int id;
    private IStat baseStat;
    private String nom;
    private int niveauDepart;
    private int baseExp;
    private IStat gainsStat; //  Stats EV
    private ICapacite[] capSet;
    private IStat evolution;
    private IType[] types;

    /**
     * @param baseIStat
     * @param nom
     * @param niveauDepart
     * @param baseExp
     * @param gainsStat
     * @param types
     */
    public Espece(int id, IStat baseIStat, String nom, int niveauDepart, int baseExp, IStat gainsStat, IType[] types) {
        this.id = id;
        this.baseStat = baseIStat;
        this.nom = nom;
        this.niveauDepart = niveauDepart;
        this.baseExp = baseExp;
        this.gainsStat = gainsStat;
        this.types = types;
    }

    /**
     * @return
     */
    @Override
    public IStat getBaseStat() {
        return baseStat;
    }

    /**
     * @return
     */
    @Override
    public String getNom() {
        return nom;
    }

    /**
     * @return
     */
    @Override
    public int getNiveauDepart() {
        return niveauDepart;
    }

    /**
     * @return
     */
    @Override
    public int getBaseExp() {
        return baseExp;
    }

    /**
     * @return
     */
    @Override
    public IStat getGainsStat() {
        return gainsStat;
    }

    /**
     * @return
     */
    @Override
    public ICapacite[] getCapSet() {
        int cmp = 0;
        List<ICapacite> cap = new ArrayList<>();
        String url = this.getURLPokemon();
        List<String> nameMove = this.recupMoves(url);
        for (String s : nameMove) {
            try {
                FileReader file = new FileReader("./resources/listeCapacites.csv");
                BufferedReader reader = new BufferedReader(file);
                reader.readLine();
                Scanner scanner = new Scanner(reader.readLine()).useDelimiter(";");
                String[] tab = scanner.nextLine().split(";");
                while (!tab[0].equals(s) && reader.ready()) {
                    cmp ++;
                    scanner = new Scanner(reader.readLine()).useDelimiter(";");
                    tab = scanner.nextLine().split(";");
                }
                if (cmp <= 111)
                    cap.add(new Capacite(tab[0], Double.parseDouble(tab[2]), Integer.parseInt(tab[1]),
                        Integer.parseInt(tab[3]), Categorie.valueOf(tab[5]), Type.valueOf(tab[6])));
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.capSet = new ICapacite[cap.size()];
        for (int i = 0; i < cap.size(); i++) {
            this.capSet[i] = cap.get(i);
            System.out.println(this.capSet[i].toString());
        }
        return this.capSet;
    } //ensemble des capacités disponibles pour cette espèce

    private String getURLPokemon() {
        String pokemonurl = "";
        try {
            JSONObject obj = requestHTTP("https://pokeapi.co/api/v2/pokemon-species/" + this.id + "/");
            JSONArray modules = (JSONArray) obj.get("varieties");
            for (Object m : modules) {
                JSONObject jsonObj = (JSONObject) m;
                JSONObject obj2 = (JSONObject) new JSONParser().parse(String.valueOf(jsonObj.get("pokemon")));
                pokemonurl = (String) obj2.get("url");
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return pokemonurl;
    }

    private List<String> recupMoves(String url) {
        List<String> moveName = new ArrayList<>();
        try {
            JSONObject obj = requestHTTP(url);
            JSONArray modules = (JSONArray) obj.get("moves");
            for (Object m : modules) {
                JSONObject jsonObj = (JSONObject) m;
                JSONObject obj2 = (JSONObject) new JSONParser().parse(String.valueOf(jsonObj.get("move")));
                moveName.add(this.recupFrenchMoves((String) obj2.get("url")));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moveName;
    }

    private String recupFrenchMoves(String url) {
        String moveName = "";
        try {
            JSONObject obj = requestHTTP(url);
            JSONArray modules = (JSONArray) obj.get("names");
            JSONObject jsonObj = (JSONObject) modules.get(3);
            moveName = (String) jsonObj.get("name");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moveName;
    }


    /**
     * @return
     */
    @Override
    public IType[] getTypes() {
        return types;
    } //une espece de pokemon peut avoir un ou deux types

    /**
     * @param niveau
     * @return
     */
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

    /**
     * @param url
     * @return
     * @throws IOException
     * @throws ParseException
     */
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
