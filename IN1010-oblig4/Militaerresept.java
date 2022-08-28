//Oblig 4: Yan, Stella, Zach, Siri
//Sub-klasse av klassen Hvit, tar ingen ekstra parametere.
public class Militaerresept extends Hvit {

  public Militaerresept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);
  }

  //Militaerresepter er gratis.
  @Override
  public double prisAaBetale(){
    return 0;
  }
}
