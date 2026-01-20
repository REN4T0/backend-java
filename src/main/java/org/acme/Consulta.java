package org.acme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class Consulta {

    static String DBLength() {
        String url = "jdbc:postgresql://localhost:5432/petshop";
        String user = "postgres";
        String pass = "renato";

        try (Connection conn = DriverManager.getConnection(url, user, pass)){
            Statement menssager = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM dogs";
            ResultSet response = menssager.executeQuery(sql);

            if(response.next()){
                String qtd = response.getString(1);
                return qtd;
            }

        } catch (Exception err){
            String errorLog = "Erro: " + err;
            return errorLog;
        }
        return "Algo de errado aconteceu";
    }

    public static void main(String args[]){
        // As informações que eu preciso para me conectar ao banco de dados.
        String url = "jdbc:postgresql://localhost:5432/petshop";
        String user = "postgres";
        String pass = "renato";

        // Abrindo uma seassão para interação com o banco de dados. O try, nesse contexto, abre e fecha a comunicação com o banco de dados, depois que a operação requisitada é realizada.
        try (Connection conn = DriverManager.getConnection(url, user, pass)){

            // O statement prepara um argumento SQL para ser executado (lido) pelo  banco.
            Statement menssager = conn.createStatement();

            // Argumento SQL para ser executado no banco.
            String sql = "SELECT * FROM dogs";

            // Essa variável recebe a resposta direta do SQL que, ao que parece, vem em um formato de matriz.
            ResultSet response = menssager.executeQuery(sql);
            //System.out.println(menssager.executeQuery("SELECT COUNT(*) FROM dogs").getString(1));
            System.out.println(Integer.parseInt(DBLength()));

            String[] row = new String[4];
            //String[] lib = new String[];
            while(response.next()){
                for (int i = 1; i <= 4; i++){
                    row[i - 1] = response.getString(i);
                }

                System.out.println(Arrays.toString(row));
            }

            /* while(response.next()){
                for (int i = 1; i <= 4; i++) {
                    System.out.print(response.getString(i));
                    System.out.print(" | ");
                }

                System.out.print("\n");
            } */

        } catch (Exception err){
            System.out.println("Error - " + err.getMessage());
        }
    }
}
