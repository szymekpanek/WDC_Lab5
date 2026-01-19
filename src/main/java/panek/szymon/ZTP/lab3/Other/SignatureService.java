package panek.szymon.ZTP.lab3.Other;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

public class SignatureService {

    public void signAndVerify(String inputFile, String algorithm) throws Exception {
        System.out.println("\n--- [PODPIS] Algorytm: " + algorithm + " ---");

        // Generowanie kluczy RSA do podpisu
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "BC");
        kpg.initialize(2048);
        KeyPair pair = kpg.generateKeyPair();

        byte[] inputData = FileUtils.read(inputFile);

        // 1. Podpisywanie (Klucz Prywatny)
        Signature signer = Signature.getInstance(algorithm, "BC");
        signer.initSign(pair.getPrivate());
        signer.update(inputData);
        byte[] signatureBytes = signer.sign();

        String sigFile = "output_signature.sig";
        FileUtils.save(sigFile, signatureBytes);

        // 2. Weryfikacja (Klucz Publiczny)
        Signature verifier = Signature.getInstance(algorithm, "BC");
        verifier.initVerify(pair.getPublic());
        // Czytamy ponownie dane i podpis z dysku (symulacja weryfikacji)
        verifier.update(FileUtils.read(inputFile));

        boolean isValid = verifier.verify(FileUtils.read(sigFile));
        System.out.println("Czy podpis z pliku jest poprawny? -> " + isValid);
    }
}