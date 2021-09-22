/*
 * Nome do projeto: Pontos de Taxi
 * Descrição: Cadastro descritivo e georreferenciado dos pontos de taxi.
 */
package com.mycompany.pontos.taxi;

import java.text.DecimalFormat;

/**
 *
 * @author Michele Liese
 */
public class Ponto implements Comparable<Ponto>{
    private String data_extracao;
    private String codigo;
    private String nome;
    private String telefone;
    private String logradouro;
    private String numero;
    private double latitude;
    private double longitude;
    private double distancia;

    /**
     * @return retorna data de extração
     */
    public String getDataExtracao() {
        return data_extracao;
    }

    /**
     * @param data_extracao seta data de extração
     */
    public void setDataExtracao(String data_extracao) {
        this.data_extracao = data_extracao;
    }

    /**
     * @return retorna o codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo seta o codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return retorna o nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome seta o nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return retorna o telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone seta o telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return retorna o logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro seta o logradouro
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return retorna o numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero seta o numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return retorna a latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude seta a latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return retorna a longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude seta a longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return retorna a distancia 
     */
    public double getDistancia() {
        return distancia;
    }
    
    /**
     * 
     * @param distancia seta a distancia
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    /**
     * 
     * @return distancia formatada
     */
    public String getDistanciaFormatada() {
        if(distancia < 1.0){
            DecimalFormat formato = new DecimalFormat("###");      
            return String.valueOf(formato.format(distancia) + " m");
        }else{
            DecimalFormat formato = new DecimalFormat("#.##");      
            return String.valueOf(formato.format(distancia) + " Km");
        }
        
    }
    
    /**
     * Sobrescrita do método compareTo para comparar distancias
     * @param ponto
     * @return 
     */
    @Override
    public int compareTo(Ponto ponto) {
        if (this.distancia < ponto.getDistancia()) { 
            return -1; 
        } if (this.distancia > ponto.getDistancia()) { 
            return 1; 
        } 
        return 0; 
    }
    
}
