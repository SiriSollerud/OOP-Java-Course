//Oblig 5 - Siri Sollerud - sirisoll - fra oblig4
import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T> {
  protected Node start;
  protected int size;

  //Indre klasse Node som skal brukes til å lenke sammen liste objektene.
  protected class Node {
    T data;
    Node neste;
    Node forrige; //Brukes i sortertLenkeListe.

    Node(T data) {
      this.data = data;
    }
  }

  //Start peker og størrelse på lenkelista som instansvariabler.
  public Lenkeliste() {
    this.start = null;
    this.size = 0;
  }

  //Setter inn elementet på en gitt posisjon og overskriver det som var det fra før av.
  public void sett(int pos, T element) {
    //Kaster unntak hvis hvis pos er en ugyldig indeks.
    if (pos >= size || pos < 0 || start == null) {
      throw new UgyldigListeIndeks(pos);
    }
    //Ellers iterer vi igjennom lenkelisten til vi kommer til riktig element
    //som skal overskrives av det elementet vi ønsker å sette inn.
    else {
      Node current = start;
      for (int i = 0; i < pos; i++) {
        current = current.neste;
      }
      current.data = element;
    }
  }

  //Legger til et elment på slutten av listen.
  public void leggTil(T element) {
    leggTil(size, element);
  }

  //Legger inn et nytt elment i listen og skyver neste elment ett hakk lenger bak.
  //First in, First out - kø.
  public void leggTil(int pos, T element) {
    Node nyNode = new Node(element);
    //Kaster unntak hvis hvis pos er en ugyldig indeks.
    if (pos > size || pos < 0) {
      throw new UgyldigListeIndeks(pos);
    }
    //Hvis vi vil legge til et elment på starten så er det to muligheter:
    else if (pos == 0) {
      //Listen kan være tom og starten er da null.
      if (start == null) {
        start = new Node(element);
      }
      //Eller listen er ikke tom og vi må legge til nyNode før start.
      else {
        nyNode.neste = start;
        start = nyNode;
      }
      size ++;
    }
    //Ellers vil vi legge til et element et annet sted enn starten.
    else {
      Node current = start;
      for (int i=0; i<pos-1; i++) {
        current = current.neste;
      }
      nyNode.neste = current.neste;
      current.neste = nyNode;
      size ++;
    }
  }

  //Fjerner på gitt posisjon i listen.
  public T fjern(int pos) {
    Node current = start;
    //Kaster unntak hvis hvis pos er en ugyldig indeks.
    if (pos >= size || pos < 0 || start == null) {
      throw new UgyldigListeIndeks(pos);
    }
    //Fjerner og returnerer starten.
    else if (pos == 0) {
      return fjern();
    }
    //Ellers fjernes noe annet enn starten.
    else {
      for (int i=0; i<pos-1; i++) {
        current = current.neste;
      }
      Node returNode = current.neste;
      current.neste = returNode.neste;
      size--;
      return returNode.data;
    }
  }

  //Fjerner og returnerer elmentet på starten av lista.
  public T fjern() {
    if(start == null) {
      //Kaster unntak hvis hvis pos er en ugyldig indeks.
      throw new UgyldigListeIndeks(0);
    }
    //Ellers fjernes og returneres start elementet.
    else {
      Node returNode = start;
      start = start.neste;
      size--;
      return returNode.data;
    }
  }

  //Returnerer størrelsen på lenke listen.
  public int stoerrelse() {
    return size;
  }

  //Henter ut et element (uten å fjerne det fra lista) på oppgitt posisjon.
  public T hent(int pos) {
    //Kaster unntak hvis hvis pos er en ugyldig indeks.
    if (pos >= size || pos < 0 || start == null) {
      throw new UgyldigListeIndeks(pos);
    }
    //Ellers itererer vi igjennom lenkelisten til vi kommer til det ønskede elmentet vi vil hente ut.
    else {
      Node current = start;
      for (int i = 0; i < pos; i++) { //don't need pos-1?
        current = current.neste;
      }
      return current.data;
    }
  }

  //Iterator
  public Iterator<T> iterator() { //this is C3?
    return new LenkelisteIterator();
  }


  private class LenkelisteIterator implements Iterator<T> {
    Node current = start;

    public boolean hasNext() {
      if (current == null) {
        return false;
      }
      return true;
    }

    public T next() {
      if (hasNext()) {
        T data = current.data;
        current = current.neste;
        return data;
      }
      return null;
    }

    public void remove() { //optional method - not sure how to make it work
      throw new UnsupportedOperationException("Remove is not implemented");
    }
  }
}