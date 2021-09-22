/*
 * Nome do projeto: Pontos de Taxi
 * Descrição: Cadastro descritivo e georreferenciado dos pontos de taxi.
 */
package com.mycompany.pontos.taxi;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Michele Liese
 */
public class Main {
    
    // Lista para armazenar cada linha do csv 
    private static ArrayList<Ponto> pontos = new ArrayList<Ponto>();
    static double latitudeUsuario = 0, longitudeUsuario = 0;
    
    /**
     * @param args
     */
    public static void main(String args[]){
        readCSV();
        menu();
    }
    
    /**
     * Método responsável pela leitura do csv e montagem da lista de pontos
     */
    static public void readCSV() {

        String arquivoCSV = "C:\\Users\\miche\\Documents\\NetBeansProjects\\pontos-taxi\\src\\main\\java\\com\\mycompany\\pontos\\taxi\\pontos_taxi.csv";
        BufferedReader br = null;
        String linha = "";
        String csvDivisor = ";";
        boolean isFirtTurn = true;
        
        try {
            br = new BufferedReader(new FileReader(arquivoCSV));

            while ((linha = br.readLine()) != null) {
                if(!isFirtTurn){
                    String[] dados = linha.split(csvDivisor);

                    Ponto ponto = new Ponto();
                    
                    ponto.setDataExtracao(dados[0]);
                    ponto.setCodigo(dados[1]);
                    ponto.setNome(dados[2]);
                    ponto.setTelefone(dados[3]);
                    ponto.setLogradouro(dados[4]);
                    ponto.setNumero(dados[5]);
                    ponto.setLatitude(Double.parseDouble(dados[6].replace(",", ".").replace("\"", "")));
                    ponto.setLongitude(Double.parseDouble(dados[7].replace(",", ".").replace("\"", "")));
                    pontos.add(ponto);
                }
                isFirtTurn = false;
            }
        } catch (FileNotFoundException exception) {
            Printer.print(exception.toString());
        } catch (IOException exception) {
            Printer.print(exception.toString());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException exception) {
                    Printer.print(exception.toString());
                }
            }
        }
    }
    
    /**
     * Menu principal da aplicação
     */
    private static void menu(){
        String opcaoMenu;
        do {            
            Printer.print("\n=============== Menu ===============");
            Printer.print("1. Listar todos os pontos de taxi");
            Printer.print("2. Informar minha localização");
            Printer.print("3. Encontrar pontos próximos");
            Printer.print("4. Buscar pontos por logradouro");
            Printer.print("5. Terminar o programa\n");

            Printer.print("Digite a opção escolhida: ");
            Scanner scanner = new Scanner(System.in);
            opcaoMenu = scanner.nextLine();
            
            switch(opcaoMenu){
                case "1":
                    listarPontosTaxi();
                break;
                
                case "2":
                    boolean isValid = false;
                    while(isValid == false) {                        
                        isValid = informarLocalizacao();
                    }
                break;
                
                case "3":
                    encontrarPontosProximos();
                break;
                
                case "4":
                    buscarPontosPorLogradouro();
                break;
                
                case "5":
                    Printer.print("Fechando o Programa...");
                break;
                
                default:
                    Printer.print("!OPÇÃO INVÁLIDA!");                    
            }
        } while (!opcaoMenu.equals("5"));
    }
    
    /**
     * Lista todos os pontos de taxi lidos do csv
     */
    private static void listarPontosTaxi(){
        Printer.printCabecalho();
        pontos.forEach(ponto -> Printer.printRegistro(ponto));
    }
    
    /**
     * Responsável por solicitar e armazenar a latitude e longitude informados pelo usuário
     * @return 
     */
    private static boolean informarLocalizacao(){
        Scanner scanner = new Scanner(System.in);
        
        Printer.print("\n>>>>>>>> Informe sua localização <<<<<<<<");
        Printer.print("\n|Digite sua latitude: ");
        
        latitudeUsuario = Double.parseDouble(scanner.nextLine().replace(",", "."));
        if(latitudeUsuario < -90.00 || latitudeUsuario > 90.00){
            Printer.print("Latitude invalida! Valores permitidos no intervalo de: (-90.00 a 90.00)");
            return false;
        }
        Printer.print("\n|Digite sua longitude: ");        
        longitudeUsuario = Double.parseDouble(scanner.nextLine().replace(",", "."));
        if(longitudeUsuario < -180.00 || longitudeUsuario > 180.00){
            Printer.print("Longitude invalida! Valores permitidos no intervalo de: (-180.00 a 180.00)");
            return false;
        }
        
        Printer.print("\nLocalização armazenada!");
        Printer.printDivider();
                
        return true;
    }
    
    /**
     * Calcula a distância para cada ponto de táxi refente a localização do usuário.
     * Organiza em ordem crescente todos os pontos de táxi e exibe apenas os três primeiros (os mais próximos)
     */
    private static void encontrarPontosProximos(){
        ArrayList<Ponto> pontosOrdenadosPorDistancia = new ArrayList<>();
        pontos.forEach(ponto -> {
            double distancia  = Haversine.calcularDistancia(latitudeUsuario, longitudeUsuario, ponto.getLatitude(), ponto.getLongitude());
            ponto.setDistancia(distancia);
        });
        
        pontosOrdenadosPorDistancia.addAll(pontos);
        Collections.sort(pontosOrdenadosPorDistancia);
        
        Printer.print("\nOs pontos de taxi mais próximos são:");
        Printer.printCabecalho();
        for(int index = 0; index < 3; index++){
            Printer.printRegistro(pontosOrdenadosPorDistancia.get(index));
        }
    }
    
    /**
     * Solicita que o usuáro digite um logradouro e filtra todos os pontos pelo logradouro digitado.
     * Exibe todos os pontos que contém todo ou parte do logradouro fornecido pelo usuário.1
     */
    private static void buscarPontosPorLogradouro(){
        ArrayList<Ponto> pontosEncontrados = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        Printer.print("Digite o logradouro (Rua/Avenida/etc..): ");
        String busca = scanner.nextLine();
        
        pontos.forEach((ponto) -> {
           if(ponto.getLogradouro().toLowerCase().contains(busca.toLowerCase()) == true){
               pontosEncontrados.add(ponto);
           } 
        });
            
        if(!pontosEncontrados.isEmpty()){
            Printer.print("\nOs pontos de taxi ao longo de " + busca.toUpperCase() + " são:");
            Printer.printCabecalho();
            pontosEncontrados.forEach((ponto) -> Printer.printRegistro(ponto));
        }else{
            Printer.print("Não foram encontrados logradouros com este nome!");
        }
    }
}


