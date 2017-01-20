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
public class StatsByPosition {

    private String position;
    private int maxBaskets;
    private int minBaskets;
    private Double avgBaskets;

    private int maxAssists;
    private int minAssists;
    private Double avgAssists;

    private int maxRebounds;
    private int minRebounds;
    private Double avgRebounds;

    public StatsByPosition() {
    }

    public StatsByPosition(String position, int maxBaskets, int minBaskets, Double avgBaskets, int maxAssists, int minAssists, Double avgAssists, int maxRebounds, int minRebounds, Double avgRebounds) {
        this.position = position;
        this.maxBaskets = maxBaskets;
        this.minBaskets = minBaskets;
        this.avgBaskets = avgBaskets;
        this.maxAssists = maxAssists;
        this.minAssists = minAssists;
        this.avgAssists = avgAssists;
        this.maxRebounds = maxRebounds;
        this.minRebounds = minRebounds;
        this.avgRebounds = avgRebounds;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getMaxBaskets() {
        return maxBaskets;
    }

    public void setMaxBaskets(int maxBaskets) {
        this.maxBaskets = maxBaskets;
    }

    public int getMinBaskets() {
        return minBaskets;
    }

    public void setMinBaskets(int minBaskets) {
        this.minBaskets = minBaskets;
    }

    public Double getAvgBaskets() {
        return avgBaskets;
    }

    public void setAvgBaskets(Double avgBaskets) {
        this.avgBaskets = avgBaskets;
    }

    public int getMaxAssists() {
        return maxAssists;
    }

    public void setMaxAssists(int maxAssists) {
        this.maxAssists = maxAssists;
    }

    public int getMinAssists() {
        return minAssists;
    }

    public void setMinAssists(int minAssists) {
        this.minAssists = minAssists;
    }

    public Double getAvgAssists() {
        return avgAssists;
    }

    public void setAvgAssists(Double avgAssists) {
        this.avgAssists = avgAssists;
    }

    public int getMaxRebounds() {
        return maxRebounds;
    }

    public void setMaxRebounds(int maxRebounds) {
        this.maxRebounds = maxRebounds;
    }

    public int getMinRebounds() {
        return minRebounds;
    }

    public void setMinRebounds(int minRebounds) {
        this.minRebounds = minRebounds;
    }

    public Double getAvgRebounds() {
        return avgRebounds;
    }

    public void setAvgRebounds(Double avgRebounds) {
        this.avgRebounds = avgRebounds;
    }

    @Override
    public String toString() {
        return "StatsByPosition{" + "position=" + position + ", maxBaskets=" + maxBaskets + ", minBaskets=" + minBaskets + ", avgBaskets=" + avgBaskets + ", maxAssists=" + maxAssists + ", minAssists=" + minAssists + ", avgAssists=" + avgAssists + ", maxRebounds=" + maxRebounds + ", minRebounds=" + minRebounds + ", avgRebounds=" + avgRebounds + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.position);
        hash = 67 * hash + this.maxBaskets;
        hash = 67 * hash + this.minBaskets;
        hash = 67 * hash + Objects.hashCode(this.avgBaskets);
        hash = 67 * hash + this.maxAssists;
        hash = 67 * hash + this.minAssists;
        hash = 67 * hash + Objects.hashCode(this.avgAssists);
        hash = 67 * hash + this.maxRebounds;
        hash = 67 * hash + this.minRebounds;
        hash = 67 * hash + Objects.hashCode(this.avgRebounds);
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
        final StatsByPosition other = (StatsByPosition) obj;
        if (this.maxBaskets != other.maxBaskets) {
            return false;
        }
        if (this.minBaskets != other.minBaskets) {
            return false;
        }
        if (this.maxAssists != other.maxAssists) {
            return false;
        }
        if (this.minAssists != other.minAssists) {
            return false;
        }
        if (this.maxRebounds != other.maxRebounds) {
            return false;
        }
        if (this.minRebounds != other.minRebounds) {
            return false;
        }
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        if (!Objects.equals(this.avgBaskets, other.avgBaskets)) {
            return false;
        }
        if (!Objects.equals(this.avgAssists, other.avgAssists)) {
            return false;
        }
        if (!Objects.equals(this.avgRebounds, other.avgRebounds)) {
            return false;
        }
        return true;
    }
    
    
    

}
