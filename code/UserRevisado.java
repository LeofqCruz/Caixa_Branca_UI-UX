package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserRevisado {
   public Connection conectarDB() {
      Connection conn = null; //A conexão é aberta, mas o fechamento é de responsabilidade do chamador (verificarUsuario), o que não é feito corretamente
      try {
         Class.forName("com.mysql.Driver.Manager").newInstance();
         String url = "jdbc:mysql://127.0.0.1/test?user=lopes&&password=123";
         conn = DriverManager.getConnection(url);
      } catch (Exception e){ }  //Este bloco catch vazio "engole" (silencia) a exceção,impedindo a depuração e podendo retornar 'conn = null' sem aviso.
      return conn;}
   public String nome = "";
   public boolean result = false;
   public boolean verificarUsuario(String login, String senha) {
      String sql = "";  
      Connection conn = conectarDB();
      //INSTRUÇÃO SQL
      sql += "select nome from usuarios ";
      sql += "where login = " + " ' " + login + " ' ";  // A construção da String SQL por concatenação é perigosa e de baixa legibilidade.
      sql += " and senha = " + " ' " + senha + " ' ";
      try {       // Se 'conn' for null (devido a falha no conectarDB), a linha abaixo gerará um NullPointerException, que será engolido no bloco catch, tornando o erro invisível.
         Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(sql);
         if (rs.next()) {
            result = true;
            nome = rs.getString("nome");} // O ResultSet (rs), o Statement (st) e a Connection (conn) não são fechados aqui, causando vazamento de recursos. O fechamento deve ocorrer em um bloco 'finally'.    
      } catch (Exception e) { } // O bloco catch vazio silencia todos os erros de execução, desde SQL inválido até o NullPointerException.
      return result;}
   }//fim da classe