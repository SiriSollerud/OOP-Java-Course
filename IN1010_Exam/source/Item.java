/*
the constructor provides all the instance variables
methods to read these variables
you must decide where in the program to read the file 'gjenstander.tct' tht contains
the data about all the items
    - the file contains a name and a value for one item on each line
    - if ther eare too few lines on the file to create all the items you need in the game,
      you may reuse some names and values
 */

public class Item {
    public String name;
    public int value;
    public int price;
    //variable that says if the item is in a chest or not?
    //or if it is picked up by a human or bot?

    public Item(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getItemName() {
        return name;
    }

    public int getItemValue() {
        return value;
    }

    public void setItemPrice(int p) {
        price = p;
    }
}
