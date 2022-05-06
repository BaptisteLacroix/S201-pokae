package readingFile;

import pokemon.Capacites;

import java.io.*;
import java.util.*;
import java.util.Random;
import java.util.regex.Pattern;

public class ReadingFile {

    private final List<String> listPokemon = new ArrayList<>();
    private final List<String> listCapacites = new ArrayList<>();
    private final List<String> listefficacite = new ArrayList<>();
    private List<String> listCapacitesLines = new ArrayList<>();
    private List<String> listefficaciteLines = new ArrayList<>();
    private List<String> listPokemonLines = new ArrayList<>();

    public ReadingFile() {
        this.pokemonAttributes();
        this.pokemonCapacites();
        this.pokemonEfficacite();
    }

    public List<String> getListCapacites() {
        return listCapacites;
    }

    public void pokemonAttributes() {
        try {
            Scanner fileScanner = new Scanner(new File("./resources/listePokemeon1G.csv"));
            while (fileScanner.hasNextLine()) {
                Scanner lineScanner = new Scanner(fileScanner.nextLine()).useDelimiter(";");
                while (lineScanner.hasNext()) {
                    this.tryElement(lineScanner);
                }
                lineScanner.close();
                this.listPokemon.add(String.valueOf(this.listPokemonLines));
                this.listPokemonLines = new ArrayList<>();
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void tryElement(Scanner lineScanner) {
        try {
            Scanner s = new Scanner(lineScanner.next()).useDelimiter(Pattern.compile(";"));
            while (s.hasNext()) {
                this.listPokemonLines.add(s.next());
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> choosePokemon() {
        Random rand = new Random();
        String s = this.listPokemon.get(rand.nextInt(152-4) + 4);
        s = s.replace("[", "");
        s = s.replace("]", "");
        while (!Objects.equals(s.substring(s.length() - 2), " 1")) {
            s = this.listPokemon.get(rand.nextInt(152-4) + 4);
            s = s.replace("[", "");
            s = s.replace("]", "");
        }
        String[] strSplit = s.split(", ");
        return new ArrayList<>(Arrays.asList(strSplit));
    }

    public void pokemonEfficacite() {
        try {
            Scanner fileScanner = new Scanner(new File("./resources/efficacites.csv"));
            while (fileScanner.hasNextLine()) {
                Scanner lineScanner = new Scanner(fileScanner.nextLine()).useDelimiter(";");
                while (lineScanner.hasNext()) {
                    this.tryElementEff(lineScanner);
                }
                lineScanner.close();
                this.listefficacite.add(String.valueOf(this.listefficaciteLines));
                this.listefficaciteLines = new ArrayList<>();
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void tryElementEff(Scanner lineScanner) {
        try {
            Scanner s = new Scanner(lineScanner.next()).useDelimiter(Pattern.compile(";"));
            while (s.hasNext()) {
                this.listefficaciteLines.add(s.next());
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> chooseEfficacite(int i) {
        String s = this.listefficacite.get(i);
        s = s.replace("[", "");
        s = s.replace("]", "");
        String[] strSplit = s.split(" ,");
        return new ArrayList<>(Arrays.asList(strSplit));
    }

    public void pokemonCapacites() {
        try {
            Scanner fileScanner = new Scanner(new File("./resources/listeCapacites.csv"));
            while (fileScanner.hasNextLine()) {
                Scanner lineScanner = new Scanner(fileScanner.nextLine()).useDelimiter(";");
                // System.out.println(lineScanner.next());
                while (lineScanner.hasNext()) {
                    this.tryElementCap(lineScanner);
                }
                lineScanner.close();
                this.listCapacites.add(String.valueOf(this.listCapacitesLines));
                this.listCapacitesLines = new ArrayList<>();
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // System.out.println(this.listCapacites);
    }

    public void tryElementCap(Scanner lineScanner) {
        try {
            Scanner s = new Scanner(lineScanner.next()).useDelimiter(Pattern.compile(";"));
            // System.out.println(s.next());
            while (s.hasNext()) {
                this.listCapacitesLines.add(s.next());
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Capacites chooseCapacites(int i) {
        String s = this.listCapacites.get(i);
        s = s.replace("[", "");
        s = s.replace("]", "");
        String[] strSplit = s.split(",");
        ArrayList<String> strList = new ArrayList<>(Arrays.asList(strSplit));
        return new Capacites(strList.get(0), strList.get(strList.size() - 1), strList.get(strList.size() - 2),
                Integer.parseInt(strList.get(1)), Double.parseDouble(strList.get(2)), Integer.parseInt(strList.get(3)));
    }

    public List<String> nextPokemon(int i) {
        List<String> evoSuiv = new ArrayList<>(3);
        if (i + 1 < this.listPokemon.size() - 1) {
            String s = this.listPokemon.get(i + 1);
            s = s.replace("[", "");
            s = s.replace("]", "");
            String[] strSplit = s.split(", ");
            ArrayList<String> strList = new ArrayList<>(Arrays.asList(strSplit));
            if (!strList.get(strList.size() - 1).equals("1")) {
                evoSuiv.add(strList.get(0));
                evoSuiv.add(strList.get(1));
                evoSuiv.add(strList.get(strList.size() - 1));
                return evoSuiv;
            }
            return Collections.emptyList();
        }
        return Collections.emptyList();
    }
}