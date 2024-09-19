public class Asignatura
{
    //ATRIBUTOS
    private String codigo;
    private String nombre;
    private String profesor;
    private int creditos;
    private boolean estado; //false = no aprobada/no tomada, true = aprobada.


    //CONSTRUCTOR
    public Asignatura(){}
    public Asignatura(String codigo, String nombre, String profesor, int creditos)
    {
        setNombre(nombre);
        setCodigo(codigo);
        setProfesor(profesor);
        setCreditos(creditos);
        this.estado = false;
    }

    //SETTERS
    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
        else
            this.nombre = "nulo";
    }

    public void setCodigo(String codigo) {
        if(codigo != null)
            this.codigo = codigo;
        else
            this.codigo = "nulo";
    }

    public void setProfesor(String profesor) {
        if(profesor != null)
            this.profesor = profesor;
        else
            this.profesor = "no hay profesor a cargo";
    }

    public void setCreditos(int creditos) {
        if(creditos >= 0)
            this.creditos = creditos;
        else
            this.creditos = 0;
    }

    public void setEstado(boolean estado){
        this.estado = true;
    }


    //GETTERS
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public String getProfesor() {
        return profesor;
    }

    public boolean getEstado(){
        return estado;
    }


    //METODOS
    public void mostrar()
    {
        System.out.println("Nombre asignatura: " + nombre);
        System.out.println("Codigo asignatura: " + codigo);
        System.out.println("Profesor asignatura: " + profesor);
        System.out.println("Creditos asignatura: " + creditos);
        System.out.println("Estado asignatura: " + estado);
    }
    
    public void mostrar(boolean simple){System.out.println("Nombre Asignatura: " + nombre + " | Profesor : " + profesor);}
}
