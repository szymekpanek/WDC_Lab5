package panek.szymon.ZTP.lab3.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    public static byte[] read(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    public static void save(String path, byte[] data) throws IOException {
        Files.write(Paths.get(path), data);
        System.out.println(" -> Zapisano plik: " + path);
    }
}