//Oblig 4: Yan, Stella, Zach, Siri
/*
Klasse som representerer Legemidler som har en en ID, et navn, pris og virkestoff.
Abstrakt klasse fordi det skal være umulig å opprette en instans av klassen Legemiddel.
*/
abstract class Legemiddel {
  static int legemiddelIDTeller = 0;
  protected int legemiddelID;
  protected String navn;
  protected double pris;
  protected double virkestoff;

  //Tar inn navn, pris og virkestoff.
  public Legemiddel(String navn, double pris, double virkestoff) {
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
    //Passer på ID nummer ved å bruke en enkel teller.
    legemiddelID = legemiddelIDTeller;
    legemiddelIDTeller ++;
  }

  //Returnerer legemiddel ID.
  public int hentID() {
    return legemiddelID;
  }

  //Returnerer navnet til legemiddelet.
  public String hentNavn() {
    return navn;
  }

  //Returnerer prisen til legemiddelet.
  public double hentPris() {
    return pris;
  }

  //Returnerer virkestoffet til legemiddelet.
  public double hentVirkestoff() {
    return virkestoff;
  }

  //Setter en ny pris til legemiddelet.
  public void settNyPris(double nyPris) {
    pris = nyPris;
  }

  @Override
 
  public String toString() {
    return "- " + hentNavn() + "\n  Legemiddel ID: "
    + hentID() + "\n  Pris: " + hentPris() + "\n  Virkestoff: " +
    hentVirkestoff() + "\n";
  }
}
