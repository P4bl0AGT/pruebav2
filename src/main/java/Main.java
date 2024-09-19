import java.io.*;

public class Main {

    public static void leerAlumno(BufferedReader lector, Instituto instituto) throws IOException {
        System.out.print("Ingrese nombre: ");
        String nombre = lector.readLine();
    
        System.out.print("Ingrese apellido: ");
        String apellido = lector.readLine();
    
        System.out.print("Ingrese edad: ");
        int edad = Integer.parseInt(lector.readLine());
    
        System.out.print("Ingrese rut: ");
        String rut = lector.readLine();
    
        System.out.println("Ingrese carrera: ");
        int talla = instituto.cantidadCarreras();
        for (int i = 0; i < talla; i++) {
            System.out.println("  » [" + i +"] " + instituto.getCarrera(i).getNombre());
        }
        
        //validar lectura
        int indice = -1;
        while (indice < 0 || indice >= talla) {
            indice = Integer.parseInt(lector.readLine());
        }
        
        Carrera carrera = instituto.getCarrera(indice).copiarCarrera();
    
        Alumno alumno = new Alumno(nombre, apellido, rut, edad, carrera);
    
        instituto.agregarAlumno(alumno);
    
        System.out.println("********************");
    }


    public static void leerCarrera(BufferedReader lector, Instituto instituto) throws IOException {

        System.out.print("Ingrese id: ");
        String id = lector.readLine();
    
        System.out.print("Ingrese nombre: ");
        String nombre = lector.readLine();
    
        System.out.print("Ingrese semestres: ");
        int semestres = Integer.parseInt(lector.readLine());
    
        System.out.print("Ingrese creditos: ");
        int creditos = Integer.parseInt(lector.readLine());

        Carrera carrera = new Carrera(id, nombre, semestres, creditos);
    
        System.out.print("Ingrese total asignaturas: ");
        int talla = Integer.parseInt(lector.readLine());

        //validar lectura
        while (talla < 1) {
            System.out.print("Reingrese: ");
            talla = Integer.parseInt(lector.readLine());
        }
        
        //leer cada asignatura de la carrera actual
        for (int i = 0; i < talla; i++) {
            leerAsignatura(lector, carrera, i+1);
        }
    
        instituto.agregarCarrera(carrera);
    
        System.out.println("********************");
    }

    public static void leerAsignatura(BufferedReader lector, Carrera carrera, int i) throws IOException {
        System.out.println("== Asignatura: " + i + "==");
        System.out.print("Ingrese codigo: ");
        String codigo = lector.readLine();
    
        System.out.print("Ingrese nombre: ");
        String nombre = lector.readLine();

        System.out.print("Ingrese profesor: ");
        String profesor = lector.readLine();
    
        System.out.print("Ingrese creditos: ");
        int creditos = Integer.parseInt(lector.readLine());

        Asignatura asignatura = new Asignatura(codigo, nombre, profesor, creditos);

        carrera.setAsignatura(asignatura);
    }
    
    public static void mostrarMenu() {
        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║   [1] Agregar alumno            ║");
        System.out.println("║   [2] Mostrar alumnos           ║");
        System.out.println("║   [3] Agregar carrera           ║");
        System.out.println("║   [4] Mostrar carreras          ║");
        System.out.println("║   [5] Buscar alumno rut         ║");
        System.out.println("║   [6] Buscar carrera id         ║");
        System.out.println("║   [7] Buscar alumnos carrera    ║");
        System.out.println("║   [8] Actualizar Estado         ║");
        System.out.println("║   [9] Mostrar Estado Asignatura ║"); 
        System.out.println("║   [0] Salir                     ║");
        System.out.println("╚═════════════════════════════════╝");
    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void presionaParaContinuar(BufferedReader lector) throws IOException {
        System.out.println("Presione ENTER para continuar");  
        lector.readLine();  
    }
    
    public static void main(String[] args) throws IOException{
        limpiarPantalla();

        //Instituto clase principal
        Instituto instituto = new Instituto();
        
        //Lector
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        
        //Cargar
        BufferedReader lectorCarreras = new BufferedReader(new FileReader("datos/carreras.csv"));
        instituto.cargarCsvCarreras(lectorCarreras);
        lectorCarreras.close();
        BufferedReader lectorAlumnos = new BufferedReader(new FileReader("datos/alumnos.csv"));
        instituto.cargarCsvAlumnos(lectorAlumnos);
        lectorAlumnos.close();


        while (true) {
            limpiarPantalla();
            mostrarMenu();
            System.out.print("Seleccionar opcion: ");
            int opcion = Integer.parseInt(lector.readLine());
            System.out.println("");

            if (opcion == 1) {
                limpiarPantalla();
                leerAlumno(lector, instituto);
            } 
            else if (opcion == 2) {
                limpiarPantalla();
                instituto.mostrarAlumnos();
                System.out.println("");
            }
            else if (opcion == 3) {
                limpiarPantalla();
                leerCarrera(lector, instituto);
            } 
            else if (opcion == 4) {
                limpiarPantalla();
                instituto.mostrarCarreras();
                System.out.println("");
            }
            else if (opcion == 5) {
                limpiarPantalla();
                instituto.buscarAlumnosRut(lector);
                System.out.println("");
            } 
            else if (opcion == 6) {
                limpiarPantalla();
                instituto.buscarCarreradId(lector);
                System.out.println("");
            } 
            else if (opcion == 7) {
                limpiarPantalla();
                instituto.buscarAlumnosPorCarrera(lector);
                System.out.println("");
            } 
            else if (opcion == 8){
                limpiarPantalla();
                instituto.actualizacionEstado(lector);
                System.out.println("");
            }
            else if (opcion == 9){
                limpiarPantalla();
                instituto.mostrarEstadoAsignaturas(lector);
                System.out.println("");
            }
            else if (opcion == 0) {
                limpiarPantalla();
                lector.close();
                System.exit(0);
            } 
            else {
                System.out.println("Opcion no valida");
                System.out.println("");
            }
            presionaParaContinuar(lector);
            limpiarPantalla();
        }

    }
}


