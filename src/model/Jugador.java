/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author ironkk
 */
public class Jugador {
 private String nombre;
  private LocalDate fechaNacimiento;



private int numCanastas;

private int numAsistencias;

private int numRebotes;

private String posicion;

private Equipo equipo;

    public Jugador(String nombre, LocalDate fechaNacimiento, int numCanastas, int numAsistencias, int numRebotes, String posicion, Equipo equipo) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.numCanastas = numCanastas;
        this.numAsistencias = numAsistencias;
        this.numRebotes = numRebotes;
        this.posicion = posicion;
        this.equipo = equipo;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
    }



    public Jugador() {
    }
    
    

    public int getNumAsistencias() {
        return numAsistencias;
    }

    public void setNumAsistencias(int numAsistencias) {
        this.numAsistencias = numAsistencias;
    }

    public int getNumRebotes() {
        return numRebotes;
    }

    public void setNumRebotes(int numRebotes) {
        this.numRebotes = numRebotes;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getNumCanastas() {
        return numCanastas;
    }

    public void setNumCanastas(int numCanastas) {
        this.numCanastas = numCanastas;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{fechaNacimiento=" + fechaNacimiento + "," + "nombre=" + nombre + ",  numCanastas=" + numCanastas + ", equipo=" + equipo + '}';
    }


    
}
