
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static Queue<Paciente> colaFIFO = new LinkedList<>();

    static PriorityQueue<Paciente> colaPrioridad =
            new PriorityQueue<>(
                    Comparator.comparingInt(Paciente::getPrioridad)
            );

    static ArrayList<Paciente> historial = new ArrayList<>();

    static Stack<String> acciones = new Stack<>();

    static int codigo = 1;

    public static void main(String[] args) {
        int opcion = 0;

        while (opcion != 9) {
            System.out.println("\n================================");
            System.out.println(" SISTEMA DE GESTION HOSPITALARIA");
            System.out.println("================================");
            System.out.println("1. Ingresar paciente sin prioridad");
            System.out.println("2. Ingresar paciente con prioridad");
            System.out.println("3. Mostrar pacientes en espera (FIFO)");
            System.out.println("4. Atender siguiente paciente (FIFO)");
            System.out.println("5. Mostrar pacientes por prioridad");
            System.out.println("6. Atender paciente por prioridad");
            System.out.println("7. Mostrar historial de pacientes");
            System.out.println("8. Mostrar cantidad de atendidos");
            System.out.println("9. Salir");
            System.out.println("10. Buscar paciente en historial");
            System.out.println("11. Consultar ultima accion");
            System.out.println("12. Deshacer ultima accion");
            System.out.println("================================");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrarPaciente(false);
                    break;

                case 2:
                    registrarPaciente(true);
                    break;

                case 3:
                    mostrarFIFO();
                    break;

                case 4:
                    atenderFIFO();
                    break;

                case 5:
                    mostrarPrioridad();
                    break;

                case 6:
                    atenderPrioridad();
                    break;

                case 7:
                    mostrarHistorial();
                    break;

                case 8:
                    System.out.println(
                            "Pacientes atendidos: " + historial.size()
                    );
                    break;

                case 9:
                    System.out.println("Sistema finalizado.");
                    break;

                case 10:
                    buscarHistorial();
                    break;

                case 11:
                    if (!acciones.empty()) {
                        System.out.println("Ultima accion: "
                                + acciones.peek());
                    } else {
                        System.out.println("No hay acciones registradas.");
                    }
                    break;

                case 12:
                    if (!acciones.empty()) {
                        System.out.println("Accion retirada: "
                                + acciones.pop());
                    } else {
                        System.out.println("La pila esta vacia.");
                    }
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }

    static void registrarPaciente(boolean conPrioridad) {
        System.out.print("Cedula: ");
        String cedula = sc.nextLine();

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();

        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.print("Motivo de consulta: ");
        String motivo = sc.nextLine();

        int prioridad = 3;

        if (conPrioridad) {
            System.out.println("1. Alta");
            System.out.println("2. Media");
            System.out.println("3. Baja");
            System.out.print("Seleccione prioridad: ");
            prioridad = sc.nextInt();
            sc.nextLine();

            while (prioridad < 1 || prioridad > 3) {
                System.out.print("Ingrese 1, 2 o 3: ");
                prioridad = sc.nextInt();
                sc.nextLine();
            }
        }

        Paciente p = new Paciente(
                codigo++, cedula, nombre, edad, motivo, prioridad
        );

        if (conPrioridad) {
            colaPrioridad.offer(p);
        } else {
            colaFIFO.offer(p);
        }

        acciones.push("Registro del paciente: " + p.getNombre());

        System.out.println("Paciente registrado correctamente.");
    }

    static void mostrarFIFO() {
        if (colaFIFO.isEmpty()) {
            System.out.println("No hay pacientes en espera FIFO.");
            return;
        }

        System.out.println("\nPacientes en espera FIFO:");
        for (Paciente p : colaFIFO) {
            p.mostrarDatos();
        }

        System.out.println("Siguiente paciente: "
                + colaFIFO.peek().getNombre());
    }

    static void atenderFIFO() {
        Paciente p = colaFIFO.poll();

        if (p == null) {
            System.out.println("No hay pacientes para atender.");
            return;
        }

        historial.add(p);
        acciones.push("Atencion FIFO: " + p.getNombre());

        System.out.println("Paciente atendido:");
        p.mostrarDatos();
    }

    static void mostrarPrioridad() {
        if (colaPrioridad.isEmpty()) {
            System.out.println("No hay pacientes con prioridad.");
            return;
        }

        System.out.println("\nPacientes ordenados por prioridad:");

        PriorityQueue<Paciente> copia =
                new PriorityQueue<>(colaPrioridad);

        while (!copia.isEmpty()) {
            copia.poll().mostrarDatos();
        }
    }

    static void atenderPrioridad() {
        Paciente p = colaPrioridad.poll();

        if (p == null) {
            System.out.println("No hay pacientes para atender.");
            return;
        }

        historial.add(p);
        acciones.push("Atencion prioritaria: " + p.getNombre());

        System.out.println("Paciente atendido por prioridad:");
        p.mostrarDatos();
    }

    static void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("El historial esta vacio.");
            return;
        }

        System.out.println("\nHISTORIAL DE ATENCION:");

        for (Paciente p : historial) {
            p.mostrarDatos();
        }
    }

    static void buscarHistorial() {
        System.out.print("Ingrese codigo del paciente: ");
        int buscado = sc.nextInt();
        sc.nextLine();

        for (Paciente p : historial) {
            if (p.getCodigo() == buscado) {
                System.out.println("Paciente encontrado:");
                p.mostrarDatos();
                return;
            }
        }

        System.out.println("Paciente no encontrado en el historial.");
    }
}
