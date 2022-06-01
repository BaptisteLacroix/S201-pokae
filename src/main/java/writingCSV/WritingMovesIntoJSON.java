package writingCSV;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WritingMovesIntoJSON {
    public static void main(String[] args) throws IOException {
        WritingMovesIntoJSON writingMovesWLVL = new WritingMovesIntoJSON();
        writingMovesWLVL.writeIntoJson();
    }

    /**
     * Il prend le nom du pokémon et le nom des mouvements qui lui sont associés et les écrit dans un fichier csv
     */
    public void writeIntoJson() throws IOException {
        int id = 1;
        FileWriter file = new FileWriter("listeCapacitesEspecesWLVL.json");
        JSONObject containerPrincipal = new JSONObject(); // (Objet) Conteneur princiapl ->  { Pokemon [
        JSONArray listeContainerSecondaire = new JSONArray();  // (Liste) Contient une liste de conteneurs secondaires
        while (id < 152) {
            String[] infoP = this.getAllURLPokemon(id);
            System.out.println("id : " + id + " Pokemon : " + infoP[1]);
            Map<Integer, String> dictionnaire = this.recupMoves(infoP[0]);
            System.out.println("dictionnaire en sortie : " + dictionnaire);

            JSONObject containerSecondaire = new JSONObject(); // (Objet) Conteneur secondaire -> { nombre [
            JSONArray listeContainerTertiaire = new JSONArray(); // (Liste) Contient une liste de 2 éléments dont un conteneur et une liste
            JSONObject containerTertiaire = new JSONObject(); // (Objet -> Liste) Conteneur tertiaire -> nom : dsds, moves [ {
            JSONArray listeContainerQuatrieme = new JSONArray(); // (Liste) Contient une liste de 2 éléments dont un conteneur et une liste
            JSONObject containerQuatrieme; // (Objet -> Liste) Conteneur tertiaire -> nom : dsds, moves [ {

            for (Map.Entry<Integer, String> values : dictionnaire.entrySet()) {
                containerQuatrieme = new JSONObject();
                containerQuatrieme.put("lvl", values.getKey());
                containerQuatrieme.put("move", values.getValue());
                listeContainerQuatrieme.add(containerQuatrieme);
            }

            containerTertiaire.put("moves", listeContainerQuatrieme);
            containerTertiaire.put("nom", infoP[1]);
            listeContainerSecondaire.add(containerTertiaire);

            id++;
        }
        try {
            containerPrincipal.put("Pokemon", listeContainerSecondaire);
            System.out.println("JSON file created : " + containerPrincipal);
            file.write(containerPrincipal.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.close();
    }

    /**
     * Il obtient l'URL du pokemon et le nom du pokemon
     *
     * @param id L'identifiant du pokémon dont vous souhaitez obtenir les informations.
     * @return L'URL du pokémon et le nom du pokémon.
     */
    private String[] getAllURLPokemon(int id) {
        String[] pokemonurl = new String[2];
        try {
            JSONObject obj = requestHTTP("https://pokeapi.co/api/v2/pokemon-species/" + id + "/");
            JSONArray modules = (JSONArray) obj.get("varieties");
            JSONObject jsonObj = (JSONObject) modules.get(0);
            JSONObject obj2 = (JSONObject) jsonObj.get("pokemon");
            pokemonurl[0] = (String) obj2.get("url");

            JSONArray modules2 = (JSONArray) obj.get("names");
            JSONObject jsonObj2 = (JSONObject) modules2.get(4);
            pokemonurl[1] = (String) jsonObj2.get("name");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return pokemonurl;
    }

    /**
     * Il prend une URL en paramètre et renvoie une liste de chaînes
     *
     * @param url l'url du pokémon
     * @return Une liste des Moves
     */
    private Map<Integer, String> recupMoves(String url) {
        Map<Integer, String> dictionnaire = new HashMap<>();
        List<String> moveName = new ArrayList<>();
        List<Integer> lvl = new ArrayList<>();
        try {
            JSONObject obj = requestHTTP(url);
            JSONArray modules = (JSONArray) obj.get("moves");
            for (Object m : modules) {
                JSONObject jsonObj = (JSONObject) m;
                JSONObject obj2 = (JSONObject) new JSONParser().parse(String.valueOf(jsonObj.get("move")));
                moveName.add(this.recupFrenchMoves((String) obj2.get("url")));
            }
            modules = (JSONArray) obj.get("moves");
            for (Object m : modules) {
                JSONObject jsonObj = (JSONObject) m;
                JSONArray obj2 = (JSONArray) new JSONParser().parse(String.valueOf(jsonObj.get("version_group_details")));
                JSONObject ob3 = (JSONObject) obj2.get(0);
                lvl.add((int) (long) ob3.get("level_learned_at"));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < moveName.size(); i++) {
            dictionnaire.put(lvl.get(i), moveName.get(i));
        }
        return dictionnaire;
    }

    /**
     * Il prend une url en paramètre et retourne le nom du move en français
     *
     * @param url l'url du move dont vous voulez obtenir le nom
     * @return Le nom du Move en français.
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
