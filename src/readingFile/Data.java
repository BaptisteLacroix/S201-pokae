package readingFile;

import dresseur.Dresseur;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data {

    public void saveData(Dresseur d) {
        try {
            FileOutputStream fileOut = new FileOutputStream("./resources/data.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(d);
            objectOut.close();
            fileOut.close();
            System.out.println("The Object was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveData(Dresseur d1, Dresseur d2) {
        ArrayList<Dresseur> listOfDresseur = new ArrayList<>();
        try {
            FileOutputStream fileOut = new FileOutputStream("./resources/data.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fileOut);
            listOfDresseur.add(d1);
            listOfDresseur.add(d2);
            oos.writeObject(listOfDresseur);
            oos.close();
            fileOut.close();
            System.out.println("The Object was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public void loadData() {
        try {
            FileInputStream fis = new FileInputStream("./resources/data.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<Dresseur> List_of_dresseur = (ArrayList<Dresseur>) ois.readObject();

            for (int i = 0; i < List_of_dresseur.size(); i++) {
                System.out.println(List_of_dresseur.get(i).nom);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
