package modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Fichaje implements Comparable<Fichaje>{

    // Atributos
    private int id;
    private Usuario usuario;
    private String tipo;
    private LocalDateTime fechaHoraFichaje;

    // Constructor

    public Fichaje(int id, Usuario usuario, String tipo, LocalDateTime fechaHoraFichaje) {
        this.id = id;
        this.usuario = usuario;
        this.tipo = tipo;
        this.fechaHoraFichaje = fechaHoraFichaje;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaHoraFichaje() {
        return fechaHoraFichaje;
    }

    public void setFechaHoraFichaje(LocalDateTime fechaHoraFichaje) {
        this.fechaHoraFichaje = fechaHoraFichaje;
    }

    public LocalDate getFecha() {
        return fechaHoraFichaje.toLocalDate();
    }

    public LocalTime getHora() {
        return fechaHoraFichaje.toLocalTime();
    }

    @Override
    public String toString() {
        return "Fichaje{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", tipo='" + tipo + '\'' +
                ", fechaHoraFichaje=" + fechaHoraFichaje +
                '}';
    }

    @Override
    public int compareTo(Fichaje o) {
        return o.getFechaHoraFichaje().compareTo(this.fechaHoraFichaje);
    }
}
