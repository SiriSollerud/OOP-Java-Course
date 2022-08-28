/*Oblig 5 - Siri Sollerud - sirisoll

Et kordinat er i formatet 'rad, kolonne' gjennom hele obligen min. Jeg glemte at det skulle være 'kolonne, rad' isteden.
Håper dette er greit.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Klassen 'Labyrint' kan tegne en labyrint fra fil, lese inn hele rutenettet til en labyrint fra fil, sette naboer
//til alle rutene i rutenettet, og finne utveien fra labyrinten gitt et kordinat.
public class Labyrint {
    private Rute[][] ruter;
    public Liste<String> losninger;
    int antKol;
    int antRad;

    private Labyrint (int antRad, int antKol, Rute[][] ruter) {
        this.ruter = ruter;
        this.antKol = antKol;
        this.antRad = antRad;
    }

    //Tegner labyrinten slik den er vist i filen
    @Override
    public String toString() {
        StringBuilder skrivLabyrint = new StringBuilder();
        for(int rad = 0; rad < antRad; rad++) {
            for(int kol = 0; kol < antKol; kol++) {
                skrivLabyrint.append(ruter[rad][kol].tilTegn());
            }
            skrivLabyrint.append("\n");
        }
        return skrivLabyrint.toString();
    }

    //Lager en labyrint basert på oppgitt fil
    public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        Scanner scanner = new Scanner(fil);
        String[] foersteLinje;
        if (scanner.hasNextLine()){
            foersteLinje = scanner.nextLine().split(" ");
        } else{
            return null;
        }
        int antRad = 0;
        int antKol = 0;

        //Sjekker om første linje er i riktig format (må være integer)
        try {
            antRad = Integer.parseInt(foersteLinje[0]);
            antKol = Integer.parseInt(foersteLinje[1]);
        } catch (NumberFormatException e){
            System.out.println("Feil filformat for rad og kolonne (første linje).");
            System.exit(-1);
        }

        //Rutenettet 'ruter' av hele labyrinten er en double array bestående av rader og kolonner
        Rute[][] ruter = new Rute[antRad][antKol];

        int radTeller = 0;
        int kolTeller = 0;
        //Skriver ut antall rader og kolonner
        System.out.println("Rader: " + antRad + "\nKolonner: " + antKol);
        while (scanner.hasNextLine()) {
            //Går gjennom linje for linje
            for(char tegn : scanner.nextLine().toCharArray()) {
                Rute nyRute;
                if (tegn == '#') {
                    nyRute = new SortRute(radTeller, kolTeller);
                } else if (tegn == '.') {
                    //Sjekker om det er en åpning
                    if (radTeller == 0 || radTeller == antRad - 1 || kolTeller == 0 || kolTeller == antKol - 1){
                        nyRute = new Aapning(radTeller, kolTeller);
                    } else {
                        nyRute = new HvitRute(radTeller, kolTeller);
                    }
                } else {
                    return null;
                }
                //Lager en ny rute på gitt kordinat
                ruter[radTeller][kolTeller] = nyRute;
                kolTeller++;
            }
            radTeller++;
            //Når en hel rad er fylt, settes kolonne telleren tilbake til 0
            kolTeller = 0;
        }
        //Lager en ny labyrint basert på antall rader og kolonner fra første linje i filen + rutenettet
        Labyrint labyrint = new Labyrint(antRad, antKol, ruter);
        //Setter naboene (retninger) til alle rutene
        labyrint.settRetningerForAlleRuter();
        //Skriver ut rutenettet til labyrinten slikt den er vist i filen
        System.out.println(labyrint);
        return labyrint;
    }

    //Setter retningen (nord, syd, vest og oest) for alle rutene i rutenettet 'ruter'
    public void settRetningerForAlleRuter() {
            for (int rad = 0; rad < antRad; rad++) {
                for (int kol = 0; kol < antKol; kol++) {
                    Rute nord = null;
                    Rute syd = null;
                    Rute vest = null;
                    Rute oest = null;
                    if (rad != 0) {
                        nord = ruter[rad-1][kol];
                    }
                    if (rad != antRad - 1) {
                        syd = ruter[rad+1][kol];
                    }
                    if (kol != 0) {
                        vest = ruter[rad][kol-1];
                    }
                    if (kol != antKol - 1) {
                        oest = ruter[rad][kol+1];
                    }
                    //Sender med rettningene til hver enkelt rute til Rute klassen slik at denne informasjonen kan bli
                    //aksessert i underklassene til Rute
                    ruter[rad][kol].settRetninger(this, nord, syd, vest, oest);
                }
            }
        }

    //Finner utveien fra kordinatet brukern oppga og returnerer utveiene (hvis det finnes noen da)
    //i formatet "--> (rad, kolonne)"
    public Liste<String> finnUtveiFra(int startRad, int startKol) {
        losninger = new Lenkeliste<>();
        ruter[startRad][startKol].forrigeRute = null;
        ruter[startRad][startKol].finnUtvei();
        return losninger;
    }

    public synchronized void leggTilLosning(String losning){
        losninger.leggTil(losning);
    }
}
