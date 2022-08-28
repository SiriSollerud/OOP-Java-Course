//Oblig 4: Yan, Stella, Zach, Siri
public class Stabel<T> extends Lenkeliste<T>{

  public void leggPaa(T element){
    super.leggTil(element);
  }
  public T taAv(){
    return super.fjern(size-1);
  }
}
