//Oblig 4: Yan, Stella, Zach, Siri
//Sub-klasse av Legemiddel. Narkotiske legemiddler har i tilegg styrke.
public class Narkotisk extends Legemiddel {
  protected int styrke;

  public Narkotisk (String navn, double pris, double virkestoff, int styrke) {
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  //Returnerer styrken til det narkotiske legemiddelet.
   public int hentStyrke() {
     return styrke;
   }

    public String toString() {
        return super.toString() +"  Type: Narkotisk\n  Styrke: " + styrke + "\n";
    }
}