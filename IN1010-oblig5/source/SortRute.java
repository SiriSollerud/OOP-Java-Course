//Oblig 5 - Siri Sollerud - sirisoll
public class SortRute extends Rute {

    public SortRute(int rad, int kol){
        super(rad, kol);
    }

    @Override
    char tilTegn() {
        return '#';
    }

    //En sortrute er en vegg og det er umulig å gå her. Derfor returneres utveier med en gang uten å legge til noe ekstra
    @Override
    public void finnUtvei() {
    }
}
