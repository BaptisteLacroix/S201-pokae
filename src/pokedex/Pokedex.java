package pokedex;

import pokemon.Capacites;
import pokemon.Espece;
import pokemon.Pokemon;
import readingFile.ReadingFile;

import java.io.Serializable;
import java.util.*;

public class Pokedex implements Serializable {

    private Map<Pokemon, Espece> info = new HashMap<>();
    private List<Capacites> capacites = new ArrayList<>();

    public Pokemon choosePokemon() {
        ReadingFile p = new ReadingFile();
        List<String> ListPokemonStats = p.choosePokemon();

        System.out.println(ListPokemonStats);
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter a name for your pokemon : ");
        String pokemonName = input.nextLine();  // Read user input

        this.choose4Capacite(p);

        Pokemon pokemon = new Pokemon(pokemonName, Integer.parseInt(ListPokemonStats.get(ListPokemonStats.size() - 1)), this.capacites,
                Integer.parseInt(ListPokemonStats.get(2)), Integer.parseInt(ListPokemonStats.get(3)),
                Integer.parseInt(ListPokemonStats.get(4)), Integer.parseInt(ListPokemonStats.get(5)), Integer.parseInt(ListPokemonStats.get(6)));

        if (ListPokemonStats.get(ListPokemonStats.size() - 2).equals("")) {
            this.info.put(pokemon, new Espece(Integer.parseInt(ListPokemonStats.get(0)), ListPokemonStats.get(1), ListPokemonStats.get(ListPokemonStats.size() - 2),
                    Integer.parseInt(ListPokemonStats.get(2)), Integer.parseInt(ListPokemonStats.get(3)),
                    Integer.parseInt(ListPokemonStats.get(4)), Integer.parseInt(ListPokemonStats.get(5)),
                    Integer.parseInt(ListPokemonStats.get(6)), Integer.parseInt(ListPokemonStats.get(ListPokemonStats.size() - 1))));
        } else {
            this.info.put(pokemon, new Espece(Integer.parseInt(ListPokemonStats.get(0)), ListPokemonStats.get(1), ListPokemonStats.get(ListPokemonStats.size() - 2),
                    ListPokemonStats.get(ListPokemonStats.size() - 3),
                    Integer.parseInt(ListPokemonStats.get(2)), Integer.parseInt(ListPokemonStats.get(3)),
                    Integer.parseInt(ListPokemonStats.get(4)), Integer.parseInt(ListPokemonStats.get(5)),
                    Integer.parseInt(ListPokemonStats.get(6)), Integer.parseInt(ListPokemonStats.get(ListPokemonStats.size() - 1))));
        }
        return pokemon;

    }

    private void choose4Capacite(ReadingFile p) {
        for (int i = 0; i < 4; i++) {
            Scanner input = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter the number of the capacity you want : ");
            String numCap = input.nextLine();  // Read user input
            this.capacites.add(p.chooseCapacites(Integer.parseInt(numCap)));
        }
    }

    public Map<Pokemon, Espece> getInfo() {
        return info;
    }

    public void setInfo(Map<Pokemon, Espece> info) {
        this.info = info;
    }

    public List<Capacites> getCapacites() {
        return capacites;
    }

    public void setCapacites(List<Capacites> capacites) {
        this.capacites = capacites;
    }

    @Override
    public String toString() {
        return "Pokedex{" +
                "info=" + info +
                ", capacites=" + capacites +
                '}';
    }
}