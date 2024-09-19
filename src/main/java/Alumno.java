
public class Alumno{

    //ATRIBUTOS
    private String nombre;
    private String apellido;
    private String rut;
    private int edad;
    private int creditosAprobados;
    private Carrera carrera;


    //CONSTRUCTOR
    public Alumno(String nombre, String apellido,String rut, int edad, Carrera carrera) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.rut = rut;
        this.carrera = carrera;
        creditosAprobados = 0;
    }

    public Alumno(String nombre, String apellido, String rut, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.edad = edad;
        creditosAprobados = 0;
    }
    

    //GETTERS
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
    public int getEdad() {
        return edad;
    }

    public String getRut() {
        return rut;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public int getCreditosAprobados() {
        return creditosAprobados;
    }


    //SETTERS
    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
        else
            this.nombre = "nulo";
    }

    public void setApellido(String apellido) {
        if(apellido != null)
            this.apellido = apellido;
        else
            this.apellido = "nulo";
    }

    public void setEdad(int edad) {
        if(edad > 0)
            this.edad = edad;
        else
            this.edad = 0;
    }

    public void setRut(String rut) {
        if(rut != null)
            this.rut = rut;
        else
            this.rut = "nulo";
    }

    public void setCarrera(Carrera carrera) {
        if(carrera != null ) 
            this.carrera = carrera;
        else
            this.carrera = null;
    }

    public void setCreditosAprobados(int creditosAprobados){
        if(creditosAprobados > 0)
            this.creditosAprobados = creditosAprobados;
        else
            this.creditosAprobados = 0;
    }


    //METODOS 
    public void mostrar()
    { 
        System.out.println("Nombre del Alumno: " + nombre);
        System.out.println("Apellido del Alumno: " + apellido);
        System.out.println("Edad del Alumno: " + edad);
        System.out.println("Rut del Alumno: " + rut);
        System.out.println("Carrera del alumno: "+ carrera.getNombre());
    }

    public void mostrar(boolean simple){System.out.println("Nombre Completo: " + nombre + " " + apellido +  " | Carrera: " + carrera.getNombre() + " | Rut: " + rut);}

}

