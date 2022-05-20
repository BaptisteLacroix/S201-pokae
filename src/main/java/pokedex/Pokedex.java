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
    Map<Integer, IPokemon> dico = new HashMap<>();

    private IPokemon[] ranch = new IPokemon[6];

    /**
     * It generates a random array of 6 Pokemon.
     * @return
     */
    @Override
    public IPokemon[] engendreRanch() {
        Random rand = new Random();
        IPokemon pokemon;
        this.initializeFromCSV();
        this.ranch[0] = dico.get(0);
        for (int i = 1; i < 6; i++) {
            pokemon = dico.get(rand.nextInt(151));
            while (pokemon == null) {
                pokemon = dico.get(rand.nextInt(151));
            }
            this.ranch[i] = pokemon;
            System.out.println(this.ranch[i].getId() + " : " + this.ranch[i].getEspece().getCapSet().length);
        }
        return this.ranch;
    }           //Renvoie un tableau de 6 Pokemon au hasard

    @Override
    public IEspece getInfo(String nomEspece) {
        throw new UnsupportedOperationException();
    }           //Renvoie une instance de l'espèce de Pokemon dont on fournit le nom


    /**
     * It reads the file line by line until it reaches the line corresponding to the attacker's type, then it reads the
     * value corresponding to the defender's type
     * @param attaque
     * @param defense
     * @return
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
     * It reads the file line by line until it reaches the line corresponding to the attacker's type, then it reads the
     * value corresponding to the defender's type
     *
     * @param nbrA The number of the attacker's pokemon
     * @param nbrD the number of the defender's pokemon
     * @param reader the BufferedReader object that reads the CSV file
     * @return The efficiency of the attack of the pokemon in the nbrA line and the nbrD column.
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
     * @param nomCapacite
     * @return
     */
    @Override
    public ICapacite getCapacite(String nomCapacite) {
        throw new UnsupportedOperationException();
    }            //Renvoie une instance de la capacité appelée nomCapacite


    /**
     * > This function is not supported
     *
     * @param nunCapacite The number of the ability to get.
     * @return A ICapacite object.
     */
    @Override
    public ICapacite getCapacite(int nunCapacite) {
        throw new UnsupportedOperationException();
    }


    /**
     * It reads a csv file and creates a Pokemon object for each line of the file
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
     * It converts a string into a type
     *
     * @param EspeceType The type of the Pokemon
     * @return The type of the pokemon
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

}