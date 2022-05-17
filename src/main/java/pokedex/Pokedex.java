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
 * @author Leo Donati
 */
public class Pokedex implements IPokedex {
    private IPokemon[] ranch;
    Map<String, IPokemon> dico = new HashMap<>();

    public IPokemon[] engendreRanch() {
        Random rand = new Random();
        // for (int i = 0; i < 6; i++) {
        // }
        throw new UnsupportedOperationException();
    }           //Renvoie un tableau de 6 Pokemon au hasard

    public IEspece getInfo(String nomEspece) {
        throw new UnsupportedOperationException();
    }           //Renvoie une instance de l'espèce de Pokemon dont on fournit le nom

    public Double getEfficacite(IType attaque, IType defense) {
        throw new UnsupportedOperationException();
    }           //Calcule l'efficacité d'un type d'attaque sur un type de cible

    public ICapacite getCapacite(String nomCapacite) {
        throw new UnsupportedOperationException();
    }               //Renvoie une instance de la capacité appelée nomCapacite

    public ICapacite getCapacite(int nunCapacite) {
        throw new UnsupportedOperationException();
    }

    public void initializeFromCSV(String namefile) {
        int id;
        String nom;
        int niveau;
        int pv;
        int force;
        int defense;
        int special;
        int vitesse;
        IType[] types = new IType[2];
        int experience;
        try {
            FileReader file = new FileReader(namefile);
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
                experience = s.nextInt();
                for (int i = 0; i < 5; i++) {
                    s.next();
                }
                types[0] = new Type(s.next());
                types[1] = new Type(s.next());
                niveau = s.nextInt();

                IStat stats = new Stat(pv, force, defense, special, vitesse);
                IEspece espece = new Espece(stats, nom, niveau, experience, , , , types);

                this.dico.put(nom, new Pokemon(id, nom, niveau, stats, experience, 100.0
                        , espece));
            }
            reader.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
