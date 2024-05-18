import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class PasswordCracker {
  public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
    // Initialisation du menu et du mot de passe
    MainMenu mainMenu = new MainMenu();
    Password password = new Password();

    // Définition du type d'attaque et du type mot de passe
    int attackType = mainMenu.attackTypeSelection();
    if (attackType != 3) {
      // Entrée du mot de passe par l'utilisateur : fonction permettant de faire le
      // contrôle de saisie nécessaire selon le type de mot de passe et de l'attaque
      // Définition du type de mot passe
      password.passwordSettings(attackType);

      // Appel de la methode factory
      PasswordCrackerFactory.crack(password, attackType);
    } else {
      String url = mainMenu.saisieUlr();
      PasswordCrackerFactory.crack(url);
    }

  }
}
