package GestorEmpleados;

public class Empleado implements  Comparable<Empleado> {

    private String dni;
    private String nombre;
    private String apellido;
    private Float telefono;
    private double salario;

    public Empleado(String DNI, String nombre, String apellido, Float telefono, double salario) {
        this.dni = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.salario = salario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Float getTelefono() {
        return telefono;
    }

    public void setTelefono(Float telefono) {
        this.telefono = telefono;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado [DNI=" + dni + ", Nombre=" + nombre + ", Apellido=" + apellido + ", Teléfono=" + telefono +
                ", Salario=" + salario +"]";
    }

    @Override
    public int compareTo(Empleado otroEmpleado) {
        return ComparadorCadenas.compararCadenas(this.dni, otroEmpleado.getDni());
    }
}
