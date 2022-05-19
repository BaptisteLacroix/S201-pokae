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
    private IPokemon[] ranch = new IPokemon[6];
    Map<Integer, IEspece> dico = new HashMap<>();

    public IPokemon[] engendreRanch() {
        Random rand = new Random();
        IPokemon v;
        this.initializeFromCSV("./resources/listePokemeon1G.csv");
        System.out.println(dico);
        for (int i = 0; i < 6; i++) {
            v = (IPokemon) dico.get(rand.nextInt(151));
            while (v == null) {
                v = (IPokemon) dico.get(rand.nextInt(151));
            }
            ranch[i] = v;
        }
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
        int pv, force, defense, special, vitesse;
        int evPV, evForce, evDefense, evSpecial, evVitesse;
        IType[] types = new IType[2];
        int expbase;
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
                    this.dico.put(id, new Espece(stats, nom, niveau, expbase, evstats, types));
                }
            }
                reader.close();
                file.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }

    /*
    private Type conversionStringType(String EspeceType) {
        return switch (EspeceType) {
            case "Combat" -> Type.Combat;
            case "Dragon" -> Type.Dragon;
            case "Eau" -> Type.Eau;
            case "Electrik" -> Type.Electrik;
            case "Feu" -> Type.Feu;
            case "Glace" -> Type.Glace;
            case "Insecte" -> Type.Insecte;
            case "Normal" -> Type.Normal;
            case "Plante" -> Type.Plante;
            case "Poison" -> Type.Poison;
            case "Psy" -> Type.Psy;
            case "Roche" -> Type.Roche;
            case "Sol" -> Type.Sol;
            case "Spectre" -> Type.Spectre;
            case "Vol" -> Type.Vol;
            default -> null;
        };
    }*/

        private Type conversionStringType (String EspeceType){
            Type type = null;
            switch (EspeceType) {
                case "Combat":
                    type = Type.Combat;
                    break;
                case "Dragon":
                    type = Type.Dragon;
                    break;
                case "Eau":
                    type = Type.Eau;
                    break;
                case "Electrik":
                    type = Type.Electrik;
                    break;
                case "Feu":
                    type = Type.Feu;
                    break;
                case "Glace":
                    type = Type.Glace;
                    break;
                case "Insecte":
                    type = Type.Insecte;
                    break;
                case "Normal":
                    type = Type.Normal;
                    break;
                case "Plante":
                    type = Type.Plante;
                    break;
                case "Poison":
                    type = Type.Poison;
                    break;
                case "Psy":
                    type = Type.Psy;
                    break;
                case "Roche":
                    type = Type.Roche;
                    break;
                case "Sol":
                    type = Type.Sol;
                    break;
                case "Spectre":
                    type = Type.Spectre;
                    break;
                case "Vol":
                    type = Type.Vol;
                    break;
            }

            return type;
        }
    }
