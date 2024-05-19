import java.io.File;
import java.io.IOException;

public class Tree {
    public static void main(String[] args) throws Exception {
        // Créez un répertoire racine pour tester
        File root = new File("testDir");
        createTestFiles(root);

        int level = 0;
        StringBuilder result = new StringBuilder();
        renderFolder(root, level, result, false);
        System.out.println(result.toString());
    }

    // Méthode pour créer des dossiers et des fichiers de test
    private static void createTestFiles(File root) throws IOException {
        if (!root.exists()) {
            root.mkdir();
        }

        File subDir1 = new File(root, "subDir1");
        File subDir2 = new File(root, "subDir2");
        subDir1.mkdir();
        subDir2.mkdir();

        new File(subDir1, "file1.txt").createNewFile();
        new File(subDir1, "file2.txt").createNewFile();
        new File(subDir2, "file3.txt").createNewFile();
        new File(root, "file4.txt").createNewFile();
    }

    private static void renderFolder(File folder, int level, StringBuilder sb, boolean isLast) {
        indent(sb, level, isLast).append(folder.getName()).append("\n");

        File[] objects = folder.listFiles();

        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                boolean last = ((i + 1) == objects.length);

                if (objects[i].isDirectory()) {
                    renderFolder(objects[i], level + 1, sb, last);
                } else {
                    renderFile(objects[i], level + 1, sb, last);
                }
            }
        }
    }

    private static void renderFile(File file, int level, StringBuilder sb, boolean isLast) {
        indent(sb, level, isLast).append(file.getName()).append("\n");
    }

    private static StringBuilder indent(StringBuilder sb, int level, boolean isLast) {
        for (int i = 1; i < level; i++) {
            sb.append("\u2502   ");
        }

        if (level > 0) {
            sb.append(isLast ? "\u2514\u2500\u2500" : "\u251c\u2500\u2500");
        }

        return sb;
    }
}
