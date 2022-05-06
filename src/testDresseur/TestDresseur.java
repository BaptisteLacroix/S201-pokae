package testDresseur;

import combat.CombatIA;
import combat.CombatJc;
import dresseur.Dresseur;
import readingFile.Data;

import javax.management.BadAttributeValueExpException;
import java.io.IOException;
import java.util.List;

public class TestDresseur {
    public static void main(String[] args) throws BadAttributeValueExpException, IOException, ClassNotFoundException {
        // Dresseur d1 = new Dresseur("Baptiste");
        // Dresseur d2 = new Dresseur("Autre");

        ////System.out.println(d1);
        System.out.println("------------------------------");

        Data d = new Data();
        // d.saveData(d1, d2);
        // d.saveData(d2);

        System.out.println("LOADING DATA !!");
        d.loadData();
        //System.out.println(d.loadData());
        //System.out.println(d.loadData().get(0));
        //System.out.println(d.loadData().get(1));

        //System.out.println(d2);
        //System.out.println("------------------------------");
        //new CombatIA(d1, d2);
    }
}
