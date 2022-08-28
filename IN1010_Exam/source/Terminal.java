/*
communicates with the human user in a command-based user interface in the terminal window
using the class Scanner
 */

import java.util.Scanner;

public class Terminal implements UserInterface {

    @Override
    public void giveStatus(String status) {
        System.out.println(status);
    }

    @Override
    public int askForCommand(String question, String[] options) {
        System.out.println(question);
        int counter = 0;
        for (String option : options) {
            if (option != null) {
                System.out.println(counter + ": " + option);
                counter++;
            }
        }
        //what about invalid user input?
        /*
        int answer = 99;
        Scanner input = new Scanner(System.in);
        while (!(answer <= counter && answer > 0)) {
            if (!input.hasNextInt()) {
                System.out.println("Please type a valid number.");
                answer = input.nextInt();
            } else {
                System.out.println("Please type a valid number.");
                answer = input.nextInt();
            }
        }
         */
        Scanner input = new Scanner(System.in);
        return input.nextInt(); //returns the answer
    }
}
