package main;

import attaque.Capacite;
import interfaces.*;
import pokedex.Pokedex;
import statsPokemon.Categorie;
import statsPokemon.Type;

import java.util.Arrays;

public class Main {

    /**
     * Ceci est une méthode qui illustre le fonctionnement des différentes classes
     * Il crée un Pokedex, crée un Pokémon, crée un Capacite, puis imprime le nom du Pokémon, son espèce, ses statistiques
     * et les dommages qu'il causerait à un autre Pokémon.
     */
    public static void main(String[] args) throws Exception {
        // Pokedex
        System.out.println("\n\n----------- Pokedex -----------\n\n");
        Pokedex p = new Pokedex();
        System.out.println("[Ranch en cours de création]");
        p.engendreRanch();
        System.out.println("Information à propos de l'espèce Bulbizarre : " + p.getInfo("Bulbizarre").getNom());
        System.out.println("Information à propos de l'espèce Bulbizarre : " + Arrays.toString(p.getInfo("Bulbizarre").getTypes()));
        IType typeA = Type.Insecte;
        IType typeD = Type.Sol;
        System.out.println("Efficacité entre Type Insecte et Type Plante : " + p.getEfficacite(typeA, Type.Plante));
        System.out.println("Nom de la capacité Ecras'Face : " + p.getCapacite("Écras'Face").getNom());
        System.out.println("Nom de la capacité qui a pour id 1 : " + p.getCapacite(1).getNom());


        // Pokemon
        System.out.println("\n\n----------- Pokemon -----------\n\n");
        System.out.println("[Affichage du ranch du joueur]");
        System.out.println(Arrays.toString(p.getRanch()));
        IPokemon pokemon = p.getRanch()[0];
        System.out.println("\nAffichage de l'espèce du Pokemon : " + pokemon.getEspece());
        System.out.println("Affichage permettant de savoir si le pokémon est pret pour mutation : " + pokemon.peutMuter());
        System.out.println("Affichage pour savoir si le Pokémon a changé de niveau : " + pokemon.aChangeNiveau());
        System.out.println("Affichage pour savoir si le Pokémon est évanoui : " + pokemon.estEvanoui());

        ICapacite[] capacite = new Capacite[4];
        System.out.println("\n[Création des capacités]");
        createCapacite(pokemon, capacite);
        System.out.println("\n[Affichage des capacités apprises par le Pokémon]");
        System.out.println(Arrays.toString(pokemon.getCapacitesApprises()));
        System.out.println("\n[Remplacement de la capacité 1 par la 0]");
        pokemon.remplaceCapacite(1, capacite[0]);
        System.out.println("\n[Affichage des capacités apprises par le Pokémon suite au changement de capacité]");
        System.out.println(Arrays.toString(pokemon.getCapacitesApprises()));

        System.out.println("\nNom Pokemon : " + pokemon.getNom());
        System.out.println("Stat Pokemon : " + pokemon.getStat());
        System.out.println("\n[Le Pokémon perd ses PV il ne lui en reste plus que 10]");
        pokemon.getStat().setPV(10);
        System.out.println("Affichage des PV suite à sa perte de vie : " + pokemon.getStat().getPV());
        System.out.println("[Le Pokémon se fait soigner]");
        pokemon.soigne();
        System.out.println("Affichage des PV suite à sa guérison: " + pokemon.getStat().getPV());


        // Espèce
        System.out.println("\n\n----------- Espèce -----------\n\n");
        System.out.println("Stat de l'espèce : " + pokemon.getEspece().getBaseStat());
        System.out.println("Ev de l'espèce : " + pokemon.getEspece().getGainsStat());
        System.out.println("Expérience de base de l'espèce : " + pokemon.getEspece().getBaseExp());
        System.out.println("\n[Recherche du nom des capacités possibles à l'apprentisssage]");
        System.out.println(Arrays.toString(Arrays.stream(pokemon.getEspece().getCapSet()).toArray()));
        System.out.println("Type de cette espèce : " + Arrays.toString(pokemon.getEspece().getTypes()));
        System.out.println("Recherche d'une possible évolution : " + pokemon.getEspece().getEvolution(pokemon.getNiveau()));

        // Capacité
        System.out.println("\n\n----------- Capacité -----------\n\n");
        IPokemon pokemon2 = p.getRanch()[1];
        ICapacite[] capacite2 = new Capacite[4];
        System.out.println("[Création des capacités]");
        createCapacite(pokemon2, capacite2);
        System.out.println("\n[Affichage des capacités apprises par le Pokémon 2]");
        System.out.println(Arrays.toString(pokemon2.getCapacitesApprises()));
        System.out.println("\nNom de la première capacité du Pokémon 1: " + capacite[0].getNom());
        System.out.println("Nom de la première capacité du Pokémon 2: " + capacite2[1].getNom());
        System.out.println("Précision de la première capacité du Pokémon 1: " + capacite[0].getPrecision());
        System.out.println("Précision de la première capacité du Pokémon 2: " + capacite2[1].getPrecision());

        System.out.println("Dégats infligé par Pokémon1 à Pokémon 2 : " + capacite[0].calculeDommage(pokemon, pokemon2));
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
