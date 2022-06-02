package combat;

import attaque.Echange;
import dresseur.Dresseur;
import interfaces.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * @author Lacroix Baptiste
 */
public class Combat implements ICombat {
    private List<ITour> tableauTours = new ArrayList<>();
    private final IDresseur dresseur1;
    private IPokemon pokemon1;
    private final IDresseur dresseur2;
    private IPokemon pokemon2;
    int ko1 = 0;
    int ko2 = 0;
    private int nbrTours = 1;

    public Combat(IDresseur dresseur1, IPokemon pokemon1, IDresseur dresseur2, IPokemon pokemon2) {
        this.dresseur1 = dresseur1;
        this.dresseur2 = dresseur2;
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
    }

    @Override
    public void commence() {
        this.pokemon1 = this.dresseur1.choisitCombattant();
        this.pokemon2 = this.dresseur2.choisitCombattant();
        System.out.println("Début du combat !");
        while (this.ko1 != 6 && this.ko2 != 6) {
            System.out.println("Début du tour : " + this.nbrTours);
            // Choix action Si echange ne fait rien si attaque check vitesse
            IAttaque attaque1 = this.dresseur1.choisitAttaque(this.pokemon1, this.pokemon2);
            // Choix action si echange ne fais rien si attaque check vitesse
            IAttaque attaque2 = this.dresseur2.choisitAttaque(this.pokemon2, this.pokemon1);
            if (attaque1.getClass() == Echange.class)
                this.pokemon1 = ((Echange) attaque1).echangeCombattant();
            if (attaque2.getClass() == Echange.class)
                this.pokemon2 = ((Echange) attaque2).echangeCombattant();
            this.tableauTours.add(this.nouveauTour(pokemon1, attaque1, pokemon2, attaque2));
            this.pokemon1.gagneExperienceDe(this.pokemon2);
            this.pokemon2.gagneExperienceDe(this.pokemon1);
            if (this.pokemon1.estEvanoui()) {
                this.ko1++;
                if (this.ko1 == 6)
                    this.termine();
                else
                    this.pokemon1 = this.dresseur1.choisitCombattant();
            }
            if (this.pokemon2.estEvanoui()) {
                this.ko2++;
                if (this.ko2 == 6)
                    this.termine();
                else
                    this.pokemon2 = this.dresseur2.choisitCombattant();
            }
            System.out.println("Fin du tour : " + this.nbrTours);
            this.nbrTours ++;
        }
        System.out.println("Fin du combat !");
        this.dresseur1.soigneRanch();
        this.dresseur2.soigneRanch();
        this.changementNiveau(this.dresseur1, this.pokemon1);
        this.changementNiveau(this.dresseur2, this.pokemon2);
    } // lance le combat

    private void changementNiveau(IDresseur dresseur, IPokemon pokemon) {
        if (pokemon.aChangeNiveau()) {
            Scanner input = new Scanner(System.in);
            System.out.println(dresseur + " voulez vous apprendre une nouvelle capacité ? (oui/non) ou (y/n) : ");
            String choix = input.next();
            if (choix.equals("oui") || choix.equals("y")) {
                this.changeCap(dresseur, pokemon);
            }
        }
    }

    private void changeCap(IDresseur dresseur, IPokemon pokemon) {
        ICapacite[] capacites = pokemon.getEspece().getCapSet();
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Capacités apprenables : ");
        System.out.println(Arrays.toString(capacites));
        System.out.println(dresseur.getNom() + " choose a new capacity to learn (give the name) : ");
        String choixCapacite = input.next();  // Read user input
        input = new Scanner(System.in);
        System.out.println(Arrays.toString(pokemon.getCapacitesApprises()));
        System.out.println(dresseur.getNom() + " choose the capacity you want to replace (give the number) : ");
        int choixIndex = input.nextInt();  // Read user input

        for (ICapacite cap : capacites) {
            if (cap.getNom().equals(choixCapacite)) {
                try {
                    pokemon.remplaceCapacite(choixIndex, cap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public IDresseur getDresseur1() {
        return dresseur1;
    } //Récupère le premier dresseur

    @Override
    public IDresseur getDresseur2() {
        return dresseur2;
    } //Récupère le second dresseur

    @Override
    public ITour nouveauTour(IPokemon pok1, IAttaque atk1, IPokemon pok2, IAttaque atk2) {
        return new Tour(pok1, atk1, pok2, atk2);
    } //Crée un tour du combat

    @Override
    public void termine() {
        Date date = new Date();
        try {
            PrintWriter writer = new PrintWriter("./resources/log.txt", StandardCharsets.UTF_8);
            writer.println(date);
            writer.println("Le combat a durée : " + this.nbrTours);
            for (ITour tour : this.tableauTours) {
                writer.println(tour.toString());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //affiche le bilan du combat et l'enregistre
}
