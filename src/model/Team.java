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


public class Team {
    
    private String city;

    private String name;
    
    private LocalDate creation;

    public Team() {
    }

    public Team(String city, String name, LocalDate creation) {
        this.city = city;
        this.name = name;
        this.creation = creation;
    }

    
    public LocalDate getCreation() {
        return creation;
    }

    public void setCreation(LocalDate creation) {
        this.creation = creation;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}