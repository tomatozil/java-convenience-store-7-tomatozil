package file;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileReader {
    private final String buffer;

    public FileReader() {
        this.buffer = readFile(makePath());
    }

    public String readFile(Path path) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            return String.valueOf(result);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected Path makePath(){
        String resourceFileName = "products.md";
        Path path;
        try  {
            path = Paths.get(getClass().getClassLoader().getResource(resourceFileName).toURI());
            return path;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
