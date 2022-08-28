//Oblig 4: Yan, Stella, Zach, Siri
public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{
  private Node siste;

  public SortertLenkeliste() {
    super();
    this.siste = null;
  }

  //Bruker mye @Override i denne klassen siden jeg endrer metoder fra Lenkeliste
  //Bruker kan ikke sette inn eller legge til et elment på en spesefikk plass lenger fordi
  //dette skal være en sortert liste (som sorteres automatisk).
  @Override
  public void sett(int pos, T element) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void leggTil(int pos, T element) {
    throw new UnsupportedOperationException();
  }

  /*
  Fire ting kan skje i legg til:
  1. Listen var tom, så vi legger først. Start må endres og siste må endres.
  2. Vi legger til bakerst i listen. Siste må endres og forrige/neste-pekere.
  3. Vi legger til forerst i listen. Start må endres og forrige/neste-pekere.
  4. Vi legger til foran current. Forrige/neste-pekere må endres.
   */
  @Override
  public void leggTil(T element) {
    Node current = start;
    Node nyNode = new Node(element);
    //Hvis listen er tom.
    if (start == null) {
      start = nyNode;
      siste = nyNode;
    }
    //Ellers, hvis listen ikke er tom.
    else {
      Node forrige = start;
      while (current != null && (current.data.compareTo(nyNode.data) <= 0)) {
        forrige = current;
        current = current.neste;
      }
      /*Disse sjekkene skjer når while løkken ikke er sann lenger - altså, elementet
      til current ikke er null eller det er større enn det vi vil sette inn.
      */
      //Hvis current er null betyr det at vi må sette inn elementet bakerst i listen.
      if (current == null) {
        forrige.neste = nyNode;
        nyNode.forrige = forrige;
        siste = nyNode;
      }
      //Hvis current er lik start betyr det at vi legger til først i listen.
      else if (current == start) {
        nyNode.neste = current;
        current.forrige = nyNode;
        start = nyNode;
      }
      //Ellers, så legger vi til foran current.
      else {
        //Ønsker å legge en ny node imellom forrige og current
        current.forrige.neste = nyNode;
        //Passe på at current fortsatt er i lenka - lenker den til nyNode.
        nyNode.neste = current;
        nyNode.forrige = current.forrige;
        current.forrige = nyNode;
      }
    }
    size++;
  }

  //Fjerner det største(siste) elementet i listen.
  @Override
  public T fjern() {
    Node returNode = siste;
    //Hvis lista er tom kastes unntak.
    if (siste == null) {
      throw new UgyldigListeIndeks(0);
    }
    siste = siste.forrige;
    //hvis sistes forrige ekisterer så slettes sistes peker (siste.neste).
    if (siste != null) {
      siste.neste = null;
    }
    //Ellers kan vi da redusere size og returnere den riktige returNoden.
    size--;
    return returNode.data;
  }
}
