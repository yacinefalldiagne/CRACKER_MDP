import java.security.*;

public class BruteForceAttack {

  private static boolean bruteForceHashed(String password, int taille, Password password2, long tempsInitial)
      throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    if (taille == 0) {
      byte[] hashBytes = md.digest(password.getBytes());
      StringBuilder sb = new StringBuilder();
      for (byte b : hashBytes) {
        sb.append(String.format("%02x", b));
      }
      if (sb.toString().equals(password2.getPassword())) {
        System.out.println("Trouvé ! Le mot de passe est : " + password);
        long tempsFinal = System.currentTimeMillis();
        long tempsMis = tempsFinal - tempsInitial;
        System.out.println("Temps mis (en millisecondes) : " + tempsMis);
        return true;
      }
      return false;
    } else {
      for (char caractere = 'a'; caractere <= 'z'; caractere++) {
        if (bruteForceHashed(password + caractere, taille - 1, password2, tempsInitial)) {
          return true;
        }
      }
    }
    return false;
  }

  public static void BruteForce(Password password) throws NoSuchAlgorithmException {
    long tempsInitial = System.currentTimeMillis();
    bruteForceHashed("", 5, password, tempsInitial);

  }

}
