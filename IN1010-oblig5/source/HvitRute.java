//Oblig 5 - Siri Sollerud - sirisoll

/*
Spm: Hva skjer om den gamle tråden først går videre til neste rute og så etterpå starter opp nye tråder?
Svar: Da kjører ikke trådene før hovedtråden er ferdig. Det vil si at alle tråder i andre retninger må vente på
at hovedtråden er ferdig med sin retning før de kan begynne.
*/

//Klassen 'HvitRute' er en underklasse til Rute. 'HvitRute' finner utveien
public class HvitRute extends Rute {
    private Liste<Thread> traadListe;

    public HvitRute(int rad, int kol){
        super(rad, kol);
    }

    @Override
    char tilTegn() {
        return '.';
    }

    @Override
    public void finnUtvei() {
        traadListe = new Lenkeliste<>();
        Liste<Rute> hviteRetninger = finnHviteRetninger();
        for (int i = 0; i < hviteRetninger.stoerrelse(); i++) {
            hviteRetninger.hent(i).forrigeRute = this;
            if (i == hviteRetninger.stoerrelse()-1) {
                hviteRetninger.hent(i).finnUtvei();
            } else {
                Thread traad = new Thread(hviteRetninger.hent(i));
                traad.start();
                traadListe.leggTil(traad);
            }
        }
        for (Thread traad : traadListe){
            try {
                traad.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Liste<Rute> finnHviteRetninger(){
        Liste<Rute> hviteRetninger = new Lenkeliste<>();
        if(nord instanceof HvitRute && nord != forrigeRute) {
            hviteRetninger.leggTil(nord);
        }
        if(syd instanceof HvitRute && syd != forrigeRute) {
            hviteRetninger.leggTil(syd);
        }
        if(vest instanceof HvitRute && vest != forrigeRute) {
            hviteRetninger.leggTil(vest);
        }
        if(oest instanceof HvitRute && oest != forrigeRute) {
            hviteRetninger.leggTil(oest);
        }
        return hviteRetninger;
    }
}
