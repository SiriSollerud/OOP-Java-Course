//Oblig 5 - Siri Sollerud - sirisoll
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Hovedprogram {
    public static void main(String[] args) {
        String filnavn = null;

        if (args.length > 0) {
            filnavn = args[0];
        } else {
            System.out.println("FEIL! Riktig bruk av programmet: java Hovedprogram <labyrintfil> <snarvei>"
                    + "\nProgrammet kan  også kjøres uten <snarvei>, men da vises ikke de korteste utveiene.");
            return;
        }
        boolean snarvei = false;
        if (args.length > 1 && args[1].equals("snarvei")) {
            snarvei = true;
        }
        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }

        // les start-koordinater fra standard input
        Scanner inn = new Scanner(System.in);
        System.out.println("Koordinatene i labyrinten er null-indeksert, så første koordinat er 0 0." +
                "\nSkriv inn koordinater <rad> <kolonne> (trykk 'a' for aa avslutte): ");
        String[] ord = inn.nextLine().split(" ");
        while (!ord[0].equals("a")) {
            try {
                int startKol = Integer.parseInt(ord[0]);
                int startRad = Integer.parseInt(ord[1]);
                //Finner utvei(er) basert på kordinatet brukeren oppga
                Liste<String> utveier = l.finnUtveiFra(startKol, startRad);

                //Skriver ut antall utveier hvis det faktisk er noen utveier
                if (utveier.stoerrelse() != 0) {
                    if (snarvei) {
                        System.out.println("Antall utveier: " + utveier.stoerrelse());
                    }
                    //Lager en Lenkeliste av de korteste utveiene/den korteste utveien
                    Liste<String> kortesteUtveier = new Lenkeliste<>();
                    for (String s : utveier) {
                        System.out.println(s);
                        if (kortesteUtveier.stoerrelse() == 0 || s.split(" --> ").length < kortesteUtveier.hent(0).split(" --> ").length) {
                            kortesteUtveier = new Lenkeliste<>();
                            kortesteUtveier.leggTil(s);
                        }
                    }
                    //Hvis brukeren ønsker å se korteste utvei(er) må brukeren skrive argumentet 'snarvei' når programmet kjøres
                    if (snarvei) {
                        System.out.println("\nKorteste utvei(er):");
                        for (String vei : kortesteUtveier){
                            System.out.println(vei);
                        }
                    }
                } else {
                    System.out.println("Ingen utveier.");
                }
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input!");
            }
            //Programmet fortsettet til brukeren avlsutter ved å skrive inn 'a'
            System.out.println("Skriv inn nye koordinater ('a' for aa avslutte): ");
            ord = inn.nextLine().split(" ");
        }
    }
}