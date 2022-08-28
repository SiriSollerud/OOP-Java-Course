public class Stabel<T> extends Lenkeliste<T>{

  public void leggPaa(T element) {
    super.leggTil(element); //super.?
  }
  public T taAv() {
    return super.fjern(size-1);
  }
}
