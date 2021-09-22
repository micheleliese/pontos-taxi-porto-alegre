/*
 * Nome do projeto: Pontos de Taxi
 * Descrição: Cadastro descritivo e georreferenciado dos pontos de taxi.
 */
package com.mycompany.pontos.taxi;

/**
 *
 * @author Michele Liese
 */
public class Printer {
    /**
     * Printa um valor no console
     * @param value valor a ser impresso
     */
    public static void print(String value){
        System.out.println(value);
    }
    
    /**
     * Printa no console o cabeçalho das informações a serem impressas
     */
    public static void printCabecalho(){
        printDivider();
        System.out.printf("%20s| %10s| %55s| %20s| %40s| %10s| %20s| %22s| %22s| \n", "data_extracao", "codigo", "nome", "telefone", "logradouro", "numero", "latitude", "longitude", "Distancia");
        printDivider();
    }
    
    /**
     * Printa no console os registros das informações do csv
     * @param ponto 
     */
    public static void printRegistro(Ponto ponto){
        System.out.printf("%20s| %10s| %55s| %20s| %40s| %10s| %20s| %22s| %22s| \n", 
            ponto.getDataExtracao(), ponto.getCodigo(), ponto.getNome(), 
            ponto.getTelefone(), ponto.getLogradouro(), ponto.getNumero(), 
            ponto.getLatitude(), ponto.getLongitude(), ponto.getDistanciaFormatada()
        
        );
    }
    
    /**
     * Printa um divisor de informação
     */
    public static void printDivider(){
        print("====================================================================================================================================================================================================================================================");
    }
}
