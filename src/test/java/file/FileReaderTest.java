package file;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class FileReaderTest {

    @Test
    void 파일_읽기_테스트() {
        FileReader fileReader = new FileReader() {
            @Override
            protected Path makePath() {
                return Paths.get("src/test/java/file/woowa.txt");
            }
        };
    }
}