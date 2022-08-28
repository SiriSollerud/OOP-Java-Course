/*
you should choose yourself how the tresure chest is filled at the start of the game
and how many items there should be in each chest
    - chest needs to be filled
    - a chest has x items in it
a treasure chest shall have methods that allow:
    - an item to be removed from it (random)
    - an item to be placed in it (sold - vary randomly from the value stored in the item)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//read the
public class TreasureChest {
    //use dictionary to store them? or just two arraylists?
    //name:value, or  just one list with names and one with values
    public List<Item> masterChest;
    public List<Item> chest = new ArrayList<>();

    public TreasureChest() {

    }

    //reads 'gjenstander.txt' and fills the chest with item objects
    //returns the filled treasure chest
    public void fillChest(File fil) throws FileNotFoundException {
        //read 'gjenstander' and fill the chest?
        Scanner scanner = null;

        try {
            scanner = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the file due to: " + e);
        }
        masterChest = new ArrayList<>();  //is this whole masterChest thing dumb as fuck?

        int itemCounter = 0; //unnecessary?
        while (scanner.hasNextLine()) {
            String[] item = scanner.nextLine().split(" ");
            String name = item[0];
            int value = Integer.parseInt(item[1]);
            Item newItem = new Item(name, value);
            itemCounter ++;
            masterChest.add(newItem);
        }
        Random r = new Random();
        int randomChestSize = r.nextInt(7);

        for (int i = 0; i <= randomChestSize; i ++) {
            Random ra = new Random();
            int randomItem = ra.nextInt(masterChest.size());
            chest.add(masterChest.get(randomItem));
        }
    }

    public Item removeItem(List<Item> chest) {
        Random r = new Random();
        int yeet = r.nextInt(chest.size());
        Item yeetedItem = chest.get(yeet);
        chest.remove(chest.get(yeet));
        return yeetedItem;
    }

    public void removeSpecificItem(Item item) {
        chest.remove(item);
    }

    /*
    When an item is added to the chest, a price (which the player receives for the
    item) is returned, and since the treasure chest is magical, that price should
    vary slightly and randomly from the value stored in the item.
     */
    public int insertItem(Item item) {
        chest.add(item);
        Random r = new Random();
        int price = r.nextInt(item.getItemValue()); // need to fix price because adds twice
        item.setItemPrice(price);
        return price;
    }

    public List<Item> getChest() {
        return chest;
    }
}
