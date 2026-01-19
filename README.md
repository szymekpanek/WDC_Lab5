

---


# Projekt 5: Kryptografia w Javie (Bouncy Castle)

## Wymagania projektowe

Aplikacja realizuje następujące funkcjonalności:
1. **Szyfrowanie symetryczne** plików dwiema wybranymi metodami.
2. **Szyfrowanie asymetryczne** plików dwiema wybranymi metodami.
3. Obliczanie **skrótu (hash)** plików dwiema funkcjami.
4. Składanie i weryfikacja **podpisu cyfrowego**.
5. Pomiar czasu operacji i obsługa plików (I/O).


## Architektura

Projekt został zaprojektowany zgodnie z zasadą **Single Responsibility Principle**. Logika biznesowa została odseparowana od warstwy prezentacji (`Main`).

| Klasa / Serwis | Odpowiedzialność |
| :--- | :--- |
| **`Main`** | Punkt wejściowy programu. Orkiestruje wywołania serwisów. |
| **`SymmetricService`** | Obsługa szyfrowania kluczem tajnym (Symetryczne). |
| **`AsymmetricService`** | Obsługa szyfrowania parą kluczy (Publiczny/Prywatny). |
| **`HashService`** | Generowanie skrótów wiadomości (Digest). |
| **`SignatureService`** | Generowanie i weryfikacja podpisów cyfrowych. |
| **`FileUtils`** | Klasa narzędziowa do operacji I/O (odczyt/zapis bajtów). |

## Zaimplementowane Algorytmy

Do realizacji zadań wybrano bibliotekę **Bouncy Castle** ze względu na szerokie wsparcie dla nowoczesnych i niestandardowych algorytmów.

| Kategoria | Metoda 1 (Standard) | Metoda 2 (Bouncy Castle) |
| :--- | :--- | :--- |
| **Symetryczne** | `AES` (CBC/PKCS7Padding) | `Twofish` (CBC/PKCS7Padding) |
| **Asymetryczne** | `RSA` (2048 bit) | `ElGamal` (512 bit) |
| **Hash (Skrót)** | `SHA-256` | `Whirlpool` |
| **Podpis** | `SHA256withRSA` | N/A |


## Przykładowy Wynik (Logi)

```text
=== [SYMETRYCZNE] Algorytm: Twofish ===
 -> Zapisano plik: output_Twofish.bin
 -> Zapisano plik: output_Twofish_decrypted.txt

=== [ASYMETRYCZNE] Algorytm: ElGamal ===
 -> Zapisano plik: output_ElGamal.bin
 -> Zapisano plik: output_ElGamal_decrypted.txt

=== [HASH] Algorytm: Whirlpool ===
 -> Zapisano plik: output_Whirlpool.hash
Skrót: 4b281c7e93...

=== [PODPIS] Algorytm: SHA256withRSA ===
 -> Zapisano plik: output_signature.sig
Czy podpis z pliku jest poprawny? -> true

```

## 📝 Ocena Biblioteki (Raport)

* **Łatwość obsługi:** Bouncy Castle wymaga jawnej rejestracji `Security.addProvider()`. API jest spójne z JCA (Java Cryptography Architecture), co ułatwia migrację, ale wymaga precyzyjnego podawania parametrów transformacji (np. `AES/CBC/PKCS7Padding`).
* **Czytelność dokumentacji:** Dokumentacja jest techniczna i skierowana do zaawansowanych użytkowników. Często wymaga analizy kodu źródłowego lub testów jednostkowych biblioteki.
* **Wydajność:** Dla standardowych algorytmów (AES) wydajność jest porównywalna z natywną Javą. Dla algorytmów specyficznych (Twofish, Whirlpool) biblioteka oferuje bardzo dobrą optymalizację programową.

---
Szymon Panek CY3
