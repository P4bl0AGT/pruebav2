import java.util.ArrayList;

public class Carrera {

    //ATRIBUTOS
    private String id;
    private String nombre;
    private int semestres;
    private int creditosTotales;
    private ArrayList<Asignatura> listaAsignaturas;


    //CONSTRUCTOR
    public Carrera() {}

    public Carrera(String id, String nombre, int semestres, int creditosTotales) {
        this.id = id;
        this.nombre = nombre;
        this.semestres = semestres;
        this.creditosTotales = creditosTotales;
        listaAsignaturas = new ArrayList<Asignatura>();
    }


    //SETTERS
    public void setId(String id) {
        if(id != null)
            this.id = id;
        else
            this.id = "nulo";
    }

    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
        else
            this.nombre = "nulo";
    }

    public void setSemestres(int semestres) {
        if(semestres != 0)
            this.semestres = semestres;
        else
            this.semestres = 0;
    }

    public void setAsignatura(Asignatura asignatura) {
        listaAsignaturas.add(asignatura);
    }
    

    public void setCreditosTotales(int creditosTotales) {
        if(creditosTotales >= 0)
            this.creditosTotales = creditosTotales;
        else
            this.creditosTotales = 0;
    }


    //GETTERS
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSemestres() {
        return semestres;
    }

    public Asignatura getAsignatura(int i) {
        return listaAsignaturas.get(i);
    }


    public int getCreditosTotales() {
        return creditosTotales;
    }

    //METODOS
    public void mostrar() {
        System.out.println("Id: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Semestres: " + semestres);
        System.out.println("Creditos Totales: " + creditosTotales);
        System.out.println("Asignaturas: ");
        System.out.print("    | ");
        for (Asignatura asignaturaActual:listaAsignaturas) {
            System.out.print(asignaturaActual.getNombre() + " | ");
        }
        System.out.println("");
    }

    public void mostrar(boolean simple){System.out.println("Nombre: " + nombre + " | Semestres " + semestres + " | ID: " + id);}


    public int cantidadAsignaturas() {
        return listaAsignaturas.size();
    }



    public Carrera copiarCarrera()
    {
        String idCopia = getId();
        String nombre = getNombre();
        int semestres = getSemestres();
        int  creditosTotales = getCreditosTotales();
        Carrera nuevaCarrera = new Carrera(idCopia, nombre, semestres, creditosTotales);

        for (int i = 0 ; i < listaAsignaturas.size(); i++) {
            Asignatura copiaAsignatura = listaAsignaturas.get(i);
            String codigo = copiaAsignatura.getCodigo();
            String nombreAsignatura = copiaAsignatura.getNombre();
            String profesor = copiaAsignatura.getProfesor();
            int creditos = copiaAsignatura.getCreditos();
            Asignatura nuevaAsignatura = new Asignatura(codigo, nombreAsignatura, profesor, creditos);

            nuevaCarrera.setAsignatura(nuevaAsignatura);
        }

        return nuevaCarrera;

    }
}
