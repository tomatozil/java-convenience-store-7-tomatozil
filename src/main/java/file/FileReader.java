package file;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReader {
    private final String buffer;

    public FileReader(){
        this.buffer = readFile(makePath());
    }

    public String readFile(Path path) {
        StringBuilder result = new StringBuilder();
        try (
                InputStream in = Files.newInputStream(path);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (Exception e) {
            System.out.println("[Error] " + e);
        }
        return String.valueOf(result);
    }

    protected Path makePath() {
        String path = "src/main/java/file/products.md";
        return Paths.get(path);
    }
}
