package GestorEmpleados;

public class ComparadorCadenas {

    public static int compararCadenas(String dni1, String dni2) {

        String numerosDni1 = dni1.substring(0, 8);
        String numerosDni2 = dni2.substring(0, 8);

        String letraDni1 = dni1.substring(8);
        String letraDni2 = dni2.substring(8);

        int numeroEntero1 = Integer.parseInt(numerosDni1);
        int numeroEntero2 = Integer.parseInt(numerosDni2);

        if (numeroEntero1 > numeroEntero2) {
            return 1;
        } else if (numeroEntero1 < numeroEntero2) {
            return -1;
        } else {
            return letraDni1.compareTo(letraDni2);
        }
    }
}
