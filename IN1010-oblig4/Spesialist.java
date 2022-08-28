//Oblig 4: Yan, Stella, Zach, Siri
//Sub-klasse av Lege og implimenterer interfacet Godkjenningsfritak som
//gir spesialister en kontrollID.
public class Spesialist extends Lege implements Godkjenningsfritak{
  protected int kontrollID;

  public Spesialist(String legeNavn, int kontrollID) {
    super(legeNavn);
    this.kontrollID = kontrollID;
  }

  //Returnerer spesialistens kontroll ID. Vanlige leger har ikke en slik ID.
  @Override
  public int hentKontrollID() {
    return kontrollID;
  }

  //Skriver ut informasjonen om en spesialist.
  @Override
  public String toString() {
    return legeNavn + " (Kontroll ID: " + kontrollID + ")" ; //super.toString() + resten
  }

}
