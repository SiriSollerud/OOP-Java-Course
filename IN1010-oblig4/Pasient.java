//Oblig 4: Yan, Stella, Zach, Siri
public class Pasient {
    protected String name;
    protected String birthNum;
    protected int ID;
    static int IDCounter = 0;
    protected Stabel<Resept> reseptStabel;

    Pasient(String name, String birthNum) {
        this.name = name;
        this.birthNum = birthNum;
         ID = IDCounter ++;
         reseptStabel = new Stabel<Resept>();
    }
    
    public int hentID() {
        return ID;
    }

    public void addResept(Resept newResept){
        reseptStabel.leggPaa(newResept);
    }

    public void getStabel(){
      //  for(int i = 0; i < reseptStabel.size-1; i ++){
    	for (Resept i:reseptStabel) {
    		System.out.println(i);
        }
    }
    
    public String toString() {
    	return "- "+ name + " [ID: " + ID + "," + " fødselsnummer: " + birthNum + "]";
    }
}