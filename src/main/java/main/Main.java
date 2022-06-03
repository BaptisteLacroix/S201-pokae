package main;

import combat.Combat;
import dresseur.DresseurIA;
import interfaces.*;

import java.util.Random;

public class Main {

    /**
     * Ceci est une méthode qui illustre le fonctionnement des différentes classes
     * Il crée un Pokedex, crée un Pokémon, crée un Capacite, puis imprime le nom du Pokémon, son espèce, ses statistiques
     * et les dommages qu'il causerait à un autre Pokémon.
     * @param args main param
     */
    public static void main(String[] args) {
        // DresseurHuman baptiste = new DresseurHuman("Baptiste");
        DresseurIA IA1 = new DresseurIA("IA1");
        DresseurIA IA2 = new DresseurIA("IA2");
        // for (IPokemon pokemon : baptiste.getRanch()) {
        //     ICapacite[] capacites = pokemon.getEspece().getCapSet();
        //     ICapacite[] capacitesApp = new ICapacite[4];
        //     int counter = 0;
        //     System.out.println("Capcités disponilbes : ");
        //     for (ICapacite cap : capacites) {
        //         System.out.printf("%-32s", "nom : " + cap.getNom() + " | précision : " + cap.getPrecision() + " | puissance : "
        //                 + cap.getPuissance() + " | PP : " + cap.getPuissance() + " | catégorie : " + cap.getCategorie() + " | type : " + cap.getType() + "\n");
        //     }
        //     System.out.println();
        //     for (int i = 0; i < 4; i++) {
        //         Scanner input = new Scanner(System.in);  // Create a Scanner object
        //         System.out.print(baptiste.getNom() + " choose a new capacity to learn for your pokemon " + pokemon.getNom() +" (give the name) : ");
        //         String choixCapacite = input.nextLine();  // Read user input
        //         for (ICapacite cap : capacites) {
        //             if (cap.getNom().equals(choixCapacite)) {
        //                 capacitesApp[counter] = cap;
        //                 counter++;
        //             }
        //         }
        //     }
        //     pokemon.apprendCapacites(capacitesApp);
        //     affichage(pokemon);
        // }

        choixIA(IA1);
        choixIA(IA2);

        //////////////////////////////////////////////////////////

        ICombat combat = new Combat(IA1, IA2);
        combat.commence();
    }

    private static void choixIA(DresseurIA dresseur) {
        Random rand = new Random();
        for (IPokemon pokemon : dresseur.getRanch()) {
            ICapacite[] capacites = pokemon.getEspece().getCapSet();
            System.out.println(dresseur.getNom() + " choose a new capacity to learn " + pokemon.getNom() + " (give the name) : ");
            ICapacite[] capacitesApp = new ICapacite[4];
            for (int i = 0; i < 4; i++) {
                capacitesApp[i] = capacites[rand.nextInt(capacites.length)];
            }
            pokemon.apprendCapacites(capacitesApp);
            affichage(pokemon);
        }
    }

    private static void affichage(IPokemon pokemon) {
        System.out.println("\n" + pokemon.getNom() + " capacités apprises : ");
        ICapacite[] capacite = pokemon.getCapacitesApprises();
        for (ICapacite caps : capacite) {
            System.out.printf("%-32s", "nom : " + caps.getNom() + " | précision : " + caps.getPrecision() + " | puissance : "
                    + caps.getPuissance() + " | PP : " + caps.getPuissance() + " | catégorie : " + caps.getCategorie() + " | type : " + caps.getType() + "\n");
        }
        System.out.println("\n");
    }
}
