import java.util.Scanner;

public class Fahrkartenautomat_A635 {

  
  // Begruessung
  public static void begruessung() {
    System.out.println("Herzlich Willkommen!\n");
  }

  
  // Kartenauswahl und Ticketanzahl
  public static double fahrkartenbestellungErfassen(Scanner tastatur) {
    double ticketPreis = 0;
    int anzahlTickets = 0;
    boolean korrekteEingabe = false;
    int auswahlTickets = 0;
    double zuZahlenderBetrag;

    System.out.println("Wählen Sie ihre Wunschfahrkarte für Berlin AB aus:");
    System.out.println("Kurzstrecke AB [2,00 EUR] (1)");
    System.out.println("Einzelfahrschein AB [3,00 EUR] (2)");
    System.out.println("Tageskarte Regeltarif AB [8,80 EUR] (3)");
    System.out.println("4-Fahrten-Karte AB [9,40 EUR] (4)\n");

    while (korrekteEingabe == false) {
      System.out.print("Ihre Wahl: ");
      auswahlTickets = tastatur.nextInt();
      if (auswahlTickets >= 1 && auswahlTickets <= 4) {
        korrekteEingabe = true;
      } else {
        System.out.println(" >>falsche Eingabe<< ");
      }
    }

    if (auswahlTickets == 1)
      ticketPreis = 2.0;
    else if (auswahlTickets == 2)
      ticketPreis = 3.0;
    else if (auswahlTickets == 3)
      ticketPreis = 8.8;
    else if (auswahlTickets == 4)
      ticketPreis = 9.4;

    korrekteEingabe = false;

    while (korrekteEingabe == false) {
      System.out.print("Anzahl der Tickets: ");
      anzahlTickets = tastatur.nextInt();

      if (anzahlTickets >= 1 && anzahlTickets <= 10)
        korrekteEingabe = true;
      else
        System.out.println(" >> Wählen Sie bitte eine Anzahl von 1 bis 10 Tickets aus. <<\n");
    }
    zuZahlenderBetrag = ticketPreis * anzahlTickets;
    return zuZahlenderBetrag;
  }

  
  // Geldeinwurf
  public static double fahrkartenBezahlen(Scanner tastatur, double zuZahlenderBetrag) {
    double eingeworfeneMuenze;
    double nochZuZahlen;
    double eingezahlterGesamtbetrag;
    eingezahlterGesamtbetrag = 0.0;
    nochZuZahlen = 0.0;
    while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
      nochZuZahlen = zuZahlenderBetrag - eingezahlterGesamtbetrag;
      System.out.printf("Noch zu zahlen: %4.2f Euro\n", nochZuZahlen);

      System.out.print("Eingabe (mind. 5 Cent, höchstens 2 Euro): ");
      eingeworfeneMuenze = tastatur.nextDouble();
      eingezahlterGesamtbetrag = eingezahlterGesamtbetrag + eingeworfeneMuenze;
    }
    return eingezahlterGesamtbetrag;
  }

  
  // Fahrscheinausgabe
  public static void fahrkartenAusgeben() {

    System.out.println("\nFahrschein wird ausgegeben");
    for (int i = 0; i < 8; i++) {
      System.out.print("=");
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("\n\n");
  }

  
  // Rückgeldberechnung und -ausgabe [neu]
  public static void rueckgeldAusgeben(double eingezahlterGesamtbetrag, double zuZahlenderBetrag) {
    double rueckgabebetrag;
    rueckgabebetrag = eingezahlterGesamtbetrag - zuZahlenderBetrag;
    if (rueckgabebetrag > 0.0) {
      System.out.format("Der Rückgabebetrag in Höhe von %4.2f Euro \n", rueckgabebetrag);
      System.out.println("wird in folgenden Münzen ausgezahlt:");

      while (rueckgabebetrag >= 2.0) { // 2-Euro-Münzen
        System.out.println("2 Euro");
        rueckgabebetrag = rueckgabebetrag - 2.0;
      }
      while (rueckgabebetrag >= 1.0) { // 1-Euro-Münzen
        System.out.println("1 Euro");
        rueckgabebetrag = rueckgabebetrag - 1.0;
      }
      while (rueckgabebetrag >= 0.5) { // 50-Cent-Münzen
        System.out.println("50 Cent");
        rueckgabebetrag = rueckgabebetrag - 0.5;
      }
      while (rueckgabebetrag >= 0.2) { // 20-Cent-Münzen
        System.out.println("20 Cent");
        rueckgabebetrag = rueckgabebetrag - 0.2;
      }
      while (rueckgabebetrag >= 0.1) { // 10-Cent-Münzen
        System.out.println("10 Cent");
        rueckgabebetrag = rueckgabebetrag - 0.1;
      }
      while (rueckgabebetrag >= 0.05) { // 5-Cent-Münzen
        System.out.println("5 Cent");
        rueckgabebetrag = rueckgabebetrag - 0.05;
      }
    }

    System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
        + "Wir wünschen Ihnen eine gute Fahrt.");
  }
  
  
  // Hauptprogramm
  public static void main(String[] args) {

    double zuZahlenderBetrag;
    double eingezahlterGesamtbetrag;
    Scanner tastatur = new Scanner(System.in);

    begruessung();
    zuZahlenderBetrag = fahrkartenbestellungErfassen(tastatur);
    eingezahlterGesamtbetrag = fahrkartenBezahlen(tastatur, zuZahlenderBetrag);
    fahrkartenAusgeben(); 
    rueckgeldAusgeben(eingezahlterGesamtbetrag, zuZahlenderBetrag); // neu
  
    tastatur.close();
  }
}