package combat;

import attaque.Echange;
import dresseur.DresseurHuman;
import dresseur.DresseurIA;
import interfaces.*;
import writingCSV.Chrono;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * @author Lacroix Baptiste
 */
public class Combat implements ICombat {
    private final List<String> tableauTours = new ArrayList<>();
    private final IDresseur dresseur1;
    private IPokemon pokemon1;
    private final IDresseur dresseur2;
    private IPokemon pokemon2;
    int ko1 = 0;
    int ko2 = 0;
    private int nbrTours = 1;
    private final Random rand = new Random();
    private final Chrono chrono = new Chrono();

    public Combat(IDresseur dresseur1, IDresseur dresseur2) {
        this.dresseur1 = dresseur1;
        this.dresseur2 = dresseur2;
    }

    @Override
    public void commence() {
        this.pokemon1 = this.dresseur1.choisitCombattant();
        this.pokemon2 = this.dresseur2.choisitCombattant();
        System.out.println("------------------ Début du combat ! ------------------");
        this.chrono.start(); // démarrage du chrono
        while (this.ko1 != 6 && this.ko2 != 6) {
            System.out.println("\n\n\n\n\n\n<<<<<<<<<<<<<<<<<< Début du tour : " + this.nbrTours + " >>>>>>>>>>>>>>>>>");
            // Choix action Si echange ne fait rien si attaque check vitesse
            IAttaque attaque1 = this.dresseur1.choisitAttaque(this.pokemon1, this.pokemon2);
            // Choix action si echange ne fais rien si attaque check vitesse
            IAttaque attaque2 = this.dresseur2.choisitAttaque(this.pokemon2, this.pokemon1);
            if (attaque1.getClass() == Echange.class)
                this.pokemon1 = ((Echange) attaque1).echangeCombattant();
            if (attaque2.getClass() == Echange.class)
                this.pokemon2 = ((Echange) attaque2).echangeCombattant();
            ITour tour = this.nouveauTour(pokemon1, attaque1, pokemon2, attaque2);
            tour.commence();
            this.tableauTours.add(tour.toString());
            this.afterTour();
            this.pokemon1.gagneExperienceDe(this.pokemon2);
            this.pokemon2.gagneExperienceDe(this.pokemon1);
            System.out.println("<<<<<<<<<<<<<<<<<< Fin du tour : " + this.nbrTours + " >>>>>>>>>>>>>>>>>\n");
            this.nbrTours++;
        }
        System.out.println("------------------ Fin du combat ! ------------------");
        System.out.println("Durée du combat  : " + this.chrono.getDureeTxt() + "\n"); // affichage au format "1 h 26 min 32 s"
        this.finCombat();
    } // lance le combat

    private void afterTour() {
        if (this.pokemon1.estEvanoui()) {
            this.ko1++;
            System.out.println("Il ne reste plus que " + (6 - this.ko1) + " pokémons dans le ranch de " +
                    this.dresseur1.getNom() + " encore en vie");
            System.out.println("ko1 : " + this.ko1);
            if (this.ko1 == 6)
                this.termine();
            else
                this.pokemon1 = this.dresseur1.choisitCombattant();
        }
        if (this.pokemon2.estEvanoui()) {
            this.ko2++;
            System.out.println("Il ne reste plus que " + (6 - this.ko2) + " pokémons dans le ranch de " +
                    this.dresseur2.getNom() + " encore en vie");
            System.out.println("ko2 : " + this.ko2);
            if (this.ko2 == 6)
                this.termine();
            else
                this.pokemon2 = this.dresseur2.choisitCombattant();
        }
    }

    private void finCombat() {
        this.dresseur1.soigneRanch();
        this.dresseur2.soigneRanch();
        for (int i = 0; i < 6; i++) {
            this.dresseur1.getPokemon(i).getCapacitesApprises()[0].resetPP();
            this.dresseur1.getPokemon(i).getCapacitesApprises()[1].resetPP();
            this.dresseur1.getPokemon(i).getCapacitesApprises()[2].resetPP();
            this.dresseur1.getPokemon(i).getCapacitesApprises()[3].resetPP();
            this.changementNiveau(this.dresseur1, this.dresseur1.getPokemon(i));

            this.dresseur2.getPokemon(i).getCapacitesApprises()[0].resetPP();
            this.dresseur2.getPokemon(i).getCapacitesApprises()[1].resetPP();
            this.dresseur2.getPokemon(i).getCapacitesApprises()[2].resetPP();
            this.dresseur2.getPokemon(i).getCapacitesApprises()[3].resetPP();
            this.changementNiveau(this.dresseur2, this.dresseur2.getPokemon(i));
        }
    }

    private void changementNiveau(IDresseur dresseur, IPokemon pokemon) {
        if (pokemon.aChangeNiveau() && dresseur.getClass() == DresseurHuman.class) {
            Scanner input = new Scanner(System.in);
            System.out.println(dresseur.getNom() + " voulez vous apprendre une nouvelle capacité ? (oui/non) ou (y/n) : ");
            String choix = input.next();
            if (choix.equals("oui") || choix.equals("y")) {
                this.changeCap(dresseur, pokemon);
            }
        } else if (pokemon.aChangeNiveau() && dresseur.getClass() == DresseurIA.class && this.rand.nextInt(2) == 0) {
            System.out.println(dresseur.getNom() + " choisit d'apprendre une nouvelle capacité !");
            this.changeCapIA(dresseur, pokemon);
        }
    }

    private void changeCapIA(IDresseur dresseur, IPokemon pokemon) {
        try {
            int capR = this.rand.nextInt(4);
            ICapacite capA = pokemon.getEspece().getCapSet()[this.rand.nextInt(pokemon.getEspece().getCapSet().length)];
            System.out.println(dresseur.getNom() + " choose a new capacity to learn " + capA.getNom());
            System.out.println(dresseur.getNom() + " choose to replace the capacity " + pokemon.getCapacitesApprises()[capR].getNom() + "\n");
            pokemon.remplaceCapacite(capR, capA);
        } catch (Exception e) {
            e.printStackTrace();
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
            PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true));
            writer.println("\n------------------ Début du combat ! ------------------\n");
            writer.println(date);
            this.chrono.stop(); // arrêt
            writer.println("Le combat a durée : " + this.chrono.getDureeTxt() + " en " + this.nbrTours + " tours");
            if (this.ko1 == 6 && this.ko2 != 6) {
                System.out.println("Le gagant est " + this.dresseur2.getNom() + "\nLe perdant est " + this.dresseur1.getNom());
                writer.println("Le gagant est " + this.dresseur2.getNom() + "\nLe perdant est " + this.dresseur1.getNom() + "\n");
            } else if (this.ko1 != 6 && this.ko2 == 6) {
                System.out.println("Le gagant est " + this.dresseur1.getNom() + "\nLe perdant est " + this.dresseur2.getNom());
                writer.println("Le gagant est " + this.dresseur1.getNom() + "\nLe perdant est " + this.dresseur2.getNom() + "\n");
            }
            int i = 1;
            for (String tour : this.tableauTours) {
                writer.println("<<<<<<<<<<<<<<<<<< Début du tour : " + i + " >>>>>>>>>>>>>>>>>");
                writer.println(tour);
                writer.println("<<<<<<<<<<<<<<<<<< Fin du tour : " + i + " >>>>>>>>>>>>>>>>>\n");
                i++;
            }
            writer.println("\n------------------ Fin du combat ! ------------------\n");
            writer.println("\n\n---------------------------------------------------------->\n\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //affiche le bilan du combat et l'enregistre
}
