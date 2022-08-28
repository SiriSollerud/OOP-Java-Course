//Oblig 4: Yan, Stella, Zach, Siri 
/*
Klasse som representerer Resepter på legemiddler som inneholder informasjon om
reseptens ID, utrskrivende lege, pasient id, og antall ganger some er igjen på
en resept(reit).
*/
abstract public class Resept {
    static int reseptIDTeller = 0;
    protected int reseptID;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;

    //Tar inn legemiddel objekt, lege objekt, pasient objekt, og reit.
    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        //Holder styr på reseptID med en enkel teller.
        reseptID = reseptIDTeller;
        reseptIDTeller ++;
    }

    //Returnerer resept ID.
    public int hentID() {
        return reseptID;
    }

    //Returnerer informasjonen til legemiddel
    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    //Returnerer navnet til den utskrevende legen.
    public Lege hentLege() {
        return utskrivendeLege;
    }

    //Returnerer reit til resepten.
    public int hentReit() {
        return reit;
    }

    //Antall ganger som er igjen på resepten(reit) minskes med 1 med hvert bruk.
    //Hvis reit er 0 minskes ikke reit lenger.
    public boolean bruk() {
        if (reit > 0) {
            reit --;
            return true;
        }
        else {
            return false;
        }
    }

    //Hver resept sub-klasse må fortelle hvilken farge resepten er.
    abstract public String farge();

    //Hver resept sub-klasse må fortelle hvord mye resepten koster.
    abstract public double prisAaBetale();

    //Skriver ut all informasjon om en Resept.
    @Override
    public String toString() {
        return "- Resept ID: " + reseptID  + "\n    Legemidel navn: " + hentLegemiddel().navn +
                "\n    Utskrivende lege: " + Resept.this.hentLege() + "\n    Pasient: " + Resept.this.pasient.name
                + " [ID: " + Resept.this.pasient.ID + "," + " fødselsnummer: " + Resept.this.pasient.birthNum + "]" +
                "\n    Reit: " + Resept.this.hentReit() + "\n    Farge: " + farge()
                + "\n    Pris: "+ Resept.this.prisAaBetale() + "\n";
    }
}