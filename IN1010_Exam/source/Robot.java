/*
simulates a robot, returning a random choice among the options

you *may* print status and the choice if you wish, to make it more fun to later play
against robots (this does not give extra points)
 */

import java.util.Random;
import java.util.Scanner;

public class Robot implements UserInterface {
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
                counter++; //so that the robot can only answer a correct number
            }

        }
        Random n = new Random();
        int answer = n.nextInt(counter); //will this give 0 and 1?
        System.out.println("Robot answered: " + options[answer]);
        return answer; //returns random robot answer
    }
}
