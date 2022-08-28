//Oblig 4: Yan, Stella, Zach, Siri
//Sub-klasse av klassen Resept, tar ingen ekstra parametere.
public class Blaa extends Resept {

  public Blaa(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);
  }

  //Returnerer fargen på resepten.
  @Override
  public String farge() {
    return "blaa";
  }

  //Pasienter med en blå resept må bare betale 25% av orignal prisen. 
  @Override
  public double prisAaBetale(){
    double rabatt = legemiddel.hentPris() * 0.25;
    return rabatt;
  }
}
