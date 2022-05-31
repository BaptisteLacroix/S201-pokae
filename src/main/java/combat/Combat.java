package combat;

import interfaces.*;

public class Combat implements ICombat {
    private final IDresseur dresseur1;
    private IPokemon pokemon1;
    private final IDresseur dresseur2;
    private IPokemon pokemon2;

    public Combat(IDresseur dresseur1, IPokemon pokemon1, IDresseur dresseur2, IPokemon pokemon2) {
        this.dresseur1 = dresseur1;
        this.dresseur2 = dresseur2;
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
    }

    @Override
    public void commence() {
        // Choix action Si echange ne fait rien si attaque check vitesse
        IAttaque attaque1 = this.dresseur1.choisitAttaque(this.pokemon1, this.pokemon2);
        // Choix action si echange ne fais rien si attaque check vitesse
        IAttaque attaque2 = this.dresseur2.choisitAttaque(this.pokemon2, this.pokemon1);
        // Si vitesses P1 > P2 alors P1 commence sinon P2
        if (this.pokemon1.getStat().getVitesse() > this.pokemon2.getStat().getVitesse()) {
            this.pokemon2.getStat().setPV(this.pokemon2.getStat().getPV() - attaque1.calculeDommage(this.pokemon1, this.pokemon2));
            this.pokemon1.getStat().setPV(this.pokemon1.getStat().getPV() - attaque2.calculeDommage(this.pokemon2, this.pokemon1));
        } else {
            this.pokemon1.getStat().setPV(this.pokemon1.getStat().getPV() - attaque1.calculeDommage(this.pokemon2, this.pokemon1));
            this.pokemon2.getStat().setPV(this.pokemon2.getStat().getPV() - attaque2.calculeDommage(this.pokemon1, this.pokemon2));
        }
        // TODO : Si Changement faire ne pas attaquer le pokémon qui change
    } // lance le combat

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
        return null;
    } //Crée un tour du combat

    @Override
    public void termine() {
    } //affiche le bilan du combat et l'enregistre
}
