package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import bd.Conexao;
import beans.Produto;

public class ProdutoSQL {

	// Cadastrar
	public static boolean cadastrar(Produto p) {

		// Validar metodo
		boolean valida = false;

		// Obter a conexao
		Conexao.obterConexao();

		// Tentativa
		try {
			String sql = "INSERT INTO produtos (nomeProduto, valorProduto) VALUES (?, ?)";

			PreparedStatement pstmt = Conexao.objConexao.prepareStatement(sql);
			pstmt.setString(1, p.getNomeProduto());
			pstmt.setDouble(2, p.getValorProduto());
			pstmt.execute();

			valida = true;
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
		} finally {
			Conexao.removerConexao();
		}

		// Retorno
		return valida;

	}

	// Metodo selecionar
	public static DefaultTableModel selecionar() {

		// DefaultTableModel
		DefaultTableModel dados = new DefaultTableModel();

		// Colunas
		dados.addColumn("Codigo");
		dados.addColumn("Produto");
		dados.addColumn("Valor");

		// Iniciar a conexao
		Conexao.obterConexao();

		// Obter os dados

		try {
			String sql = "SELECT * FROM produtos";

			Statement stmt = Conexao.objConexao.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dados.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getDouble(3) });
			}

		} catch (Exception erro) {
			System.out.println("Falha ao obter os Produtos" + erro.getMessage());

		} finally {
			Conexao.removerConexao();
		}

		// Retorno
		return dados;

	}

	// Remover
	static public void remover(int codigo) {

		// Conexao
		Conexao.obterConexao();

		// Tentativa
		try {
			String sql = "DELETE FROM produtos WHERE idProduto = ?";

			PreparedStatement pstmt = Conexao.objConexao.prepareStatement(sql);
			pstmt.setInt(1, codigo);
			pstmt.execute();
		} catch (Exception e) {
			System.out.println("Falha ao excluir");
		} finally {
			Conexao.removerConexao();
		}
	}
	
	//Alterar 
	public static void alterar(Produto p) {
		
		//Conexao
		Conexao.obterConexao();
		
		//Tentativas
		try {
			String sql = "UPDATE produtos SET nomeProduto=?, valorProduto=? WHERE idProduto=?";
			
			PreparedStatement pstmt = Conexao.objConexao.prepareStatement(sql);
			pstmt.setString(1, p.getNomeProduto());
			pstmt.setDouble(2, p.getValorProduto());
			pstmt.setInt(3, p.getIdProduto());
			
			pstmt.execute();
			
		} catch (Exception erro) {
			System.out.println("Falha ao alterar");
			
		}finally {
			Conexao.removerConexao();
		}
		
	}
}
