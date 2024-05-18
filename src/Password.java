import java.util.Scanner;

public class Password {
  // Attributs du mot de passe :
  // Le type permet de dire si c'est un mot de passe simple ou hashe
  private int type = 0;

  // Cette chaine stockera le mot de passe
  private String password = "";

  // Constructeur sans paramètres
  Password() {
  };

  // Guetters et setters
  public String getPassword() {
    return password;
  }

  public int getType() {
    return type;
  }

  // Methode utilisée par la méthode "passwordSettings" pour définir la valeur du
  // mot de passe.
  private void setPassword(String password) {
    this.password = password;
  }

  public void passwordSettings(int attackType) {
    // boolean isOk = false;
    String password = "";
    Scanner input = new Scanner(System.in);
    if (attackType == 1) {
      System.out.println(
          "Veuillez entrer le mot de passe que vous souhaitez craquer.Les mots de passe hachés doivent être de 5 caractères de longueur.. ");
      password = input.nextLine();
    }
    if (attackType == 2) {
      System.out.println(
          "Veuillez entrer le mot de passe que vous souhaitez craquer.Les mots de passe hachés doivent être de 8 caractères maximum.");
      password = input.nextLine();
    }

    this.setPassword(password);
  }

}
