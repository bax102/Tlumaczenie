import com.detectlanguage.DetectLanguage;
import com.detectlanguage.errors.APIError;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

import static com.detectlanguage.DetectLanguage.apiKey;

public class Main {

    public static void main (String [] args) throws FileNotFoundException, APIError {

        DetectLanguage.apiKey = "916733a75af3345d70233a45e32951c3";

        Locale jezykAngielski = new Locale ("en");
        Locale jezykFrancuski = new Locale ("fr");
        Locale jezykPolski = new Locale ("pl");

        File folder = new File ("C:\\Users\\piotr\\Desktop\\artykuly\\");
        File [] pliki = folder.listFiles();


        for (File plik: pliki) {
            String jezyk = kodJezyka(plik);
            Locale jezyczek = new Locale(jezyk);
            String poFrancusku = jezyczek.getDisplayLanguage(jezykFrancuski);
            String poPolsku = jezyczek.getDisplayLanguage(jezykPolski);

            System.out.println("Tekst " + plik.getName() + "jest napisany w języku: " + kodJezyka(plik) + " " + poFrancusku + " / " + poPolsku);
        }
    }

    public static String kodJezyka (File plik) throws FileNotFoundException, APIError {

        Scanner scan = new Scanner (plik);

        String calosc ="";

        while (scan.hasNextLine()) {
            String tekst = scan.nextLine();
            calosc += tekst;
        }
        String kodJezyka = DetectLanguage.simpleDetect(calosc);

        return kodJezyka;
    }
}
