/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date Type.java
 */
package statsPokemon;

import interfaces.IType;

/**
 * @author Lacroix Baptiste and Vidal Théo
 * Classe qui permet de définir le type
 */
public enum Type implements IType {
    Plante("Plante"),
    Feu("Feu"),
    Insecte("Insecte"),
    Normal("Normal"),
    Electrik("Electrik"),
    Sol("Sol"),
    Poison("Poison"),
    Eau("Eau"),
    Combat("Combat"),
    Psy("Psy"),
    Roche("Roche"),
    Spectre("Spectre"),
    Glace("Glace"),
    Dragon("Dragon"),
    Vol("Vol");

	/**
	 * Nom du Type
	 */
    private String nom;


    /**
     * Constructeur de l'enum Type.
	 * @param nom nom du Type
     */
    Type(String nom) {
        this.nom = nom;
    }


    /**
     * Cette fonction renvoie le nom du type
     *
     * @return Le nom du type.
     */
    @Override
    public String getNom() {
        return this.nom;
    }
}
