package com.viacep2.demo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CadastroDoUsuario {
 public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);
           
    try {
    System.out.print("Digite o CEP: ");
    String cep = scanner.nextLine();
    String endereco = buscarEndereco(cep);
           
    if (endereco != null) {
    System.out.println("Endereço encontrado: " + endereco);
    System.out.print("Você confirma este endereço? (s/n): ");
    String confirmacao = scanner.nextLine();
           
     if (confirmacao.equalsIgnoreCase("s")) {
     System.out.print("Digite o número: ");
     String numero = scanner.nextLine();
     System.out.print("Digite o complemento: ");
     String complemento = scanner.nextLine();
           
      System.out.print("Digite seu nome: ");
      String nome = scanner.nextLine();
      System.out.print("Digite seu CPF: ");
      String cpf = scanner.nextLine();
      System.out.print("Digite seu telefone: ");
      String telefone = scanner.nextLine();
      System.out.print("Digite seu email: ");
      String email = scanner.nextLine();

      System.out.println("Cadastro realizado com sucesso!");
      System.out.println("Nome: " + nome);
      System.out.println("CPF: " + cpf);
      System.out.println("Telefone: " + telefone);
      System.out.println("Email: " + email);
      System.out.println("Endereço: " + endereco + ", " + numero + " - " + complemento);
         } else {
       System.out.println("Cadastro cancelado.");
           }
         } else {
       System.out.println("CEP não encontrado.");
       }
       } catch (Exception e) {
       System.out.println("Erro ao realizar o cadastro: " + e.getMessage());
      } finally {
       scanner.close();
           }
      }
           
    private static String buscarEndereco(String cep) {
    String urlString = "https://viacep.com.br/ws/" + cep + "/json/";
      try {
      URL url = new URL(urlString);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
           
     BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
     String inputLine;
     StringBuilder content = new StringBuilder();
     while ((inputLine = in.readLine()) != null) {
    content.append(inputLine);
      }
    in.close();
    conn.disconnect();
           
    JSONObject json = new JSONObject(content.toString());
    if (json.has("erro")) {
    return null;
     } else {
      return json.getString("logradouro") + ", " + json.getString("bairro") + ", " + json.getString("localidade") + " - " + json.getString("uf");
        }
     } catch (Exception e) {
      System.out.println("Erro ao buscar o endereço: " + e.getMessage());
      return null;
       }      
        } 
       
}
