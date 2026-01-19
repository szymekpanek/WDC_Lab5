package panek.szymon.ZTP.lab3.symmetric;

import panek.szymon.ZTP.lab3.FileHandler.FileUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class SymmetricService {

    // Metoda generująca klucz (pomocnicza)
    public SecretKey generateKey(String algorithm, int keySize) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm, "BC");
        keyGen.init(keySize);
        return keyGen.generateKey();
    }

    // Pełny cykl: Szyfrowanie -> Plik -> Odszyfrowanie -> Plik
    public void runCycle(String inputFile, String algorithm, int keySize) throws Exception {
        System.out.println("\n--- [SYMETRYCZNE] Algorytm: " + algorithm + " ---");

        SecretKey key = generateKey(algorithm, keySize);
        byte[] inputData = FileUtils.read(inputFile);

        // 1. Szyfrowanie
        Cipher cipher = Cipher.getInstance(algorithm + "/CBC/PKCS7Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(new byte[16])); // IV=0 dla uproszczenia
        byte[] encryptedData = cipher.doFinal(inputData);

        String encryptedFile = "output_" + algorithm + ".bin";
        FileUtils.save(encryptedFile, encryptedData);

        // 2. Odszyfrowanie (z pliku, który przed chwilą zapisaliśmy)
        byte[] dataToDecrypt = FileUtils.read(encryptedFile);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
        byte[] decryptedData = cipher.doFinal(dataToDecrypt);

        FileUtils.save("output_" + algorithm + "_decrypted.txt", decryptedData);
    }
}