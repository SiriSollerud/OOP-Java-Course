/*
constructor takes (at least) two parameters:
    - starting place
    - reference to an object of one of the classes that implements the UserInterface
a player has a backpack
    - object of class TreasureChest
methods:
    - newMove() conducts all operations in a move
        - should item be left in the chest(sold)?
            - if sold the player's fortune must be updated
        - if room in backpack: want to get a new item from the chest?
        - player selects and exit and goes to the next place
            - in task 2, the next place is pre-decided as each place only has 1 exit
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    String playerName;
    Place place;
    UserInterface u;
    TreasureChest backpack;
    int fortune; //player's score
    int backpackRoom = 3;


    public Player (String name, Place place, UserInterface u, TreasureChest backpack) {
        this.playerName = name;
        this.place = place;
        this.u = u;
        this.backpack = backpack;
    }
/*
    void giveStatus(String status);
    int askForCommand(String question, String[] options);
 */

    public void newMove(TreasureChest chest) {
        System.out.println(place.description);
        sellItem(chest);
        getNewItem(chest);
        selectExit();
    }

    //ask if an item should be sold or not
    public void sellItem(TreasureChest treasurechest) {
        String question1 = "A chest appears. Do you wish to leave an item from your backpack" +
                "\nin the chest to take a chance on increasing your fortune?";
        String[] options1 = {"yes", "no"};
        int sellAnswer = u.askForCommand(question1, options1);
        if (sellAnswer == 0 && backpack.getChest().size() != 0) {
            String[] options2 = new String[backpackRoom];
            String backpackChoice = ("Backpack space: " + backpack.getChest().size() + "/" + backpackRoom +
                    "\nChoose which item from your backpack you want to leave: ");
            int counter = 0;
            for (Item possession: backpack.getChest()) {
                options2[counter] = possession.getItemName() + " " + possession.getItemValue();
                counter ++;
            }
            int choiceAnswer = u.askForCommand(backpackChoice, options2);

            if (choiceAnswer <= backpackRoom && choiceAnswer >= 0) {
                String[] item = options2[choiceAnswer].split(" ");
                String itemName = item[0];
                Item itemToYeet = null; //to prevent ConcurrentModificationException: you can't remove items from a list from within a for each loop
                for (Item posession : backpack.getChest()) {
                    if (posession.getItemName().equals(itemName)) {
                        place.getPlacedChest().insertItem(posession); //inserts item into chest in the place
                        itemToYeet = posession; //removes the item placed in the chest from the backpack
                        int price = posession.price;
                        fortune += price;
                    }
                }
                backpack.removeSpecificItem(itemToYeet);
            }
        } else if (backpack.getChest().size() == 0) {
            System.out.println("Your backpack is empty, nothing to sell");
        }
    }


    //if room in backpack, want to get a new item?
    public void getNewItem(TreasureChest chest) {
        if (chest.getChest().size() != 0 ) {
            System.out.print("\nYou have come upon a chest. The chest contains:\n");
            for (Item item : chest.getChest()) {
                System.out.println(item.getItemName() + " " + item.getItemValue());
            }

            String[] options = {"yes", "no"};
            backpackRoom = 4;
            String addItem = "Do you wish to pick up a new item from the chest?";
            int addAnswer = u.askForCommand(addItem, options);
            if ((backpack.getChest() == null || backpack.getChest().size() < backpackRoom) && addAnswer == 0) {
                if (chest.getChest().size() != 0) {
                    Item addToBackpack = chest.removeItem(chest.getChest());
                    System.out.println("Item added: " + addToBackpack.getItemName() +" " + addToBackpack.getItemValue());
                    fortune += addToBackpack.getItemValue();
                    backpack.insertItem(addToBackpack);
                }
            }
        } else {
            System.out.println("You have come upon an empty chest. We'll move along.");
        }


        if (backpack.getChest().size() == backpackRoom) {
            System.out.println("Your backpack is full!");
        }
    }

    //player selects an exit and goes to the next place
    //  in task 2, the next place is pre-decided as each place only has 1 exit
    public void selectExit() {
        //ask them if i want to go forward?
        String question = "\nDo you wish move forward to another place?";
        String[] options = {"yes", "no"};
        int move = u.askForCommand(question, options);
        if (move == 0) {
            String question2 = "Choose exit: ";
            String[] options2 = new String[place.exit.size()];
            int counter = 0;
            for (Place exit : place.exit) {
                options2[counter] = exit.description;
                counter ++;
            }
            int exitAnswer = u.askForCommand(question2, options2);
            if (exitAnswer == 0) {
                place = place.exit.get(0);
            }
        }
    }
}
