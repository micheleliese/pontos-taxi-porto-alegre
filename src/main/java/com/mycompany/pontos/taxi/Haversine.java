/*
 * Nome do projeto: Pontos de Taxi
 * Descrição: Cadastro descritivo e georreferenciado dos pontos de taxi.
 */
package com.mycompany.pontos.taxi;

/**
 *
 * @author Michele Liese
 */
public class Haversine {
    
    /**
     * Converte grau para radiano
     * @param grau
     * @return 
     */
    private static double grauParaRadiano(double grau) {
        return (grau * Math.PI / 180.0);
    }
    
    /**
     * Converte radiano para grau
     * @param radiano
     * @return 
     */
    private static double radianoParaGrau(double radiano) {
        return (radiano * 180.0 / Math.PI);
    }
    
    /**
     * Calcula a distancia usando a formula de Haversine
     * @param latitude1 informação fornecida pelo usuario
     * @param longitude1 informação fornecida pelo usuario
     * @param latitude2 informação vinda do csv
     * @param longitude2 informação vinda do csv
     * @return 
     */
    public static double calcularDistancia(double latitude1, double longitude1, double latitude2, double longitude2) {
        double theta = longitude1 - longitude2;
        double distancia = 60 * 1.1515 * radianoParaGrau(Math.acos(Math.sin(grauParaRadiano(latitude1)) * Math.sin(grauParaRadiano(latitude2)) 
                + Math.cos(grauParaRadiano(latitude1)) * Math.cos(grauParaRadiano(latitude2)) * Math.cos(grauParaRadiano(theta))));
        return distancia;
    }
}
