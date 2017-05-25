/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author xavimanzano
 */
public class Estadisticas {
    public int avgBaskets;
    public int avgAssists;
    public int avgRebounds;
    public int minBaskets;
    public int minAssists;
    public int minRebounds;
    public int maxBaskets;
    public int maxAssists;
    public int maxRebounds;


    public Estadisticas() {
    }

    @Override
    public String toString() {
        return "Estadisticas{" +
                "avgBaskets=" + avgBaskets +
                ", avgAssists=" + avgAssists +
                ", avgRebounds=" + avgRebounds +
                ", minBaskets=" + minBaskets +
                ", minAssists=" + minAssists +
                ", minRebounds=" + minRebounds +
                ", maxBaskets=" + maxBaskets +
                ", maxAssists=" + maxAssists +
                ", maxRebounds=" + maxRebounds +
                '}';
    }
}
