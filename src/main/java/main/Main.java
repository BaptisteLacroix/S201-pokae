package main;

import combat.Capacite;
import interfaces.*;
import pokedex.Pokedex;
import statsPokemon.Categorie;
import statsPokemon.Type;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        // Pokedex
        System.out.println("\n\n----------- Pokedex -----------\n\n");
        Pokedex p = new Pokedex();
        p.engendreRanch();
        System.out.println(p.getInfo("Bulbizarre").getNom());
        // System.out.println("INFOOOO : " + p.getInfo("Bulbizare").getNom()); // -> Erreur
        IType typeA = Type.Insecte;
        IType typeD = Type.Sol;
        System.out.println(p.getEfficacite(typeA, typeD));
        System.out.println(p.getCapacite("Écras'Face").getNom()); // -> Fonctionne
        // System.out.println("INFOOOO : " + p.getCapacite("Écras").getNom()); // -> Erreur
        System.out.println(p.getCapacite(1).getNom()); // -> Fonctionne
        // System.out.println("INFOOOO : " + p.getCapacite(0).getNom()); // -> Erreur


        // Pokemon
        System.out.println("\n\n----------- Pokemon -----------\n\n");
        System.out.println(Arrays.toString(p.getRanch()));
        IPokemon pokemon = p.getRanch()[0];
        System.out.println(pokemon.getEspece());
        System.out.println(pokemon.peutMuter());
        System.out.println(pokemon.aChangeNiveau());
        System.out.println(pokemon.estEvanoui());

        ICapacite[] capacite = new Capacite[4];
        capacite[0] = new Capacite("Écras'Face", 1.00, 40,
                35, Categorie.Physique, Type.Normal);
        capacite[1] = new Capacite("Poing Karaté", 1.00, 50,
                25, Categorie.Physique, Type.Normal);
        capacite[2] = new Capacite("Poing Feu", 1.00, 75,
                15, Categorie.Physique, Type.Feu);
        capacite[3] = new Capacite("Guillotine", 0.30, -1,
                5, Categorie.Physique, Type.Normal);
        pokemon.apprendCapacites(capacite);
        System.out.println(Arrays.toString(pokemon.getCapacitesApprises()));
        pokemon.remplaceCapacite(1, capacite[0]);
        System.out.println(Arrays.toString(pokemon.getCapacitesApprises()));

        System.out.println("\nNom Pokemon : " + pokemon.getNom());
        System.out.println("Stat Pokemon : " + pokemon.getStat());
        pokemon.getStat().setPV(10);
        System.out.println("PV SOIGNE : " + pokemon.getStat().getPV());
        pokemon.soigne();
        System.out.println("PV : " + pokemon.getStat().getPV());


        // Espèce
        System.out.println("\n\n----------- Espèce -----------\n\n");
        System.out.println(pokemon.getEspece().getGainsStat());
        System.out.println(pokemon.getEspece().getBaseExp());
        System.out.println(Arrays.toString(Arrays.stream(pokemon.getEspece().getCapSet()).toArray()));
        System.out.println(Arrays.toString(pokemon.getEspece().getTypes()));
        System.out.println(pokemon.getEspece().getEvolution(pokemon.getNiveau()));
    }
}
