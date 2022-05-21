/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IPokedex.java
 */
package main.java.pokedex;

import main.interfaces.IPokedex;
import main.interfaces.IEspece;
import main.interfaces.IPokemon;
import main.interfaces.ICapacite;
import main.interfaces.IStat;
import main.interfaces.IType;
import main.java.pokemon.Espece;
import main.java.pokemon.Pokemon;
import main.java.statsPokemon.Capacite;
import main.java.statsPokemon.Categorie;
import main.java.statsPokemon.Stat;
import main.java.statsPokemon.Type;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Lacroix Baptiste
 */
public class Pokedex implements IPokedex {
    private final Map<Integer, IPokemon> dico = new HashMap<>();
    private final IPokemon[] ranch = new IPokemon[6];

    /**
     * Il génère une équipe aléatoire de 6 pokemon
     *
     * @return Un tableau d'IPokemon
     */
    @Override
    public IPokemon[] engendreRanch() {
        Random rand = new Random();
        IPokemon pokemon;
        this.initializeFromCSV();
        for (int i = 0; i < 6; i++) {
            pokemon = dico.get(rand.nextInt(151));
            while (pokemon == null) {
                pokemon = dico.get(rand.nextInt(151));
            }
            this.ranch[i] = pokemon;
            // System.out.println(this.ranch[i].getNom());
            // System.out.println(this.ranch[i].peutMuter());
        }
        return this.ranch;
    }           //Renvoie un tableau de 6 Pokemon au hasard


