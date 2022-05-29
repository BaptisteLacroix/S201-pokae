package combat;

import interfaces.*;

public class Combat implements ICombat {
    private IDresseur dresseur1;
    private IDresseur dresseur2;

    public Combat(IDresseur dresseur1, IDresseur dresseur2) {
        this.dresseur1 = dresseur1;
        this.dresseur2 = dresseur2;
    }

    @Override
    public void commence() {

    }

    @Override
    public IDresseur getDresseur1() {
        return dresseur1;
    }

    @Override
    public IDresseur getDresseur2() {
        return dresseur2;
    }

    @Override
    public ITour nouveauTour(IPokemon pok1, IAttaque atk1, IPokemon pok2, IAttaque atk2) {
        return null;
    }

    @Override
    public void termine() {

    }
}
