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
import main.java.statsPokemon.Stat;
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
     * Il s'agit d'un constructeur.
     *
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
     * > Cette fonction renvoie la statistique de base de l'élément
     *
     * @return La baseStat
     */
    @Override
    public IStat getBaseStat() {
        return baseStat;
    }


    /**
     * > Cette fonction retourne le nom de l'objet
     *
     * @return Le nom de la personne
     */
    @Override
    public String getNom() {
        return nom;
    }


    /**
     * > Cette fonction renvoie le niveau de départ du Pokemon
     *
     * @return La variable niveauDepart.
     */
    @Override
    public int getNiveauDepart() {
        return niveauDepart;
    }


    /**
     * Renvoie l'expérience de base du Pokémon.
     *
     * @return L'expérience de base du Pokémon.
     */
    @Override
    public int getBaseExp() {
        return baseExp;
    }


    /**
     * Renvoie la statistique utilisée pour calculer les gains de cette statistique.
     *
     * @return La variable gainsStat.
     */
    @Override
    public IStat getGainsStat() {
        return gainsStat;
    }

    /**
     * Une méthode qui renvoie l'ensemble des capacités disponibles pour cette espèce.
     *
     * @return
     */
    @Override
    public ICapacite[] getCapSet() {
        List<ICapacite> cap = new ArrayList<>();
        String url = this.getURLPokemon();
        List<String> nameMove = this.recupMoves(url);
        for (String s : nameMove) {
            try {
                FileReader file = new FileReader("./resources/listeCapacites.csv");
                BufferedReader reader = new BufferedReader(file);
                reader.readLine();
                while (reader.ready()) {
                    Scanner scanner = new Scanner(reader.readLine()).useDelimiter(";");
                    String[] tab = scanner.nextLine().split(";");
                    if (tab[0].equals(s)) {
                        cap.add(new Capacite(tab[0], Double.parseDouble(tab[2]), Integer.parseInt(tab[1]),
                                Integer.parseInt(tab[3]), Categorie.valueOf(tab[5]), Type.valueOf(tab[6])));
                    }
                }
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.capSet = new ICapacite[cap.size()];
        for (int i = 0; i < cap.size(); i++) {
            this.capSet[i] = cap.get(i);
        }
        return this.capSet;
    } //ensemble des capacités disponibles pour cette espèce

    /**
     * Il obtient l'URL du pokemon à partir de l'API pokemon-species
     *
     * @return L'URL du Pokémon
     */
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

    /**
     * Il prend une URL en paramètre et renvoie une liste de chaînes
     *
     * @param url l'url du pokémon
     * @return Une liste de mouvements
     */
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
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return moveName;
    }

    /**
     * Il prend une url en paramètre, et retourne le nom du move en français
     *
     * @param url l'url du move dont vous voulez obtenir le nom
     * @return Le nom du déménagement en français.
     */
    private String recupFrenchMoves(String url) {
        String moveName = "";
        try {
            JSONObject obj = requestHTTP(url);
            JSONArray modules = (JSONArray) obj.get("names");
            JSONObject jsonObj = (JSONObject) modules.get(3);
            moveName = (String) jsonObj.get("name");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return moveName;
    }


    /**
     * Une méthode qui renvoie les types du pokémon.
     *
     * @return les types du pokémon
     */
    @Override
    public IType[] getTypes() {
        return types;
    } //une espece de pokemon peut avoir un ou deux types


    @Override
    public IEspece getEvolution(int niveau) {
        if (niveau >= 32)
            return null;
        Espece espece = null;
        try {
            boolean trouve = false;
            FileReader file = new FileReader("./resources/listePokemeon1G.csv");
            BufferedReader reader = new BufferedReader(file);
            reader.readLine();
            while (reader.ready() && !trouve) {
                Scanner scanner = new Scanner(reader.readLine()).useDelimiter(";");
                String[] tab = scanner.nextLine().split(";");
                if (tab[1].equals(this.nom) && !tab[17].equals("")) {
                    scanner = new Scanner(reader.readLine()).useDelimiter(";");
                    tab = scanner.nextLine().split(";");
                    IType[] type = new IType[2];
                    IStat stats = new Stat(Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]),
                            Integer.parseInt(tab[5]), Integer.parseInt(tab[6]));
                    IStat evstats = new Stat(Integer.parseInt(tab[8]), Integer.parseInt(tab[9]), Integer.parseInt(tab[10]),
                            Integer.parseInt(tab[11]), Integer.parseInt(tab[12]));
                    type[0] = conversionStringType(tab[13]);
                    type[1] = conversionStringType(tab[14]);
                    espece = new Espece(Integer.parseInt(tab[0]), stats, tab[1], Integer.parseInt(tab[15]),
                            Integer.parseInt(tab[7]), evstats, type);
                    trouve = true;
                }
            }
            reader.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return espece;
    }  //renvoie null si aucune evolution possible

    /**
     * Il convertit une chaîne en un Type
     *
     * @param EspeceType Le type de Pokémon.
     * @return Le type de pokémon
     */
    private Type conversionStringType(String EspeceType) {
        switch (EspeceType) {
            case "Combat":
                return Type.Combat;
            case "Dragon":
                return Type.Dragon;
            case "Eau":
                return Type.Eau;
            case "Electrik":
                return Type.Electrik;
            case "Feu":
                return Type.Feu;
            case "Glace":
                return Type.Glace;
            case "Insecte":
                return Type.Insecte;
            case "Normal":
                return Type.Normal;
            case "Plante":
                return Type.Plante;
            case "Poison":
                return Type.Poison;
            case "Psy":
                return Type.Psy;
            case "Roche":
                return Type.Roche;
            case "Sol":
                return Type.Sol;
            case "Spectre":
                return Type.Spectre;
            case "Vol":
                return Type.Vol;
            default:
                return null;
        }
    }

    /**
     * Il prend une URL sous forme de chaîne, ouvre une connexion à cette URL, lit la réponse et renvoie un JSONObject
     *
     * @param url L'URL du point de terminaison de l'API.
     * @return Un objet JSON
     */
    private JSONObject requestHTTP(String url) throws IOException, ParseException {
        URL http = new URL(url);
        HttpURLConnection hpCon = (HttpURLConnection) http.openConnection();
        hpCon.connect();

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(hpCon.getInputStream()));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        inputStr = responseStrBuilder.toString();
        streamReader.close();

        return (JSONObject) new JSONParser().parse(inputStr);
    }

}
