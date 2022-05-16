/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IPokemon.java
 */
package interfaces.java.main.pokemon;

import interfaces.java.main.combat.IAttaque;
import interfaces.java.main.statsPokemon.IStat;
import interfaces.java.main.statsPokemon.ICapacite;

/**
 * @author Leo Donati
 *
 */
public class IPokemon {
	public IStat getStat();
	public double getExperience();
	public int getNiveau();
	public int getId();
	public String getNom();
	public double getPourcentagePV();
	
	public IEspece getEspece();
	public void vaMuterEn(IEspece esp);
	
	public ICapacite[] getCapacitesApprises();
	public void apprendCapacites(ICapacite[] caps);
	public void remplaceCapacite(int i, ICapacite cap) throws Exception;
	
	public void gagneExperienceDe(IPokemon pok);
	public void subitAttaqueDe(IPokemon pok, IAttaque atk);
	
	public boolean estEvanoui();
	public boolean aChangeNiveau();
	public boolean peutMuter();
	
	public void soigne();
}