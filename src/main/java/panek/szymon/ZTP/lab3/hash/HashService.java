package panek.szymon.ZTP.lab3.hash;

import panek.szymon.ZTP.lab3.Other.FileUtils;

import java.security.MessageDigest;
import java.util.HexFormat;

public class HashService {

    public void generateHash(String inputFile, String algorithm) throws Exception {
        System.out.println("\n--- [HASH] Algorytm: " + algorithm + " ---");

        byte[] inputData = FileUtils.read(inputFile);

        MessageDigest digest = MessageDigest.getInstance(algorithm, "BC");
        byte[] hashBytes = digest.digest(inputData);

        // Konwersja na czytelny format HEX i zapis do pliku tekstowego
        String hexHash = HexFormat.of().formatHex(hashBytes);
        FileUtils.save("output_" + algorithm + ".hash", hexHash.getBytes());

        System.out.println("Skrót: " + hexHash.substring(0, Math.min(20, hexHash.length())) + "...");
    }
}