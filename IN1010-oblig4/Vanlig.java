//Oblig 4: Yan, Stella, Zach, Siri
//Sub-klasse av klassen Legemiddel, tar ingen ekstra parametere.
public class Vanlig extends Legemiddel {

  public Vanlig (String navn, double pris, double virkestoff) {
    super(navn, pris, virkestoff);
  }
  public String toString() {
    return super.toString() +"  Type: Vanlig\n";
  }
}
