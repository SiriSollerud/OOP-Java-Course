//Oblig 4: Yan, Stella, Zach, Siri
//Klassen Lege tar inn navnet på legen og skriver ut dette navnet med toString() metoden.
public class Lege implements Comparable<Lege> {
  protected String legeNavn;
  private Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>();

  public Lege(String legeNavn){
    this.legeNavn = legeNavn;
  }

  public String hentLegeNavn(){
    return legeNavn;
  }

  @Override
  public String toString() {
    return  legeNavn ;
  }

  public int compareTo(Lege lege) {
    return this.hentLegeNavn().compareTo(lege.hentLegeNavn());
  }

  public Lenkeliste<Resept> hentUtskrevedeResepter() {
    return this.utskrevedeResepter;
  }

  //added pasient.ID as param to UlovligUtskrift on all of the resept methods below
  public Hvit skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift{
    if(legemiddel instanceof Narkotisk && this instanceof Spesialist) {
      throw new UlovligUtskrift(this, legemiddel, pasient.ID);
    }else {
      Hvit n = new Hvit(legemiddel, this, pasient, reit);
    
      this.utskrevedeResepter.leggTil(n); 
      pasient.addResept(n);
      return n;
    }
  }
  public Militaerresept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift{
    if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
      throw new UlovligUtskrift(this, legemiddel, pasient.ID);
    } else {
      Militaerresept n = new Militaerresept(legemiddel, this, pasient, reit);
      this.utskrevedeResepter.leggTil(n);
      pasient.addResept(n);
      return n;
    }
  }

  public Presept skrivPResept(Legemiddel legemiddel, Pasient pasient)throws UlovligUtskrift{
    if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
      throw new UlovligUtskrift(this, legemiddel, pasient.ID);
    } else {
      Presept n = new Presept(legemiddel, this, pasient);
      this.utskrevedeResepter.leggTil(n);
      pasient.addResept(n);
      return n;
    }
  }

  public Blaa skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift{
    if (legemiddel instanceof Narkotisk && !(this instanceof Spesialist)) {
      throw new UlovligUtskrift(this, legemiddel, pasient.ID);
    } else {
      Blaa n = new Blaa(legemiddel, this, pasient, reit);
      this.utskrevedeResepter.leggTil(n);
      pasient.addResept(n);
      return n;
    }
  }
}
