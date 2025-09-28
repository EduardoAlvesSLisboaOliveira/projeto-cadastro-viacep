package com.eduardo.cadastro;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);

        int sair = 1;

        while(sair != 0){
            System.out.println("""
            ------------MENU------------

            1. fazer cadastro
            2. ver pessoas cadastradas
            3. sair

            ----------------------------
            """);

            int opcaoMenu = sc.nextInt();
            sc.nextLine();

            switch (opcaoMenu) {
                case 1:
                    fazerCadastro(sc);
                    break;
                case 2:
                    verPessoasCadastradas();
                    break;
                case 3:
                    sair = 0;
                    break;
                default:
                    System.out.println("digite um valor valido!");
                    break;
            }
        }
        sc.close();
     }

    public static void fazerCadastro(Scanner sc) throws IOException, InterruptedException{
        String nome, email, cep, complemento;
        Endereco endereco;

        //  NOME
        System.out.println("digite seu nome:");
        nome = sc.nextLine();

        // EMAIL
        System.out.println("digite seu email:");
        email = sc.nextLine();
        while(!email.contains("@")){
            System.out.println("email invalido, digite novamente!");
            email = sc.nextLine();
        }

        //CEP
        System.out.println("digite seu CEP:");
        cep = sc.nextLine();
        while(cep.length() != 8){
            System.out.println("digite um cep valido!");
            cep = sc.nextLine();
        }

        //COMPLEMENTO
        System.out.println("digite o complemento:");
        complemento = sc.nextLine();
        try{
            endereco = requisicaoAPI(cep);
        }catch(RuntimeException | IOException | InterruptedException e) {
            System.out.println("Erro ao buscar o CEP: " + e.getMessage());
            System.out.println("cadastro cancelado!");
            return;
        }
        
        Pessoa pessoa = new Pessoa(nome, email, complemento, endereco);
        List<Pessoa> listaDePessoas = carregarPessoasDoArquivo();
        escreveNoArquivo(pessoa, listaDePessoas);

        System.out.println("cadastro realizado com sucesso!");

    }

    public static Endereco requisicaoAPI(String cep) throws IOException, InterruptedException{
        String enderecoAPI = "https://viacep.com.br/ws/" + cep + "/json/";
        
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(enderecoAPI))
                .build();

        //faz a http response
        HttpResponse<String> response = client
                .send(request, BodyHandlers.ofString()); 

                
        String json = response.body();
        System.out.println(json);

        //transforma de Gson para objeto
        Gson gson = new Gson();
        Endereco endereco = gson.fromJson(json, Endereco.class);
        return endereco;
    }


    public static void verPessoasCadastradas() throws IOException{
        
        List <Pessoa> listaDePessoas = carregarPessoasDoArquivo();

        if(listaDePessoas.isEmpty()){
            System.out.println("ainda nao houve nenhum cadastro.");
        }else{
            System.out.println("Pessoas cadastradas:");
            for(Pessoa p : listaDePessoas){
                System.out.println(".......................");
                System.out.println(p);
            }
        }
    }

    public static void escreveNoArquivo(Pessoa pessoa, List listaDePessoas) throws IOException{
        listaDePessoas.add(pessoa);

        //escreve no arquivo
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter("pessoas.json");
        gson.toJson(listaDePessoas, writer);
        writer.close();
    }

    public static List<Pessoa> carregarPessoasDoArquivo() throws FileNotFoundException{
        try(FileReader reader = new FileReader("pessoas.json")){

            Gson gson = new Gson();
            Type tipoLista = new TypeToken<ArrayList<Pessoa>>() {}.getType();
            List<Pessoa> listaDePessoas = gson.fromJson(reader, tipoLista);

            if(listaDePessoas == null){
            return new ArrayList<>();
            }

            return listaDePessoas;

        }catch(FileNotFoundException e){
            System.out.println("arquivo não encontrado, um novo será criado");
            return new ArrayList<>();
        }catch(IOException e){
            System.out.println("erro ao ler arquivo"+ e.getMessage());
            return new ArrayList<>();
        }
    }
}
