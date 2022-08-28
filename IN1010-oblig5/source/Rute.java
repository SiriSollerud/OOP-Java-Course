//Oblig 5 - Siri Sollerud - sirisoll

//Klassen Rute representerer en rute i rutenettet til en Labyrint. HvitRute og Sortrute er underklasser til 'Rute'
//'Rute' brukes til å finne utveien fra labyrinten og sette riktig informasjon til hver rute
public abstract class Rute implements Runnable {
    protected int kol;
    protected int rad;
    protected Labyrint labyrint;
    protected Rute nord;
    protected Rute syd;
    protected Rute vest;
    protected Rute oest;
    protected Rute forrigeRute;

    public Rute(int rad, int kol){
        this.kol = kol;
        this.rad = rad;
    }

    //Hver rute er enten hvit (".") eller sort ("#")
    abstract char tilTegn();

    //Gjør finnUtvei abstrakt slik at jeg kan ta nytte av polymorfi
    public abstract void finnUtvei();

    //Hver rute har nå informasjon om naboene i retningne nord, syd, vest og oest + hvilken labyrint ruten hører til
    public void settRetninger(Labyrint labyrint, Rute nord, Rute syd, Rute vest, Rute oest){
        this.labyrint = labyrint;
        this.nord = nord;
        this.syd = syd;
        this.vest = vest;
        this.oest = oest;
    }

    @Override
    public void run() {
        finnUtvei();
    }
}
