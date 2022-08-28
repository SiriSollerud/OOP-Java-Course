//Oblig 4: Yan, Stella, Zach, Siri
//Sub-klasse av klassen Resept, Hvit tar ingen ekstra parametere.
public class Hvit extends Resept {

  public Hvit(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);
  }

  //Returnerer fargen på resepten.
  @Override
  public String farge() {
    return "hvit";
  }

  //Returnerer hvor mye man må betale for resepten. Hvit har ingen rabatt.
  @Override
  public double prisAaBetale(){
    double pris = legemiddel.hentPris();
    return pris;
  }
}
