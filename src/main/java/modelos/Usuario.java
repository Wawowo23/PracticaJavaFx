package modelos;

public class Usuario {

    private int id;
    private int clave;
    private String nombre;

    // Constructor

    public Usuario(int id, int clave, String nombre) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Otros metodos
    public boolean login(int clave) {
        return this.clave == clave;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", clave=" + clave +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
