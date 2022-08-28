/*
void giveStatus
    gives the user information about what is happpening in the game
    ex: about the place the player has arrived at

int askForCommand(String question, String[] options)
    gives the user a choice ('question') with some options ('options')
    returns an integer that gives the index of the selected option
 */
public interface UserInterface {
    void giveStatus(String status);
    int askForCommand(String question, String[] options);
}
