/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author ironkk
 */
public class Ranking {
    private String name;
    private int nBaskets;
    private int max;

    public Ranking() {
    }

    public Ranking(String name, int nBaskets, int max) {
        this.name = name;
        this.nBaskets = nBaskets;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getnBaskets() {
        return nBaskets;
    }

    public void setnBaskets(int nBaskets) {
        this.nBaskets = nBaskets;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Ranking{" + "name=" + name + ", nBaskets=" + nBaskets + ", max=" + max + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + this.nBaskets;
        hash = 89 * hash + this.max;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ranking other = (Ranking) obj;
        if (this.nBaskets != other.nBaskets) {
            return false;
        }
        if (this.max != other.max) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
}
