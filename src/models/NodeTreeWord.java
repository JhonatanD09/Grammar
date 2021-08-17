package models;

public class NodeTreeWord {
	
	private String data;
	private NodeTreeWord izq, der;
	
	public NodeTreeWord(String data) {
		this.data = data;
		this.izq = null;
		this.der = null;
	}
	
	public void insert(String dataIzq,String dataDer) {
		if (!dataIzq.equals("")) {			
			izq = new NodeTreeWord(dataIzq);
		}
		if (!dataDer.equals("")) {
			der = new NodeTreeWord(dataDer);
		}
	}
	
	public String getData() {
		return data;
	}
	
	public NodeTreeWord getDer() {
		return der;
	}
	
	public NodeTreeWord getIzq() {
		return izq;
	}
}
