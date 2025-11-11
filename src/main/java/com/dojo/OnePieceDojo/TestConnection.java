package com.dojo.OnePieceDojo;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/piratas";
        String user = "postgres";
        String password = "";

        try {
            // Carrega o driver (não é mais necessário com versões recentes do JDBC, mas é boa prática)
            Class.forName("org.postgresql.Driver");

            // Tenta estabelecer a conexão
            Connection conn = DriverManager.getConnection(url, user, password);

            // Se a conexão for estabelecida com sucesso, imprime uma mensagem
            if (conn != null) {
                System.out.println("Conexão com o banco de dados PostgreSQL estabelecida com sucesso!");
                conn.close(); // Fecha a conexão
            } else {
                System.out.println("Falha ao estabelecer conexão.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver PostgreSQL não encontrado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
