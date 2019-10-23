package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class Conexao {

	//Atributo de conexao
	public static Connection objConexao = null;
	
	//Criar conexao
	public static void obterConexao () {
		
		//Tentaviva
		try {
			//Dados do Banco
			String servidor = "localhost";
			String usuario = "root";
			String senha = "diogoluiz1995";
			String base = "java";
			
			//Efetuando conexao
			objConexao = DriverManager.getConnection("jdbc:mysql://"+servidor+"/"+base, usuario, senha);
			
			//Mensagem
			System.out.println("Conexao ok");
			
		} catch (Exception erroPadrao ) {
			System.out.println("Falha ao conectar"+erroPadrao.getMessage());
			
		
		}
	}
	
	//Remover conexao
	public static void removerConexao() {
		try {
			objConexao.close();
		} catch (SQLException e) {
			System.out.println("Falha ao remover a conexao");
		}
	}
}