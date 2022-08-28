//Oblig3 Siri Sollerud (sirisoll)
//Unntaklskasse som kastes dersom vi forsøker å nå en ugyldig indeks.
class UgyldigListeIndeks extends RuntimeException {
    UgyldigListeIndeks(int indeks) {
        super("Ugyldig indeks:" + indeks);
    }
}
