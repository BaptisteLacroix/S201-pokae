/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IEspece.java
 */
package pokemon;

import attaque.Capacite;
import interfaces.ICapacite;
import interfaces.IEspece;
import interfaces.IStat;
import interfaces.IType;
import statsPokemon.Categorie;
import statsPokemon.Stat;
import statsPokemon.Type;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author Lacroix baptiste and Vidal Théo
 */
public class Espece implements IEspece {
    private final IStat baseStat;
    private final String nom;
    private final int niveauDepart;
    private final int baseExp;
    private final IStat gainsStat; //  Stats EV
    private ICapacite[] capSet;
    private final IType[] types;


    public Espece(IStat baseIStat, String nom, int niveauDepart, int baseExp, IStat gainsStat, IType[] types) {
        this.baseStat = baseIStat;
        this.nom = nom;
        this.niveauDepart = niveauDepart;
        this.baseExp = baseExp;
        this.gainsStat = gainsStat;
        this.types = types;
        this.setCapSet();
    }


    /**
     * Cette fonction renvoie la statistique de base de l'élément
     *
     * @return La baseStat
     */
    @Override
    public IStat getBaseStat() {
        return baseStat;
    }


    /**
     * Cette fonction retourne le nom de l'objet
     *
     * @return Le nom du Pokemon
     */
    @Override
    public String getNom() {
        return nom;
    }


    /**
     * Cette fonction renvoie le niveau de départ du Pokemon
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
     * Renvoie la statistique utilisée pour calculer les gains.
     *
     * @return La variable gainsStat.
     */
    @Override
    public IStat getGainsStat() {
        return gainsStat;
    }


    /**
     * Il lit un fichier csv et crée une liste d'objets ICapacite
     */
    private void setCapSet() {
        boolean trouve = false;
        try {
            FileReader file = new FileReader("./resources/listeCapacitesEspeces.csv");
            BufferedReader reader = new BufferedReader(file);
            while (reader.ready() && !trouve) {
                Scanner scanner = new Scanner(reader.readLine()).useDelimiter(";");
                String[] tab = scanner.nextLine().split(";");
                if (tab[0].equals(this.nom)) {
                    this.creatCap(tab);
                    trouve = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Il lit un fichier csv et crée une liste d'objets ICapacite
     *
     * @param tableau le tableau de chaînes contenant le nom, le type et les capacités du pokémon.
     */
    private void creatCap(String[] tableau) {
        boolean trouve = false;
        List<ICapacite> cap = new ArrayList<>();
        for (int i = 1; i < tableau.length; i++) {
            try {
                FileReader file2 = new FileReader("./resources/listeCapacites.csv");
                BufferedReader reader = new BufferedReader(file2);
                reader.readLine();
                while (reader.ready() && !trouve) {
                    Scanner scanner = new Scanner(reader.readLine()).useDelimiter(";");
                    String[] tab = scanner.nextLine().split(";");
                    if (tab[0].equals(tableau[i])) {
                        cap.add(new Capacite(tab[0], Double.parseDouble(tab[2]), Integer.parseInt(tab[1]),
                                Integer.parseInt(tab[3]), Categorie.valueOf(tab[5]), conversionStringType(tab[6])));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.capSet = new ICapacite[cap.size()];
        for (int i = 0; i < cap.size(); i++) {
            if (cap.get(i) != null) {
                this.capSet[i] = cap.get(i);
            }
        }
    }

    /**
     * Une méthode qui renvoie l'ensemble des capacités disponibles pour cette espèce.
     *
     * @return un objet ICapacite
     */
    @Override
    public ICapacite[] getCapSet() {
        return this.capSet;
    } //ensemble des capacités disponibles pour cette espèce


    /**
     * Une méthode qui renvoie les types du pokémon.
     *
     * @return les types du pokémon
     */
    @Override
    public IType[] getTypes() {
        return types;
    } //une espece de pokemon peut avoir un ou deux types


    /**
     * Renvoie l'évolution si possible du Pokemon actuel
     *
     * @param niveau niveau du Pokemon dont on cherche son évolution
     * @return un objet IEspece
     */
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
                    espece = new Espece(stats, tab[1], Integer.parseInt(tab[15]),
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
    private static IType conversionStringType(String EspeceType) {
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

}
