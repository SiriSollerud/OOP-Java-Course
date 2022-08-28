//Oblig 4: Yan, Stella, Zach, Siri
//Sub-klasse av Legemiddel. Vanedannende legemiddler har i tilegg styrke.
public class Vanedannende extends Legemiddel {
  protected int styrke;

  public Vanedannende (String navn, double pris, double virkestoff, int styrke) {
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  //Returnerer styrken til det vanedannende legemiddelet.
  public int hentStyrke() {
    return styrke;
  }
  public String toString() {
    return super.toString() +"  Type: Vanedannende\n  Styrke: " + styrke + "\n";
  }
}
