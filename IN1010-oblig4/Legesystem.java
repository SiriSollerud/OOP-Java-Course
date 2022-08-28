//Oblig 4: Yan, Stella, Zach, Siri

/*
Det eneste vi har problemer med i denne koden er at vi tror vi ikke teller antall resepter riktig. Vi skjønte ikke
helt hva obligen var ute etter, så vi prøvde å implimentere koden slik vi forstod obligen.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Legesystem {
	// Opprett lister som lagrer objektene i legesystemet
	static Liste<Lege> legeliste = new SortertLenkeliste<Lege>();
	static Liste<Pasient> pasientliste = new Lenkeliste<Pasient>();
	static Liste<Legemiddel> legemiddelliste = new Lenkeliste<Legemiddel>();
	static Liste<Resept> reseptliste = new Lenkeliste<Resept>();
	static Liste<Lege> spesialisterliste = new SortertLenkeliste<Lege>();
	static Liste<Resept> misbruk =new Lenkeliste<Resept>();
	//static Liste<String> ugyldigResepter =new Lenkeliste<String>();

	public static void main(String[] args) throws NumberFormatException, UlovligUtskrift {
		File a = new File("storFil.txt");
		lesFraFil(a);
		System.out.println("Lege: " + legeliste.stoerrelse());
		System.out.println("Pasient: " + pasientliste.stoerrelse());
		System.out.println("Legemiddel: " + legemiddelliste.stoerrelse());
		System.out.println("Resept: " + reseptliste.stoerrelse());
		System.out.println("Spesialist:" + spesialisterliste.stoerrelse());
		menu();
	}

	@SuppressWarnings("resource")
	// E1
	private static void lesFraFil(File fil) throws NumberFormatException, UlovligUtskrift {
		Scanner scanner = null;
		try {
			scanner = new Scanner(fil);
			System.out.println("Reading File " + fil);
		} catch (FileNotFoundException e) {
			// System.out.println(System.getProperty("user.dir"));
			System.out.println("Fant ikke filen, starter opp som et tomt Legesystem");
			return;
		}
		// System.out.println("found the file");
		String innlest = scanner.nextLine();
		while (scanner.hasNextLine()) {
			String[] info = innlest.split(" ");
			// Legger til alle pasientene i filen
			if (info[1].compareTo("Pasienter") == 0) {
				while (scanner.hasNextLine()) {
					innlest = scanner.nextLine();
					if (innlest.charAt(0) == '#') {
						break;
					}
					String[] a = innlest.split(",");
					Pasient w = new Pasient(a[0], a[1]);
					pasientliste.leggTil(w);
				}
			}
			// Legger inn Legemidlene
			else if (info[1].compareTo("Legemidler") == 0) {
				while (scanner.hasNextLine()) {
					innlest = scanner.nextLine();
					// Om vi er ferdig med å legge til legemidler, bryt whileløkken,
					// slik at vi fortsetter til koden for å legge til leger
					if (innlest.charAt(0) == '#') {
						break;
					}
					String[] legemiddel = innlest.split(",");
					for (int i = 1; i <= legemiddel.length - 2; i++) {
						if (legemiddel[i].compareTo("narkotisk") == 0 || legemiddel[i].compareTo("a") == 0) {
							double c1 = Double.parseDouble(legemiddel[i + 1]);
							double c2 = Double.parseDouble(legemiddel[i + 2]);
							int c3 = Integer.parseInt(legemiddel[i + 3]);
							String w = "";
							for (int s = 0; s < i; s++) {
								if (legemiddel[s] != null) {
									w += legemiddel[s];
									// System.out.println(w);
								}
							}
							Narkotisk n = new Narkotisk(w, c1, c2, c3);
							legemiddelliste.leggTil(n);

						} else if (legemiddel[i].compareTo("vanedannende") == 0 || legemiddel[i].compareTo("b") == 0) {
							double c1 = Double.parseDouble(legemiddel[i + 1]);
							double c2 = Double.parseDouble(legemiddel[i + 2]);
							int c3 = Integer.parseInt(legemiddel[i + 3]);
							String w = "";
							for (int s = 0; s < i; s++) {
								if (legemiddel[s] != null) {
									w += legemiddel[s];
								}
							}
							Vanedannende n = new Vanedannende(w, c1, c2, c3);
							legemiddelliste.leggTil(n);
						} else if (legemiddel[i].compareTo("vanlig") == 0 || legemiddel[i].compareTo("c") == 0) {
							double c1 = Double.parseDouble(legemiddel[i + 1]);
							double c2 = Double.parseDouble(legemiddel[i + 2]);
							String w = "";
							for (int s = 0; s < i; s++) {
								if (legemiddel[s] != null) {
									w += legemiddel[s];
								}
							}
							Vanlig n = new Vanlig(w, c1, c2);
							legemiddelliste.leggTil(n);
						}
					}
				}
			}
			// Legger inn leger
			else if (info[1].compareTo("Leger") == 0) {
				while (scanner.hasNextLine()) {
					innlest = scanner.nextLine();
					// Om vi er ferdig med å legge til leger, bryt whileløkken,
					// slik at vi fortsetter til koden for å legge til resepter
					if (innlest.charAt(0) == '#') {
						break;
					}
					info = innlest.split(",");
					int kontrollid = Integer.parseInt(info[1]);
					if (kontrollid == 0) {
						Lege a = new Lege(info[0]);
						legeliste.leggTil(a);
					} else {
						Lege a = new Spesialist(info[0], Integer.parseInt(info[1]));
						spesialisterliste.leggTil(a);
						legeliste.leggTil((Lege) a);
						// System.out.println("spesialister in legeliste!!!!!");
					}
				}

			}

			// Legger inn Resepter
			else if (info[1].compareTo("Resepter") == 0) {
				/*
				 * for(Lege i:legeliste) { System.out.println(i); }
				 */
				// int c = 1;
				int count = 0;
				Lenkeliste<String[]> bug = new Lenkeliste<String[]>();
				// while loop while it has next line
				while (scanner.hasNextLine()) {
					innlest = scanner.nextLine();
					// System.out.println("Read line:"+ c);
					// c++;
					info = innlest.split(",");
					// l1 is the Id of the legemiddel
					try {
						int l1 = Integer.parseInt(info[0]);
						Legemiddel l = null;
						for (Legemiddel i : legemiddelliste) {
							if (i.hentID() == l1) {
								l = i;
								// System.out.println("found a legemiddel");
								break;
							}
						}
						// n1 is the doctor's name, index could be out of range here if the data is
						// incomplete.therefore should add try catch in this whole section
						String n1 = info[1];
						// System.out.println(n1);
						Lege n = null;
						for (Lege i2 : legeliste) {
							if (i2.hentLegeNavn().equals(n1)) {
								n = i2;
								// System.out.println("found a lege");
								break;
							}
						}
						// p1 is the patient ID
						int p1 = Integer.parseInt(info[2]);
						// System.out.println(p1);
						Pasient p = null;
						for (Pasient i1 : pasientliste) {
							// System.out.println(i1.ID);
							if (i1.ID == p1) {
								p = i1;
								// System.out.println("found a pasient");
								break;
							}
						}
						if (info[3].compareTo("hvit") == 0 || info[3].compareTo("militaer") == 0
								|| info[3].compareTo("blaa") == 0) {
							int test = Integer.parseInt(info[4]);
						}
						switch (info[3]) {
							case "hvit":
								try {
									// System.out.println(l+"\n"+p+"\n"+info[4]);
									Hvit pr = n.skrivHvitResept(l, p, Integer.parseInt(info[4]));
									reseptliste.leggTil(pr);

									break;
								} catch (Exception e) {
									count++;
									// System.out.println("hvit" + count);
									break;
								}
							case "blaa":
								try {

									Blaa pr1 = n.skrivBlaaResept(l, p, Integer.parseInt(info[4]));
									reseptliste.leggTil(pr1);
									break;
								} catch (Exception e) {
									count++;
									// System.out.println("bloaa" + count);
									break;
								}
							case "p":
								// System.out.println("yes p");
								try {

									Presept pr2 = n.skrivPResept(l, p);
									reseptliste.leggTil(pr2);
									break;
								} catch (Exception e) {
									count++;
									// System.out.println(" p" + count);
									break;
								}
							case "militaer":
								// System.out.println("yes mm");
								try {
									Militaerresept pr3 = n.skrivMilitaerResept(l, p, Integer.parseInt(info[4]));
									reseptliste.leggTil(pr3);
									break;
								} catch (Exception e) {
									count++;
									// System.out.println("mili" + count);
									break;
								}
							default:
								bug.leggTil(info);
						}
					} catch (Exception e) {
						bug.leggTil(info);
					}
				}
				System.out.println(
						"Invalid prescriptions[Doctors Issuing Narcotic Drugs Without License] in total: " + count);
				if (bug.stoerrelse() != 0) {
					System.out.println("Incomplete prescription Count:" + bug.stoerrelse() + ". \nas followed:");
				}
				for (String[] i : bug) {
					for (String a : i) {
						System.out.print(a + ",");
					}
					System.out.println();
				}
				System.out.println();
			}
		}

	}

	//	E2
	public static void menu() {
		Scanner scanInput = new Scanner(System.in);
		String input = "";
		while(input.compareTo("f")!= 0) {
			System.out.println();
			System.out.println("HOVEDMENY: ");
			System.out.println("Velg mellom a, b, c, d, e, f");
			System.out.println("a. Skriv ut en oversikt over alle elementer");
			System.out.println("b. Opprett et nytt element i systemet");
			System.out.println("c. Bruk en gitt resept fra listen til en pasient");
			System.out.println("d. Skriv ut forskjellige former for statistikk");
			System.out.println("e. Skriv all data til fil");
			System.out.println("f. Avslutt programmet: ");
			input = scanInput.nextLine();
			if (input.equalsIgnoreCase("a")) {
				oversikt();
			} else if (input.equalsIgnoreCase("b")) {
				nyElement();
			} else if (input.equalsIgnoreCase("c")) {
				brukResept();
			} else if (input.equalsIgnoreCase("d")) {
				skrivStatistikk();
			} else if (input.equalsIgnoreCase("e")) {
				lagre();
			} else if (input.equalsIgnoreCase("f")) {
				System.out.println("Systemet avsluttes.");
			} else {
				System.out.println("Prøv igjen, og vennligst velg en at alternativene.");
			}
		}
	}

	// E3
	public static void oversikt() {
		System.out.println("\nVelg hvilken oversikt du ønsker å skrive ut: ");
		System.out.println("a. Oversikt over pasienter");
		System.out.println("b. Oversikt over resepter");
		System.out.println("c. Oversikt over alle leger");
		System.out.println("d. Oversikt over spesialiser");
		System.out.println("e. Oversikt over legemidler");
		System.out.println("f. Skriv ut full oversikt");
		System.out.println("g. Gå tilbake til hovedmeny:  ");
		Scanner scanInput = new Scanner(System.in);
		String svar = "";
		if (scanInput.hasNext()) {
			svar = scanInput.next();
		}
		switch (svar) {
			case "a":
				skrivPasient();
				oversikt();
				break;
			case "b":
				skrivResept();
				oversikt();
				break;
			case "c":
				skrivLege();
				oversikt();
				break;
			case "d":
				skrivSpesialist();
				oversikt();
				break;
			case "e":
				skrivLegemidler();
				oversikt();
				break;
			case "f":
				skrivPasient();
				skrivResept();
				skrivLege();
				skrivSpesialist();
				skrivLegemidler();
				oversikt();
				break;
			case "g":
				break;
			default:
				System.out.println("Prøv igjen, og vennligst velg en av alternativene");
				oversikt();
				break;
		}
	}
	//E3
	public static void skrivPasient(){
		System.out.println("\nOversikt over pasienter: ");
		if (pasientliste.stoerrelse() == 0) {
			System.out.println("- Ingen pasienter i systemet");
		}
		for (Pasient pasient : pasientliste) {
			System.out.println(pasient.toString());
		}
	}
	//E3
	public static void skrivResept(){
		System.out.println("\nOversikt over pasient resepter: ");
		if (pasientliste.stoerrelse() == 0) {
			System.out.println("- Ingen pasienter, dermed ingen resepter i systemet\n");
		}
		for (Pasient pasient : pasientliste) {
			System.out.println(pasient.toString());
			System.out.println("  Pasientens reseptliste: ");
			if (pasient.reseptStabel.stoerrelse() == 0) {
				System.out.println("	Ingen resepter\n");
			} else {
				pasient.getStabel();
			}
		}
	}
	//E3
	public static void skrivLege() {
		System.out.println("\nOversikt over alle leger: ");
		if (legeliste.stoerrelse() == 0) {
			System.out.println("- Ingen leger i systemet");
		}
		for (Lege lege : legeliste) {
			System.out.println("- " + lege.toString());
		}
	}
	//E3
	public static void skrivSpesialist() {
		System.out.println("\nOversikt over spesialister: ");
		if (spesialisterliste.stoerrelse() == 0) {
			System.out.println("- Ingen spesialister i systemet");
		}
		for (Lege lege : spesialisterliste) {
			System.out.println("- " + lege.toString());
		}
	}
	//E3
	public static void skrivLegemidler() {
		System.out.println("\nOversikt over legemideler:");
		if (legemiddelliste.stoerrelse() == 0) {
			System.out.println("- Ingen legemidler i systemet");
		} else {
			for (Legemiddel legemiddel : legemiddelliste) {
				System.out.println(legemiddel.toString());
			}
		}
	}

	// E4
	public static void nyElement() {
		try {

			System.out.println("Velg mellom a, b, c, d");
			System.out.println("a. Registrer en ny lege");
			System.out.println("b. Registrer en ny pasient");
			System.out.println("c. Registrer en ny resept");
			System.out.println("d. Registrer et nytt legemiddel");
			System.out.println("e. Gå tilbake til hovedmenyen: ");
			Scanner scanInput = new Scanner(System.in);
			String svar = "";
			if (scanInput.hasNext()) {
				svar = scanInput.next();
			}
			switch (svar) {
				case "a":
					createLege();
					break;
				case "b":
					createPasient();
					break;
				case "c":
					nyResept();
					break;
				case "d":
					nyttLegemiddel();
					break;
				case "e":
					break;
				default:
					System.out.println("Prøv igjen, og vennligst velg en av alternativene");
					nyElement();
					break;
			}
		} catch (Exception e) {
			System.out.println("Prøv igjen, og vennligst velg en av alternativene");
			nyElement();
		}

	}
	// E4
	public static void createLege() {
		Scanner scanLege = new Scanner(System.in);
		System.out.println("Velg mellom a eller b");
		System.out.println("a. Registrer en ny lege");
		System.out.println("b. Registrer en ny spesialist");
		System.out.println("c. Gå tilbake til hovedmenyen: ");
		String svarLege = "q";
		if (scanLege.hasNext()) {
			svarLege = scanLege.next();
		}

		switch (svarLege) {
			case "a":
				System.out.println("Skriv inn navnet til legen: ");
				String navn = "";
				if (scanLege.hasNext()) {
					navn = scanLege.next();
				}
				for (Lege i : legeliste) {
					if (navn.equalsIgnoreCase(i.hentLegeNavn())) {
						System.out.println("Lege allerede registrert.");
						break;
					}
				}
				Lege newLege = new Lege(navn);
				legeliste.leggTil(newLege);
				System.out.println("Lege: " + navn + " er registrert");
				break;
			case "b":
				System.out.println("Skriv inn navnet til legen: ");
				navn = "";
				if (scanLege.hasNext()) {
					navn = scanLege.next();
				}
				for (Lege i : legeliste) {
					if (navn.equalsIgnoreCase(i.hentLegeNavn())) {
						System.out.println("Lege allerede registrert.");
						break;
					}
				}
				System.out.println("Skriv inn kontroll ID til spesialisten: ");
				int kontrollID = 0;
				if (scanLege.hasNext()) {
					kontrollID = scanLege.nextInt();
				}
				Spesialist spesi = new Spesialist(navn, kontrollID);
				spesialisterliste.leggTil(spesi);
				legeliste.leggTil(spesi);
				System.out.println("Spesialisten: " + spesi + " er registrert");
				break;
			case "c":
				break;
			default:
				System.out.println("Prøv igjen, og vennligst velg em av alternativene: ");
				createLege();
				break;
		}
	}

	// E4
	public static void createPasient() {
		Scanner scanPasient = new Scanner(System.in);
		System.out.println("Skriv inn navnet til pasienten: ");
		String navn = scanPasient.nextLine();
		System.out.println("Skriv inn fødselsnummeret til pasienten: ");
		String fNum = scanPasient.nextLine();
		// hvis pasienten allerede er registrert
		for (Pasient i : pasientliste) {
			if (navn.compareTo(i.name) == 0 && fNum.compareTo(i.birthNum) == 0) {
				System.out.println("Pasient " + navn + " allerede registrert.");
				return;
			}
		}
		Pasient pasi = new Pasient(navn, fNum);
		pasientliste.leggTil(pasi);
		System.out.println(pasi + " er registrert ");
	}

	// E4
	public static void nyttLegemiddel() {
		System.out.println("Velg mellom a, b, c");
		System.out.println("a. Registrer et nytt vanlig legemiddel");
		System.out.println("b. Registrer et nytt vanedannende legemiddel");
		System.out.println("c. Registrer et nytt narkotisk legemiddel");
		System.out.println("d. Gå tilbake til hovedmenyen: ");
		Scanner input = new Scanner(System.in);
		String svar = input.nextLine();
		switch (svar) {
			case "a":
				Vanlig vanlig = new Vanlig(middelNavn(), middelPris(), middelVirkestoff());
				legemiddelliste.leggTil(vanlig);
				System.out.println("Legemidlet '" + vanlig.navn + "' ble lagret");
				break;
			case "b":
				Vanedannende vanedannende = new Vanedannende(middelNavn(), middelPris(), middelVirkestoff(), middelStyrke());
				legemiddelliste.leggTil(vanedannende);
				System.out.println("Legemidlet '" + vanedannende.navn + "' ble lagret");
				break;
			case "c":
				Narkotisk narkotisk = new Narkotisk(middelNavn(), middelPris(), middelVirkestoff(), middelStyrke());
				legemiddelliste.leggTil(narkotisk);
				System.out.println("Legemidlet '" + narkotisk.navn + "' ble lagret");
				break;
			case "d":
				break;
			default:
				System.out.println("Prøv igjen, og vennligst velg en av alternativene");
				nyttLegemiddel();
				break;
		}
	}
	// E4
	public static String middelNavn(){
		Scanner input = new Scanner(System.in);
		System.out.println("Skriv navnet til legemiddelet: ");
		String navn = input.nextLine();
		return navn;
	}
	// E4
	public static double middelPris(){
		Scanner input = new Scanner(System.in);
		System.out.println("Skriv prisen til legemiddelet: ");
		double pris = 0;
		try {
			String p = input.nextLine();
			pris = Double.parseDouble(p);

		}catch (Exception e) {
			System.out.println("Prøv igjen, og vennligst skriv et tall");
			middelPris();
		}
		return pris;
	}
	// E4
	public static double middelVirkestoff(){
		Scanner input = new Scanner(System.in);
		System.out.println("Skriv virkestoffet til legemiddelet: ");
		double virkestoff = 0;
		try {
			String v = input.nextLine();
			virkestoff = Double.parseDouble(v);

		}catch (Exception e) {
			System.out.println("Prøv igjen, og vennligst skriv et tall");
			middelVirkestoff();
		}
		return virkestoff;
	}
	// E4
	public static int middelStyrke(){
		Scanner input = new Scanner(System.in);
		System.out.println("Skriv styrken til legemiddelet: ");
		int styrke = 0;
		try {
			String s = input.nextLine();
			styrke = Integer.parseInt(s);

		}catch (Exception e) {
			System.out.println("Prøv igjen, og vennligst skriv et heltall");
			middelStyrke();
		}
		return styrke;
	}

	// E4
	public static void nyResept() throws UlovligUtskrift {
		String inputd = ",";
		Lege nyd = null;
		Scanner scanResept = new Scanner(System.in);
		System.out.println("Hvilken lege skal utskrive resepten?");
		for (Lege i : legeliste) {
			System.out.println("- " + i.legeNavn);
		}
		inputd = scanResept.nextLine();
		for(Lege i2: legeliste) {
			if(i2.legeNavn.compareTo(inputd)==0) {
				nyd=i2;
			}
		}
		if(nyd==null) {
			System.out.println("Lege '"+inputd+"' ekisterer ikke\n");
			nyResept();
		}
		System.out.println("Velg alternativ a, b, c, d, e");
		System.out.println("a. Opprett en blaa resept");
		System.out.println("b. Opprett en hvit resept");
		System.out.println("c. Oppprett en militaerresept");
		System.out.println("d. Opprett en P-resept");
		System.out.println("e. Tilbake til hovedmenyen: ");
		String svarResept = scanResept.nextLine();

		switch (svarResept) {
			case "a":
				Resept j = nyd.skrivBlaaResept(inputMiddel(nyd), inputPasient(), inputReit());
				reseptliste.leggTil(j);
				System.out.println("Ny Resept:\n"+j);
				break;

			case "b":
				j = nyd.skrivHvitResept(inputMiddel(nyd), inputPasient(), inputReit());
				reseptliste.leggTil(j);
				System.out.println("Ny Resept:\n"+j);
				break;

			case "c":
				j = nyd.skrivMilitaerResept(inputMiddel(nyd), inputPasient(), inputReit());
				reseptliste.leggTil(j);
				System.out.println("Ny Resept:\n"+j);
				break;
			case "d":
				j = nyd.skrivPResept(inputMiddel(nyd), inputPasient());
				reseptliste.leggTil(j);
				System.out.println("Ny Resept:\n"+j);
				break;
			case "e":
				break;
			default:
				System.out.println("Prøv igjen, og vennligst velg en av alternativene");
				nyResept();
				break;
		}
	}
	// E4
	public static Legemiddel inputMiddel(Lege nyd){
		Scanner scanResept = new Scanner(System.in);
		Legemiddel nyttMiddel = null;
		System.out.println("Hvilken legemiddel vil du bruke?");
		if(nyd instanceof Spesialist) {
			for (Legemiddel i : legemiddelliste){
				System.out.println(i.legemiddelID +": "+i.navn);
			}
		} else {
			System.out.println("["+nyd.legeNavn+" er ikke spesialist, velg mellom vanlig og vanedannende legemiddel!]");
			for (Legemiddel i3 : legemiddelliste) {
				if(i3 instanceof Vanlig ||i3 instanceof Vanedannende) {
					System.out.println(i3.legemiddelID +": "+i3.navn);
				}
			}
		}
		String inputLegemiddel = scanResept.nextLine();
		try {
			int inputInt = Integer.parseInt(inputLegemiddel);
			if (inputInt < legemiddelliste.stoerrelse() && inputInt >= 0) {
				nyttMiddel = legemiddelliste.hent(inputInt);
			} else {
				System.out.println("Legemiddel ekisterer ikke. Vennligst registrer ny legemiddel.");
				inputMiddel(nyd);
			}
		} catch (Exception e) {
			System.out.println("Legemiddel ID: "+inputLegemiddel+ " ekisterer ikke");
			inputMiddel(nyd);
		}
		return nyttMiddel;
	}
	// E4
	public static Pasient inputPasient(){
		Scanner scanResept = new Scanner(System.in);
		Pasient nyPasient = null;
		System.out.println("Hvilken pasient vil du lage en resept for?");
		int num = 0;
		for (Pasient i : pasientliste) {
			System.out.println(num + ": " + i.name + " (fnr: " + i.birthNum + ")");
			num++;
		}
		try {
			String inputPasient = scanResept.nextLine();
			int inputInt = Integer.parseInt(inputPasient);
			// if the patient exists
			if (inputInt < pasientliste.stoerrelse() && inputInt >= 0) {
				nyPasient = pasientliste.hent(inputInt);
				// if patient doesn't exist
			} else {
				System.out.println("Pasienten ekisterer ikke. Prøv igjen");
				inputPasient();
			}
		} catch (Exception e) {
			System.out.println("Invalid input,try again");
			inputPasient();
		}
		return nyPasient;
	}
	// E4
	public static int inputReit() {
		Scanner reitAnt = new Scanner(System.in);
		int inputInt = 0;
		System.out.println("Skriv reit til denne resepten (må bruke tall): ");
		try {
			String inputReit = reitAnt.nextLine();
			inputInt = Integer.parseInt(inputReit);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			inputReit();
		}
		return inputInt;
	}

	// E5
	public static void brukResept() {
		Scanner scanPasient = new Scanner(System.in);
		String inputPasient = ",";
		System.out.println("Hvilken pasient vil du se resepter for?");
		int num = 0;
		for (Pasient i : pasientliste) {
			System.out.println(num + ": " + i.name + " (fnr: " + i.birthNum + ")");
			num++;
		}
		inputPasient = scanPasient.nextLine();
		try {
			int inputInt = Integer.parseInt(inputPasient);
			if (inputInt < pasientliste.stoerrelse() && inputInt >= 0) {
				velgResept(inputInt);
			} else {
				System.out.println("Prøv igjen, og vennligst velg en at alternativene");
				brukResept();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// E5
	public static void velgResept(int num) {
		Scanner scanResept = new Scanner(System.in);
		String inputResept = ",";
		Pasient thisPatient = pasientliste.hent(num);
		Resept thePrescription= null;
		System.out.println("Valgt pasient: " + thisPatient.name + " (fnr " + thisPatient.birthNum + ")");
		System.out.println("Hvilken resept vil du bruke?");
		for (Resept r : thisPatient.reseptStabel) {
			System.out.println(r);
		}
		inputResept = scanResept.nextLine();
		try {
			int inputRInt = Integer.parseInt(inputResept);
			if (inputRInt < reseptliste.stoerrelse() && inputRInt >= 0) {
				for (Resept s1 : thisPatient.reseptStabel) {
					if (inputRInt == s1.reseptID) {
						thePrescription = s1;
					}}
			}
			if(thePrescription.bruk()) {
				System.out.println("Brukte resept paa " + thePrescription.reseptID+ " "+ thePrescription.legemiddel
						+ "	Antall gjenvaerende reit: " + thePrescription.reit);
			} else {
				System.out.println("ingen gjenvaerende reit)");
				brukResept();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			brukResept();
		}
	}

	// E6
	public static void skrivStatistikk() {
		System.out.println("\nVelg mellom a, b, c, d");
		System.out.println("a. Totalt antall på utskrevne vanedannende legemidler");
		System.out.println("b. Totalt antall på utskrevne narkotiske legemidler");
		System.out.println("c. Statistikk om mulig misbruk av narkotika");
		System.out.println("d. Gå tilbake til hovedmeny:  ");
		Scanner scanInput = new Scanner(System.in);
		String svar = "";
		if (scanInput.hasNext()) {
			svar = scanInput.next();
		}
		switch (svar) {
			case "a":
				if (reseptliste.stoerrelse() == 0) {
					System.out.println("Ingen resepter");
					return;
				}
				int vanRes = 0;
				for (Resept resept : reseptliste) {
					if (resept.hentLegemiddel() instanceof Vanedannende) {
						vanRes++;
					}
				}

				if (vanRes == 0) {
					System.out.println("Ingen vanedannende resepter");
				} else {
					System.out.println("Totalt antall utskrevne resepter på vanedannende legemidler: " + vanRes);
					System.out.println("Størrelse på reseptlise: " + reseptliste.stoerrelse());
				}
				skrivStatistikk();
				break;
			case "b":
				if (reseptliste.stoerrelse() == 0) {
					System.out.println("Ingen resepter");
					return;
				}
				int narkRes = 0;
				for (Resept resept : reseptliste) {
					if (resept.hentLegemiddel() instanceof Narkotisk) {
						narkRes++;
					}
				}
				if (narkRes == 0) {
					System.out.println("Ingen narkotiske legemidler");
				} else {
					System.out.println("Pasientliste str: " + pasientliste.stoerrelse());
					System.out.println("Totalt antall utskrevne resepter på narkotiske legemidler: " + narkRes);
				}
				skrivStatistikk();
				break;
			// navn på lege som har skrivet ut narkRes, og antall narkRes per lege
			case "c":
				System.out.println("Antall resepter som er mulig bevis på misbruk av narkotika: "+ misbruk.stoerrelse());
				System.out.println("Begrunnelse: Legen er ikke en spesialist. En lege må være en spesialist for å utgi narkotiske legemiddler. ");
				System.out.println("Resept informasjon: ");
				for(Resept K: misbruk) {
					System.out.println(K);
				}

				System.out.println("\nLege som har utskrevet narkotiske resepter uten å være en spesialist: ");
				/*
				misbruk.hentLege().hentLegeNavn()
				 */
				if (misbruk.stoerrelse() == 0) {
					System.out.println("- Ingen leger ser ut som misbruker systemet for narkotiske stoffer");
					return;
				}

				int narkResLege = 0;
				Set<Lege> dirtyLege = new HashSet<Lege>();
				for (Resept resept : misbruk) {
					Lege lege = resept.hentLege();
					dirtyLege.add(lege);
				}
				for (Lege lege : dirtyLege){
					narkRes = 0;
					narkResLege++;
					for (Resept r : lege.hentUtskrevedeResepter()) {
						if (r.hentLegemiddel() instanceof Narkotisk) { //&& legenavn == leganavn
							narkRes++;
						}
					}
					System.out.println("- Lege: " + lege.hentLegeNavn() + " (antall narkotiske resepter utskrevet:  " + narkRes + ")");
				}
				System.out.println("\nPasienter som har narkotiske legemidler og antallet per pasient: ");
				if (pasientliste.stoerrelse() == 0) {
					System.out.println("- Ingen pasienter");

				}
				int narkPas = 0;
				for (Pasient pasient : pasientliste) {
					int narkPerPas = 0;
					for (Resept resept : pasient.reseptStabel) {
						if (resept.hentLegemiddel() instanceof Narkotisk) {
							narkPerPas++;
						}
					}
					if (narkPerPas != 0) {
						narkPas++;
						System.out.printf("- Navn: " + pasient.name + "\n	Antall narkotiske legemidler: "
								+ narkPerPas + "\n");
					}
				}
				System.out.println("Antall leger: " + narkResLege + "\nAntall pasienter: " + narkPas);
				skrivStatistikk();
				break;
			case "d":
				break;
			default:
				System.out.println("Prøv igjen, og vennligst velg en at alternativene");
				skrivStatistikk();
				break;
		}
	}

	// E8
	@SuppressWarnings("resource")
	public static void lagre() {
		String w = "new";
		// create printerwriter to print to the new file
		// System.out.println("filename: "+w);
		try {
			PrintWriter writer = new PrintWriter(w + ".txt", "UTF-8");
			// patient writing to the file
			writer.println("# Pasienter (navn, fnr)");
			for (Pasient i : pasientliste) {
				writer.println(i.name + "," + i.birthNum);
			}
			// legemiddel writing to the file
			writer.println("# Legemidler (navn,type,pris,virkestoff,[styrke]");
			for (Legemiddel i : legemiddelliste) {
				writer.print(i.hentNavn() + "," + i.getClass().getSimpleName().toLowerCase() + "," + i.hentPris() + ","
						+ i.hentVirkestoff());
				if (i instanceof Narkotisk) {
					writer.println("," + ((Narkotisk) i).hentStyrke());
				} else if (i instanceof Vanedannende) {
					writer.println("," + ((Vanedannende) i).hentStyrke());
				} else {
					writer.println();
				}
			}
			// lege writing to the file
			writer.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
			for (Lege i : legeliste) {
				if (i instanceof Spesialist) {
					writer.println(i.hentLegeNavn() + "," + ((Spesialist) i).hentKontrollID());
				} else {
					writer.println(i.hentLegeNavn() + ",0");
				}
			}
			// resept: writing to the file
			writer.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
			for (Resept i : reseptliste) {
				writer.print(
						i.hentLegemiddel().legemiddelID + "," + i.hentLege().hentLegeNavn() + "," + i.pasient.ID + ",");
				if (i instanceof Blaa) {
					writer.println("blaa" + "," + i.hentReit());
				} else if (i instanceof Presept) {
					writer.println("p");
				} else if (i instanceof Militaerresept) {
					writer.println("militaer," + i.hentReit());
				} else {
					writer.println("hvit," + i.hentReit());
				}
			}
			System.out.println("All data er lagret til fil. Vennligst sjekk fil: 'new.txt'");
			writer.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}