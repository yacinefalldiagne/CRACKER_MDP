import java.util.Scanner;

public class MainMenu {
  // Types d'attaques et types de mots de passe supportes
  String[] attaqueTypes = { "Brute Force", "Dictionnaire", "Server Authentification" };
  // String[] passwordTypes = { "simple", "hashed" };

  // Constructeur sans parametres
  MainMenu() {
  };

  // Menu permettant la selection du type d'attaque
  public int attackTypeSelection() {
    boolean isPasswordGood = false;
    int response = 0;
    Scanner userResponse = new Scanner(System.in);
    do {
      System.out.println("Veuillez choisir le type d'attaque :");

      // Affichage des types d'attaques
      for (int i = 0; i < attaqueTypes.length; i++) {
        System.out.println("[" + (i + 1) + "]- Attaque par " + attaqueTypes[i]);
      }

      // Récupération de la réponse de l'utilisateur
      response = userResponse.nextInt();

      // Controle de saisie
      if (response >= 1 && response <= attaqueTypes.length) {
        isPasswordGood = true;
      }
    } while (isPasswordGood == false);
    return response;
  }

  public String saisieUlr() {
    Scanner userResponse = new Scanner(System.in);
    System.out.println("Veuillez saisir l'url du site:");
    return userResponse.nextLine();
  }

}