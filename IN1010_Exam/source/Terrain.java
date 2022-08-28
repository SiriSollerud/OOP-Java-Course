/*
each place has only one exit
the constructor of the Terrain class reads 'steder.txt' and builds up a terrain with:
    - place objects linked together
        - contains a treasure chest of items
methods
    - getStart() that returns a references to the place that is the start of the game
    - can make a cyclic path through your places, and return a randomly selected starting place
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Terrain {
    public List<Place> terrain;

    //constructor reads 'steder.txt' and builds up a terrain with:
    //  - place objects linked together
    //      - contains a treasure chest of items
    public Terrain(File fil) throws FileNotFoundException{
        //read 'steder' and fill the terrain with places?
        Scanner scanner = null;
        try {
            scanner = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the file due to: " + e);
        }

        terrain = new ArrayList<>();
        int placeCounter = 0;
        Place newPlace = null;
        while (scanner.hasNextLine()) {
            String placeDescription = scanner.nextLine();
            newPlace = new Place(placeDescription);
            newPlace.addChest();

            terrain.add(newPlace);
            placeCounter++;
        }
        //next place (aka. the exit) is random to increase variety in the game
        Random r = new Random();
        for (Place place: terrain) {
            int ra = r.nextInt(terrain.size());
            Place exit = terrain.get(ra);
            while (exit == place) { //can't have the exit be the same place
                ra = r.nextInt(terrain.size());
                exit = terrain.get(ra);
            }
            place.makeExit(exit);
        }
    }

    // getStart() that returns a references to the place that is the start of the game
    public Place getStart() {
        return terrain.get(0);
    }


}
