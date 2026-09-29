import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class leerFichero {
    static void main(String[] args) throws FileNotFoundException {
        String ruta= "UD00_ADPracticas/datos/otro.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}