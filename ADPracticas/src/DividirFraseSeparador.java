import java.util.Scanner;

public class DividirFraseSeparador {

    public static void main(String[] args) {

        System.out.println("Introduce una cadena de palabras, se mostrará una palabra por cada línea:");

        Scanner entrada = new Scanner(System.in);
        String cadena = entrada.nextLine();

        String[] palabras = cadena.split(" ");

        for (String palabra : palabras) {
            System.out.println(palabra);
        }
    }
}