//Oblig 5 - Siri Sollerud - sirisoll
public class Aapning extends HvitRute {

    public Aapning(int rad, int kol) {
        super(rad, kol);
    }

    @Override
    public void finnUtvei() {
        Rute losningsRute = forrigeRute;
        StringBuilder losning = new StringBuilder("(" + rad + ", " + kol + ")");
        while(losningsRute != null){
            losning.insert(0, "(" + losningsRute.rad + ", " + losningsRute.kol + ")-->" );
            losningsRute = losningsRute.forrigeRute;
        }
        labyrint.leggTilLosning(losning.toString());
    }
}