package beans;

public class Produto {
	
	//Atributos
	private int idProduto;
	private String nomeProduto;
	private double valorProduto;
	public int getIdProduto() {
		return idProduto;
	}
	
	//Set e Get
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public double getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}
	
	

}
