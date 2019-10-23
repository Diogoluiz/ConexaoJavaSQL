package formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.Conexao;
import beans.Produto;
import dao.ProdutoSQL;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;

public class FormularioInicial extends JFrame {

	//Variaveis
	private int codigo;
	
	//Componentes
	private JPanel contentPane;
	private JTextField txtProduto;
	private JTextField txtValor;
	private JTable tabelaProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioInicial frame = new FormularioInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormularioInicial() {
		
		//Caracteristicas de formulario
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Tabela
		tabelaProduto = new JTable(ProdutoSQL.selecionar());
		
		//Barra de rolagem da tabela
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 151, 408, 254);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(tabelaProduto);		
		
		//Campo produto
		txtProduto = new JTextField();
		txtProduto.setBounds(127, 19, 209, 20);
		contentPane.add(txtProduto);
		txtProduto.setColumns(10);
		
		txtValor = new JTextField();
		txtValor.setBounds(127, 50, 209, 20);
		contentPane.add(txtValor);
		txtValor.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obter os dados informados
				String nomeProduto = txtProduto.getText();
				double valorProduto = Double.parseDouble(txtValor.getText());
				
				//Instanciar um objeto da classe Produto
				Produto p = new Produto();
				p.setNomeProduto(nomeProduto);
				p.setValorProduto(valorProduto);
				
				//Executar o metodo de cadastro
				boolean retorno = ProdutoSQL.cadastrar(p);
				
				//Atualizar formulario
				atualizarFormulario();
				
				//Mensagem
				if(retorno) {
					JOptionPane.showMessageDialog(null, "Produto Cadastrado");
				}else {
					JOptionPane.showMessageDialog(null, "Falha ao Cadastrar");
				}
				
				//Cursor selecionado no campo Produto
				txtProduto.requestFocus();
			}
		});
		btnCadastrar.setBounds(10, 89, 96, 23);
		contentPane.add(btnCadastrar);
		
		//Rotulo valor
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(36, 19, 70, 14);
		contentPane.add(lblProduto);
		
		//Rotulo Valor
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(36, 53, 48, 14);
		contentPane.add(lblValor);
		
		//Botao Alterar
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Objeto
				Produto p = new Produto();
				p.setIdProduto(codigo);
				p.setNomeProduto(txtProduto.getText());
				p.setValorProduto(Double.parseDouble(txtValor.getText()));
				
				//Alterar Produto
				ProdutoSQL.alterar(p);
				
				//Atualizar Formulario
				atualizarFormulario();
				
				//Mensagem
				JOptionPane.showMessageDialog(null, "Produto alterado");
			}
		});
		btnAlterar.setEnabled(false);
		btnAlterar.setBounds(116, 89, 89, 23);
		contentPane.add(btnAlterar);
		
		//Botao Excluir
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Conexao.obterConexao();
				
				//Excluir o produto
				ProdutoSQL.remover(codigo);
				
				//Atualizar Formulario
				atualizarFormulario();
				
				//Mensagem
				JOptionPane.showMessageDialog(null, "Produto removido");
				
				//Voltar o cursor no campo produto
				txtProduto.requestFocus();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(218, 89, 89, 23);
		contentPane.add(btnExcluir);
		
		//Botao cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Limpar os campos
				txtProduto.setText("");
				txtValor.setText("");
				
				//Botoes
				btnCadastrar.setEnabled(true);
				btnAlterar.setEnabled(false);
				btnExcluir.setEnabled(false);
				btnCancelar.setEnabled(false);
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(320, 89, 89, 23);
		contentPane.add(btnCancelar);
		
		//Açao da tabelaProduto
		tabelaProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Obter Linha
				int linha = tabelaProduto.getSelectedRow();
				
				//Atribuir Valores
				codigo = Integer.parseInt(tabelaProduto.getValueAt(linha, 0).toString());
				txtProduto.setText(tabelaProduto.getValueAt(linha, 1).toString());
				txtValor.setText(tabelaProduto.getValueAt(linha, 2).toString());
				
				//Botoes
				btnCadastrar.setEnabled(false);
				btnAlterar.setEnabled(true);
				btnExcluir.setEnabled(true);
				btnCancelar.setEnabled(true);
			}
		});
			
	}
	
	private void atualizarFormulario() {
		//Atualizar a tabela de produtos
		tabelaProduto.setModel(ProdutoSQL.selecionar());
		
		//Limpar os campos
		txtProduto.setText("");
		txtValor.setText("");
	}
	
	
}
