package v1.file;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileReader {

    public String readFile(String resourceFileName) {
        Path path = makePath(resourceFileName);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            return String.valueOf(result);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected Path makePath(String resourceFileName) {
        try  {
            Path path = Paths.get(getClass().getClassLoader().getResource(resourceFileName).toURI());
            return path;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
