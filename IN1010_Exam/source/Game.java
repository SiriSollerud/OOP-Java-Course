import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
in task 2 there's only one player
main method
    - creates the terrain
    - creates the player
    - goes into a loop executing moves until the game is finished
        - finish: name of the player + fortune is displayed
can divide this into two classes:
    - GameControl
        - using a main method that sets all parameters and determines the type of game
          when expanding the possibilities in later tasks
    - Game

 */
public class Game {
    private static int gameTurns = 3;
    private static String placeFileName = "steder.txt";

    public static void main(String[] args) throws FileNotFoundException {
        //creates the terrain
        File file = new File(placeFileName);
        Terrain terra = new Terrain(file);

        //creates the human user
        Terminal user = new Terminal();
        Place start = terra.getStart();
        TreasureChest chest = start.getPlacedChest();
        TreasureChest userBackpack = new TreasureChest();

        //asks user for username
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter preferred username: ");
        String userName = scan.nextLine();
        Player playa = new Player(userName, start, user, userBackpack);

        //creates the machine user
        Robot machine = new Robot();
        TreasureChest roboBackpack = new TreasureChest();
        String roboName = "Bot"; //robot is not asked for name
        Player robo = new Player(roboName, start, machine, roboBackpack);
        int counter = 1;

        for (int i = 1; i <= gameTurns; i ++) {
            System.out.println("\n--*--*--*--*--*--*--*--" + "\n" +
                    userName + "'s turn: " + counter + "/" + gameTurns + "\n" +
                    userName + "'s fortune: " + playa.fortune +
                    "\n--*--*--*--*--*--*--*--");
            playa.newMove(playa.place.chest);
            System.out.println("--*--*--*--*--*--*--*--" + "\n" +
                    roboName + "'s turn: " + counter + "/" + gameTurns + "\n" +
                    roboName + "'s fortune: " + robo.fortune +
                    "\n--*--*--*--*--*--*--*--");
            robo.newMove(robo.place.chest);
            counter ++;
        }

        //In the end the final fortunes are printed and the winner is selected
        System.out.println("\n--*--*Final fortunes*--*--\n" + userName + ": " + playa.fortune + "\n"
                + roboName + ": " + robo.fortune + "\n--*--*--*--*--*--*--*--*--");
        if (playa.fortune > robo.fortune) {
            System.out.println("Congratulations you beat the machine!");
        } else {
            System.out.println("The machine beat you :(");
        }
    }
}
