//Oblig 4: Yan, Stella, Zach, Siri
public class UlovligUtskrift extends Exception{
    public UlovligUtskrift(Lege lege, Legemiddel legemiddel, int pasientID){
        super("Legen " + lege.hentLegeNavn() + "har ikke lov til å skrive ut" + legemiddel.hentNavn());
    }
}