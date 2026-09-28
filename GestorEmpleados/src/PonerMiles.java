import java.util.Scanner;

public class PonerMiles {
    public static void main(String[] args) {

        System.out.println("Introduce un número de varios dígitos mayor que 1000: ");

        Scanner entrada = new Scanner(System.in);
        String numero = entrada.nextLine();

        StringBuilder numeroModificable = new StringBuilder(numero);

        for (int i = numero.length() - 3; i > 0; i = i - 3) {
            numeroModificable.insert(i, ".");
        }
        System.out.println(numeroModificable);
    }
}

