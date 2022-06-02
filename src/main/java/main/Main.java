package main;

import attaque.Capacite;
import dresseur.Dresseur;
import interfaces.*;
import statsPokemon.Categorie;
import statsPokemon.Type;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    /**
     * Ceci est une méthode qui illustre le fonctionnement des différentes classes
     * Il crée un Pokedex, crée un Pokémon, crée un Capacite, puis imprime le nom du Pokémon, son espèce, ses statistiques
     * et les dommages qu'il causerait à un autre Pokémon.
     */
    public static void main(String[] args) throws Exception {
        Random rand = new Random();
        Dresseur baptiste = new Dresseur("Baptiste");
        Dresseur IA = new Dresseur("IA");
        for (IPokemon pokemon : baptiste.getRanch()) {
            ICapacite[] capacites = pokemon.getEspece().getCapSet();
            ICapacite[] capacitesApp = new ICapacite[4];
            int counter = 0;
            for (int i = 0; i < 4; i ++) {
                Scanner input = new Scanner(System.in);  // Create a Scanner object
                System.out.println(Arrays.toString(capacites));
                System.out.print(baptiste.getNom() + " choose a new capacity to learn (give the name) : ");
                String choixCapacite = input.next();  // Read user input
                System.out.println(counter);
                for (ICapacite cap : capacites) {
                    if (cap.getNom().equals(choixCapacite)) {
                        capacitesApp[counter] = cap;
                        counter ++;
                    }
                }
            }
            System.out.println("\n" + Arrays.toString(capacitesApp));
            pokemon.apprendCapacites(capacitesApp);
            System.out.println(pokemon.getNom() + " capacités apprises : " + Arrays.toString(pokemon.getCapacitesApprises()));
        }

        for (IPokemon pokemon : IA.getRanch()) {
            ICapacite[] capacites = pokemon.getEspece().getCapSet();
            System.out.println(Arrays.toString(capacites));
            System.out.println(IA.getNom() + " choose a new capacity to learn (give the name) : ");
            ICapacite[] capacitesApp = new ICapacite[4];
            for (int i = 0; i < 5; i ++) {
                capacitesApp[i] = capacites[rand.nextInt(capacites.length)];
            }
            pokemon.apprendCapacites(capacitesApp);
            System.out.println(pokemon.getNom() + " capacités apprises : " + Arrays.toString(pokemon.getCapacitesApprises()));
        }
    }

    /**
     * Il crée les quatre capacités du pokémon puis les enseigne au pokémon
     *
     * @param pokemon2  le pokémon qui apprendra les mouvements
     * @param capacite2 la gamme d'ICapacite
     */
    private static void createCapacite(IPokemon pokemon2, ICapacite[] capacite2) {
        capacite2[0] = new Capacite("Écras'Face", 1.00, 40,
                35, Categorie.Physique, Type.Normal);
        capacite2[1] = new Capacite("Poing Karaté", 1.00, 50,
                25, Categorie.Physique, Type.Normal);
        capacite2[2] = new Capacite("Poing Feu", 1.00, 75,
                15, Categorie.Physique, Type.Feu);
        capacite2[3] = new Capacite("Guillotine", 0.30, -1,
                5, Categorie.Physique, Type.Normal);
        System.out.println("[Apprentissage des capacités]");
        pokemon2.apprendCapacites(capacite2);
    }
}
