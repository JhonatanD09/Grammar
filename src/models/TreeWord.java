package models;

public class TreeWord {

	private NodeTreeWord root;
	private NodeTreeWord actual;
	private int altura;

	public TreeWord() {
		altura = 0;
		root = null;
	}

	public void add(String node, String dataIzq, String dataDer, String puntero) {
		if (root == null) {
			root = new NodeTreeWord(puntero);
			actual = root;
		} else {
				actual.insert(dataIzq, dataDer);
				if (getNoTerminal(actual, puntero) != null) {
					actual = getNoTerminal(actual, puntero);
				}else {
					actual.insert(dataIzq, dataDer);					
			}
		}
	}

	public NodeTreeWord getNoTerminal(NodeTreeWord nodeTreeWord, String puntero) {
		if (nodeTreeWord.getDer() != null && nodeTreeWord.getDer().getData().equals(puntero)) {
			return nodeTreeWord.getDer();
		} else if (nodeTreeWord.getIzq() != null) {
			return nodeTreeWord.getIzq();
		}
		return null;
	}

	public NodeTreeWord getRoot() {
		return root;
	}

	public int getAltura() {
		return altura;
	}

}
