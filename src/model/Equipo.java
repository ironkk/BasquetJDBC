package model;

import java.time.LocalDate;

/**
 *
 * @author ironkk
 */
public class Equipo {

   private String nombre;    
   
private String localidad;

private LocalDate creacion; 

    public Equipo(String nombre, String localidad, LocalDate creacion) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.creacion = creacion;
    }



   
    public Equipo(String nombre) {this.nombre = nombre;}

    public Equipo() {
    }


    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDate creacion) {
        this.creacion = creacion;
    }

    @Override
    public String toString() {
        return "Equipo{" + "localidad=" + localidad + ", nombre=" + nombre + ", creacion=" + creacion + '}';
    }


    
}
