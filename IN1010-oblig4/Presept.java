//Oblig2 Siri Sollerud (sirisoll)
//Sub-klasse av klassen Hvit. P-resept tar ingen reit inn fordi reit er alltid 3.
public class Presept extends Hvit{

  public Presept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient){
    //Sender med 3 til super fordi p-resepter skal alltid ha 3 reit.
    super(legemiddel, utskrivendeLege, pasient, 3); //3 ok her?
  }

  //P-resept har en -108 rabatt.
  @Override
  public double prisAaBetale(){
    double pPris = legemiddel.hentPris() - 108;
    if (pPris < 0)
      pPris = 0;
    return pPris;
  }
}