    /**
     * Il lit le fichier ligne par ligne jusqu'à ce qu'il atteigne la ligne correspondant au nom de l'espèce, puis il lit
     * la valeur correspondant à l'espèce
     *
     * @param nomEspece chaine de caractère (String) correspondant au nom de l'espece recherchée
     * @return une instance de l'espèce de Pokemon
     */
    @Override
    public IEspece getInfo(String nomEspece) {
        IEspece espece = null;
        IType[] type = new IType[2];
        boolean trouve = false;
        try {
            FileReader file = new FileReader("./resources/listePokemeon1G.csv");
            BufferedReader reader = new BufferedReader(file);
            reader.readLine();
            while (reader.ready() && !trouve) {
                Scanner scanner = new Scanner(reader.readLine()).useDelimiter(";");
                String[] tab = scanner.nextLine().split(";");
                if (tab[1].equals(nomEspece)) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return espece;
    }           //Renvoie une instance de l'espèce de Pokemon dont on fournit le nom


    /**
     * Il lit un fichier csv et renvoie un double
     * It reads the file line by line until it reaches the line corresponding to the attacker's type, then it reads the
     * value corresponding to the defender's type
     *
     * @param attaque Le type d'attaque
     * @param defense Le type du Pokémon défenseur
     * @return L'efficacité de l'attaque
     */
    @Override
    public Double getEfficacite(IType attaque, IType defense) {
        double eff = 0.0;
        int nbrA = 0;
        int nbrD = 0;
        try {
            FileReader file = new FileReader("./resources/efficacites.csv");
            BufferedReader reader = new BufferedReader(file);
            Scanner s = new Scanner(reader.readLine()).useDelimiter(";");
            String[] tab = s.nextLine().split(";");
            for (int i = 0; i < tab.length - 1; i++) {
                if (attaque.getNom().equals(tab[i]))
                    nbrA = i;
                if (defense.getNom().equals(tab[i]))
                    nbrD = i;
            }
            eff = this.foundEfficacite(nbrA, nbrD, reader);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eff;
    }           //Calcule l'efficacité d'un type d'attaque sur un type de cible


    /**
     * Il lit le fichier ligne par ligne jusqu'à ce qu'il atteigne la ligne correspondant au type de l'attaquant, puis il
     * lit la valeur correspondant au type du défenseur
     *
     * @param nbrA   Le numéro du pokémon de l'attaquant
     * @param nbrD   le nombre de pokémon du défenseur
     * @param reader l'objet BufferedReader qui lit le fichier CSV
     * @return L'efficacité de l'attaque du pokémon dans la ligne nbrA et la colonne nbrD.
     */
    private double foundEfficacite(int nbrA, int nbrD, BufferedReader reader) throws IOException {
        for (int i = 0; i < nbrA - 1; i++) {
            reader.readLine();
        }
        Scanner s = new Scanner(reader.readLine()).useDelimiter(";");
        String[] tab = s.nextLine().split(";");
        return Double.parseDouble(tab[nbrD]);
    }


    /**
     * Il lit un fichier csv et renvoie un objet Capacite
     *
     * @param nomCapacite le nom de la capacité
     * @return Un objet ICapacite
     */
    @Override
    public ICapacite getCapacite(String nomCapacite) {
        ICapacite capacite = null;
        boolean trouve = false;
        try {
            FileReader file = new FileReader("./resources/listeCapacites.csv");
            BufferedReader reader = new BufferedReader(file);
            reader.readLine();
            while (reader.ready() && !trouve) {
                Scanner scanner = new Scanner(reader.readLine()).useDelimiter(";");
                String[] tab = scanner.nextLine().split(";");
                if (tab[0].equals(nomCapacite)) {
                    capacite = new Capacite(tab[0], Double.parseDouble(tab[2]), Integer.parseInt(tab[1]),
                            Integer.parseInt(tab[3]), Categorie.valueOf(tab[5]), conversionStringType(tab[6]));
                    trouve = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return capacite;
    }            //Renvoie une instance de la capacité appelée nomCapacite


    /**
     * Il lit un fichier csv et renvoie un objet Capacite
     *
     * @param nunCapacite le numéro de la capacité
     * @return Un objet ICapacite
     */
    @Override
    public ICapacite getCapacite(int nunCapacite) {
        ICapacite capacite = null;
        boolean trouve = false;
        try {
            FileReader file = new FileReader("./resources/listeCapacites.csv");
            BufferedReader reader = new BufferedReader(file);
            reader.readLine();
            while (reader.ready() && !trouve) {
                Scanner scanner = new Scanner(reader.readLine()).useDelimiter(";");
                String[] tab = scanner.nextLine().split(";");
                if (Integer.parseInt(tab[4]) == nunCapacite) {
                    capacite = new Capacite(tab[0], Double.parseDouble(tab[2]), Integer.parseInt(tab[1]),
                            Integer.parseInt(tab[3]), Categorie.valueOf(tab[5]), conversionStringType(tab[6]));
                    trouve = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return capacite;
    }


    /**
     * Il lit un fichier CSV et crée un objet Pokemon pour chaque ligne du fichier
     */
    private void initializeFromCSV() {
        int id;
        String nom;
        int niveau;
        int pv, force, defense, special, vitesse;
        int evPV, evForce, evDefense, evSpecial, evVitesse;
        IType[] types = new IType[2];
        int expbase;
        try {
            FileReader file = new FileReader("./resources/listePokemeon1G.csv");
            BufferedReader reader = new BufferedReader(file);
            reader.readLine();
            while (reader.ready()) {
                Scanner s = new Scanner(reader.readLine()).useDelimiter(";");
                id = s.nextInt();
                nom = s.next();
                pv = s.nextInt();
                force = s.nextInt();
                defense = s.nextInt();
                special = s.nextInt();
                vitesse = s.nextInt();
                expbase = s.nextInt();
                evPV = s.nextInt();
                evForce = s.nextInt();
                evDefense = s.nextInt();
                evSpecial = s.nextInt();
                evVitesse = s.nextInt();
                types[0] = conversionStringType(s.next());
                types[1] = conversionStringType(s.next());
                niveau = s.nextInt();

                if (niveau == 1) {
                    IStat stats = new Stat(pv, force, defense, special, vitesse);
                    IStat evstats = new Stat(evPV, evForce, evDefense, evSpecial, evVitesse);
                    IEspece espece = new Espece(id, stats, nom, niveau, expbase, evstats, types);
                    this.dico.put(id, new Pokemon(id, nom, niveau, stats, expbase, 100.0, espece));
                }
            }
            reader.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Il prend une chaîne et renvoie le type correspondant
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

    /**
     * Cette fonction renvoie le tableau des Pokémon qui se trouvent dans le ranch.
     *
     * @return Le tableau ranch est renvoyé.
     */
    public IPokemon[] getRanch() {
        return ranch;
    }
}