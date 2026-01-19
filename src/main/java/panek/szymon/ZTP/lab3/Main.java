package panek.szymon.ZTP.lab3;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;
import panek.szymon.ZTP.lab3.Other.SignatureService;
import panek.szymon.ZTP.lab3.asymmetric.AsymmetricService;
import panek.szymon.ZTP.lab3.hash.HashService;
import panek.szymon.ZTP.lab3.symmetric.SymmetricService;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {

    // Konfiguracja
    private static final String PLIK_WEJSCIOWY = "dane.txt";

    public static void main(String[] args) {
        try {
            // 0. Konfiguracja wstępna
            Security.addProvider(new BouncyCastleProvider());
            utworzPlikTestowyJesliNieIstnieje();

            // Inicjalizacja serwisów
            SymmetricService symmetric = new SymmetricService();
            AsymmetricService asymmetric = new AsymmetricService();
            HashService hashing = new HashService();
            SignatureService signing = new SignatureService();

            // === REALIZACJA ZADAŃ ===

            // Zadanie 1: Szyfrowanie symetryczne (2 metody)
            symmetric.runCycle(PLIK_WEJSCIOWY, "AES", 128);
            symmetric.runCycle(PLIK_WEJSCIOWY, "Twofish", 128);

            // Zadanie 2: Szyfrowanie asymetryczne (2 metody)
            asymmetric.runCycle(PLIK_WEJSCIOWY, "RSA", "RSA/ECB/PKCS1Padding", 2048);
            asymmetric.runCycle(PLIK_WEJSCIOWY, "ElGamal", "ElGamal/None/PKCS1Padding", 512);

            // Zadanie 3: Funkcje skrótu (2 metody)
            hashing.generateHash(PLIK_WEJSCIOWY, "SHA-256");
            hashing.generateHash(PLIK_WEJSCIOWY, "Whirlpool");

            // Zadanie 4: Podpis cyfrowy
            signing.signAndVerify(PLIK_WEJSCIOWY, "SHA256withRSA");

            System.out.println("\n=== KONIEC PROJEKTU ===");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void utworzPlikTestowyJesliNieIstnieje() throws Exception {
        if (!Files.exists(Paths.get(PLIK_WEJSCIOWY))) {
            Files.writeString(Paths.get(PLIK_WEJSCIOWY), "To są tajne dane do projektu z Cyberbezpieczeństwa.");
        }
    }
}
