import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Instituto
{
    //ATRIBUTOS
    private ArrayList<Alumno> listaAlumnos;
    private HashMap<String,Alumno> mapaAlumnos;
    private ArrayList<Carrera> listaCarreras;
    private HashMap<String,Carrera> mapaCarreras;


    //CONSTRUCTOR
    public Instituto() {
        listaAlumnos = new ArrayList<>();
        mapaAlumnos = new HashMap<>();
        listaCarreras = new ArrayList<>();
        mapaCarreras = new HashMap<>();
    }


    //SETTERS
    public void setAlumno(Alumno alumno) {
        listaAlumnos.add(alumno);
        mapaAlumnos.put(alumno.getRut(), alumno);
    }
    
    public void setCarrera(Carrera carrera) {
        listaCarreras.add(carrera);
        mapaCarreras.put(carrera.getId(), carrera);
    }

    public Alumno getAlumno(int i) {
        return listaAlumnos.get(i);
    }

    public Carrera getCarrera(int i) {
        return listaCarreras.get(i);
    }


    //METODOS
    public void agregarAlumno(Alumno nuevoAlumno) {
        if(mapaAlumnos.get(nuevoAlumno.getRut()) == null)
        {
            listaAlumnos.add(nuevoAlumno);
            mapaAlumnos.put(nuevoAlumno.getRut(), nuevoAlumno);
        }
    }

    public int cantidadAlumnos() {
        return listaAlumnos.size();
    }

    public void agregarCarrera(Carrera nuevaCarrera) {
        if(mapaCarreras.get(nuevaCarrera.getId()) == null)
        {
            listaCarreras.add(nuevaCarrera);
            mapaCarreras.put(nuevaCarrera.getId(), nuevaCarrera);
        }
    }

    public int cantidadCarreras() {
        return listaCarreras.size();
    }

    public void mostrarAlumnos() {
        System.out.println("Lista Alumnos");
        System.out.println("* * * * * * *");
        for(int i = 0 ; i < listaAlumnos.size() ; i++)
        {
            Alumno alumnoActual = (Alumno) listaAlumnos.get(i);
            alumnoActual.mostrar(true);
        }
    }

    public void mostrarCarreras() {
        System.out.println("Lista Carreras");
        System.out.println("* * * * * * *");
        for(int i = 0 ; i < listaCarreras.size() ; i++)
        {
            Carrera carreraActual = (Carrera) listaCarreras.get(i);
            carreraActual.mostrar(true);
        }
    }

    public void buscarAlumnosRut(BufferedReader lector) throws IOException {
        System.out.println("Porfavor ingresar el RUT del alumno a buscar");
        String rut = lector.readLine();

        Alumno alumnoBuscado = mapaAlumnos.get(rut);
        if(alumnoBuscado != null)
            alumnoBuscado.mostrar();
        else
            System.out.println("No se encuentra el alumno con el rut ingresado");
    }

    public void buscarCarreradId(BufferedReader lector) throws IOException {
        System.out.println("Porfavor ingresar el ID de la carrera a buscar");
        String id = lector.readLine();

        Carrera carreraBuscada = mapaCarreras.get(id);
        if(carreraBuscada != null)
            carreraBuscada.mostrar();
        else
            System.out.println("No se encuentra la carrera con el id ingresado");
    }

    public void buscarAlumnosPorCarrera(BufferedReader lector)throws IOException{
        System.out.println("Porfavor ingresar la Carrera a buscar: ");
        String carrera = lector.readLine();
        Alumno alumnoCarrera;
        boolean hayAlumnosCarrera = false;
        
        if (listaAlumnos.size() != 0) {
            for (int i = 0; i < listaAlumnos.size() ; i++)
            {
                alumnoCarrera = listaAlumnos.get(i);

                if (alumnoCarrera.getCarrera().getNombre().equals(carrera)) {
                    alumnoCarrera.mostrar();
                    System.out.println("");
                    hayAlumnosCarrera = true;
                }
            }

            if (!hayAlumnosCarrera)
                System.out.println("No hay alumnos inscritos en la carrera");
        }
        else
            System.out.println("No hay alumnos inscritos en el instituto");
    }

    public void cargarCsvCarreras(BufferedReader lectorCsv) throws IOException{
        String linea;

        while((linea = lectorCsv.readLine()) != null) {
            
            if (linea.trim().startsWith("#")) {
                
                //leer carreras
                linea = lectorCsv.readLine();
                String[] datosCarrera = linea.split(",");
                String idCarrera = datosCarrera[0];
                String nombreCarrera = datosCarrera[1];
                int semestresCarrera = Integer.parseInt(datosCarrera[2]);
                int creditosCarrera = Integer.parseInt(datosCarrera[3]);

                Carrera carrera = new Carrera(idCarrera, nombreCarrera, semestresCarrera, creditosCarrera);

                //leer asignaturas
                for (int i = 0; i < 10; i++) {
                    linea = lectorCsv.readLine();
                    String[] datosAsignatura = linea.split(",");


                    String codigoAsignatura = datosAsignatura[0];
                    String nombreAsignatura = datosAsignatura[1];
                    String profesorAsignatura = datosAsignatura[2];
                    int creditosAsignatura = Integer.parseInt(datosAsignatura[3].trim());

                    Asignatura asignatura = new Asignatura(codigoAsignatura, nombreAsignatura, profesorAsignatura, creditosAsignatura);

                    carrera.setAsignatura(asignatura);
                }

                //agregar carrera a mapa y lista de clase instituto
                listaCarreras.add(carrera);
                mapaCarreras.put(carrera.getId(), carrera);
            }
        }
    }

    public void cargarCsvAlumnos(BufferedReader lectorCsv) throws IOException{
        String linea;
        int talla = listaCarreras.size();
        int posRandom;

        while((linea = lectorCsv.readLine()) != null) {
            String[] datos = linea.split(",");
            String nombre = datos[0];
            String apellido = datos[1];
            String rut = datos[2];
            int edad = Integer.parseInt(datos[3].trim());

            Alumno alumno = new Alumno(nombre, apellido, rut, edad);
            
            Random random = new Random();
            posRandom = random.nextInt(talla);  // entre [0 y talla[
            alumno.setCarrera(listaCarreras.get(posRandom));

            //agregar alumno a mapa y lista de clase instituto
            listaAlumnos.add(alumno);
            mapaAlumnos.put(alumno.getRut(), alumno);
        }
    }

    public void actualizacionEstado(BufferedReader lector)throws IOException{
        
        int creditosAprobados = 0;
        System.out.println("Hola podrias darme el rut del Alumno al cual actualizar su estado");
        String rut = lector.readLine();

        Alumno alumnoBuscado = mapaAlumnos.get(rut);

        if (alumnoBuscado == null){
            System.out.println("Alumno NO encontrado");
            return;
        }
        System.out.println("Alumno : " + alumnoBuscado.getNombre() + " " + alumnoBuscado.getApellido());
        
        Carrera carreraAlumno = alumnoBuscado.getCarrera();

        System.out.println("Deseas actualizar los estados de asignaturas");
        System.out.println("1.- SI");
        System.out.println("2.- NO");
        String opcion = lector.readLine();

        if (Integer.parseInt(opcion) == 2)
        {
            System.out.println("Muchas Gracias");
            return;
        }
        else{

            for (int i = 0 ; i < carreraAlumno.cantidadAsignaturas() ; i++)
            {
                Asignatura asignatura = carreraAlumno.getAsignatura(i);

                System.out.println("Porfavor selecciona una opcion para ver si cursaste o ya aprobaste la asignatura de : " + asignatura.getNombre());
                System.out.println("1.- APROBADO");
                System.out.println("2.- PENDIENTE");
                String estadoActualizado = lector.readLine();

                if (Integer.parseInt(estadoActualizado) == 1)
                {
                    asignatura.setEstado(true);
                    creditosAprobados += asignatura.getCreditos();
                }
                
            }   

            System.out.println("Tus estados de asignatura han sido actualizados con exito");
        }
        alumnoBuscado.setCreditosAprobados(creditosAprobados);

    }

    public void mostrarEstadoAsignaturas(BufferedReader lector)throws IOException{
                
        System.out.println("Hola podrias darme el rut del Alumno al cual actualizar su estado");
        String rut = lector.readLine();

        Alumno alumnoBuscado = mapaAlumnos.get(rut);

        if (alumnoBuscado == null){
            System.out.println("Alumno NO encontrado");
            return;
        }
        System.out.println("Alumno : " + alumnoBuscado.getNombre() + " " + alumnoBuscado.getApellido());
        
        Carrera carreraAlumno = alumnoBuscado.getCarrera();

        for (int i = 0 ; i < carreraAlumno.cantidadAsignaturas() ; i++)
            {
                Asignatura asignatura = carreraAlumno.getAsignatura(i);
                
                if(asignatura.getEstado() == true)
                {
                    System.out.println("La asignatura " + asignatura.getNombre() + " ya la has APROBADO felicidades");
                }
                else{
                    System.out.println("La asignatura " + asignatura.getNombre() + " aun esta PENDIENTE");
                }   
            }   
            System.out.println("Creditos totales: " + alumnoBuscado.getCreditosAprobados());
        }

    }
