/*
constructor should take a String parameter containing the description of the place
    - as in the file 'steder.txt'
methods:
    - place a treasure chest with items in the place
    - returns a reference to the treasure chest in the place
        - so that the player can use the treasure chest
    - move forward - which returns a reference to the next place
        - objects of the class Place have only one exit in task 2
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Place {
    public String description;
    public TreasureChest chest;
    public List<Place> exit = new ArrayList<>();
    private static String fileName = "gjenstander.txt";
    //previous place as instance variable?

    public Place (String placeDescription){
        this.description = placeDescription;
    }

    //place a treasure chest with items in the place
    public void addChest() throws FileNotFoundException {
        File file = new File(fileName);
        TreasureChest placeChest = new TreasureChest();
        placeChest.fillChest(file); //not sure why this happens?
        chest = placeChest;
    }

    //return a reference to the treasure chest in the place
    //  - so that the player can use the treasure chest
    public TreasureChest getPlacedChest() {
        return chest;
    }
    //move forward --> which returns a reference to the next place
    //  - objects of the class Place have only one exit in task 2
    public Place moveForward(Place thisPlace) {
        exit.add(thisPlace);

        //don't know how :( need some next kind of iterator?
        //maybe i don't... just use list.next?
        return null;
    }
    public void makeExit(Place thisPlace) {
        exit.add(thisPlace);
    }
}
