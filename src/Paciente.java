
public class Paciente {
    private int codigo;
    private String cedula;
    private String nombre;
    private int edad;
    private String motivoConsulta;
    private int prioridad;

    public Paciente(int codigo, String cedula, String nombre,
                    int edad, String motivoConsulta, int prioridad) {
        this.codigo = codigo;
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.motivoConsulta = motivoConsulta;
        this.prioridad = prioridad;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void mostrarDatos() {
        System.out.println("Código: " + codigo);
        System.out.println("Cédula: " + cedula);
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Motivo: " + motivoConsulta);

        String nivel = prioridad == 1 ? "Alta"
                : prioridad == 2 ? "Media" : "Baja";

        System.out.println("Prioridad: " + nivel);
        System.out.println("--------------------------");
    }
}
