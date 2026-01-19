package panek.szymon.ZTP.lab3.asymmetric;

import panek.szymon.ZTP.lab3.Other.FileUtils;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class AsymmetricService {

    public void runCycle(String inputFile, String keyAlgo, String cipherAlgo, int keySize) throws Exception {
        System.out.println("\n--- [ASYMETRYCZNE] Algorytm: " + keyAlgo + " ---");

        // Generowanie pary kluczy
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(keyAlgo, "BC");
        kpg.initialize(keySize);
        KeyPair pair = kpg.generateKeyPair();

        byte[] inputData = FileUtils.read(inputFile);

        // 1. Szyfrowanie (Klucz Publiczny)
        Cipher cipher = Cipher.getInstance(cipherAlgo, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, pair.getPublic());
        byte[] encryptedData = cipher.doFinal(inputData);

        String encryptedFile = "output_" + keyAlgo + ".bin";
        FileUtils.save(encryptedFile, encryptedData);

        // 2. Odszyfrowanie (Klucz Prywatny)
        byte[] dataToDecrypt = FileUtils.read(encryptedFile);
        cipher.init(Cipher.DECRYPT_MODE, pair.getPrivate());
        byte[] decryptedData = cipher.doFinal(dataToDecrypt);

        FileUtils.save("output_" + keyAlgo + "_decrypted.txt", decryptedData);
    }
}